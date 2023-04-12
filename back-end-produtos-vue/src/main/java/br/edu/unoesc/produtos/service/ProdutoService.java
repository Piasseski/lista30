package br.edu.unoesc.produtos.service;


import java.util.List;
import java.util.Optional;

import br.edu.unoesc.produtos.model.Produto;

public interface ProdutoService {
	void popularTabelaInicial();

	Produto incluir(Produto funcionario);
	Produto alterar(Long id, Produto funcionario);
	void excluir(Long id);

	List<Produto> listar();
	
	Produto buscar(Long id);	  // Lança uma exceção caso o não exista o funcionario com id procurado
	Produto buscarPorId(Long id); // Retorna um novo objeto Livro caso id não seja encontrado
	Optional<Produto> porId(Long id);
}