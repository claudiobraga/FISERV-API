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


@RestController
@RequestMapping("/opportunities")
public class OpportunityController {
	
	
	@Autowired
	private OpportunityRepository opportunityRepository;
	
	
	@GetMapping
	public List<Opportunity> Listar() {
			
		return opportunityRepository.findAll();
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Opportunity> fetchById(@PathVariable Long id) {
		Optional<Opportunity> opportunity = opportunityRepository.findById(id);
		
		if(!opportunity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(opportunity.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Opportunity adding(@Valid @RequestBody Opportunity opportunity) {
		Optional<Opportunity>ExistOpportunity = opportunityRepository
				.findByDescriptionAndNameOfCompany(opportunity.getDescription(),
						opportunity.getNameOfCompany());
		
		if(ExistOpportunity.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"There is an Opportunity for this Post with the same description");
		}
		return opportunityRepository.save(opportunity);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeById(@PathVariable Long id) {
		opportunityRepository.deleteById(id);
	}

}
