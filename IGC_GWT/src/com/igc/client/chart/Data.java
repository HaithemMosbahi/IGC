package com.igc.client.chart;

public class Data {
	private String name;
	private int data1;
	
	
	public Data(String name) {
		super();
		this.name = name;
	}
	public Data(String name, int data1) {
		super();
		this.name = name;
		this.data1 = data1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getData1() {
		return data1;
	}
	public void setData1(int data1) {
		this.data1 = data1;
	}
	

}
