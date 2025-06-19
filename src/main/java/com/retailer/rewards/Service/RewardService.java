package com.retailer.rewards.Service;

import java.util.HashMap;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.retailer.rewards.model.Customer;
import com.retailer.rewards.model.Transaction;

@Service
public class RewardService {
	
	public Map<String, Map<String, Integer>> calculateRewards(Customer customer){
		Map<String , Integer> monthlyPoints = new HashMap<>();
		int totalPoints = 0;
		
		for(Transaction tx: customer.getTransactions()) {
			String month = tx.getDate().getMonth().name();
			int points = calculatePoints(tx.getAmount());
			monthlyPoints.merge(month, points, Integer::sum);
			totalPoints += points;
		}
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

