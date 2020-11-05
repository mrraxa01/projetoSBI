package com.mrraxa01.projetoSBI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrraxa01.projetoSBI.domain.PagamentoComBoleto;
import com.mrraxa01.projetoSBI.domain.PagamentoComCartao;

//Essa classe é necessária para implementar a classe abstrata pagamento que só pode ser instanciada pelas subclasses pagamento com cartão ou boleto

@Configuration
public class JacksonConfig {

//código padrão do jackson, só muda as sub classes linhas 19 e 20
	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartao.class);
				objectMapper.registerSubtypes(PagamentoComBoleto.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
