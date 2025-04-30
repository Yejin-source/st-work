package com.example.jpa2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "emp")
public class Emp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eno;
	@Column(name = "ename")
	private String ename;
	@Column(name = "gender")
	private String gender;
	
	@JsonIgnoreProperties({"emps"}) // 양방향 시 발생하는 무한 참조(양쪽 setter의 재귀참조 발생) 
	@ManyToOne // Emp to Dept 관계
	@JoinColumn(name = "dno") // Emp 테이블의 FK
	private Dept dept;
}
