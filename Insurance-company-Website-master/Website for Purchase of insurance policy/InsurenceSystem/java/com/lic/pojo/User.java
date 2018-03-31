package com.lic.pojo;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.*;


@Entity  
@Table(name ="User")  


public class User {
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id")
private int id;
	
@Column(name = "ssn",unique=true)
private String ssn;

@Column(name = "fName",nullable = false)
private String fName;

@Column(name = "lName",nullable = false)
private String lName;

@Column(name = "address",nullable = false)
private String address;

@Column(name = "email",nullable = false)
private String email;

@Column(name = "cellPhone",nullable = false)
private String cellPhone;


@Column(name = "type",nullable = false)
private String type;


@Column(name = "userName",nullable = false)
private String userName;

@Column(name = "password",nullable = false)
private String password;



/*private Client client;*/


/*public Client getClient() {
	return client;
}
public void setClient(Client client) {
	this.client = client;
}
*/public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getfName() {
	return fName;
}
public String getSsn() {
	return ssn;
}
public void setSsn(String ssn) {
	this.ssn = ssn;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCellPhone() {
	return cellPhone;
}
public void setCellPhone(String cellPhone) {
	this.cellPhone = cellPhone;
}

}
