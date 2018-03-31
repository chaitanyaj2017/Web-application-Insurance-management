package com.lic.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lic.pojo.User;

public class UserValidator implements Validator {

//	 private Pattern pattern;  
//	 private Matcher matcher;  
//	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
//			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
//			 String ID_PATTERN = "[0-9]+";  
//			 String STRING_PATTERN = "[a-zA-Z]+";  
//			 String MOBILE_PATTERN = "[0-9]{10}";  
			  
	
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	public void validate(Object obj, Errors errors) {
//		User user = (User) obj;
		  
/*			   
		if (!(user.getSsn().isEmpty())) {  
			   pattern = Pattern.compile(ID_PATTERN);  
			   matcher = pattern.matcher(user.getSsn());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("ssn", "ssn.incorrect",  
			      "Enter a numeric value");  
			   }  
		   }
*/		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ssn", "error.invalid.ssn", "SSN is required");
			   
			   
			   
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fName", "error.invalid.fname", "First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lName", "error.invalid.lname", "Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.invalid.userName", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email.emailAddress","Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.address", "Address Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellPhone", "error.invalid.cellphone", "Phone Number Required");
		
		
		// check if user exists
		
	}
}
