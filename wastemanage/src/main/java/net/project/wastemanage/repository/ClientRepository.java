package net.project.wastemanage.repository;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import net.project.wastemanage.model.Client;



@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
