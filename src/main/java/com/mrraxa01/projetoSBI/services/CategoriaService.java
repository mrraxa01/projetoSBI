package com.mrraxa01.projetoSBI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.domain.Categoria;
import com.mrraxa01.projetoSBI.repositories.CategoriaRepository;
import com.mrraxa01.projetoSBI.services.exceptions.DataIntegrityException;
import com.mrraxa01.projetoSBI.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);//garante que é um novo obj no BD
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());//método implementado acima, busca o id no BD e caso não exista já lança excessão.
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	}catch (DataIntegrityViolationException e){
		throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		
	}
		}
}
