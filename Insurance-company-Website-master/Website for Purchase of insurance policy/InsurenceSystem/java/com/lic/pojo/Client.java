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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "Client")
public class Client {

	@Id
	@GeneratedValue(generator = "newgenerator")
	@GenericGenerator(name = "newgenerator", strategy = "foreign", parameters = {
			@Parameter(value = "user", name = "property") })
	private int clientId;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "client_policy", joinColumns = @JoinColumn(name = "clientId"), inverseJoinColumns = @JoinColumn(name = "policyId"))
	private Set<Policy> policies = new HashSet<Policy>(0);

	@OneToMany(cascade=CascadeType.ALL,mappedBy="client")
	private Set<ClientRequests> clientRequests = new HashSet<ClientRequests>(0); 
	
	
	
	public Set<ClientRequests> getClientRequests() {
		return clientRequests;
	}

	public void setClientRequests(Set<ClientRequests> clientRequests) {
		this.clientRequests = clientRequests;
	}


	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "dob", nullable = false)
	private String dob;

	@Column(name = "ocuupation", nullable = false)
	private String ocuupation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clientId")
	User user = new User();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "client_employee", joinColumns = @JoinColumn(name = "clientId"), inverseJoinColumns = @JoinColumn(name = "employeeId"))
	private Set<Employee> employees = new HashSet<Employee>(0);

	// getters and setters

	public User getUser() {
		return user;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getOcuupation() {
		return ocuupation;
	}

	public void setOcuupation(String ocuupation) {
		this.ocuupation = ocuupation;
	}

	public Set<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<Policy> policies) {
		this.policies = policies;
	}
}
