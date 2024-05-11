package com.pos_sales.service;

import com.pos_sales.model.ProductModel;
import com.pos_sales.model.TransactionModel;
import com.pos_sales.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TransactionService {
	@Autowired
	TransactionRepository trepo;
	
	//C - Create or insert a transaction record
	public TransactionModel insertTransaction(TransactionModel transaction) {
		return trepo.save(transaction);
	}
	
	//Read a record from the table named tbl_transaction
	public List<TransactionModel> getAllTransaction() {
		return trepo.findAll();
	}
	
	//R - Read or search student record by product name
			public TransactionModel findByTransactionid(int transactionid) {
				if (trepo.findByTransactionid(transactionid) !=null)
					return trepo.findByTransactionid(transactionid);
				else
					return null;
			}
			
			//U - Update a product record
			public TransactionModel putTransaction(int transactionid, TransactionModel newTransactionDetails) throws Exception{
				TransactionModel transaction = new TransactionModel();
				
				try {
					//steps in updating
					//Step 1 - search the id number of the product
					transaction = trepo.findById(transactionid).get();  //findById() is a pre-defined method
					
					//Step 2 - update the record
					transaction.setTotal_quantity(newTransactionDetails.getTotal_quantity());
					transaction.setTotal_price(newTransactionDetails.getTotal_price());
					transaction.setTendered_bill(newTransactionDetails.getTendered_bill());
					transaction.setBalance(newTransactionDetails.getBalance());
					transaction.setCustomer_name(newTransactionDetails.getCustomer_name());
					transaction.setCustomer_num(newTransactionDetails.getCustomer_num());
					transaction.setCustomer_email(newTransactionDetails.getCustomer_email());
					transaction.setRefunded(newTransactionDetails.isRefunded());
					transaction.setReturned(newTransactionDetails.isReturned());
					transaction.setProduct(newTransactionDetails.getProduct());
					transaction.setCashier(newTransactionDetails.getCashier());
					
					//Step 3 - save the information and return the value
					return trepo.save(transaction);
					
				} catch (NoSuchElementException nex) {
					throw new Exception("Transaction " + transactionid + " does not exist!");
				}
			}

			public Double computeGrossSalesByBusiness(String business) {
				return trepo.computeGrossSalesByBusiness(business);
			}

			public TransactionModel isReturned(int transactionid,  TransactionModel newTransactionDetails) throws Exception {
						TransactionModel transaction = new TransactionModel();
						try {
							//steps in updating
							//Step 1 - search the id number of the user
							transaction = trepo.findByTransactionid(transactionid);

							//Step 2 - update the record
							transaction.setReturned(newTransactionDetails.isReturned());

							//Step 3 - save the information and return the value
							return trepo.save(transaction);

						} catch (NoSuchElementException nex) {
							throw new Exception("Cannot refund this transaction.");
						}
					}

			public TransactionModel isRefund (int transactionid,  TransactionModel newTransactionDetails) throws Exception {
				TransactionModel transaction = new TransactionModel();
				try {
					//steps in updating
					//Step 1 - search the id number of the user
					transaction = trepo.findByTransactionid(transactionid);
					
					//Step 2 - update the record
					transaction.setRefunded(newTransactionDetails.isRefunded());
					
					//Step 3 - save the information and return the value
					return trepo.save(transaction);
					
				} catch (NoSuchElementException nex) {
					throw new Exception("Cannot refund this transaction.");
				}
			}

			
			//D - Delete transaction record
			public String deleteTransaction(int transactionid) {
				String msg;
				if (trepo.findById(transactionid) != null) {					//Step 1 - find the record
					trepo.deleteById(transactionid);                                //Step 2 - delete the record
					
					msg = "Transaction " + transactionid  + " successfully deleted!";
				} else
					msg = "Transaction " + transactionid + " is NOT found!";
				
				return msg;
			}

			// Display products purchased according to the transaction performed ^-^
		    public List<ProductModel> getProducts(int transactionId) {
		        // Find the transaction by ID
		        TransactionModel transaction = trepo.findById(transactionId).get();

		        if (transaction != null) {
		            // Get the list of products associated with the transaction
		            return transaction.getProduct();
		        } else {
		            // Handle the case where the transaction with the given ID is not found
		            return null; // You can return an empty list or handle the error as needed
		        }
		    }

			public Double computeNetSalesByBusiness(String business) {
				// Calculate net sales based on gross sales and deductions
				Double grossSales = trepo.computeGrossSalesByBusiness(business);
				Double deductions = computeDeductionsByBusiness(business); // Implement this method to calculate deductions
				Double netSales = grossSales - deductions;

				return netSales;
			}

			private Double computeDeductionsByBusiness(String business) {
				// Logic to compute deductions like returns, discounts, and allowances
				Double returnedPrices = computeReturnedPricesByBusiness(business);
				Double refundedPrices = computeRefundedPricesByBusiness(business);

				Double totalDeductions = returnedPrices + refundedPrices;

				return totalDeductions;
			}

			public Double computeReturnedPricesByBusiness(String business) {
				List<TransactionModel> returnedTransactions = trepo.findAllByBusinessAndReturned(business, true);
				Double returnedPrices = 0.0;
				for (TransactionModel transaction : returnedTransactions) {
					returnedPrices += transaction.getTotal_price();
				}
				return returnedPrices;
			}

			public Double computeRefundedPricesByBusiness(String business) {
				List<TransactionModel> refundedTransactions = trepo.findAllByBusinessAndRefunded(business, true);
				Double refundedPrices = 0.0;
				for (TransactionModel transaction : refundedTransactions) {
					refundedPrices += transaction.getTotal_price();
				}
				return refundedPrices;
			}

			public List<ProductModel> getReturnedProductsByBusiness(String business) {
				List<TransactionModel> returnedTransactionsByBusiness = trepo.findAllByBusinessAndReturned(business, true);
				List<ProductModel> returnedProducts = new ArrayList<>();
				for (TransactionModel transaction : returnedTransactionsByBusiness) {
					returnedProducts.addAll(transaction.getProduct()); // Assuming you have a method to retrieve products associated with a transaction
				}
				return returnedProducts;
			}

			public List<ProductModel> getRefundedProductsByBusiness(String business) {
				List<TransactionModel> refundedTransactionsByBusiness  = trepo.findAllByBusinessAndRefunded(business, true);
				List<ProductModel> refundedProducts = new ArrayList<>();
				for (TransactionModel transaction : refundedTransactionsByBusiness) {
					refundedProducts.addAll(transaction.getProduct()); // Assuming you have a method to retrieve products associated with a transaction
				}
				return refundedProducts;
			}

			public List<TransactionModel> getAllTransactionByBusiness(String business) {
				return trepo.findAllByBusiness(business);
			}

}
