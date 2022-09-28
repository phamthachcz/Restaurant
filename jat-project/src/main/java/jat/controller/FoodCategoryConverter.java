package jat.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import jat.dao.FoodCategoryDAO;
import jat.dto.FoodCategory;



@ApplicationScoped
@FacesConverter(forClass = FoodCategory.class, managed = true)
public class FoodCategoryConverter implements Converter<FoodCategory>{

	@Inject
	private FoodCategoryDAO categoryDAO;
	
	@Override
	public FoodCategory getAsObject(FacesContext context, UIComponent component, String value) {
		// TODO Auto-generated method stub
		if ("null".equals(value) || value == null || value.isBlank()) {
			return null;
		}
		int id = Integer.parseInt(value);
		return categoryDAO.find(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, FoodCategory value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return "null";
		}
		return Integer.toString(value.getId());
	}
	
}
