package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Endereco;
import com.lucashcampos.projetodelivery.domain.Frete;
import com.lucashcampos.projetodelivery.domain.Loja;
import com.lucashcampos.projetodelivery.dto.NewLojaDTO;
import com.lucashcampos.projetodelivery.repositories.LojaRepository;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repo;	

	public Loja find(Integer id) {
		Optional<Loja> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Loja.class.getName()));
	}
	
	public List<Loja> findByUserId(Integer userId) {
        return repo.findByUsuarioId(userId);
    }

	public List<Loja> findAll() {
		return repo.findAll();
	}

	public Loja insert(Loja obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Loja update(Loja obj) {
		Loja newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public Page<Loja> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(Loja newObj, Loja obj) {

		if (obj.getRazaoSocial() != null)
			newObj.setRazaoSocial(obj.getRazaoSocial());

		if (obj.getCnpjCpf() != null)
			newObj.setCnpjCpf(obj.getCnpjCpf());

		if (obj.getNomeResponsavel() != null)
			newObj.setNomeResponsavel(obj.getNomeResponsavel());

		if (obj.getNomeFantasia() != null)
			newObj.setNomeFantasia(obj.getNomeFantasia());

		if (obj.getEspecialidades() != null)
			newObj.setEspecialidades(obj.getEspecialidades());

		if (obj.getEndereco() != null)
			newObj.setEndereco(obj.getEndereco());

		if (obj.getTelefone() != null)
			newObj.setTelefone(obj.getTelefone());

		if (obj.getWhatsapp() != null)
			newObj.setWhatsapp(obj.getWhatsapp());

		if (obj.getMediaSatisfacao() != null)
			newObj.setMediaSatisfacao(obj.getMediaSatisfacao());

		if (obj.getLogo() != null)
			newObj.setLogo(obj.getLogo());

		if (obj.getSite() != null)
			newObj.setSite(obj.getSite());

		if (obj.getInstagram() != null)
			newObj.setInstagram(obj.getInstagram());

		if (obj.getFacebook() != null)
			newObj.setFacebook(obj.getFacebook());
		
		if (obj.getFretes() != null)
			newObj.setFretes(obj.getFretes());
		
	}

	public Loja fromDTO(NewLojaDTO objDTO) {
	    Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getCep());
	    
	    // Cria uma lista com os IDs das especialidades da loja
	    List<Integer> idsEspecialidades = objDTO.getEspecialidades().stream()
	            .map(especialidade -> especialidade.getCod())
	            .collect(Collectors.toList());

	    // Cria uma lista de fretes
	    List<Frete> fretes = objDTO.getFretes().stream()
	            .map(freteDTO -> new Frete(freteDTO.getKm(),freteDTO.getValor(),freteDTO.getPrazo()))
	            .collect(Collectors.toList());

	    return new Loja(
	            objDTO.getRazaoSocial(),
	            objDTO.getCnpjCpf(),
	            objDTO.getNomeResponsavel(),
	            objDTO.getNomeFantasia(),
	            idsEspecialidades,
	            endereco,
	            objDTO.getTelefone(),
	            objDTO.getWhatsapp(),
	            objDTO.getMediaSatisfacao(),
	            objDTO.getLogo(),
	            objDTO.getSite(),
	            objDTO.getInstagram(),
	            objDTO.getFacebook(),
	            objDTO.getPrazoRetirada(),
	            objDTO.getLimiteDistanciaKm(),
	            fretes,
	            objDTO.getUsuarios()
	    );
	}

}
