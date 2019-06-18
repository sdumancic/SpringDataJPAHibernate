package com.sdumancic.springdata.transactionmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    BankAccountRepository repository;

    @Override
    @Transactional
    public void transfer(int amount) {
        BankAccount obama = repository.findById(1).get();
        BankAccount donald = repository.findById(2).get();
        obama.setBal(obama.getBal() - amount);
        repository.save(obama);

        if (true){
            throw new RuntimeException();
        }

        donald.setBal(donald.getBal() + amount);
        repository.save(donald);
    }
}
