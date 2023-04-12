package br.edu.unoesc.produtos.controllers;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unoesc.produtos.model.Produto;
import br.edu.unoesc.produtos.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoRestController {
	@Autowired 
	private ProdutoService servico;
	
	@GetMapping
	public ResponseEntity<Iterable<Produto>> listar() {
		List<Produto> produtos = servico.listar();
		
		if (produtos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		System.out.println(produtos);
		return ResponseEntity.ok(produtos);
	}

    @PostMapping()
    public ResponseEntity<Void> incluir(@RequestBody Produto produto) {
    	produto = servico.incluir(produto);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        									 .path("/{id}")
        									 .buildAndExpand(produto.getId())
        									 .toUri();
        
        System.out.println(uri.toString());
        
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable("id") Long id, @RequestBody Produto produto) {
    	if (servico.porId(id).isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    	
        return ResponseEntity.ok(servico.alterar(id, produto));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
    	try {
    		servico.excluir(id);   		
    	} catch (ObjectNotFoundException e) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	return ResponseEntity.noContent().build();
    }
}