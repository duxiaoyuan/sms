package com.wisdom.service;

import java.util.List;

import com.wisdom.entity.Module;

public interface ModuleService {

	/**
	 * 添加模块
	 * 
	 * @param module
	 *            要添加的模块
	 * @return 添加成功的模块信息
	 */
	Module addModule(Module module);

	/**
	 * 查询模块树形表格
	 * 
	 * @param parent_id
	 * @return
	 */

	List<Module> queryTreeGrid(Integer page, Integer rows);

	int queryRootModuleCount(Integer parentId);

	Module findOne(Integer moduleId);
	
	/**
	 * 查询左侧菜单
	 * 
	 * @param parent_id
	 * @return
	 */

	List<Module> queryMenu();
}
