package org.example.KursachP.repositories;

import org.example.KursachP.models.Ord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdRepository extends JpaRepository<Ord, Integer> {

    List<Ord> findByNumberOrder(int numberOrder);

}
