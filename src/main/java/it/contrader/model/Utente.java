package it.contrader.model;

import javax.persistence.Entity;

import javax.persistence.*;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Utente {

	public enum Usertype {
		ADMIN,
		USER
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String username;
	private String password;
	private Usertype usertype;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usertype getUsertype() {
		return usertype;
	}

	public void setUsertype(Usertype usertype) {
		this.usertype = usertype;
	}
	
}

