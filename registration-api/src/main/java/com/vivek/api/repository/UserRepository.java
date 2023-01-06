package com.vivek.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vivek.api.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("from User where userName=:name")
	User findByUserName(@Param("name") String userName);
	
	@Query("from User where userName=:name AND password=:pass")
	User findByUserNameOrPassword(@Param("name") String name,@Param("pass") String pass);
}
