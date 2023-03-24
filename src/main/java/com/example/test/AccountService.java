package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    public String addAccount(Account account) {
        accountRepository.save(account);
        return "Account added";
    }

    public String linkAccount(List<Integer> ids, int accId) {
        for (Integer i : ids) {
            User user = userRepository.findById(i).get();
            Account account = accountRepository.findById(accId).get();
            user.setAccount(account);
            userRepository.save(user);
        }
        return "All users are Linked";
    }

    public String transactions(List<Extra> extras, int acc_id) {
        Account account = accountRepository.findById(acc_id).get();
        List<User> usersList = account.getUsers();
        Set<User> users = new HashSet<>();
        for (User user : usersList) //for O(1) time
            users.add(user);


        for (Extra extra : extras) {
            User u = userRepository.findById(extra.getId()).get();
            if (users.contains(u)) {
                if (extra.getOperation().equalsIgnoreCase("c")) {
                    if (account.getBalance() + extra.getValue() <= 10000000)
                        account.setBalance(account.getBalance() + extra.getValue());
                    else {
                        //do nothing
                    }
                }
                else if (extra.getOperation().equalsIgnoreCase("d")) {
                    if (account.getBalance() - extra.getValue() >= 0)
                        account.setBalance(account.getBalance() - extra.getValue());
                    else {
                        //do nothing
                    }
                }
            }


        }
        accountRepository.save(account);
        return "Done";
    }

}