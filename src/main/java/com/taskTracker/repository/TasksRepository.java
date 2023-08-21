package com.taskTracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskTracker.entity.Tasks;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, String>{
	
	List<Tasks> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword, String descriptionKeyword);
	
}
