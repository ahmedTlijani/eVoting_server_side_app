package com.example.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.BureauElection;

public interface BureauElectionRepository extends CrudRepository<BureauElection, Integer> {

	
	@Query(value="select * from bureau where adresse =:adresse_id", nativeQuery=true)
	BureauElection getOneVotingOfficeByAdresseId(@Param("adresse_id") int adresse_id);
	
	
}
