package com.pos_sales.controller;

import com.pos_sales.model.ProductModel;
import com.pos_sales.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "https://dilven.vercel.app")
public class ProductController {

		@Autowired
		ProductService pserv;

		//Test
			@GetMapping("/print")
				public String printHelloProduct() {
					return "Hello, Product!";
				}
		
				//Create or insert a product record
				@PostMapping("/postProduct")
				public ProductModel insertProduct(@RequestBody ProductModel product) {
					product.setDeleted(false);
					return pserv.insertProduct(product);
				}

				// Read all records for a specific business
				@GetMapping("/getAllProduct")
				public List<ProductModel> getAllProduct(@RequestParam String business) {
					return pserv.getAllProductByBusiness(business);
				}

			//Read a record by product name
				@GetMapping("/getByProduct")
				public ProductModel findByProductname(@RequestParam String productname) {
					return pserv.findByProductName(productname);	
				}
				
				//Update quantity
				@PutMapping("/putProduct")
				public ProductModel putProduct(@RequestParam int productid, @RequestBody ProductModel newProductDetails) throws Exception{
					return pserv.putProduct(productid, newProductDetails);
				}
				
				@PutMapping("/putQuantity")
				public ProductModel putQuantity(@RequestParam int productid, @RequestBody ProductModel newProductDetails) throws Exception{
					return pserv.putQuantity(productid, newProductDetails);
				}
				
				//Delete a record
				@PutMapping("/deleteProduct/{productid}")
				public String deleteProduct(@PathVariable int productid) {
					return pserv.deleteProduct(productid); 
				}

				// Display most purchased
				@GetMapping("/most-purchased")
				public ResponseEntity<ProductModel> getMostPurchasedProduct(@RequestParam String business) {
					// Call the service to retrieve the most purchased product for the given business
					ProductModel mostPurchasedProduct = pserv.getMostPurchasedProductByBusiness(business);

					if (mostPurchasedProduct != null) {
						return ResponseEntity.ok(mostPurchasedProduct);
					} else {
						return ResponseEntity.notFound().build();
					}
				}

				
			 	// Decrease quantity of a product
			    @PutMapping("/decreaseQuantity/{productid}")
			    public ResponseEntity<String> decreaseQuantity(
			            @PathVariable int productid,
			            @RequestParam("quantityToDecrease") int quantityToDecrease
			    ) {
			        try {
			            ProductModel product = pserv.decreaseQuantity(productid, quantityToDecrease);
			            return ResponseEntity.ok("Quantity decreased for product: " + product.getProductname());
			        } catch (Exception e) {
			            return ResponseEntity.badRequest().body(e.getMessage());
			        }
			    }

			    // Increment purchase count of a product by a specified quantity
			    @PutMapping("/incrementPurchaseCount/{productid}")
			    public ResponseEntity<String> incrementPurchaseCount(@PathVariable int productid, @RequestParam int quantityPurchased) {
			        try {
			            ProductModel product = pserv.incrementPurchaseCount(productid, quantityPurchased);
			            return ResponseEntity.ok("Purchase count incremented for product: " + product.getProductname());
			        } catch (Exception e) {
			            return ResponseEntity.badRequest().body(e.getMessage());
			        }
			    }
				
}