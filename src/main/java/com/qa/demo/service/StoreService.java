package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.dto.StoreDTO;
import com.qa.demo.exception.StoreNotFoundException;
import com.qa.demo.persistence.domain.Store;
import com.qa.demo.persistence.repositry.StoreRepository;
import com.qa.demo.utils.GameBeanUtils;

@Service
public class StoreService {
	
	private StoreRepository repo;
	
	private ModelMapper mapper;
	
	@Autowired
	public StoreService(StoreRepository repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}
	
	private StoreDTO mapToDTO(Store store) {
		return this.mapper.map(store,  StoreDTO.class);
	}
	
	private Store mapFromDTO(StoreDTO storeDTO) {
		return this.mapper.map(storeDTO, Store.class);
	}
	
	public StoreDTO create(Store store) {
        Store created = this.repo.save(store);
        StoreDTO mapped = this.mapToDTO(created);
        return mapped;
    }

    public List<StoreDTO> read() {
        List<Store> found = this.repo.findAll();
        List<StoreDTO> streamed = found.stream().map(this::mapToDTO).collect(Collectors.toList());
        return streamed;
    }

    public StoreDTO read(Long id) {
        Store found = this.repo.findById(id).orElseThrow(StoreNotFoundException::new);
        return this.mapToDTO(found);
    }

    public StoreDTO update(StoreDTO storeDTO, Long id) {
        Store toUpdate = this.repo.findById(id).orElseThrow(StoreNotFoundException::new);
        GameBeanUtils.mergeNotNull(storeDTO, toUpdate);
        return this.mapToDTO(this.repo.save(toUpdate));
    }

    public boolean delete(Long id) {
        if (!this.repo.existsById(id)) {
            throw new StoreNotFoundException();
        }
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}




