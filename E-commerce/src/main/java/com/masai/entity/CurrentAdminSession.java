package com.masai.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAdminSession {
	
	@Id
	@Column(unique = true)
	private Integer id;
	private String uuid;
	private LocalDate date;

}
