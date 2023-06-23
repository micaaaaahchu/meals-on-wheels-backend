package com.lithan.mow.service;

import com.lithan.mow.entity.Customer;
import com.lithan.mow.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer register(Customer customer){

            String password = passwordEncoder.encode(customer.getPassword());
            customer.setPassword(password);
            return  customerRepository.save(customer);
    }
    public Boolean isCustomerExist(String email){
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()){
            return true; // Customer exists
        }
        return false; // Customer does not exist
    }


}