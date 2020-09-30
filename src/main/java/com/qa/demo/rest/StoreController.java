package com.qa.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.dto.GameDTO;
import com.qa.demo.dto.StoreDTO;
import com.qa.demo.persistence.domain.Game;
import com.qa.demo.persistence.domain.Store;
import com.qa.demo.service.StoreService;


@RestController
@RequestMapping("/store")
public class StoreController {
	
	private StoreService service;
	
	@Autowired
	public StoreController(StoreService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<StoreDTO> create(@RequestBody Store store) {
		StoreDTO created = this.service.create(store);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}
	
	@GetMapping("/read")
	public ResponseEntity<List<StoreDTO>> read() {
		return ResponseEntity.ok(this.service.read());
	}
	
	@GetMapping("/read/{id}")
    public ResponseEntity<StoreDTO> read(@PathVariable Long id) {
        return ResponseEntity.ok(this.service.read(id));
    }

	@PutMapping("/update/{id}")
    public ResponseEntity<StoreDTO> update(@PathVariable Long id, @RequestBody StoreDTO storeDTO) {
        return new ResponseEntity<>(this.service.update(storeDTO, id), HttpStatus.ACCEPTED);
    }
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<StoreDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}





