package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.DayDTO;
import com.qa.demo.exception.DayNotFoundException;
import com.qa.demo.persistence.domain.Day;
import com.qa.demo.persistence.repositry.DayRepository;
import com.qa.demo.utils.TaskBeanUtils;

@Service
public class DayService {
	
	private DayRepository repo;
	
	private ModelMapper mapper;
	
	@Autowired
	public DayService(DayRepository repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private DayDTO mapToDTO(Day day) {
		return this.mapper.map(day,  DayDTO.class);
	}
	
	private Day mapFromDTO(DayDTO dayDTO) {
		return this.mapper.map(dayDTO, Day.class);
	}
	
	public DayDTO create(Day day) {
        Day created = this.repo.save(day);
        DayDTO mapped = this.mapToDTO(created);
        return mapped;
    }

    public List<DayDTO> read() {
        List<Day> found = this.repo.findAll();
        List<DayDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
        return streamed;
    }

    public DayDTO read(Long id) {
        Day found = this.repo.findById(id).orElseThrow(DayNotFoundException::new);
        return this.mapToDTO(found);
    }

    public DayDTO update(DayDTO dayDTO, Long id) {
        Day toUpdate = this.repo.findById(id).orElseThrow(DayNotFoundException::new);
        TaskBeanUtils.mergeNotNull(dayDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new DayNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}




