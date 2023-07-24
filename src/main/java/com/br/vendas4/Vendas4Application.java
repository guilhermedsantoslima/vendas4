package com.br.vendas4;

import com.br.vendas4.domain.entity.Cliente;
import com.br.vendas4.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Vendas4Application {
    @Bean
	public CommandLineRunner init(@Autowired Clientes clientes){
		return args -> {
			System.out.println("Salvando clientes");
			clientes.salvar(new Cliente("Guilherme"));
			clientes.salvar(new Cliente("Outro Cliente"));

			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
			System.out.println("Atualizando Clientes");
			todosClientes.forEach(c -> {
				c.setNome(c.getNome() + " atualizado.");
				clientes.atualizar(c);
			});

			todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);

			System.out.println("Buscando Clientes");
			clientes.buscarPorNome("Gui").forEach(System.out::println);

			System.out.println("Deletando Clientes");
			clientes.obterTodos().forEach(c->{
				clientes.deletar(c);
			});

			todosClientes = clientes.obterTodos();

			if(todosClientes.isEmpty()){
				System.out.println("Nenhum cliente encontrado.");
			}
			todosClientes.forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(Vendas4Application.class, args);
	}

}
