package com.lucashcampos.projetodelivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucashcampos.projetodelivery.domain.Restaurante;
import com.lucashcampos.projetodelivery.repositories.RestauranteRepository;
import com.lucashcampos.projetodelivery.services.exceptions.ObjectNotFoundException;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository repo;

	public Restaurante find(Integer id) {
		Optional<Restaurante> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + id + ", Tipo: " + Restaurante.class.getName()));
	}

	public List<Restaurante> findAll() {
		return repo.findAll();
	}

	public Restaurante insert(Restaurante obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Restaurante update(Restaurante obj) {
		Restaurante newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		repo.deleteById(id);
	}

	public Page<Restaurante> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	private void updateData(Restaurante newObj, Restaurante obj) {

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
	}

}
