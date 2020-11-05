package com.mrraxa01.projetoSBI.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.domain.Categoria;
import com.mrraxa01.projetoSBI.domain.PagamentoComBoleto;
import com.mrraxa01.projetoSBI.domain.Pedido;
import com.mrraxa01.projetoSBI.domain.Produto;
import com.mrraxa01.projetoSBI.domain.enums.EstadoPagamento;
import com.mrraxa01.projetoSBI.repositories.CategoriaRepository;
import com.mrraxa01.projetoSBI.repositories.ProdutoRepository;
import com.mrraxa01.projetoSBI.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	public ProdutoRepository repo;
	@Autowired
	public CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
		
	}

		
	}

