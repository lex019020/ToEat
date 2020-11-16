package ru.miet.toeat.model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name = "noname";
	private String description = "nodescription";
	private int id = -1;
	
	public Product() {
		super();
	}
	public Product(String name, String description, int id) {
		super();
		this.name = name;
		this.description = description;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
