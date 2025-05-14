package com.example.ajax.dto;

import lombok.Data;

@Data
public class Member {
	private String id;
	private String pw;
	private String gender;
	private int age;
	
	private String address;
	
	private String postcode;
	private String roadAddress;
	private String jibunAddress;
	private String detailAddress;
	private String extraAddress;
	
	
	public void setAddress() {
		address = this.postcode + this.roadAddress + this.jibunAddress 
					+ this.detailAddress + this.extraAddress;
	}
}
