package com.pos_sales.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="tbl_transaction")
	public class TransactionModel {

		@Id
	    @GeneratedValue (strategy = GenerationType.IDENTITY)
	    private int transactionid;

	    private int total_quantity;
	    private double total_price;
	    private double tendered_bill;
	    private double balance;
	    private String customer_name;
	    private String customer_num;
	    private String customer_email;
	    private String date_time;
	    private boolean refunded;
	    private boolean returned;

		private String cashier;

		private Double netSales;

		private String business;

	    @Column(unique = false)
	    @ManyToMany
	    public List<ProductModel> product;


	    public TransactionModel() {}

	    public TransactionModel(int transactionid, int total_quantity, double total_price, double tendered_bill, double balance, String customer_name,
	    		String customer_num, String customer_email, String date_time, boolean refunded, boolean returned, List<ProductModel> product, double netSales, String cashier, String business) {
	    	super();
	        this.transactionid = transactionid;
	        this.total_quantity = total_quantity;
	        this.total_price = total_price;
	        this.tendered_bill = tendered_bill;
	        this.balance = balance;
	        this.customer_name = customer_name;
	        this.customer_num = customer_num;
	        this.customer_email = customer_email;
	        this.date_time = date_time;
	        this.refunded = refunded;
	        this.returned= returned;
	        this.product = product;
			this.netSales = netSales;
			this.cashier = cashier;
			this.business = business;
	    }


	    //SETTERS AND GETTERS


	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCashier() {
			return cashier;
		}

		public void setCashier(String cashier) {
			this.cashier = cashier;
		}

		public Double getNetSales() {
			return netSales;
		}

		public void setNetSales(Double netSales) {
			this.netSales = netSales;
		}

		public String getDate_time() {
			return date_time;
		}

		public void setDate_time(String date_time) {
			this.date_time = date_time;
		}

		public String getCustomer_name() {
			return customer_name;
		}

		public void setCustomer_name(String customer_name) {
			this.customer_name = customer_name;
		}

		public String getCustomer_num() {
			return customer_num;
		}

		public void setCustomer_num(String customer_num) {
			this.customer_num = customer_num;
		}

		public String getCustomer_email() {
			return customer_email;
		}

		public void setCustomer_email(String customer_email) {
			this.customer_email = customer_email;
		}

		public int getTransactionid() {
			return transactionid;
		}

		public int getTotal_quantity() {
			return total_quantity;
		}

		public List<ProductModel> getProduct() {
		return product;
	}

		public void setProduct(List<ProductModel> product) {
			this.product = product;
		}

		public void setTotal_quantity(int total_quantity) {
			this.total_quantity = total_quantity;
		}

		public double getTotal_price() {
			return total_price;
		}

		public void setTotal_price(double total_price) {
			this.total_price = total_price;
		}

		public double getTendered_bill() {
			return tendered_bill;
		}

		public void setTendered_bill(double tendered_bill) {
			this.tendered_bill = tendered_bill;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public boolean isRefunded() {
			return refunded;
		}

		public void setRefunded(boolean refunded) {
			this.refunded = refunded;
		}

		public boolean isReturned() {
			return returned;
		}

		public void setReturned(boolean returned) {
			this.returned = returned;
		}



}