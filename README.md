# 💻 DESAFIO FINAL - BootCamp DIO 2023:

Ao longo dessa jornada construiremos uma aplicação back-end de cadastro e delete de usuario com CRUD de tarefas. 
Trabalharemos com cadastro de NOME, do usuario no caso e TITULO, DESCRIÇAO, STATUS DA TAREFA E PRIORIDADE da nossa tarefa.
Iremos permitir no maximo 3 tarefas ativas por dia para cada usuario.

CONSIDERAÇÕES GERAIS:

Construiremos: USUARIO, TAREFA

- GET - obrigatório

- POST - obrigatório

- PUT - obrigatório

- DELETE - opcional

- Pode criar/editar apenas alterando um status entre PENDENTE, EM ANDAMENTO e COCLUIDA.
- Pode criar/editar apenas alterando um prioridade entre BAIXA, MEDIA e ALTA.

Todas as respostas devem vir com status 200 se deu certo e um status diferente de 200 se algo deu errado.
As mensagens de erro devem vir sempre em português

## 💻 Tecnologias utilizadas:

- JAVA 20
- MySQL Database
- Maven
- Spring Boot
- JPA
- Postman

**Inicie o sistema a partir da clase** 

   @SpringBootApplication
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
   

**▶️Utilização**

**USUARIO**

1 - Cadastrar USUARIO - **POST** ✅️

![cadastrandoUsuario](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/402159d5-939b-4c89-af99-357be4dc790e)

2 - Deletar USUARIO por id - **DELETE** ❌

![deletandoUsuario](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/b773b406-2622-4788-85db-c5c9ca8d7f9e)

**TAREFA**
1 - Cadastrar TAREFA - **POST** ✅️

![cadastrandoTarefa](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/7100543a-558d-4bc0-a769-9c77d118540e)

2 - Atualizar TAREFA - **PUT** ☑️

![alterandoTarefa](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/3b968621-88cc-4530-91f9-e6ff1af7217a)

3 - Buscar TAREFA por id - **GET** 🔍

![pegandoTarefa](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/e3a173d2-b666-4eb6-b7da-e8384af8957f)

4 - Deletar TAREFA por id - **DELETE** ❌

![deletandoTarefa](https://github.com/wilton007/api-desafio-boot-camp-dio-final/assets/95870794/433c536a-d3f5-43e2-9c1d-98cec5636646)

