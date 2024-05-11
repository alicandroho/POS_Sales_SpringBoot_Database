package com.pos_sales.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="tbl_accounts")
	public class AccountsModel {

		@Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private int userid;
		@Column( unique = true)
	    private String username;

	    private String password;

	    private String account_type;
		@Column( unique = true)
	    private String email;

	    private String fname;

	    private String lname;
	    private String business_name;
	    private String address;
	    private String contactnum;
	    private String gender;
	    private String bday;

		private String cashier;

	    private String resetToken;
	    private LocalDateTime resetTokenExpiration;
	    
	    public AccountsModel() {}
	    
	    public AccountsModel(int userid, String username, String password, String account_type, String email, String fname,  String lname
	    		, String business_name, String address, String contactnum, String gender, String bday, String resetToken,  LocalDateTime resetTokenExpiration, String cashier) {
	        this.userid = userid;
	        this.username = username;
	        this.password = password;
	        this.account_type = account_type;
	        this.email = email;
	        this.fname = fname;
	        this.lname = lname;
	        this.business_name = business_name;
	        this.address = address;
	        this.contactnum = contactnum;
	        this.gender = gender;
	        this.bday = bday;
	        this.resetToken = resetToken;
			this.cashier = cashier;
	    }


		public LocalDateTime getResetTokenExpiration() {
			return resetTokenExpiration;
		}

		public void setResetTokenExpiration(LocalDateTime resetTokenExpiration) {
			this.resetTokenExpiration = resetTokenExpiration;
		}

		public String getResetToken() {
			return resetToken;
		}

		public void setResetToken(String resetToken) {
			this.resetToken = resetToken;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getBday() {
			return bday;
		}

		public void setBday(String bday) {
			this.bday = bday;
		}

		public int getUserid() {
			return userid;
		}

//		public void setUserid(int userid) {
//			this.userid = userid;
//		}


	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
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

		public String getAccount_type() {
			return account_type;
		}

		public void setAccount_type(String account_type) {
			this.account_type = account_type;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}


		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getBusiness_name() {
			return business_name;
		}

		public void setBusiness_name(String business_name) {
			this.business_name = business_name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getContactnum() {
			return contactnum;
		}

		public void setContactnum(String contactnum) {
			this.contactnum = contactnum;
		}




	    
}