package com.ragnar.moneyportal.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ragnar.moneyportal.constants.TransactionConstants;
import com.ragnar.moneyportal.dto.AccountDto;
import com.ragnar.moneyportal.dto.TransferDto;
import com.ragnar.moneyportal.entity.Account;
import com.ragnar.moneyportal.entity.Transaction;
import com.ragnar.moneyportal.service.AccountService;
import com.ragnar.moneyportal.service.TransactionService;
import com.ragnar.moneyportal.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@Autowired
	UserService userService;

	@Autowired
	TransactionService transactionService;

	public static final String LOGINPAGE = "redirect:/showMyLoginPage";

	public static final String TRANSFER = "transfer";

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/account-form")
	public String showAccountForm(Model theModel) {
		theModel.addAttribute("myAccount", new Account());
		return "account";
	}

	@PostMapping("/create-account")
	public String addNewAccount(@Valid @ModelAttribute("myAccount") AccountDto theAccount,
			BindingResult theBindingResult, Authentication principal, RedirectAttributes redirAttrs) {

		if (theBindingResult.hasErrors()) {
			return "account";
		}
		accountService.addAccount(theAccount, principal.getName());
		redirAttrs.addFlashAttribute("success", "Account added successfully");
		return "redirect:/account/account-form";
	}

	@GetMapping("/accounts")
	public String getCustomerAccounts(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			List<Account> theCustomerAccounts = accountService.getCustomerAccounts(currentUserName);
			model.addAttribute("theAccounts", theCustomerAccounts);
			return "accounts";
		}
		return LOGINPAGE;
	}

	@GetMapping("/details/{id}")
	public String accountDetails(Model model, @PathVariable("id") final Long id,
			@Valid @ModelAttribute("transfer") TransferDto transfer) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Account myAccount = accountService.findAccountbyId(id);
			List<Transaction> transaction = transactionService.findTransactionByAccountId(myAccount);
			model.addAttribute("income", transactionService.getToatalIncome(myAccount, TransactionConstants.CREDIT));
			model.addAttribute("expense", transactionService.getToatalIncome(myAccount, TransactionConstants.DEBIT));
            
			model.addAttribute("theTransactions", transaction);
                  
			model.addAttribute("accountNumber", myAccount.getAccountNo());
			model.addAttribute("accountBalance", myAccount.getBalance());
			model.addAttribute("accountId", myAccount.getId());

			return "account-details";
		}
		return LOGINPAGE;
	}

	@GetMapping("/transferForm/{id}")
	public String showTransferForm(Model theModel, @PathVariable("id") final Long id) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			Account myAccount = accountService.findAccountbyId(id);
			theModel.addAttribute(TRANSFER, new TransferDto());
			theModel.addAttribute("accountNumber", myAccount.getAccountNo());
			return TRANSFER;
		}
		return LOGINPAGE;
	}

	@PostMapping("/transferFund")
	public String transferFund(RedirectAttributes redirAttrs, @ModelAttribute("transfer") TransferDto transfer,
			@RequestParam("sender") String sender) {

		Account account = accountService.checkAccount(transfer.getReciverId());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			if (account != null) {
				accountService.transfer(sender, transfer.getReciverId(), transfer.getAmount());
				redirAttrs.addFlashAttribute("success", "Transfer successful");

				return "redirect:/account/transferForm/" + transfer.getReciverId();
			} else {
				redirAttrs.addFlashAttribute("error", "Account number doesn't exist");
				return TRANSFER;
			}
		}
		return LOGINPAGE;
	}

	@RequestMapping(value = "delete/{id}")
	public String deleteAccount(@PathVariable long id) {
		accountService.deleteAccountById(id);
		return "redirect:/account/accounts";
	}

}