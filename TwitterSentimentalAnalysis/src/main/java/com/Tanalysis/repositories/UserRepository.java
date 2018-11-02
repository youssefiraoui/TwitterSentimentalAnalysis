package com.Tanalysis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Tanalysis.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
