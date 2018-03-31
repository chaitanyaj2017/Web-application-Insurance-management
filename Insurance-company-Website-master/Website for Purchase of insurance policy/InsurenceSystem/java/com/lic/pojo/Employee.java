package com.lic.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(generator = "newgenerator")
	@GenericGenerator(name = "newgenerator", strategy = "foreign", parameters = {
			@Parameter(value = "user", name = "property") })
	private int employeeId;

	@Column(name = "salary", nullable = false)
	private int salary=10000;

	@Column(name = "employmentStatus", nullable = false)
	private String employmentStatus="Inactive";

	@ManyToMany(mappedBy = "employees")
	private Set<Client> clients = new HashSet<Client>();
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="employee")
	private Set<ClientRequests> ClientRequests = new HashSet<ClientRequests>(0);

	
	// User setters and getters and declarations
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employeeId")
	User user = new User();
	
	
	
	
	
	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	public Set<ClientRequests> getClientRequests() {
		return ClientRequests;
	}

	public void setClientRequests(Set<ClientRequests> clientRequests) {
		ClientRequests = clientRequests;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//

	public int getSalary() {
		return salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEmploymentStatus() {
		return employmentStatus;
	}

	public void setEmploymentStatus(String employmentStatus) {
		this.employmentStatus = employmentStatus;
		 
	}

	
}
