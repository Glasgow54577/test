package org.example.KursachP.repositories;

import org.example.KursachP.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {
    List<Shop> findByNameShop(String nameShop);
}
