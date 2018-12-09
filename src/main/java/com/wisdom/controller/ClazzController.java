package com.wisdom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdom.entity.Classes;
import com.wisdom.entity.Students;
import com.wisdom.service.ClazzService;
import com.wisdom.service.StudentsService;
import com.wisdom.tools.Result;

import javafx.scene.control.Pagination;

@RestController
@RequestMapping("clazz")
public class ClazzController {

	@Autowired
	private ClazzService service;

	@Autowired
	private StudentsService stuService;

	/**
	 * 根据名字分页查询班级 http://localhost:8080/Wisdom/clazz/selectAllClasses
	 * 
	 * @param name
	 *            班级名称
	 * @param pageable
	 * @param page
	 *            页数
	 * @param rows
	 *            条数
	 * @return map{"total":总条数,"rows":数据}
	 */
	@RequestMapping("/selectAllClasses")
	public Map<String, Object> selectAllClasses(String name, Pageable pageable, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		Sort sort = new Sort(Direction.ASC, "id");
		Pageable pager = new PageRequest(page - 1, rows, sort);
		Page<Classes> claPage = service.findByName("%" + name + "%", pager);
		map.put("total", claPage.getTotalElements());
		map.put("rows", claPage.getContent());
		return map;
	}

	@RequestMapping("/selectAll")
	public List<Classes> selectAll() {
		// Map<String, Object> map = new HashMap<String, Object>();
		// Sort sort = new Sort(Direction.ASC , "id");
		// Pageable pager = new PageRequest(page-1, rows, sort);
		// Page<Classes> claPage = service.findByName("%"+name+"%",pager);
		// map.put("total", claPage.getTotalElements());
		// map.put("rows", claPage.getContent());

		List<Classes> list = service.getAll();

		System.out.println("sdfsdf" + list);
		return list;
	}

	/**
	 * 添加/修改班级信息 http://localhost:8080/Wisdom/clazz/addClass?
	 * 
	 * @param clazz
	 * @return
	 */
	@RequestMapping("addClass")
	public Object addClass(Classes clazz) {
		System.out.println("clazz=>" + clazz);

		Classes classByName = service.findByName(clazz.getName());
		Classes addClazz = new Classes();
		if (classByName != null && !"".equals(classByName)) {

			if (clazz.getId() == null) {

				return new Result("班级已存在", 0);

			} else if (clazz.getId() == classByName.getId()) {

				addClazz = service.saveClazz(clazz);
			}

		} else {

			addClazz = service.saveClazz(clazz);
		}

		if (addClazz != null && !"".equals(addClazz)) {

			return new Result("操作成功", 1);

		} else {

			return new Result("操作失败", 0);

		}

	}

	/**
	 * 根据班级id删除班级信息 http://localhost:8080/Wisdom/clazz/delClazz
	 * 
	 * @param clazzId
	 * @return
	 */
	@RequestMapping("/delClazz")
	public Object deleteClazz(Integer id) {

		List<Students> stuList = stuService.findByClassId(id);
		System.out.println("当前班级下的学生"+stuList);
		if (stuList != null && stuList.size() > 0) {

			return new Result("当前班级下还有学生，你不能删除", 0);

		} else {

			String clazz = service.deleteClazz(id);

			if (clazz != null && "删除成功".equals(clazz)) {

				return new Result("删除成功", 1);

			} else {

				return new Result("删除失败", 0);
			}
		}
	}

}
