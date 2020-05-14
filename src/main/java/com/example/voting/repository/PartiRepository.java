package com.example.voting.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.voting.entities.Parti;
import com.example.voting.entities.Electeur;

public interface PartiRepository extends CrudRepository<Parti, Integer> {

}
