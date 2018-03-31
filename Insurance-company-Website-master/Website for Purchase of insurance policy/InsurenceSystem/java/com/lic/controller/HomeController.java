package com.lic.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.lic.dao.UserDAO;
import com.lic.pojo.Client;
import com.lic.pojo.Employee;
import com.lic.pojo.User;
import com.lic.validator.UserValidator;

import exception.UserException;

/**
 * Handles requests for the application home page.
 */
@Controller

public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	@Qualifier("userValidator")
	UserValidator userValidator;
	
	@Autowired
	@Qualifier("empValidator")
	UserValidator empValidator;

	@InitBinder("userValidator")
	private void initUserBinder(WebDataBinder binder) {
		binder.setValidator(userValidator);
	}
	
	@InitBinder("empValidator")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.setValidator(empValidator);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		/*
		 * User user1 = new User(); user1.setAddress("aaa");
		 * user1.setCellPhone("aaa"); user1.setEmail("aaa");
		 * user1.setfName("aaa"); user1.setlName("aaa"); user1.setSsn(201);
		 * user1.setType("aaa"); user1.setAns("aaa"); user1.setPassword("aaa");
		 * user1.setUserName("aaa");
		 * 
		 * User user2 = new User(); user2.setAddress("asa");
		 * user2.setCellPhone("sfdsf"); user2.setEmail("dfsd");
		 * user2.setfName("aaa"); user2.setlName("bbb"); user2.setSsn(202);
		 * user2.setType("employee2"); user2.setAns("ans");
		 * user2.setPassword("pass"); user2.setUserName("userName");
		 * 
		 * User user3 = new User(); user3.setAddress("asa");
		 * user3.setCellPhone("sfdsf"); user3.setEmail("dfsd");
		 * user3.setfName("aaa"); user3.setlName("bbb"); user3.setSsn(203);
		 * user3.setType("employee1"); user3.setAns("ans");
		 * user3.setPassword("pass"); user3.setUserName("userName");
		 * 
		 * Policy policy1 = new Policy(); policy1.setMaturityPeriod(20);
		 * policy1.setMaxAge(18); policy1.setMinAge(15);
		 * policy1.setPolicyName("For Kids"); policy1.setRoi(12);
		 * 
		 * Policy policy2 = new Policy(); policy2.setMaturityPeriod(40);
		 * policy2.setMaxAge(36); policy2.setMinAge(30);
		 * policy2.setPolicyName("For Adults"); policy2.setRoi(14);
		 * 
		 * Client client = new Client(); client.setAge(22);
		 * client.setDob("12/1/1990"); client.setUser(user1);
		 * client.setOcuupation("Shopkeeper");
		 * 
		 * Employee employee1 = new Employee();
		 * employee1.setEmploymentStatus("active"); employee1.setSalary(2000);
		 * employee1.setUser(user2);
		 * 
		 * Employee employee2 = new Employee();
		 * employee2.setEmploymentStatus("active"); employee2.setSalary(3000);
		 * employee2.setUser(user3);
		 * 
		 * client.getEmployees().add(employee1);
		 * client.getEmployees().add(employee2);
		 * 
		 * client.getEmployees().add(employee1);
		 * client.getEmployees().add(employee2);
		 * 
		 * client.getPolicies().add(policy1); client.getPolicies().add(policy2);
		 * 
		 * SessionFactory sessionFactory = new
		 * Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		 * Session session = sessionFactory.openSession();
		 * session.beginTransaction(); // session.save(client);
		 * session.getTransaction().commit(); sessionFactory.close();
		 */
		
		
		return "home";
	}

	/*
	 * Take user to home page
	 */
	@RequestMapping(value = "/HomeController/signin.htm", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}

	/*
	 * redirect to register user form
	 */
	@RequestMapping(value = "/HomeController/signup.htm", method = RequestMethod.GET)
	protected ModelAndView signup(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result)
			throws Exception {
		System.out.print("SignUp User");

		return new ModelAndView("register-user", "user", new User());

	}

	/*
	 * Save user information
	 */
	@RequestMapping(value = "/HomeController/createUser.htm", method = RequestMethod.POST)
	protected ModelAndView createUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result) throws Exception {
		System.out.print("		Create User");

		userValidator.validate(user, result);
		
		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}
		try {
			
		List<User> users=userDao.getAllUsers();
		
		for(User tempUser : users){
			if(tempUser.getUserName().equalsIgnoreCase(user.getUserName()))
			{
				System.out.println(user.getSsn());
				System.out.println(tempUser.getSsn());
				String errorMessege="User name already exists!! Choose another.";
				request.getSession().setAttribute("errorMessege", errorMessege);
				ModelAndView mav= new  ModelAndView("register-user");
				return mav;
			}
		}
			
		String accountType = request.getParameter("accountType");
		if(accountType.equalsIgnoreCase("e"))
		{
			
		}
			
		
			System.out.print("create user.htm ");

			User u = userDao.register(user);

			request.getSession().setAttribute("user", u);

			

			if (accountType.equalsIgnoreCase("client")) {
				System.out.println(" 		going to client info page ");
				return new ModelAndView("clientForm", "client", new Client());
			} else {
				System.out.println(" 		going to employee info page ");
				return new ModelAndView("employeeForm", "employee", new Employee());
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	/*
	 * Save the client specific information
	 */
	@RequestMapping(value = "/HomeController/createInfo.htm", method = RequestMethod.GET)
	protected ModelAndView createInfo(HttpServletRequest request, @ModelAttribute("client") Client client,
			BindingResult result) throws Exception {

		System.out.print("			save client info ");
		if (result.hasErrors()) {
			return new ModelAndView("register-user", "client", client);
		}
		try {

			System.out.print("SignUp new User");
			User u = ((User) request.getSession().getAttribute("user"));
			client.setUser(u);
			// u.setClient(client);
			// Employee e = userDao.getEmployee(1);
			// client.getEmployees().add(e);

			Client c = userDao.registerClient(client);
			request.getSession().removeAttribute("user");

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		return new ModelAndView("home");

	}

	/*
	 * Save Employee specific information
	 */

	@RequestMapping(value = "/HomeController/createInfoEmp.htm", method = RequestMethod.POST)
	protected ModelAndView createInfoForEmployee(HttpServletRequest request,
			@ModelAttribute("employee") Employee employee, BindingResult result) throws Exception {
		System.out.print("			save employee info ");
		if (result.hasErrors()) {
			return new ModelAndView("register-user", "employee", employee);
		}
		try {

			User u = ((User) request.getSession().getAttribute("user"));
			employee.setUser(u);
			Employee e = userDao.registerEmployee(employee);
			request.getSession().removeAttribute("user");

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
		return new ModelAndView("home");

	}

	/*
	 * redirects to forgotpassword.jsp page
	 */
	@RequestMapping(value = "/HomeController/forgotPassword.htm", method = RequestMethod.GET)
	protected ModelAndView forgotPassword(HttpServletRequest request) throws Exception {
		System.out.print("			going to forgot password page");
		return new ModelAndView("forgotPassword");
	}

	/*
	 * Sends an email to recipient
	 */
	@RequestMapping(value = "/HomeController/sendEmail.htm", method = RequestMethod.GET)
	public String doSendEmail(HttpServletRequest request) throws Exception {
		String userName = request.getParameter("username");
		User u = userDao.getUserByUserName(userName);

		System.out.println(u.getEmail());
		// takes input from e-mail form
		String recipientAddress = u.getEmail();
		String subject = "Password";
		String message = "Please find the password :" + u.getPassword();

		// prints debug info
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + message);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(recipientAddress);
		email.setSubject(subject);
		email.setText(message);

		System.out.println("__sending email to: " + recipientAddress);

		// sends the e-mail
		mailSender.send(email);

		// forwards to the view named "Result"
		return "home";
	}

}
