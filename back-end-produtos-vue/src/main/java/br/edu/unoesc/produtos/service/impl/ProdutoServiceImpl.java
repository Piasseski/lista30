package br.edu.unoesc.produtos.service.impl;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unoesc.produtos.model.Produto;
import br.edu.unoesc.produtos.repository.ProdutoRepository;
import br.edu.unoesc.produtos.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	@Autowired
	private ProdutoRepository repositorio;
	
	@Override
	public void popularTabelaInicial() {
		repositorio.saveAll(List.of(
				new Produto(1L, "Mesa", 10, new BigDecimal("3.33"), LocalDate.of(2000, 10, 5)),
				new Produto(2L, "Cadeira", 20, new BigDecimal("33.33"), LocalDate.of(2001, 12, 28)),
				new Produto(3L, "Notebook", 0, new BigDecimal("3333.33"), LocalDate.of(1975, 6, 6))
			)
		);
	}

	@Override
	public Produto incluir(Produto produto) {
		produto.setId(null);
		
		return repositorio.save(produto);
	}

	@Override
	public Produto alterar(Long id, Produto produto) {
		var l = repositorio.findById(id)
						   .orElseThrow(
								   () -> new ObjectNotFoundException("Produto não encontrado! Id: "
										   + id + ", Tipo: " + Produto.class.getName(), null)
						   );
		
		// Atualiza os dados do banco com os dados vindos da requisição
		l.setDescricao(produto.getDescricao());
		l.setQuantidade(produto.getQuantidade());
		l.setValor(produto.getValor());
		l.setDataCompra(produto.getDataCompra());
		
		return repositorio.save(l);
	}

	@Override
	public void excluir(Long id) {
		if (repositorio.existsById(id)) {
			repositorio.deleteById(id);
		} else {
			throw new ObjectNotFoundException("Produto não encontrado! Id: "
					   						  + id + ", Tipo: " + Produto.class.getName(), null);
		}
	}

	@Override
	public List<Produto> listar() {
		List<Produto> produtos = new ArrayList<Produto>();
		
		// Recupera todos os registros da tabela
		Iterable<Produto> itens = repositorio.findAll();
		
		// Cria uma cópia dos dados na lista 'funcionarios'
		itens.forEach(produtos::add);
		
		return produtos;
	}

	@Override
	public Produto buscar(Long id) {
		Optional<Produto> obj = repositorio.findById(id);
				
		return obj.orElseThrow(
						() -> new ObjectNotFoundException("Produto não encontrado! Id: "
					  		+ id + ", Tipo: " + Produto.class.getName(), null)
					);
	}

	@Override
	public Produto buscarPorId(Long id) {
		return repositorio.findById(id).orElse(new Produto());					      
	}

	@Override
	public Optional<Produto> porId(Long id) {
		return repositorio.findById(id);
	}
}