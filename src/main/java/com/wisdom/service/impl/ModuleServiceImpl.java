package com.wisdom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.dao.ModuleRepository;
import com.wisdom.entity.Module;
import com.wisdom.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {

	@Autowired
	private ModuleRepository moduleRepo;
	
	@Override
	public Module addModule(Module module) {
		
		return moduleRepo.save(module);
	}

	// -----------------查询模块管理树形表格
	@Override
	public List<Module> queryTreeGrid(Integer page, Integer rows) {

		List<Module> rootList = moduleRepo.findByParentId(0);
		this.setTreeGridChildrens(rootList);
		return rootList;
	}

	private void setTreeGridChildrens(List<Module> rootList) {

		for (Module modules : rootList) {

			List<Module> childrenList = moduleRepo.findByParentId(modules.getModuleId());
			System.out
					.println("*****************************************************设置子菜单=>" + modules.getModuleName());
			if (childrenList != null && !childrenList.isEmpty()) {
				System.out.println("设置的子菜单是=>" + childrenList);
				modules.setChildren(childrenList);

				this.setTreeGridChildrens(childrenList);
			}
		}
	}

	@Override
	public int queryRootModuleCount(Integer parentId) {

		List<Module> moduleList= moduleRepo.findByParentId(parentId);
		
		return moduleList.size();
	}

	@Override
	public Module findOne(Integer moduleId) {
		
		return moduleRepo.findOne(moduleId);
	}

	@Override
	public List<Module> queryMenu() {
		List<Module> rootList = moduleRepo.findByParentId(0);
		this.setTreeGridChildrens(rootList);
		return rootList;
	}
}
