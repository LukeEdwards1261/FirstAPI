package com.qa.demo.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
public class Task {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "title", unique = true)
	@NotNull
	private String title;
	
	@Column(name = "task")
	//@Size(min = 1, max = 21)
	@NotNull
	private String task;
	
	@Column(name = "type")
	@NotNull
	private String type;
	
	@ManyToOne
	private Day day;

	public Task(@NotNull String title, @NotNull String task, @NotNull String type) {
		super();
		this.title = title;
		this.task = task;
		this.type = type;
	}




	
	
	
	
	
	
	
	
}
