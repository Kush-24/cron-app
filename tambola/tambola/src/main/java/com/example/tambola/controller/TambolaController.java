package com.example.tambola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tambola.entiry.TambolaUserCard;
import com.example.tambola.service.TambolaService;

@RestController
@RequestMapping("/tambola")
public class TambolaController {
	
	@Autowired
	TambolaService tambolaService;

	@GetMapping("/get-ticket-for-user")
	public TambolaUserCard getTicketForUser(){
		System.out.println("getTicketForUser :");
		try {
			return tambolaService.generateCustomTambolaCard();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
