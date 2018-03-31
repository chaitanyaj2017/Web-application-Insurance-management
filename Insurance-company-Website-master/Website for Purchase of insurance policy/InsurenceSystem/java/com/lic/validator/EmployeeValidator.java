package com.lic.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lic.pojo.Employee;

public class EmployeeValidator implements Validator {
	private Pattern pattern;
	 private Matcher matcher;
	 private static final String EMAIL_PATTERN =
	 "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	 + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	 String MOBILE_PATTERN = "[0-9]{10}";

	public boolean supports(Class<?> aClass) {
		return Employee.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
		 Employee emp = (Employee) obj;

		
		  
		 
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salary", "error.invalid.salary", "salary is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employmentStatus", "error.invalid.employmentStatus","Employment Status is required");

	}

}
