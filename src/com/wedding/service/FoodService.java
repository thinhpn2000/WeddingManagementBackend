package com.wedding.service;

import java.util.List;

import com.wedding.models.Food;
import com.wedding.repository.FoodRepository;

public class FoodService {
	private FoodRepository foodRepository;

	public FoodService() {
		foodRepository = new FoodRepository();
	}

	public void addFood(Food food) {
		foodRepository.add(food);
		
	}

	
	public void deleteFood(int id) {
		foodRepository.delele(id);
		
	}

	
	public Food getFoodById(int id) {
		Food food = foodRepository.getByIDInFood(id);
		if(food != null) {
			if(food.getEndingDate() != null) {
				food = foodRepository.getByIDInUpdatedFood(id);
				food.setFromUpdatedFood(true);
				return food;
			}
			return food;
		}
		return null;
	}

	
	public List<Food> getAllFood() {
		return foodRepository.getAll();
	}

	
	public void updateFood(Food foodToUpdate) {
		Food food = getFoodById(foodToUpdate.getFoodID());
		if(food != null) {
			if(food.getFoodPrice() == foodToUpdate.getFoodPrice()) {
				foodRepository.updateOthersInFood(foodToUpdate);
			} else if(food.isFromUpdatedFood() && (food.getFoodPrice() != foodToUpdate.getFoodPrice())) {
				foodRepository.updateHasPriceInUpdatedFood(foodToUpdate);
			} else {
				foodRepository.updateHasPriceInFood(foodToUpdate);
			}
		}
	}
}
