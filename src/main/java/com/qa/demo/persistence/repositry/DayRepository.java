package com.qa.demo.persistence.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.persistence.domain.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Long>{

}
