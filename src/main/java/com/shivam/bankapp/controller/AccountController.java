package com.shivam.bankapp.controller;


import com.shivam.bankapp.domain.Account;
import com.shivam.bankapp.dto.*;
import com.shivam.bankapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = { "http://abc.com", "http://pqrr.com", "http://localhost:3000" })
@RequestMapping(value = "/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/login")
    public ResponseEntity<List<MenuDto>> login(@Valid @RequestBody LoginDto dto) {

        String role = "admin";

        List<MenuDto> custMenu = List.of(
                 new MenuDto("deposit"),
                new MenuDto("withdraw"),
                new MenuDto("transfer")
        );

        return ResponseEntity.ok(custMenu);
    }

    @PostMapping("/create")
    public ResponseEntity<AppResponse> createNewAccount(@Valid @RequestBody AccountDto dto) {
        service.createAccount(dto);
        AppResponse response = new AppResponse("success", "Account Created Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<AppResponse> withdraw(@Valid @RequestBody AccountDetailsDto dto) {
        double sts = service.withdraw(dto.getSrcId(), dto.getAmt());

        AppResponse response = new AppResponse("success", "Amount Withdraw : " + sts);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/deposit")
    public ResponseEntity<AppResponse> deposit(@Valid @RequestBody AccountDetailsDto dto) {
        double sts = service.deposit(dto.getSrcId(), dto.getAmt());

        AppResponse response = new AppResponse("success", "Amount Deposited : " + sts);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/block")
    public ResponseEntity<AppResponse> toogleBlock(@Valid @RequestBody AccountDetailsDto dto) {
        int sts = service.toogleBlock(dto.getSrcId(), dto.isBlocked(), dto.getPassword());

        AppResponse response = new AppResponse("success", "Account Blocked : " + sts);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/active")
    public ResponseEntity<AppResponse> toogleActive(@Valid @RequestBody AccountDetailsDto dto) {
        int sts = service.toogleActive(dto.getSrcId(),dto.isActive(),dto.getPassword());
        AppResponse response = new AppResponse("success", "Account Active  : " + sts);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/transfer")
    public ResponseEntity<AppResponse> transfer(@Valid @RequestBody AccountDetailsDto dto) {
        double sts = service.transfer(dto.getSrcId(), dto.getDstId(), dto.getAmt());

        AppResponse response = new AppResponse("success", "Amount Transferred : " + sts);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> showAllAccounts(@Valid @RequestBody AccountDetailsDto dto) {
        return ResponseEntity.ok(service.showAllAccounts(dto));
    }
}
