package com.mrraxa01.projetoSBI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.domain.Categoria;
import com.mrraxa01.projetoSBI.repositories.CategoriaRepository;
import com.mrraxa01.projetoSBI.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	public CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " + Categoria.class.getName()));
	}
}
