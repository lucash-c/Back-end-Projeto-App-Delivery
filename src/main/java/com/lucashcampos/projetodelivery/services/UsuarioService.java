package com.lucashcampos.projetodelivery.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Usuario;
import com.lucashcampos.projetodelivery.domain.enums.Perfil;
import com.lucashcampos.projetodelivery.domain.enums.TipoCliente;
import com.lucashcampos.projetodelivery.dto.UsuarioNewDTO;
import com.lucashcampos.projetodelivery.dto.UsuarioUpdateDTO;
import com.lucashcampos.projetodelivery.repositories.EnderecoRepository;
import com.lucashcampos.projetodelivery.repositories.UsuarioRepository;
import com.lucashcampos.projetodelivery.security.UserSS;
import com.lucashcampos.projetodelivery.services.exceptions.AuthorizationException;
import com.lucashcampos.projetodelivery.services.exceptions.DataIntegrityException;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private S3Service s3service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Usuario find(Integer id) {

		UserSS user = UserService.authenticated();

		if (user == null || !user.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("Acessso negado!");
		}
		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Usuario.class.getName()));
	}

	public List<Usuario> findAll() {
		return repo.findAll();
	}

	public Usuario findByEmail(String email) {
		UserSS user = UserService.authenticated();
		if (user == null || !user.hasRole(Perfil.ADMIN)) {
			throw new AuthorizationException("Acesso negado!");
		}

		Usuario obj = repo.findByEmail(email);
		if (obj == null) {
			throw new ObjectNotFoundException("Usuario não encontrado!");
		}
		return obj;
	}

	@Transactional
	public Usuario insert(Usuario obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	@Transactional
	public Usuario update(Usuario obj) {
		Usuario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	@Transactional
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir este cliente, pois há pedidos relacionados");
		}

	}

	public void isNotVisible(Integer id) {
		Usuario user = find(id);
		user.setIsVisible(false);
		user.setIsActive(false);
		repo.save(user);
	}

	public void defineIsActive(Integer id, Boolean val) {
		Usuario user = find(id);
		user.setIsActive(val);
		repo.save(user);
	}

	public Page<Usuario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Usuario fromDTO(UsuarioNewDTO objDTO) {

		// Criação do endereço
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getBairro(),
				objDTO.getCidade(), objDTO.getEstado(), objDTO.getComplemento(), objDTO.getCep());

		Usuario cli = new Usuario(null, objDTO.getCpf_cnpj(), objDTO.getNome(), objDTO.getEmail(),
				TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()), end, objDTO.getPerfis());
		cli.setObs(objDTO.getObs());
		cli.setDiaVencimento(objDTO.getDiaVencimento());
		cli.setFormaPagamento(objDTO.getFormaPagamento());
		cli.setPlano(objDTO.getPlano());
		cli.setTelefone1(objDTO.getTelefone1());

		if (objDTO.getTelefone2() != null && !objDTO.getTelefone2().isEmpty()) {
			cli.setTelefone2(objDTO.getTelefone2());
		}
		cli.setIsActive(true);
		cli.setIsVisible(true);
		return cli;
	}

	public Usuario fromDTO(UsuarioUpdateDTO objDTO) {

		Boolean novoEndereco = false;
		Endereco end = null;

		Optional<Endereco> enderecoOptional = enderecoRepository
				.findById(objDTO.getEnderecoId() != null ? objDTO.getEnderecoId() : -1);
		if (enderecoOptional.isPresent()) {
			end = enderecoOptional.get();
			end.setLogradouro(objDTO.getLogradouro());
			end.setNumero(objDTO.getNumero());
			end.setBairro(objDTO.getBairro());
			end.setCidade(objDTO.getCidade());
			end.setEstado(objDTO.getEstado());
			end.setComplemento(objDTO.getComplemento());
			end.setCep(objDTO.getCep());

			// Aqui você pode salvar ou atualizar o endereço no banco de dados
			enderecoRepository.save(end);
		} else {
			// Criação do endereço
			novoEndereco = true;
			end = new Endereco(objDTO.getEnderecoId(), objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getBairro(),
					objDTO.getCidade(), objDTO.getEstado(), objDTO.getComplemento(), objDTO.getCep());

			// Salvar o novo endereço
			enderecoRepository.save(end);
		}

		Usuario cli = repo.findByEmail(objDTO.getEmail());

		cli.setNome(objDTO.getNome());
		cli.setTipo(TipoCliente.toEnum(objDTO.getTipo()));
		cli.setObs(objDTO.getObs());
		cli.setDiaVencimento(objDTO.getDiaVencimento());
		cli.setFormaPagamento(objDTO.getFormaPagamento());
		cli.setPlano(objDTO.getPlano());
		cli.setIsActive(objDTO.isActive());
		cli.setIsVisible(objDTO.isVisible());
		cli.setTelefone1(objDTO.getTelefone1());

		if (objDTO.getTelefone2() != null && !objDTO.getTelefone2().isEmpty()) {
			cli.setTelefone2(objDTO.getTelefone2());
		}

		if (objDTO.getSenha() != null && !objDTO.getSenha().isEmpty()) {
			cli.setSenha(pe.encode(objDTO.getSenha()));
		}

		cli.setPerfis(objDTO.getPerfis());

		if (!novoEndereco) {
			// Obtém o endereço a ser substituído com base no ID caso ele ja exista
			cli.getEnderecos().removeIf(e -> e.getId().equals(objDTO.getEnderecoId()));
		}

		cli.getEnderecos().add(end);

		return cli;
	}

	private void updateData(Usuario newObj, Usuario obj) {
		if (obj.getNome() != null) {
			newObj.setNome(obj.getNome());
		}
		if (obj.getEmail() != null) {
			newObj.setEmail(obj.getEmail());
		}
		if (obj.getCpf_cnpj() != null) {
			newObj.setCpf_cnpj(obj.getCpf_cnpj());
		}
		if (obj.getTipo() != null) {
			newObj.setTipo(obj.getTipo());
		}
		if (obj.getPlano() != null) {
			newObj.setPlano(obj.getPlano());
		}
		if (obj.getDiaVencimento() != null) {
			newObj.setDiaVencimento(obj.getDiaVencimento());
		}
		if (obj.getFormaPagamento() != null) {
			newObj.setFormaPagamento(obj.getFormaPagamento());
		}
		if (obj.getObs() != null) {
			newObj.setObs(obj.getObs());
		}
		if (obj.getIsActive() != null) {
			newObj.setIsActive(obj.getIsActive());
		}
		if (obj.getIsVisible() != null) {
			newObj.setIsVisible(obj.getIsVisible());
		}
		if (obj.getLojas() != null) {
			newObj.setLojas(obj.getLojas());
		}
		if (obj.getEnderecos() != null) {
			newObj.setEnderecos(obj.getEnderecos());
		}
		if (obj.getTelefone1() != null) {
			newObj.setTelefone1(obj.getTelefone1());
		}
		if (obj.getTelefone2() != null) {
			newObj.setTelefone2(obj.getTelefone1());
		}
		if (obj.getPerfis() != null && !obj.getPerfis().isEmpty()) {
			obj.getPerfis().forEach(newObj::addPerfil); // Adiciona cada perfil individualmente
		}
		if (obj.getSenha() != null) {
			newObj.setSenha(obj.getSenha());
		}
	}

	public URI uploadProfilePicture(MultipartFile multipartFile) {
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado!");
		}

		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);

		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		String fileName = prefix + user.getId() + ".jpg";

		return s3service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
	}
}
