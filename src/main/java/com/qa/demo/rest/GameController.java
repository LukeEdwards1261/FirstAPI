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
import com.qa.demo.persistence.domain.Game;
import com.qa.demo.service.GameService;

// localhost:8999/games/(sometype of crud)

@RestController
@RequestMapping("/games")
public class GameController {
	
	private GameService service;

	@Autowired
	private GameController(GameService service) {
		super();
		this.service = service;
	}
	//create
	@PostMapping("/create")
	public ResponseEntity<GameDTO> create(@RequestBody Game games) {
//		GamesDTO created = this.service.create(games);
		return new ResponseEntity<>(this.service.createGame(games), HttpStatus.CREATED);
	}
	//readAll
	@GetMapping("/readAll")
	public ResponseEntity<List<GameDTO>> getAllGames() {
		return ResponseEntity.ok(this.service.readAllGames());
	}
	
	//readById
	@GetMapping("/read/{id}")
	public ResponseEntity<GameDTO> getGamesById(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.readById(id));
	}
	
	//update
	@PutMapping("/update/{id}")
	public ResponseEntity<GameDTO> updateGamesById(@PathVariable Long id, @RequestBody GameDTO gamesDTO) {
		GameDTO updated = this.service.update(gamesDTO, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}
	
	//delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<GameDTO> deleteGamesById(@PathVariable Long id) {
		return this.service.deleteGamesById(id)
				? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}








