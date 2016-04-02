package com.sensedia.model;

import java.io.Serializable;

/**
 * Created by renan on 01/04/16.
 */
public class Model implements Serializable {

	private int key;
	private String value;

	public Model(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public Model() {
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Model{" + "key=" + key + ", value='" + value + '\'' + '}';
	}

}
