package org.example.KursachP.services;

import org.example.KursachP.models.Client;
import org.example.KursachP.models.SheetOrd;
import org.example.KursachP.repositories.SheetOrdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SheetOrdService {
    private final SheetOrdRepository sheetOrdRepository;
    @Autowired
    public SheetOrdService(SheetOrdRepository sheetOrdRepository) {
        this.sheetOrdRepository = sheetOrdRepository;
    }

    public List<SheetOrd> findAll(){
        return sheetOrdRepository.findAll();
    }

    public SheetOrd findOne(int id){
        Optional<SheetOrd> foundOrdClient = sheetOrdRepository.findById(id);
        return  foundOrdClient.orElse(null);
    }



    @Transactional
    public void save(SheetOrd sheetOrder){
        sheetOrdRepository.save(sheetOrder);
    }
    @Transactional
    public  void update(int id, SheetOrd updateSheetOrd){
        updateSheetOrd.setId(id);
        sheetOrdRepository.save(updateSheetOrd);
    }

    @Transactional
    public void delete(int id){
        sheetOrdRepository.deleteById(id);
    }


}
