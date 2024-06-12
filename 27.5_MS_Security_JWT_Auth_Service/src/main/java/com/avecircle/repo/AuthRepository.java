package com.avecircle.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avecircle.entity.UserCredential;

@Repository
public interface AuthRepository extends JpaRepository<UserCredential, Integer>{
	
	Optional<UserCredential> findByName(String name);

}
