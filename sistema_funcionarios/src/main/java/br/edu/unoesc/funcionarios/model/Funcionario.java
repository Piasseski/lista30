package br.edu.unoesc.funcionarios.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(nullable = false, unique = true, length = 100)
	String nome;
	
	@Column(nullable = false)
	String endereco;
	
	@Column(nullable = false)
	Integer numDep;
	
	@Column(length = 100)
	BigDecimal salario;
	
	@Column(length = 100)
	LocalDate nascimento;
}