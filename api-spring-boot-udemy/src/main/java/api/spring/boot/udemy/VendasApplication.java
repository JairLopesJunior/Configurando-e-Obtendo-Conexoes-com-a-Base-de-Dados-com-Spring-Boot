package api.spring.boot.udemy;

import api.spring.boot.udemy.domain.entity.Cliente;
import api.spring.boot.udemy.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
          Cliente cliente = new Cliente();
          cliente.setNome("Jair");
          clientes.salvar(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Joao");
            clientes.salvar(cliente2);

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado");
                clientes.atualizar(c);
            });

            System.out.println("Buscando por nome:");
            clientes.buscarPorNome("oa").forEach(System.out::println);

            System.out.println("Deletando:");
            clientes.obterTodos().forEach(c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.obterTodos();
            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado");
            }else{
                todosClientes.forEach(System.out::println);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}
