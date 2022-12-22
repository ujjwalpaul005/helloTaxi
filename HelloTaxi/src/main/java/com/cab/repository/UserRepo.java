package com.cab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	List<User> findByMoblieNumber(String moblieNumber);
}
