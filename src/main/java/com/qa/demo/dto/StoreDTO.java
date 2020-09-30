package com.qa.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class StoreDTO {

	private long id;
	private String location;
	private String manager;
	private int storeSize;
	private List<GameDTO> games = new ArrayList<>();
	
}
