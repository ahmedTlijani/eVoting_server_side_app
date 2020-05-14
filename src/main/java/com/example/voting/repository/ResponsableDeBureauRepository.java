package com.example.voting.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.voting.entities.ResponsableDeBureau;
import com.example.voting.entities.Admin;
import com.example.voting.entities.Parti;

public interface ResponsableDeBureauRepository extends CrudRepository<ResponsableDeBureau, Integer> {

	@Query(value="select * from responsable_de_bureau where username =:username and password =:password ", nativeQuery=true)
	ResponsableDeBureau getElementByUsernameAndPassowrd(@Param("username") String username,@Param("password") String password);
	
}
