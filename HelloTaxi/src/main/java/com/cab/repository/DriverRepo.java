package com.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.Driver;

@Repository
public interface DriverRepo extends JpaRepository<Driver, Integer>{

}
