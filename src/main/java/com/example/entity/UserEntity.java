package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_MASTER_TABLE")
public class UserEntity {
	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer uid;

	@Column(name = "USER_NAME")
	private String username;

	@Column(name = "USER_PASSWORD")
	private String password;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "USER_PHNO")
	private Long phno;
}
