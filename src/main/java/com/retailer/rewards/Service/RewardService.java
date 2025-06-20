package com.retailer.rewards.Service;

import java.util.HashMap;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;

@Service
public class RewardService {
	
	public Map<String, Map<String, Integer>> calculateRewards(Customer customer){
		Map<String, Integer> monthlyPoints = customer.getTransactions().stream()
			    .collect(Collectors.groupingBy(
			        tx -> tx.getDate().getMonth().name(),
			        Collectors.summingInt(tx -> calculatePoints(tx.getAmount()))
			    ));

			int totalPoints = monthlyPoints.values().stream()
			    .mapToInt(Integer::intValue)
			    .sum();

			monthlyPoints.put("Total", totalPoints);

			return Map.of(customer.getName(), monthlyPoints);

	}
	
	
	private int calculatePoints(double amount) {
		int points = 0;
		if(amount > 100) {
			points += (amount - 100)*2 + 50;
		}else if(amount > 50) {
			points += (amount - 50);
		}
		return points;
	}
}

