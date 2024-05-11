package com.pos_sales.service;

import com.pos_sales.model.AccountsModel;
import com.pos_sales.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;



@Service
public class AccountsService {
	@Autowired
	AccountsRepository arepo;
	
	//C - Create or insert an account record
	public AccountsModel insertAccount(AccountsModel account) {
		return arepo.save(account);
	}
	
	//Read a record from the table named tbl_supplier
	public List<AccountsModel> getAllAccounts() {
		return arepo.findAll();
	}
	
	//R - Read or search account record by username
			public AccountsModel findByUsername(String username) {
				if (arepo.findByUsername(username) !=null)
					return arepo.findByUsername(username);
				else
					return null;
			}

			public AccountsModel findByUserid(int userid) {
				if (arepo.findByUserid(userid) !=null)
					return arepo.findByUserid(userid);
				else
					return null;
			}

	// Search email by email
			public AccountsModel findByEmail(String email) {
				if(arepo.findByEmail(email) != null)
					return arepo.findByEmail(email);
				else
					return null;
			}
			
			// Search token by email
						public AccountsModel findByResetToken(String resetToken) {
							if(arepo.findByResetToken(resetToken) != null)
								return arepo.findByResetToken(resetToken);
							else
								return null;
						}
			
			//U - Update a product record
			public AccountsModel putAccounts(int userid, AccountsModel newAccountsDetails) throws Exception{
				AccountsModel account = new AccountsModel();
				
				try {
					//steps in updating
					//Step 1 - search the id number of the user
					account = arepo.findById(userid).get();  //findById() is a pre-defined method
					
					//Step 2 - update the record
					account.setUsername(newAccountsDetails.getUsername());
					account.setPassword(newAccountsDetails.getPassword());
					account.setAccount_type(newAccountsDetails.getAccount_type());
					account.setEmail(newAccountsDetails.getEmail());
					account.setFname(newAccountsDetails.getFname());
					account.setLname(newAccountsDetails.getLname());
					account.setBusiness_name(newAccountsDetails.getBusiness_name());
					account.setContactnum(newAccountsDetails.getContactnum());
					account.setGender(newAccountsDetails.getGender());
					account.setBday(newAccountsDetails.getBday());
					account.setCashier(newAccountsDetails.getCashier());
					
					//Step 3 - save the information and return the value
					return arepo.save(account);
					
				} catch (NoSuchElementException nex) {
					throw new Exception("User " + userid + " does not exist!");
				}
			}
			
			public AccountsModel updatePassword(String resetToken,  AccountsModel newAccountsDetails) throws Exception {
				AccountsModel account = new AccountsModel();
				try {
					//steps in updating
					//Step 1 - search the id number of the user
					account = arepo.findByResetToken(resetToken); //findById() is a pre-defined method
					
					//Step 2 - update the record
					account.setPassword(newAccountsDetails.getPassword());
					
					//Step 3 - save the information and return the value
					return arepo.save(account);
					
				} catch (NoSuchElementException nex) {
					throw new Exception("User  does not exist!");
				}
			}

			//D - Delete product record
			public String deleteAccount(int userid) {
				String msg;
				if (arepo.findById(userid) != null) {					//Step 1 - find the record
					arepo.deleteById(userid);                                //Step 2 - delete the record
					
					msg = "User " + userid  + " successfully deleted!";
				} else
					msg = "User " + userid + " is NOT found!";
				
				return msg;
			}
			
			
}
