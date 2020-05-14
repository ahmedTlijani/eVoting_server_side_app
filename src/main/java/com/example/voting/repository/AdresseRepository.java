package com.example.voting.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.entities.Admin;
import com.example.voting.entities.Adresse;
import com.example.voting.entities.Electeur;

public interface AdresseRepository extends CrudRepository<Adresse, Integer> {

	
	@Query(value="select * from adresse where state =:state", nativeQuery=true)
	List<Adresse> getProvinceByState(@Param("state") String state);

	@Query(value="select * from adresse where state =:state and province =:province", nativeQuery=true)
	Adresse getAdresseId(@Param("state") String state, @Param("province") String province);
	
	@Query(value="select * from adresse where adresse_id =:adresse_id", nativeQuery=true)
	Adresse getCodePostal(@Param("adresse_id") int adresse_id);
	
	
}
