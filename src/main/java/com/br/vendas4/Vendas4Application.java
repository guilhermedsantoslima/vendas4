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
			clientes.salvar(new Cliente("Guilherme"));
			clientes.salvar(new Cliente("Canadense"));

			List<Cliente> todosClientes = clientes.obterTodos();
			todosClientes.forEach(System.out::println);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(Vendas4Application.class, args);
	}

}
