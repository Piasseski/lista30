package br.edu.unoesc.produtos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.edu.unoesc.produtos.service.ProdutoService;

@SpringBootApplication
public class ProdutosApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProdutosApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}

	@Bean
	CommandLineRunner commandLineRunner(ProdutoService servico) {
		return args -> {
			servico.popularTabelaInicial();
		};
	}
}
