package com.wisdom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.wisdom.entity.Students;
import com.wisdom.entity.condittion.StudentsCondittion;

/**
 * 学生
 * @author admin
 *
 */
public interface StudentsService {
	/**
	 * 添加学生/编辑学生
	 */
	Students addstu(Students stu);
	/**
	 * 条件查询学生
	 */
	Page<Students> selectall(StudentsCondittion con, Integer page, Integer rows);
	/**
	 * 删除学生
	 */
	String deletestu(Integer id);
	/**
	 * 根据id查询学生
	 */
	Students selectById(Integer id);
	/**
	 * 解锁用户
	 */
	int jie(Integer id);
	/**
	 * 锁定用户
	 */
	int suo(Integer id);
	
	 /**
     * 查询指定班级下的所有学生
     * @param clssId
     * @return
     */
    List<Students> findByClassId(Integer clssId);
    
    /**
     * 根据学号查询用户
     * @param studentNo
     * @return
     */
    Students findByStudentNo(String studentNo);
}
