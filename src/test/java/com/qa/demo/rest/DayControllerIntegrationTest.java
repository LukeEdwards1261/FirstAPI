package com.qa.demo.rest;


import static org.junit.jupiter.api.Assertions.assertEquals; //different version compare to previous, may cause errors
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.demo.dto.DayDTO;
import com.qa.demo.persistence.domain.Day;
import com.qa.demo.persistence.repositry.DayRepository;



@SpringBootTest
@AutoConfigureMockMvc
public class DayControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

    @Autowired
    private DayRepository repo;
    
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Long id;
    private Day testDay;
    private Day testDayWithId;
    
    private DayDTO mapToDTO(Day day) {
        return this.modelMapper.map(day, DayDTO.class);
    }

    @BeforeEach
    void init() {
        this.repo.deleteAll();
        this.testDay = new Day("Monday");
        this.testDayWithId = this.repo.save(this.testDay);
        this.id = this.testDayWithId.getId();
    }

    @Test
    void testCreate() throws Exception {
        this.mock
            .perform(request(HttpMethod.POST, "/day/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(testDay))
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(content().json(this.objectMapper.writeValueAsString(testDayWithId)));
    }

    @Test
    void testRead() throws Exception {
        this.mock
            .perform(request(HttpMethod.GET, "/day/read/" + this.id)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(this.objectMapper.writeValueAsString(this.testDay)));
    }

    @Test
    void testReadAll() throws Exception {
        List<Day> dayList = new ArrayList<>();
        dayList.add(this.testDayWithId);

        String content = this.mock
            .perform(request(HttpMethod.GET, "/day/read")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(dayList), content);
    }

    @Test
    void testUpdate() throws Exception {
    	System.out.println(this.testDay.getName());
        Day newDay = new Day("Tuesday");
        Day updatedDay = new Day(newDay.getName()); //changed from getDay to getName
        updatedDay.setId(this.id);

        String result = this.mock
            .perform(request(HttpMethod.PUT, "/day/update/" + this.id)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(newDay)))
            .andExpect(status().isAccepted())
            .andReturn().getResponse().getContentAsString();
        
        assertEquals(this.objectMapper.writeValueAsString(this.mapToDTO(updatedDay)), result);
    }
    
    @Test
    void testDelete() throws Exception {
        this.mock
            .perform(request(HttpMethod.DELETE, "/day/delete/" + this.id))
            .andExpect(status().isNoContent());
    }

}
