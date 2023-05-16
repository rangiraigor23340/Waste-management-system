package net.project.wastemanage.service;

import java.util.List; 

import org.springframework.data.domain.Page;


import net.project.wastemanage.model.Client;



public interface ClientService {
	
	Client getClientById(long id);
	void deleteClientById(long id);
	List<Client> getAllClients();
	void saveClient(Client client);


	Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
}
