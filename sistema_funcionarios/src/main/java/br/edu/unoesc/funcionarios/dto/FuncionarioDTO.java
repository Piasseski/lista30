package br.edu.unoesc.funcionarios.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.unoesc.funcionarios.model.Funcionario;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
public class FuncionarioDTO implements Serializable {
	Long id;
	
	@NotBlank(message = "É obrigatório informar o nome")
	@Size(min=1, max = 100, message = "O nome deve ter entre {min} e {max} caracteres")
	String nome;
	
	@NotNull(message = "É obrigatório informar o endereço do funcionário")
	String endereco;
	
	@NotNull(message = "É obrigatório informar o número do departamento")
	Integer numDep;
	
	@NotNull(message = "É obrigatório informar o salário do funcionário")
	@Min(value = 1, message = "O salário  dever ser de pelo menos 1 real")
	@Max(value = 100000, message = "O salário do funcionário deve ter entre {min} e {max} caracteres")
	BigDecimal salario;
	
	@NotNull(message = "É obrigatório informar o nascimento do funcionário")
	LocalDate nascimento;;
	
	public FuncionarioDTO(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.nome = funcionario.getNome();
		this.endereco = funcionario.getEndereco();
		this.numDep = funcionario.getNumDep();
		this.salario = funcionario.getSalario();
		this.nascimento = funcionario.getNascimento();
	}
}