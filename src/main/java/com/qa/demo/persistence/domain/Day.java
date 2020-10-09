package com.qa.demo.persistence.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Day {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name; //changed from day to name
	
	@OneToMany(mappedBy = "day", cascade = CascadeType.ALL)
	private List<Task> task = new ArrayList<>();

	public Day(String name) { //changed from day to name
		this.name = name; //changed from day to name
	}

	
	
	
	
}
