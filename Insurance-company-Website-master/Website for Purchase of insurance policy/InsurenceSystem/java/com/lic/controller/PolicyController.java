package com.lic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lic.dao.PolicyDAO;
import com.lic.dao.UserDAO;
import com.lic.pojo.Policy;

import exception.UserException;

@Controller
@RequestMapping(value = "/PolicyController")
public class PolicyController {

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("policyDao")
	PolicyDAO policyDao;

	@RequestMapping(value = "/addOrUpdatePolicy")
	public ModelAndView addOrUpdatePolicy(HttpServletRequest request) throws UserException {
		List<Policy> policies = policyDao.getPolicies();
		request.getSession().setAttribute("policies", policies);
		return new ModelAndView("policyList");
	}

	@RequestMapping(value = "/toAddPolicy")
	public ModelAndView updatePolicy(HttpServletRequest request) throws UserException {

		return new ModelAndView("addPolicy");
	}

	@RequestMapping(value = "/addPolicy")
	public ModelAndView mergePolicy(HttpServletRequest request) throws UserException {
		Policy p = new Policy();
		int minAge = Integer.parseInt(request.getParameter("minAge"));
		int maxAge = Integer.parseInt(request.getParameter("maxAge"));
		System.out.println(minAge + " ******* " + maxAge);
		if (maxAge < minAge) {
			String error = "Minimum age must be less than maximum age !";
			request.getSession().setAttribute("error", error);
			return new ModelAndView("addPolicy");
		}
		p.setPolicyId(Integer.parseInt(request.getParameter("policyId")));
		p.setMaturityPeriod(Integer.parseInt(request.getParameter("matPeriod")));
		p.setPolicyName(request.getParameter("policyName"));
		p.setRoi(Integer.parseInt(request.getParameter("roi")));
		p.setMinAge(Integer.parseInt(request.getParameter("minAge")));
		p.setMaxAge(Integer.parseInt(request.getParameter("maxAge")));
		p.setPremium(Integer.parseInt(request.getParameter("premium")));
		Policy savedPolicy = policyDao.savePolicy(p);
		return new ModelAndView("addPolicySuccessPage");
	}

	@RequestMapping(value = "/gotoEmployeeHomePage")
	public ModelAndView gotoEmployeeHomePage(HttpServletRequest request) throws UserException {

		return new ModelAndView("employeeHomePage");
	}

}
