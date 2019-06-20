package com.project.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class Validation {
	
	public static List<?> getValidation(BindingResult result){
		List<String> resultList = new ArrayList<String>();
		if(result.hasErrors()) {
			System.out.println("----------出 现 错  误----------");
			List<FieldError> list = result.getFieldErrors();					//把错误信息封装到resultList中
			for (FieldError fieldError : list) {
				System.out.println(fieldError.getDefaultMessage());
				resultList.add(fieldError.getDefaultMessage());
			}
		}
		return resultList;
		
	}
}
