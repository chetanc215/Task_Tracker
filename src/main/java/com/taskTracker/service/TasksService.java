package com.taskTracker.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskTracker.entity.Tasks;
import com.taskTracker.exception.NotFoundException;
import com.taskTracker.exception.ValidationException;
import com.taskTracker.repository.TasksRepository;

@Service
public class TasksService {
	
	private static final Logger logger = LoggerFactory.getLogger(TasksService.class);
	
	@Autowired
	private TasksRepository tasksrepository;
	
	
	public Tasks createTask(Tasks tasks) {
		logger.info("TaskService: Create new task method");
		 String providedId = tasks.getId();
	        if (tasksrepository.existsById(providedId)) {
	            throw new ValidationException("Task with ID " + providedId + " already exists.");
	        }
		return tasksrepository.save(tasks);
	}
	
	
	
	public Tasks getTaskById(String id) {
		logger.info("TaskService: getTaskById method");
		Optional<Tasks> task =  tasksrepository.findById(id);
		if (task.isPresent()) {
	        return task.get();
	    } else {
	    	logger.info("TaskService: Id not present");
	        throw new NotFoundException("Task " + id + " not found.");
	    }
	}
		
		
	public List<Tasks> getAllTasks(){
		logger.info("TaskService: getAllTasks method");
		return tasksrepository.findAll();
	}
	
	
	public Tasks updateTaskById(String id, Tasks tasks) {
		logger.info("TaskService: updateTaskById method");
        Tasks updatedTask = getTaskById(id);
        
        if(tasks.getTitle()!=null) {
            updatedTask.setTitle(tasks.getTitle());
        }
        if(tasks.getDescription()!=null) {
        	updatedTask.setDescription(tasks.getDescription());
        }
        if(tasks.getDueDate()!=null) {
        	updatedTask.setDueDate(tasks.getDueDate());
        }
        return tasksrepository.save(updatedTask);
    }
	
	
	public boolean deleteTaskById(String id) {
		logger.info("TaskService: deleteById method");
        Tasks task = getTaskById(id);
        if (task != null) {
            tasksrepository.delete(task);
            return true;
        }
        logger.info("TaskService: Id not present");
        return false;
        
    } 
	
	public List<Tasks> searchTasksByKeyword(String keyword) {
        return tasksrepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }
		
}
