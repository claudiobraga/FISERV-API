//Classe Modelo que Representa a Tabela na BD - Entidade
//Nas classes modelos os get e set e o hash code imperam

package com.claudiobraga.fiservnenaghopportunity.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//Fazer a vinculação da classe com a tabela de Dados
@Entity //entidade JPA = representa uma tabela na BD
public class Opportunity {
	
	
	@Id//Anotação para Identificar o id chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY)//usar a geração de identidade-autoincre
	private Long id;
	
	//Anotações para validações-Bean Validation
	@NotNull
	@Size(max = 50)
	@Column(name = "name_Of_The_Company")
	private String nameOfCompany;
	
	@NotNull
	@Size(max = 200)
	private String description;
	
	@Min(0)
	private BigDecimal value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameOfCompany() {
		return nameOfCompany;
	}
	public void setNameOfCompany(String nameOfCompany) {
		this.nameOfCompany = nameOfCompany;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Opportunity other = (Opportunity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
