package com.qa.demo.persistence.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.demo.persistence.domain.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
