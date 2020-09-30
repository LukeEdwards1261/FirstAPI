package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.GameDTO;
import com.qa.demo.exception.GameNotFoundException;
import com.qa.demo.persistence.domain.Game;
import com.qa.demo.persistence.repositry.GameRepository;
import com.qa.demo.utils.GameBeanUtils;


@Service
public class GameService {

	private GameRepository repo;
	
	private ModelMapper mapper;

	@Autowired
	public GameService(GameRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private GameDTO mapToDTO(Game games) {
		return this.mapper.map(games,GameDTO.class);
	}
	
	private Game mapFromDTO(GameDTO gamesDTO) {
		return this.mapper.map(gamesDTO,Game.class);
	}
		
//	public create
	public GameDTO createGame(Game games) {
//		Games toSave = this.mapFromDTO(games);
		Game saved = this.repo.save(games);
		return this.mapToDTO(saved);
	}
	
//	public readAll
	public List<GameDTO> readAllGames() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}
//	public readById
	public GameDTO readById(long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(GameNotFoundException::new));
	}
//	public update
	public GameDTO update(GameDTO gamesDTO, Long id) {
		Game toUpdate = this.repo.findById(id).orElseThrow(GameNotFoundException::new);
		GameBeanUtils.mergeNotNull(gamesDTO, toUpdate);
		return this.mapToDTO(this.repo.save(toUpdate));
	}
	
//	public delete
	public boolean deleteGamesById(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}
	
}





