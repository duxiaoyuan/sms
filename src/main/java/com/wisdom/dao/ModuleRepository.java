package com.wisdom.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisdom.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer>{

	List<Module> findByParentId(int i);

}
