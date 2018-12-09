package com.wisdom.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.wisdom.common.Result;
import com.wisdom.entity.Teachers;
import com.wisdom.service.LoginService;
import com.wisdom.tools.NECaptchaVerifier;
import com.wisdom.tools.NESecretPair;

@RestController
@RequestMapping("/login")
public class LoginController {

        @Autowired
		private LoginService lservice;
	
	    private static final long serialVersionUID = -3185301474503659058L;
	    private static final String captchaId = "64c5dc41cca44c649ba773ff7f143178"; // 验证码id
	    private static final String secretId = "226f1b8d49541357b54909c84ac4bbea"; // 密钥对id
	    private static final String secretKey = "fac9d3d1d50a2566c919c9adc4e44245"; // 密钥对key

	    private final NECaptchaVerifier verifier = new NECaptchaVerifier(captchaId, new NESecretPair(secretId, secretKey));
	    @RequestMapping("/tlogin")
        @CrossOrigin(origins="http://127.0.0.1:8020",allowCredentials="true")//cros跨域设置

		public String tlogin(HttpServletRequest request, @RequestBody Teachers teachers){

            // 从请求体里获得验证码validate数据

	        String user = "{'id':'123456'}";
            // 发起二次校验
	        boolean isValid = verifier.verify(teachers.getCode(), user);
	        
	        System.out.println("validate = " + teachers.getCode() + ", isValid = " + isValid);
	       
	        if(isValid) {
                Result result = lservice.login(teachers);
//                Teachers t=lservice.findTeacher(teachers.getId());
	        	if(result.isSuccess()) {
	        	    request.getSession().setAttribute("teachers",teachers);
	        	}
	        	return JSON.toJSONString(result);
	        }
	        return JSON.toJSONString(new Result(false,"验证码错误"));
		}
	    
	    
	    @RequestMapping("/adminLogin")
	    public Object adminLogin(String name,String pass) {
	    	
	    	if(name !=null && "admin".equals(name) && pass !=null && "123456".equals(pass)) {
	    		
	    		return new Result(true,"成功");
	    		
	    	}else {
	    		
	    		return new Result(false,"失败");
	    	}
	    }
}
