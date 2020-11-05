package com.mrraxa01.projetoSBI.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	//pega o nome da catedoria passada na URL e codifica sem espaços ou caracteres especiais
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			return "";
			
		}
	}
	
	
	//pega os dados da URL ...categorias=1,2,4 e salva os ids de categoria em uma lista de integer
	public static List<Integer> decodeIntList(String s){
		String[] vet = s.split(",");
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<vet.length; i++) {
			list.add(Integer.parseInt(vet[i]));
		}
		return list;
		//todo o método acima pode ser substituida por uma lambda
		//return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
	
}
