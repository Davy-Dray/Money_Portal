package com.ragnar.moneyportal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.service.AccountService;

@Controller
public class HomeController {

	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public String showHome(Principal principal, Model model) {

		List<Account> myAccounts = accountService.getCustomerAccounts(principal.getName());

		model.addAttribute("theAccounts", myAccounts);

		return "home";
	}

}
