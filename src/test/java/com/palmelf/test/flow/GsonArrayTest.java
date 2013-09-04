package com.palmelf.test.flow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonArrayTest {
	private String name;
	private int age;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "name:" + this.name + " age:" + this.age;
	}

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().serializeNulls().create();

		String obj2 = "[{name:'ding',age:'2'},{name:'king',age:'1'}]";

		GsonArrayTest[] test2 = gson.fromJson(obj2,
				new TypeToken<GsonArrayTest[]>() {
				}.getType());
		for (GsonArrayTest a : test2) {
			System.out.println("a:" + a.toString());
		}
	}
}
