# Instruções

### Para rodar o projeto

- Criar o banco de dados

```sql
CREATE DATABASE brabank;
```

- Importar projeto.

  > Sua IDE já vai fazer o download das dependências, caso não, MAVEN INSTALL e MAVEN UPDATE no projeto.

- Rodar classe BrabankapiApplication

### Testes de integração

- Criar o banco de dados de testes

```sql
CREATE DATABASE brabank_test;
```

- Criar o arquivo application.properties em `src/teste/resources`:

```java
# DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
spring.datasource.url=jdbc:mysql://localhost:3306/brabank_test?useTimezone=true&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=xxxx
spring.datasource.tomcat.test-on-borrow=true
spring.datasource.tomcat.validation-query=SELECT 1
spring.datasource.sql-script-encoding=UTF-8

# JPA (JpaBaseConfiguration, HibernateJpaAutoConfiguration)
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

- Alterar a classe BrabankapiApplicationTests:

```java
package br.com.senai.brabankapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrabankapiApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class BrabankapiApplicationTests {

	@Test
	void contextLoads() {
	}

}
```

- Criar a classe UsuarioResourceTest

```java
package br.com.senai.brabankapi;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.senai.brabankapi.domain.Usuario;
import br.com.senai.brabankapi.resources.UsuariosResource;

public class UsuarioResourceTest extends BrabankapiApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private UsuariosResource usuariosResource;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(usuariosResource).build();
	}

	@Test
	public void criarUsuarioComDadosCorretos_RetornarStatusCode201() throws Exception {
		Usuario usuario = new Usuario(null,"João","12345678910","teste@teste.com","M","123456");
		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(usuario);
		System.out.println(json);
		this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
							.contentType(MediaType.APPLICATION_JSON)
							.content(json)
							)
						.andExpect(MockMvcResultMatchers.status().isCreated())
						.andExpect(MockMvcResultMatchers.header().string("location", Matchers.containsString("http://localhost/usuarios")));
	}

	@Test
	public void criarUsuarioComDadosErrados_RetornarStatusCode400() throws Exception {

		this.mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
							.contentType(MediaType.APPLICATION_JSON)
							.content("")
							)
						.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
```

- Rodar o teste

[Siga as instruções da aula](https://youtu.be/Y4_LmPhx1Jc)
