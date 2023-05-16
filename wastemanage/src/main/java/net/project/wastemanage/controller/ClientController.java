package net.project.wastemanage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.project.wastemanage.model.Client;
import net.project.wastemanage.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientService clientService;
	
	// display list of clients
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);		
	}
	
	@GetMapping("/showNewClientForm")
	public String showNewClientForm(Model model) {
		// create model attribute to bind form data
		Client client = new Client();
		model.addAttribute("client", client);
		return "new_client";
	}
	
	@PostMapping("/saveClient")
	public String saveClient(@ModelAttribute("client") Client client) {
		// save client to database
		clientService.saveClient(client);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get client from the service
		Client client = clientService.getClientById(id);
		
		// set client as a model attribute to pre-populate the form
		model.addAttribute("client", client);
		return "update_client";
	}
	
	@GetMapping("/deleteClient/{id}")
	public String deleteClient(@PathVariable(value = "id") long id) {
		// call delete client method 
		clientService.deleteClientById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Client> page = clientService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Client> listClients = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listClients", listClients);
		return "index";
	}
}
