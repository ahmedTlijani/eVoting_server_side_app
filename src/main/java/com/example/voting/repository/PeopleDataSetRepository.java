package com.example.voting.repository;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.websocket.Session;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.entities.Electeur;
import com.example.voting.entities.BureauElection;

public interface PeopleDataSetRepository extends CrudRepository<PeopleDataSet, Integer> {

	@Query(value="select * from people_data_set where cin=:cin", nativeQuery=true)
	PeopleDataSet getElementByCin(@Param("cin") String cin);
		
}
