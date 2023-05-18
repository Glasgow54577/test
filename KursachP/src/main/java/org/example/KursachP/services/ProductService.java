package org.example.KursachP.services;

import org.example.KursachP.models.Product;
import org.example.KursachP.models.SheetOrd;
import org.example.KursachP.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findOne(int id){
        Optional<Product> foundProduct = productRepository.findById(id);
        return  foundProduct.orElse(null);
    }

    public List<Product> findByProductName (String productName){
        return productRepository.findByProductName(productName);
    }


    @Transactional
    public void save(Product product){
        productRepository.save(product);
    }

    @Transactional
    public  void update(int id, Product updateProduct){
        updateProduct.setId(id);
        productRepository.save(updateProduct);
    }

    @Transactional
    public void delete(int id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void updateAmount(int id, int amount){
        findOne(id).setAmount(findOne(id).getAmount()-amount);
        update(id, findOne(id));
    }

    @Transactional
    public void updateAmountPlus(int id, int amount){
        findOne(id).setAmount(findOne(id).getAmount()+amount);
        update(id, findOne(id));
    }

    @Transactional
    public SheetOrd setSheetOrd(SheetOrd sheetOrd, Product product){
        sheetOrd.setNameProduct(findOne(product.getId()).getProductName());
        sheetOrd.setIdProduct(product.getId());
        return sheetOrd;
    }

}
