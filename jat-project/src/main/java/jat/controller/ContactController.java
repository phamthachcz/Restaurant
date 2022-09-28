package jat.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jat.dao.ContactDAO;
import jat.dto.Contact;


@SessionScoped
@Named
public class ContactController implements Serializable{
	
	private Contact newContact;
	
	public Contact getNewContact() {
		return newContact;
	}

	public void setNewContact(Contact newContact) {
		this.newContact = newContact;
	}

	@Inject
	private ContactDAO contactDAO;
	
	public List<Contact> getAllContacts(){
		return contactDAO.getAll();
	}
	
	public String sendContact() {
		newContact = new Contact();
		return "contact";
	}
	
	public String save() {
		contactDAO.save(newContact);
		newContact = null;
		return "index";
	}
	
	public String contactsManage() {
		return "contactManage";
	}

	
}
