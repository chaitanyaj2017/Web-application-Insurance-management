package com.lic.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lic.pojo.ClientRequests;
import com.lic.pojo.Policy;
import com.lic.pojo.User;

import exception.UserException;

public class PolicyDAO extends DAO {
	public PolicyDAO() {
	}

	public List<Policy> getPolicies() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Policy");
			List<Policy> policies = q.list();
			commit();
			return policies;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}

	}
	
	
	/*
	*/
	

	public Policy savePolicy(Policy p) throws UserException {
		try {
			begin();
			Policy policy=new Policy();
			policy.setPolicyId(p.getPolicyId());
			policy.setPolicyName(p.getPolicyName());
			policy.setMaturityPeriod(p.getMaturityPeriod());
			policy.setMaxAge(p.getMaxAge());
			policy.setMinAge(p.getMinAge());
			policy.setRoi(p.getRoi());
			policy.setPremium(p.getPremium());
			
			getSession().save(policy);
			commit();
			return policy;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}

	}
	
	
	

	public Policy getpolicyById(int policyId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Policy where policyId = :policyId");
			q.setInteger("policyId", policyId);
			Policy p = (Policy)q.uniqueResult();
			commit();
			return p;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}

	}
	
	
	public List<Policy> getBestThreePolicies() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Policy p  order by p.roi desc");
			q.setFirstResult(0);
			q.setMaxResults(3);
			List<Policy> policies = q.list();
			commit();
			return policies;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}

	}

	public Policy getPolicyByName(String policyName) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from Policy where policyName = :policyName");
			q.setString("policyName", policyName);
			Policy policy = (Policy) q.uniqueResult();

			commit();
			return policy;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}

	}

	
	/*
	 * saves the request made by client
	*/
	public ClientRequests saveRequest(ClientRequests clientRequest) throws UserException {
		try {
			begin();
			// Query q = getSession().createQuery("from Policy where policyName
			// = :policyName");

			System.out.println("saving client request");
			getSession().save(clientRequest);
			commit();
			return clientRequest;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Failed to save request", e);
		}

	}
	
	
	/*
	 *   get requests given reqId
	 *      
	 */	
	
	public ClientRequests getClientRequest(int reqId)throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from ClientRequests where requestId = :reqId");
			q.setInteger("reqId", reqId);
			ClientRequests clientReq = (ClientRequests) q.uniqueResult();
			
			System.out.println("getting client request from clientId ");
			commit();
			return clientReq;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Failed to retrive request", e);
		}

	}
	
	
		
	
	/*
	 * get user by Id
	*/
	public User getUserbyId(int  clientId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where id = :clientId");
			q.setInteger("clientId", clientId);
			User u=(User) q.uniqueResult();
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Failed to retrive user from clientId", e);
		}

	}

	
	
	/*
	 * merge the updated request by employee
	*/
	public ClientRequests updateRequest(ClientRequests clientRequest) throws UserException {
		try {
			begin();
	
			ClientRequests clientReq = new ClientRequests();
			clientReq.setClient(clientRequest.getClient());
			clientReq.setEmployee(clientRequest.getEmployee());
			clientReq.setPolicy(clientRequest.getPolicy());
			clientReq.setRequestId(clientRequest.getRequestId());
			clientReq.setStatus(clientRequest.getStatus());
			System.out.println("Updating Client Request");
			getSession().merge(clientReq);
			commit();
			return clientReq;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Failed to update  request", e);
		}

	}
	
	

}


