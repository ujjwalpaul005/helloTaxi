package com.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cab.model.CurrentUserSession;

@Repository
public interface SessionRepo extends JpaRepository<CurrentUserSession, String>{

	public CurrentUserSession findByUuid(String uuid);
}
