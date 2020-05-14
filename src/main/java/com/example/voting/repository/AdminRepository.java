package com.example.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.Parti;
import com.example.voting.entities.PeopleDataSet;
import com.example.voting.entities.Admin;
import com.example.voting.entities.Electeur;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	
	@Query(value="select * from admin where username =:username and password =:password ", nativeQuery=true)
	Admin getElementByUsernameAndPassowrd(@Param("username") String username,@Param("password") String password);
	
	
}
