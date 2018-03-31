package com.lic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lic.dao.PolicyDAO;
import com.lic.dao.UserDAO;
import com.lic.pojo.Client;
import com.lic.pojo.ClientRequests;
import com.lic.pojo.Employee;
import com.lic.pojo.Policy;
import com.lic.pojo.User;

import exception.UserException;

@Controller

@RequestMapping(value = "/LogInController")
public class LogInController {
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("policyDao")
	PolicyDAO policyDao;

	@RequestMapping(value = "/authentication")
	public ModelAndView authentication(HttpServletRequest request) {

		/*
		 * If user is present in session then remove it
		*/
		if (request.getSession().getAttribute("client") != null) {
			request.getSession().removeAttribute("client");
			request.getSession().removeAttribute("clientRequests");
		} else if (request.getSession().getAttribute("employee") != null) {
			request.getSession().removeAttribute("employee");
			request.getSession().removeAttribute("clientRequests");
		}

		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		try {
			User user = userDao.get(userName, password);
			if (user == null) {
				return new ModelAndView("error");
			}
			System.out.println(user.getfName());
			if (user.getType().equalsIgnoreCase("c")) {

				Client client = userDao.getClient(user.getId());
				Set<ClientRequests> clientRequests = client.getClientRequests();
				request.getSession().setAttribute("clientRequests", clientRequests);
				request.getSession().setAttribute("client", client);

				return new ModelAndView("clientHomePage", "user", user);
			} else {
				Employee employee = userDao.getEmployee(user.getId());
				Set<ClientRequests> clientRequests = employee.getClientRequests();
				request.getSession().setAttribute("clientRequests", clientRequests);
				request.getSession().setAttribute("employee", employee);
				return new ModelAndView("employeeHomePage", "user", user);
			}

		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	/*
	 * redirect to buy policy page with employee and selected-policy object
	 */
	@RequestMapping(value = "/buyPolicyPage")
	public ModelAndView getBuyPolicyPage(HttpServletRequest request) {

		List all_user_employees;
		try {
			String p = request.getParameter("radio");
			Policy selectedpolicy = policyDao.getPolicyByName(p);
			request.getSession().setAttribute("selectedpolicy", selectedpolicy);
			all_user_employees = userDao.getAllEmployees();
			request.getSession().setAttribute("all_user_employees", all_user_employees);
			new ModelAndView("buyPolicy");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("buyPolicy");

	}

	/*
	 * chooes between all or best of three and redirect to policyDetails.jsp
	 */
	@RequestMapping(value = "/showPolicyDetails")
	public ModelAndView showolicyDetails(HttpServletRequest request) {

		String choise = request.getParameter("radio");

		if (choise.equalsIgnoreCase("allPolicies")) {
			List policies;
			List temp;
			try {
				temp = (List) request.getSession().getAttribute("allpolicies");

				if (temp != null) {
					request.getSession().removeAttribute("allpolicies");
				}
				policies = policyDao.getPolicies();
				request.getSession().setAttribute("allpolicies", policies);
				return new ModelAndView("policyDetails");
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (choise.equalsIgnoreCase("bestOfThree")) {
			List policies;
			List temp;
			try {
				temp = (List) request.getSession().getAttribute("allpolicies");

				if (temp != null) {
					request.getSession().removeAttribute("allpolicies");
				}

				request.getSession().removeAttribute("allpolicies");
				policies = policyDao.getBestThreePolicies();
				request.getSession().setAttribute("allpolicies", policies);
				return new ModelAndView("policyDetails");
			} catch (UserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return new ModelAndView("showPolicies");

	}

	/*
	 * 
	 */
	@RequestMapping(value = "/requestPolicy")
	public ModelAndView requestPolicy(HttpServletRequest request) {

		try {
			int eId = Integer.parseInt(request.getParameter("selectedEmployee"));
			Employee e = userDao.getEmployee(eId);
			Policy p = (Policy) request.getSession().getAttribute("selectedpolicy");
			Client c = (Client) request.getSession().getAttribute("client");
			ClientRequests clientRequest = new ClientRequests();
			clientRequest.setClient(c);
			clientRequest.setEmployee(e);
			clientRequest.setPolicy(p);
			policyDao.saveRequest(clientRequest);

		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("buyPolicy");

	}

	/*
	 * get data to update status of the request and redirect to update status
	 * page
	 */

	@RequestMapping(value = "/updateStatus")
	public ModelAndView updateStatus(HttpServletRequest request) {
		int reqId = Integer.parseInt(request.getParameter("id"));
		try {
			ClientRequests clientRequest = policyDao.getClientRequest(reqId);
			request.getSession().setAttribute("clientRequest", clientRequest);
			int clientId = clientRequest.getClient().getClientId();
			User userToprocess = policyDao.getUserbyId(clientId);
			request.getSession().setAttribute("userToprocess", userToprocess);
			return new ModelAndView("updateStatus");
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("updateStatus");

	}

	/*
	 * set and merge the updated request
	 */
	@RequestMapping(value = "/setStatus")
	public ModelAndView setStatus(HttpServletRequest request) throws UserException {
		String status = request.getParameter("status");
		ClientRequests clientRequest = (ClientRequests) request.getSession().getAttribute("clientRequest");
		
		if(clientRequest.getStatus().equalsIgnoreCase(status)){
			return new ModelAndView("updateStatus");
		}

		
		
		if (status.equalsIgnoreCase("approved")) {

			System.out.println("in approved");
			
			Policy policyApproved = clientRequest.getPolicy();
			int clientId = clientRequest.getClient().getClientId();
			Client processedClient = userDao.getClient(clientId);
			String errorAge;
			if(processedClient.getAge()<policyApproved.getMinAge()){
				errorAge="Age is less than minimum age required to be elligible for policy !";
				request.getSession().setAttribute("errorAgeMin", errorAge);
				ModelAndView mav= new  ModelAndView("updateStatus");
				return mav;	
			}
			else if(processedClient.getAge()>policyApproved.getMaxAge()){
				request.getSession().removeAttribute("error");
				errorAge="Age is more than maximum age for policy !";
				request.getSession().setAttribute("errorAgeMax", errorAge);
				ModelAndView mav= new  ModelAndView("updateStatus");
				return mav;
			}
			errorAge=" ";
			request.getSession().removeAttribute("errorAgeMin");
			request.getSession().removeAttribute("errorAgeMax");
		
			clientRequest.setStatus(status);
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			// rough
			int flag=0;
			for(Employee emp : processedClient.getEmployees()){
				if(emp.getEmployeeId()==employee.getEmployeeId()){
					System.out.println("Employee is already present"+ emp.getUser().getUserName());
					processedClient.getPolicies().add(policyApproved);
					Client cl = userDao.mergeClient(processedClient);
					policyDao.updateRequest(clientRequest);
					System.out.println("end approved");
					flag=1;
					break;
				}
				
			}
			if (flag==0)
			{
				
				System.out.println("__Employee is not present__");
				processedClient.getEmployees().add(employee);
				processedClient.getPolicies().add(policyApproved);
				Client cl = userDao.mergeClient(processedClient);
				policyDao.updateRequest(clientRequest);
				System.out.println("end approved");
	
			}
			
			
			
			//rough
/*			processedClient.getEmployees().add(employee);
			processedClient.getPolicies().add(policyApproved);
			Client cl = userDao.mergeClient(processedClient);
			policyDao.updateRequest(clientRequest);
			System.out.println("end approved");
*/
		}
		else if(request.getParameter("status").equalsIgnoreCase("deny")){
			String stat=clientRequest.getStatus();
			if(stat.equalsIgnoreCase("approved")){
				String msg="Policy is Approved! Can not Deny it now.";
				request.getSession().setAttribute("msg", msg);
				return new ModelAndView("updateStatus");
			}
			clientRequest.setStatus("Denied");
			policyDao.updateRequest(clientRequest);
		}

		System.out.println("______Status :" + status + "___");

		return new ModelAndView("updateStatus");

	}

	
	@RequestMapping(value = "/toLogoutPage")
	public ModelAndView toLogoutPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("logoutPage");

	}

}
