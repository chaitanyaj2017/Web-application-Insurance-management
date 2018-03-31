package com.lic.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lic.pojo.Client;
import com.lic.pojo.Employee;
import com.lic.pojo.User;

import exception.UserException;

public class UserDAO extends DAO {

	public UserDAO() {
	}
/*
 * get all users
*/	
	
	public List getAllUsers() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User");
			List<User> userList = q.list();
			commit();
			return   userList;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user List " , e);
		}

	}

	
	public User get(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);

			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}

	}

	public User getUserByUserName(String username) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :username");
			q.setString("username", username);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}

	}

	public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
			q.setInteger("personID", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}
	
	/*
	 *     This method returns user for user name and password
	 * */
	public User getUser(String userName, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setString("userName", userName);
			q.setString("password", password);
			
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user ", e);
		}
	}
		

	// register user
	public User register(User u) throws UserException {
		try {
			begin();
			System.out.println("saving user ");
			User user= new User();
			user.setSsn(u.getSsn());
			user.setfName(u.getfName());
			user.setlName(u.getlName());
			user.setAddress(u.getAddress());
			user.setCellPhone(u.getCellPhone());
			user.setEmail(u.getEmail());
			user.setType(u.getType());
			user.setUserName(u.getUserName());
			user.setPassword(u.getPassword());
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	
	public Client registerClient(Client c) throws UserException {
		try {
			begin();
			System.out.println("saving client ");
			Client client= new Client();
			client.setAge(c.getAge());
			client.setDob(c.getDob());
			client.setOcuupation(c.getOcuupation());
			client.setUser(c.getUser());
			client.setEmployees(c.getEmployees());
			getSession().save(client);
			commit();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}
	
/* 
 * merge client
*/	
	public Client mergeClient(Client c) throws UserException {
		try {
			begin();
			Client client=new Client();
			client.setClientId(c.getClientId());
			client.setAge(c.getAge());
			client.setClientId(c.getClientId());
			client.setClientRequests(c.getClientRequests());
			client.setDob(c.getDob());
			client.setEmployees(c.getEmployees());
			client.setOcuupation(c.getOcuupation());
			client.setPolicies(c.getPolicies());
			client.setUser(c.getUser());
			System.out.println("merging client ");
			getSession().merge(client);
			commit();
			return c;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("_____Client  Merging___Failed____!!__ " + e.getMessage());
		}
	}

	
	/*
 * returns employee given the id
*/	
	public Employee getEmployee(int employeeId) throws UserException{
		try {
			begin();
			Query q = getSession().createQuery("from Employee where employeeId= :employeeId");
			q.setParameter("employeeId", employeeId);
			Employee employee= (Employee) q.uniqueResult();
			commit();
			return employee;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("      Employee with  Id "+employeeId +" does not exist ..!", e);
		}
	}

	
	
	/*
	 * returns Client given the id
	*/
	
	public Client getClient(int clientId) throws UserException{
		try {
			begin();
			Query q = getSession().createQuery("from Client where clientId= :clientId");
			q.setParameter("clientId", clientId);
			Client client= (Client) q.uniqueResult();
			commit();
			return client;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("      Client with  Id "+clientId +" does not exist ..!", e);
		}
	}

	
	
	/*
	 * Saves given employee
	*/
	public Employee registerEmployee(Employee e) throws UserException {
		try {
			begin();
			System.out.println("saving employee ");
			Employee employee= new Employee();
			employee.setEmploymentStatus(e.getEmploymentStatus());
			employee.setSalary(e.getSalary());
			employee.setUser(e.getUser());
			
			getSession().save(employee);
			commit();
			return e;

		} catch (HibernateException exp) {
			rollback();
			throw new UserException("Exception while creating user: " + exp.getMessage());
		}
	}
	
	
	
	
	
	/*
	 * get all employees
	*/
	
	public List getAllEmployees() throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User u  where u.type like '%e%'");
			List user_employees=q.list();
			commit();
			return user_employees;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " , e);
		}

	}
}
