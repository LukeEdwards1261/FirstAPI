package com.qa.demo.persistence.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Store {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(name = "store_location", unique = true)
	private String location;
	
	@Column(name = "store_manager")
	private String manager;
	
	@Column(name = "store_size")
	private int storeSize;
	
	@OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
//	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Game> games;

	public Store(String location) {
		super();
		this.location = location;
	}

	
	
	
	
}
