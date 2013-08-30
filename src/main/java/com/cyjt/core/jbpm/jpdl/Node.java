package com.cyjt.core.jbpm.jpdl;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private String type;
	private Rectangle rectangle;
	private List<Transition> transitions = new ArrayList();
	private int x;
	private int y;
	private int width;
	private int height;

	public Node(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public Node(String name, String type, int x, int y, int w, int h) {
		this.name = name;
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;

		this.rectangle = new Rectangle();
		this.rectangle.setBounds(x, y, w, h);
	}

	public int getCenterX() {
		return this.x + this.width / 2;
	}

	public int getCenterY() {
		return this.y + this.height / 2;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void addTransition(Transition tran) {
		this.transitions.add(tran);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Rectangle getRectangle() {
		return this.rectangle;
	}

	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
	}

	public List<Transition> getTransitions() {
		return this.transitions;
	}

	public void setTransitions(List<Transition> transitions) {
		this.transitions = transitions;
	}
}
