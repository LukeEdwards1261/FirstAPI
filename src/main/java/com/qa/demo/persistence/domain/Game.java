package com.qa.demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Game {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "name", unique = true)
	@NotNull
	private String name;
	
	@Column(name = "age_rating")
	//@Size(min = 1, max = 21)
	@NotNull
	private int age_rating;
	
	@Column
	@NotNull
	private String type;
	
	@ManyToOne
	private Store store;


//	public Games(Long id,@NotNull String name, @NotNull int age_rating, @NotNull String type) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.age_rating = age_rating;
//		this.type = type;
//	}

	
	
	
	
	
	
	
	
}
