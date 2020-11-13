package com.mrraxa01.projetoSBI.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mrraxa01.projetoSBI.domain.enums.Perfil;

public class UserSS implements UserDetails{
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities; 
	
	//UserSS => classe usuário que atende as especificações SpringSecurity - Obrigatório
	
	public UserSS() {}
	
	
	
	public UserSS(Integer id, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}



	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
	
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// se quiser implementar uma lógica com usuário expirando
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// se quiser implementar uma lógica com usuário expirando
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}


}
