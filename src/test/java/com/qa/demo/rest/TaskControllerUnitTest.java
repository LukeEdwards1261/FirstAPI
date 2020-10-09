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

import com.qa.demo.dto.TaskDTO;
import com.qa.demo.persistence.domain.Task;
import com.qa.demo.service.TaskService;


@SpringBootTest
class TaskControllerUnitTest {

    @Autowired
    private TaskController controller;

    @MockBean
    private TaskService service;

    @Autowired
    private ModelMapper modelMapper;

    private TaskDTO mapToDTO(Task task) {
        return this.modelMapper.map(task, TaskDTO.class);
    }

    private List<Task> taskList;
    private Task testTask;
    private Task testTaskWithId;
    private TaskDTO taskDTO;

    private final Long id = 1L;
    private final String title = "shopping";
    private final String task = "get milk";
    private final String type = "chores";
    

    @BeforeEach
    void init() {
        this.taskList = new ArrayList<>();
        this.testTask = new Task(title, task, type);
        this.testTaskWithId = new Task(testTask.getTitle(), testTask.getTask(),
                testTask.getType());
        this.testTaskWithId.setId(id);
        this.taskList.add(testTaskWithId);
        this.taskDTO = this.mapToDTO(testTaskWithId);
    }

    @Test
    void createTest() {
        when(this.service.createTask(testTask)).thenReturn(this.taskDTO);
        TaskDTO testCreated = this.taskDTO;
        assertThat(new ResponseEntity<TaskDTO>(testCreated, HttpStatus.CREATED))
                .isEqualTo(this.controller.createTask(testTask));
        verify(this.service, times(1)).createTask(this.testTask);
    }

    @Test
    void readTest() {
        when(this.service.readById(this.id))
            .thenReturn(this.taskDTO);
        TaskDTO testReadOne = this.taskDTO;
        assertThat(new ResponseEntity<TaskDTO>(testReadOne, HttpStatus.OK))
                .isEqualTo(this.controller.getTaskById(this.id));
        verify(this.service, times(1)).readById(this.id);
    }

    @Test
    void readAllTest() {
        when(this.service.readAllTask())
                .thenReturn(this.taskList.stream().map(this::mapToDTO).collect(Collectors.toList()));
        assertThat(this.controller.getAllTask().getBody().isEmpty()).isFalse();

        verify(this.service, times(1)).readAllTask();
    }

    // controller <- service
    @Test
    void updateTest() {
        TaskDTO newTask = new TaskDTO(null, "project", "write program", "work");
        TaskDTO newTaskWithId = new TaskDTO(this.id, newTask.getTitle(), newTask.getTask(),
                newTask.getType());
        when(this.service.update(newTask, this.id)).thenReturn(newTaskWithId);

        assertThat(new ResponseEntity<TaskDTO>(newTaskWithId, HttpStatus.ACCEPTED))
                .isEqualTo(this.controller.updateTaskById(this.id, newTask));

        verify(this.service, times(1)).update(newTask, this.id);
    }

    // controller -> service
    @Test
    void deleteTest() {
        this.controller.deleteTaskById(id); 
        verify(this.service, times(1)).delete(id);
    }

}