package jat.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jat.dao.FoodDAO;
import jat.dto.Food;



@SessionScoped
@Named
public class FoodController implements Serializable {
	
	private Food editedFood;

	public Food getEditedFood() {
		return editedFood;
	}

	public void setEditedFood(Food editedFood) {
		this.editedFood = editedFood;
	}
	
	@Inject
	private FoodDAO foodDAO;
	
	public List<Food> getAllFoods() {
		return foodDAO.getAll();
	}
	
	public String foodManage() {
		return "foodManage";
	}
	
	public String newFood() {
		editedFood = new Food();
		return "editFood";
	}
	
	public String save() {
		foodDAO.save(editedFood);
		editedFood = null;
		return "foodManage";
	}
	
	public String editFood(Food edit) {
		editedFood = edit;
		return "editFood";
	}
	
	public String removeFood(Food edit) {
		foodDAO.delete(edit);
		editedFood = null;
		return "foodManage";
	}
	
	public String viewAllFood() {
		return "orderFood";
	}
	
}
