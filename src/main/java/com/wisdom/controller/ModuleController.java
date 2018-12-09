package com.wisdom.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wisdom.entity.Module;
import com.wisdom.service.ModuleService;
import com.wisdom.tools.Result;

@RestController
@RequestMapping("module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;

	@RequestMapping("/queryTreeGrid")
	public Object queryTreeGrid(Integer page, Integer rows) {

		List<Module> moduleList = moduleService.queryTreeGrid((page - 1) * rows, rows);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", moduleService.queryRootModuleCount(0));
		map.put("rows", moduleList);
		System.out.println("模块信息==》" + moduleList);
		return map;
	}

	/**
	 * 添加模块 http://localhost:8080/module/addModule
	 * 
	 * @param module
	 *            要添加的模块信息
	 * @return
	 */
	@RequestMapping("/insert")
	public Object addModule(Module module) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		try {
			date = df.parse(df.format(new Date()));// 获取系统当前时间并格式化

		} catch (ParseException e) {

			e.printStackTrace();
		}
		Module mo = new Module();
		if (module.getModuleId() == null) {
			module.setModuleCreateTime(date);
			module.setStatus(1);
			mo = module;
		} else {

			Module beforeModule = moduleService.findOne(module.getModuleId());
			beforeModule.setModuleName(module.getModuleName());
			beforeModule.setModuleUrl(module.getModuleUrl());
			beforeModule.setModuleWeight(module.getModuleWeight());
			mo = beforeModule;

		}
		module = moduleService.addModule(mo);
		if (module != null && !"".equals(module)) {

			return new Result("设置成功", 1);
		} else {

			return new Result("设置失败", 0);
		}
	}

	/**
	 * 通过id查询模块信息
	 * 
	 * @param moduleId
	 * @return
	 */
	@RequestMapping("/selectModuleById")
	public Module selectModuleById(Integer moduleId) {

		return moduleService.findOne(moduleId);
	}

	@RequestMapping("/queryMenu")
	public Object queryMenu() {

		List<Module> moduleList = moduleService.queryMenu();

		return moduleList;
	}

}
