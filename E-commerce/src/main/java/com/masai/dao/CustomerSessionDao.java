package com.masai.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.CurrentUserSession;

public interface CustomerSessionDao  extends JpaRepository<CurrentUserSession, Integer>{
	

	public CurrentUserSession findByUuid(String uuid);

}
