package com.qa.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.dto.TaskDTO;
import com.qa.demo.persistence.domain.Task;
import com.qa.demo.service.TaskService;

// localhost:8999/task/(sometype of crud)

@RestController
@CrossOrigin
@RequestMapping("/task")
public class TaskController {
	
	private TaskService service;

	@Autowired
	private TaskController(TaskService service) {
		super();
		this.service = service;
	}
	//create
	@PostMapping("/create")
	public ResponseEntity<TaskDTO> createTask(@RequestBody Task task) {
//		GamesDTO created = this.service.create(games);
		return new ResponseEntity<>(this.service.createTask(task), HttpStatus.CREATED);
	}
	//readAll
	@GetMapping("/readAll")
	public ResponseEntity<List<TaskDTO>> getAllTask() {
		return ResponseEntity.ok(this.service.readAllTask());
	}
	
	//readById
	@GetMapping("/read/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<TaskDTO> updateTaskById(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
		TaskDTO updated = this.service.update(taskDTO, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<TaskDTO> deleteTaskById(@PathVariable Long id) {
		return this.service.delete(id)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}








