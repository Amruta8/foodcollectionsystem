package fcs.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	String name,email,mobile,Address,dateOfBirth,password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + ", mobile=" + mobile
				+ ", Address=" + Address + ", dateOfBirth=" + dateOfBirth
				+ ", password=" + password + "]";
	}
	
}
