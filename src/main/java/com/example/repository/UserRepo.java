package com.example.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Serializable> {

	@Query(value = "SELECT email FROM UserEntity")
	public List<String> findAllEmails();

	@Query(value = "SELECT email FROM UserEntity  where uid=:id")
	public String findEmailById( Integer id);
	
	@Query(value="SELECT uid FROM UserEntity")
	public Integer[] getAllId(Integer id);
	


}
