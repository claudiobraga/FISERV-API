//Classe Controladora
package com.claudiobraga.fiservnenaghopportunity.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.claudiobraga.fiservnenaghopportunity.model.Opportunity;
import com.claudiobraga.fiservnenaghopportunity.repository.OpportunityRepository;

//Indicar ao Spring que esta classe é um controlador REST
@RestController
//Mapear a URI - Indicar qual é a URI- requisições
@RequestMapping("/opportunities") // GET http://localhost:8080/opportunities
public class OpportunityController {
	
	//Injecção de depencias que instancia a variável
	//Váriavel de instância que guarda os dados vindos da BD
	@Autowired
	private OpportunityRepository opportunityRepository;
	
	//Método resposável pela requisição GET
	@GetMapping
	public List<Opportunity> Listar() {
			
		return opportunityRepository.findAll();
	}
	
	//Ober oportunidades pelo o ID retornando o código certo caso não existe o id
	@GetMapping("/{id}")
	public ResponseEntity<Opportunity> fetchById(@PathVariable Long id) {
		Optional<Opportunity> opportunity = opportunityRepository.findById(id);
		
		//Se opt não existe ou não presente dá o notfound 404
		if(!opportunity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		//Existindo devolve o código 200 ok
		return ResponseEntity.ok(opportunity.get());
	}
	
	//Método para Adicionar uma Oportunidade retornado o código 201 crested
	//A anotação requestbody transforma o json em objecto java e coloca no Opportunity
	//A anotação valid é para validar os campos da tabela na BD.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Opportunity adding(@Valid @RequestBody Opportunity opportunity) {
		//Fazendo uso do método implementado na interface
		Optional<Opportunity>ExistOpportunity = opportunityRepository
				.findByDescriptionAndNameOfCompany(opportunity.getDescription(),
						opportunity.getNameOfCompany());
		
		if(ExistOpportunity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is an Opportunity for this Post with the same description");
		}
		return opportunityRepository.save(opportunity);
	}
	
	
	//Método para Elinar uma Opportunity devolvendo 204
	@DeleteMapping("/{id}")//place holder
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeById(@PathVariable Long id) {
		opportunityRepository.deleteById(id);
	}

}
