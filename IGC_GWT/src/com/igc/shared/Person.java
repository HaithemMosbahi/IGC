package com.igc.shared;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;


@Entity
public class Person implements Serializable {
	

	
	@com.googlecode.objectify.annotation.Id private Long Id;
	private Key key;
	private String firstName;
	private String lastName;
	private String fullName;
	private String nickname;
	private String email;
	private Date birthdayDate;
	private boolean loggedIn=false;
	private String loginUrl;
	private String logoutUrl;
	private String university;
	private List<String> favMusic;
	private List<String> favProgLang;
	private List<String> favWebFramk;
	private Date registerDate;
	private String gwtUser;
	private String comment;
	private String sex;
	private String occupation;
	
	
	
	

	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public String getOccupation() {
		return occupation;
	}



	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}



	// Getters and Setters
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getNickname() {
		return nickname;
	}



	public void setNickname(String nickname) {
		this.nickname = nickname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getBirthdayDate() {
		return birthdayDate;
	}



	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}



	public boolean isLoggedIn() {
		return loggedIn;
	}



	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}



	public String getLoginUrl() {
		return loginUrl;
	}



	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}



	public String getUniversity() {
		return university;
	}



	public void setUniversity(String university) {
		this.university = university;
	}



	public List<String> getFavMusic() {
		return favMusic;
	}



	public void setFavMusic(List<String> favMusic) {
		this.favMusic = favMusic;
	}



	public List<String> getFavProgLang() {
		return favProgLang;
	}



	public void setFavProgLang(List<String> favProgLang) {
		this.favProgLang = favProgLang;
	}



	public List<String> getFavWebFramk() {
		return favWebFramk;
	}



	public void setFavWebFramk(List<String> favWebFramk) {
		this.favWebFramk = favWebFramk;
	}


	public String getLogoutUrl() {
		return logoutUrl;
	}


	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


	public String isGwtUser() {
		return gwtUser;
	}


	public void setGwtUser(String gwtUser) {
		this.gwtUser = gwtUser;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}



	public String getFullName() {
		return firstName+" "+lastName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}




	
	

}
