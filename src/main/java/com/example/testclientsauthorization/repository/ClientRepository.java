package com.example.testclientsauthorization.repository;

import com.example.testclientsauthorization.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

    Client findByEmailAddress(String emailAddress);

}
