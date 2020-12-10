package ru.miet.toeat.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

import ru.miet.toeat.tools.Tools;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name = "noname";
	private int id = -1;

	public Product() {
		super();
	}
	public Product(String name, int id) throws FormatException {
		super();
		setName(name);
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

	@Override
	public boolean equals(@Nullable Object obj) {
		if(!(obj instanceof Product))
			return false;
		return equals(obj);
	}

	public boolean equals(Product compare){
		boolean ret = true;
		if(!name.equals(compare.name)) {
			ret = false;
		}
		if(id != compare.id){
			ret = false;
		}
		return ret;
	}

	@NonNull
	@Override
	public String toString() {
		return name;
	}
}