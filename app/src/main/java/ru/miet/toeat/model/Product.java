package ru.miet.toeat.model;

import java.io.Serializable;

import ru.miet.toeat.tools.Tools;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name = "noname";
	private String description = "nodescription";
	private int id = -1;

	public Product() {
		super();
	}
	public Product(String name, String description, int id) throws FormatException {
		super();
		setName(name);
		setDescription(description);
		setId(id);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws FormatException {
		if(Tools.isCorrectFormat(name, ""))
			this.name = name;
		else
			throw new FormatException("Wrong set name in Product");
	}
	public int getId() {
		return id;
	}
	public void setId(int id) throws FormatException {
		if(Tools.isInRange(id, 0, Integer.MAX_VALUE))
			this.id = id;
		else
			throw new FormatException("Wrong set id in Product");
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) throws FormatException {
		if(Tools.isCorrectFormat(description, ""))
			this.description = description;
		else
			throw new FormatException("Wrong set name in Product");
	}

	public boolean equals(Product compare){
		boolean ret = true;
		if(!name.equals(name)) {
			ret = false;
		}
		if(!description.equals(description)) {
			ret = false;
		}
		if(id != compare.id){
			ret = false;
		}
		return ret;
	}
}