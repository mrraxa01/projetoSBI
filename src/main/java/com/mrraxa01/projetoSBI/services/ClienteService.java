package com.mrraxa01.projetoSBI.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mrraxa01.projetoSBI.DTO.ClienteDTO;
import com.mrraxa01.projetoSBI.DTO.ClienteNewDTO;
import com.mrraxa01.projetoSBI.domain.Cidade;
import com.mrraxa01.projetoSBI.domain.Cliente;
import com.mrraxa01.projetoSBI.domain.Endereco;
import com.mrraxa01.projetoSBI.domain.enums.Perfil;
import com.mrraxa01.projetoSBI.domain.enums.TipoCliente;
import com.mrraxa01.projetoSBI.repositories.ClienteRepository;
import com.mrraxa01.projetoSBI.repositories.EnderecoRepository;
import com.mrraxa01.projetoSBI.security.UserSS;
import com.mrraxa01.projetoSBI.services.exceptions.AuthorizationException;
import com.mrraxa01.projetoSBI.services.exceptions.DataIntegrityException;
import com.mrraxa01.projetoSBI.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	public ClienteRepository repo;
	
	@Autowired
	public EnderecoRepository enderecoRepository;

	@Autowired
	public BCryptPasswordEncoder pe;
	
	public Cliente find(Integer id) {
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
				throw new AuthorizationException("Acesso Negado");
		}
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " +id + ", Tipo: " + Cliente.class.getName()));
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repo.deleteById(id);
	}catch (DataIntegrityViolationException e){
		throw new DataIntegrityException("Não é possível excluir um cliente com pedidos ativos!");
		
	}
		}
	
	public List<Cliente> findAll() {
		List<Cliente> list = repo.findAll();
		return list;
}
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()), pe.encode(objDTO.getSenha()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getCep(), objDTO.getBairro(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}

	private void updateData(Cliente newObj, Cliente Obj) {
		newObj.setNome(Obj.getNome());
		newObj.setEmail(Obj.getEmail());
	}
}
