package com.example.demo;

import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerWriter implements ItemWriter<Customer>{

	@Autowired
    private CustomerRepository customerRepository;

	public void write(List<? extends Customer> customers) throws Exception {
        customerRepository.saveAll(customers);
    }
}

