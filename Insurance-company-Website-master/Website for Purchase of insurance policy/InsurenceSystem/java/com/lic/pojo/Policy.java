
package com.lic.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Policy")
public class Policy {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "policyId", unique = true, nullable = false)
	private int policyId;
	
	@Column(name="premium",nullable = false)
	private int premium;	
	
	

	public int getPremium() {
		return premium;
	}



	public void setPremium(int premium) {
		this.premium = premium;
	}

	@ManyToMany(mappedBy = "policies")
	private Set<Client> clients = new HashSet<Client>(0);
	
	
	
	

	public Set<Client> getClients() {
		return clients;
	}
    
	
	
	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

	@Column(name = "policyName", nullable = false)
	private String policyName;

	@Column(name = "minAge", nullable = false)
	private int minAge;

	@Column(name = "maxAge", nullable = false)
	private int maxAge;

	@Column(name = "maturityPeriod", nullable = false)
	private int maturityPeriod;

	@Column(name = "roi", nullable = false)
	private int roi;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getMaturityPeriod() {
		return maturityPeriod;
	}

	public void setMaturityPeriod(int maturityPeriod) {
		this.maturityPeriod = maturityPeriod;
	}

	public int getRoi() {
		return roi;
	}

	public void setRoi(int roi) {
		this.roi = roi;
	}

}
