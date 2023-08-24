package com.blogApp.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role {
	@Id
  private int id;
  private String name;
  @ManyToMany
  List<User> users=new ArrayList<>();
  public Role() {
		super();
		// TODO Auto-generated constructor stub
	}
public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
