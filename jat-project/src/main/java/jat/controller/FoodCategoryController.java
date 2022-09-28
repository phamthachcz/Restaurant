package jat.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jat.dao.FoodCategoryDAO;

import jat.dto.FoodCategory;



@SessionScoped
@Named
public class FoodCategoryController implements Serializable {
	
	private FoodCategory editeFoodCategory;

	public FoodCategory getEditeFoodCategory() {
		return editeFoodCategory;
	}

	public void setEditeFoodCategory(FoodCategory editeFoodCategory) {
		this.editeFoodCategory = editeFoodCategory;
	}
	
	@Inject
	private FoodCategoryDAO categoryDAO;
	
	public List<FoodCategory> getAllFoodCategories() {
		return categoryDAO.getAll();
	}
	
	public String foodCategoryManage() {
		return "foodCateManage";
	}
	
	public String newFoodCate() {
		editeFoodCategory = new FoodCategory();
		return "editFoodCategory";
	}
	
	public String save() {
		categoryDAO.save(editeFoodCategory);
		editeFoodCategory = null;
		return "foodCateManage";
	}
	
	public String editFoodCategory(FoodCategory edit) {
		editeFoodCategory = edit;
		return "editFoodCategory";
	}
	
	public String removeFoodCategory(FoodCategory edit) {
		categoryDAO.delete(edit);
		editeFoodCategory = null;
		return "foodCateManage";
	}
	
	
}
