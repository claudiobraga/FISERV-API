//Interface resposnsável por toda operação de CRUD na BD
//Porque herda os métodos do JpaRepository
package com.claudiobraga.fiservnenaghopportunity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.claudiobraga.fiservnenaghopportunity.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
	
	//Implementar um método diferente que não foi ainda implementado pelo o JPA
	//Que procura pela descrição e nome da companhia. É preciso passar os paramêtros
	Optional<Opportunity> findByDescriptionAndNameOfCompany(String description, String nameOfCompany);

}
