package com.retailer.rewards.Controller;

import com.retailer.rewards.Dto.CustomerRequest;
import com.retailer.rewards.Dto.TransactionRequest;
import com.retailer.rewards.Exception.CustomerNotFoundException;
import com.retailer.rewards.Repository.CustomerRepository;
import com.retailer.rewards.Repository.TransactionRepository;
import com.retailer.rewards.Service.RewardService;
import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RetailerController {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private RewardService rewardService;

    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.getName());
        return new ResponseEntity<>(customerRepo.save(customer), HttpStatus.CREATED);
    }

    @PostMapping("/transactions")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody TransactionRequest request) throws CustomerNotFoundException{
        Customer customer = customerRepo.findById(request.getCustomerId())
                .orElseThrow(()-> new CustomerNotFoundException("Customer with ID " + request.getCustomerId() + " not found"));

        Transaction tx = new Transaction();
        tx.setAmount(request.getAmount());
        tx.setDate(request.getDate());
        tx.setCustomer(customer);

        transactionRepo.save(tx);
        return new ResponseEntity<>("Transaction added ", HttpStatus.CREATED);
    }

    @GetMapping("/rewards")
    public List<Map<String , Map<String, Integer>>> getAllCustomerRewards(){
        return customerRepo.findAll().stream()
                .map(rewardService::calculateRewards)
                .collect(Collectors.toList());
    }

    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<?> getRewardsByCustomerId(@PathVariable Long customerId) throws CustomerNotFoundException{
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(()-> new CustomerNotFoundException("Customer with ID " + customerId + " not found"));

        Map<String , Map<String, Integer>> rewards = rewardService.calculateRewards(customer);
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

}

