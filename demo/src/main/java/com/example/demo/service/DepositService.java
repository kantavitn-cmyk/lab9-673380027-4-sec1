package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Account;
import com.example.demo.model.DepositTransaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.DepositRepository;

import jakarta.transaction.Transactional;

@Service 
public class DepositService {
    private final AccountRepository accountRepository;
    private final DepositRepository depositRepository;

    public DepositService(
            AccountRepository accountRepository,
            DepositRepository depositRepository) {
        this.accountRepository = accountRepository;
        this.depositRepository = depositRepository;
    }

    @Transactional
    public void deposit(Long accountId, Double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() ->
                        new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        DepositTransaction transaction = new DepositTransaction();
        transaction.setAmount(amount);
        transaction.setAccount(account);

        depositRepository.save(transaction);
    }
}
