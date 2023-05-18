package org.example.KursachP.services;

import org.example.KursachP.models.Client;
import org.example.KursachP.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;
    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findOne(int id){
        Optional<Client> foundClient = clientRepository.findById(id);
        return  foundClient.orElse(null);
    }


    @Transactional
    public void save(Client client){
        clientRepository.save(client);
    }
    @Transactional
    public  void update(int id, Client updateClient){
        updateClient.setId(id);
        clientRepository.save(updateClient);
    }

    @Transactional
    public void delete(int id){
        clientRepository.deleteById(id);
    }
}
