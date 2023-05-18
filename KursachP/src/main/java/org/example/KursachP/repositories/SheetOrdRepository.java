package org.example.KursachP.repositories;

import org.example.KursachP.models.Product;
import org.example.KursachP.models.SheetOrd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SheetOrdRepository extends JpaRepository<SheetOrd, Integer> {
//    List<SheetOrd> findByNumberOrder(int numberOrder);
}
