package com.example.voting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.Electeur;
import com.example.voting.entities.PeopleDataSet;

public interface ElecteurRepository extends CrudRepository<Electeur, Integer> {
	
	@Query(value="select * from electeur where cin =:cin", nativeQuery=true)
	Electeur getElementByCin(@Param("cin") String cin);
	
}
