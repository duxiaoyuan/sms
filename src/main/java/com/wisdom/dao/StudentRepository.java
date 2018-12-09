package com.wisdom.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wisdom.entity.Students;


public interface StudentRepository extends JpaRepository<Students, Integer>,JpaSpecificationExecutor<Students>{
	
		/**
		 * 锁定用户
		 * @param id
		 * @return
		 */
	    @Modifying
	    @Transactional
	    @Query(value = "update students set is_lock=1 where id=?1", nativeQuery = true)
	    public int suo(Integer id);
	    
	    
	    /**
	     * 解锁用户
	     * @param id
	     * @return
	     */
	    @Modifying
	    @Transactional
	    @Query(value = "update students set is_lock=0 where id=?1", nativeQuery = true)
	    public int jie(Integer id);
	
	    /**
	     * 查询指定班级下的所有学生
	     * @param clssId
	     * @return
	     */
	    List<Students> findByClassId(Integer classId);
	    
	    /**
	     * 根据学号查询用户
	     * @param studentNo
	     * @return
	     */
	    Students findByStudentNo(String studentNo);

}
