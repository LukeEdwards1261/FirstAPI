package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.TaskDTO;
import com.qa.demo.exception.TaskNotFoundException;
import com.qa.demo.persistence.domain.Task;
import com.qa.demo.persistence.repositry.TaskRepository;
import com.qa.demo.utils.TaskBeanUtils;

@Service
public class TaskService {

	private TaskRepository repo;
	
	private ModelMapper mapper;

	@Autowired
	public TaskService(TaskRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private TaskDTO mapToDTO(Task task) {
		return this.mapper.map(task,TaskDTO.class);
	}
	
//	private Game mapFromDTO(GameDTO gamesDTO) {
//		return this.mapper.map(gamesDTO,Game.class);
//	}
		
//	public create
	public TaskDTO createTask(Task task) {
		Task created = this.repo.save(task);
		TaskDTO mapped = this.mapToDTO(created);
		return mapped;
	}
	
//	public readAll
	public List<TaskDTO> readAllTask() {
		List<Task> found = this.repo.findAll();
        List<TaskDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
		return streamed;
	}
//	public readById
	public TaskDTO readById(Long id) {
		Task found = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		return this.mapToDTO(found);
	}
//	public update
	public TaskDTO update(TaskDTO taskDTO, Long id) {
		Task toUpdate = this.repo.findById(id).orElseThrow(TaskNotFoundException::new);
		TaskBeanUtils.mergeNotNull(taskDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
//	public delete
    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new TaskNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
	
}





