package br.com.senai.brabankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 
 	1. Criamos um novo projeto pelo start.spring.io
	2. Importamos o projeto no eclipse 
	3. Conhecemos o arquivo pom.xml (configuração de dependências e build)
	4. Configuramos no arquivo aplication.properties a conexão com o banco de dados
*/

/*
 	Classe responsável por iniciar a aplicação
*/
@SpringBootApplication
public class BrabankapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrabankapiApplication.class, args);
	}

}
