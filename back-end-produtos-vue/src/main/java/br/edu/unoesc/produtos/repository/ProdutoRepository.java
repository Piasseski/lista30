package br.edu.unoesc.produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}