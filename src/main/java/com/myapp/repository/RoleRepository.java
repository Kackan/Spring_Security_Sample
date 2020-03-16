package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.myapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query("from Role where name=:name")
	public Role findByRoleName(@Param("name")String name);
}
