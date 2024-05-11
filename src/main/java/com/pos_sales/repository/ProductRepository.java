package com.pos_sales.repository;

import com.pos_sales.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
        ProductModel findByProductname(String productname);

        List<ProductModel> findAllByIsDeletedFalse();
        ProductModel findByProductnameAndIsDeletedFalse(String productname);

        List<ProductModel> findAllByBusiness(String business);

        ProductModel findTopByBusinessOrderByPurchaseCountDesc(String business);

}