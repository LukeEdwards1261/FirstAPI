package com.qa.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.demo.dto.TaskDTO;
import com.qa.demo.persistence.domain.Task;
import com.qa.demo.persistence.repositry.TaskRepository;

@SpringBootTest
public class TaskServiceIntegrationTest {
	
	 @Autowired
	    private TaskService service;

	    @Autowired
	    private TaskRepository repo;

	    @Autowired
	    private ModelMapper modelMapper;

	    private TaskDTO mapToDTO(Task task) {
	        return this.modelMapper.map(task, TaskDTO.class);
	    }

	    private Task testTask;
	    private Task testTaskWithId;
	    private TaskDTO testTaskDTO;

	    private Long id;
	    private final String title = "shopping";
	    private final String task = "get milk";
	    private final String type = "chores";

	    @BeforeEach
	    void init() {
	        this.repo.deleteAll();
	        this.testTask = new Task(title, task, type);
	        this.testTaskWithId = this.repo.save(this.testTask);
	        this.testTaskDTO = this.mapToDTO(testTaskWithId);
	        this.id = this.testTaskWithId.getId();
	    }

	    @Test
	    void testCreate() {
	        assertThat(this.testTaskDTO)
	            .isEqualTo(this.service.createTask(testTask));
	    }

	    @Test
	    void testRead() {
	        assertThat(this.testTaskDTO)
	                .isEqualTo(this.service.readById(this.id));
	    }

	    @Test
	    void testReadAll() {
	        assertThat(this.service.readAllTask())
	                .isEqualTo(Stream.of(this.testTaskDTO)
	                        .collect(Collectors.toList()));
	    }

	    @Test
	    void testUpdate() {
	        TaskDTO newTask = new TaskDTO(null, "project", "write program", "work");
	        TaskDTO updatedTask =
	                new TaskDTO(this.id, newTask.getTitle(), newTask.getTask(), newTask.getType());

	        assertThat(updatedTask)
	            .isEqualTo(this.service.update(newTask, this.id));
	    }

	    @Test
	    void testDelete() {
	        assertThat(this.service.delete(this.id)).isTrue();
	    }

}
