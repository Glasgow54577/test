package org.example.KursachP.services;

import org.example.KursachP.models.Ord;
import org.example.KursachP.repositories.OrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrdService {
    private final OrdRepository ordRepository;

    @Autowired
    public OrdService(OrdRepository ordRepository) {
        this.ordRepository = ordRepository;
    }

    public List<Ord> findAll(){
        return ordRepository.findAll();
    }

    public Ord findOne(int id){
        Optional<Ord> foundOrder = ordRepository.findById(id);
        return  foundOrder.orElse(null);
    }

    public List<Ord> findByNumberOrder (int numberOrder){
        return ordRepository.findByNumberOrder(numberOrder);
    }

    @Transactional
    public void save(Ord ord){
        ordRepository.save(ord);
    }



}
