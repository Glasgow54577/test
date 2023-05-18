package org.example.KursachP.services;

import org.example.KursachP.models.Product;
import org.example.KursachP.models.Shop;
import org.example.KursachP.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ShopService {
    private final ShopRepository shopRepository;
    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    public List<Shop> findAll(){
        return shopRepository.findAll();
    }
    public Shop findOne(int id){
        Optional<Shop> foundShop = shopRepository.findById(id);
        return  foundShop.orElse(null);
    }

    public List<Shop> findByNameShop (String nameShop){
        return shopRepository.findByNameShop(nameShop);
    }

    @Transactional
    public void save(Shop shop){
        shopRepository.save(shop);
    }
    @Transactional
    public  void update(int id, Shop updateShop){
        updateShop.setId(id);
        shopRepository.save(updateShop);
    }

    @Transactional
    public void delete(int id){
        shopRepository.deleteById(id);
    }
}
