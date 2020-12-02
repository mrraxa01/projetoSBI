package com.mrraxa01.projetoSBI.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.domain.Cidade;
import com.mrraxa01.projetoSBI.repositories.CidadeRepository;

@Service
public class CidadeService {


	@Autowired
	private CidadeRepository repo;

	public List<Cidade> findByEstado(Integer estadoId) {
		return repo.findCidades(estadoId);
	}
}
