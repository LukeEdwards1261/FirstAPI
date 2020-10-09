package com.qa.demo.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.demo.dto.DayDTO;
import com.qa.demo.persistence.domain.Day;
import com.qa.demo.service.DayService;

@SpringBootTest
public class DayControllerUnitTest {
	
	@Autowired
	private DayController controller;
	
	@MockBean
	private DayService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
    private DayDTO mapToDTO(Day day) {
        return this.modelMapper.map(day, DayDTO.class);
    }
    
    private List<Day> dayList;
    private Day testDay;
    private Day testDayWithId;
    private DayDTO dayDTO;
    
    private final Long id = 1L;
    private final String name = "Monday";
    //private final List<TaskDTO> task  [];
    
    @BeforeEach
    void init() {
    	this.dayList = new ArrayList<>();
    	this.testDay = new Day(name);
    	this.testDayWithId = new Day(testDay.getName());
    	this.testDayWithId.setId(id);
    	this.dayList.add(testDayWithId);
    	this.dayDTO = this.mapToDTO(testDayWithId);
    }
    
    @Test
    void createDay() {
    	when(this.service.create(testDay)).thenReturn(this.dayDTO);
    	DayDTO dayCreated = this.dayDTO;
    	assertThat(new ResponseEntity<DayDTO>(dayCreated, HttpStatus.CREATED))
    		.isEqualTo(this.controller.create(this.testDay));
    	verify(this.service, times(1)).create(this.testDay);
    }
    
    @Test
    void readDay() {
    	when(this.service.read(this.id))
    		.thenReturn(this.dayDTO);
    	DayDTO testReadOneDay = this.dayDTO;
    	assertThat(new ResponseEntity<DayDTO>(testReadOneDay, HttpStatus.OK))
    		.isEqualTo(this.controller.readDayById(this.id));
    	verify(this.service, times(1)).read(this.id);
    }
    
    @Test
    void readAllDay() {
    	when(this.service.read())
    		.thenReturn(this.dayList.stream().map(this::mapToDTO).collect(Collectors.toList()));
    	assertThat(this.controller.read().getBody().isEmpty()).isFalse();
    	
    	verify(this.service, times(1)).read();
    }
    
    @Test
    void updateTest() {
    	DayDTO newDay = new DayDTO(null, "Tuesday", null);
    	DayDTO newDayWithId = new DayDTO(this.id, newDay.getName(), newDay.getTask());
    	when(this.service.update(newDay, this.id)).thenReturn(newDayWithId);
    	assertThat(new ResponseEntity<DayDTO>(newDayWithId, HttpStatus.ACCEPTED))
    		.isEqualTo(this.controller.update(this.id, newDay));
    	
    	verify(this.service, times(1)).update(newDay, this.id);
    }
    
    @Test
    void deleteDay() {
    	this.controller.delete(id);
    	verify(this.service, times(1)).delete(id);
    }
    
    
   
    
    
    

}
