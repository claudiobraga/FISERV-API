package com.claudiobraga.fiservnenaghopportunity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.claudiobraga.fiservnenaghopportunity.model.Opportunity;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long>{
	
	Optional<Opportunity> findByDescriptionAndNameOfCompany(String description, String nameOfCompany);

}
