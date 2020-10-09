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

import com.qa.demo.dto.DayDTO;
import com.qa.demo.persistence.domain.Day;
import com.qa.demo.service.DayService;


@RestController
@CrossOrigin
@RequestMapping("/day")
public class DayController {
	
	private DayService service;
	
	@Autowired
	public DayController(DayService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<DayDTO> create(@RequestBody Day day) {
		DayDTO created = this.service.create(day);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<DayDTO>> read() {
		return ResponseEntity.ok(this.service.read());
	}
	
	@GetMapping("/read/{id}")
    public ResponseEntity<DayDTO> readDayById(@PathVariable Long id) { //changed from read to readDayById
        return ResponseEntity.ok(this.service.read(id));
    }

	@PutMapping("/update/{id}")
    public ResponseEntity<DayDTO> update(@PathVariable Long id, @RequestBody DayDTO dayDTO) {
        return new ResponseEntity<>(this.service.update(dayDTO, id), HttpStatus.ACCEPTED);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<DayDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





