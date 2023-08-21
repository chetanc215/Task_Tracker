package com.taskTracker.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taskTracker.entity.Tasks;
import com.taskTracker.exception.NotFoundException;
import com.taskTracker.service.TasksService;

@RestController
@RequestMapping("/tasks")
public class TasksController {
	
	private static final Logger logger = LoggerFactory.getLogger(TasksController.class);
	
	@Autowired
	private TasksService tasksService;
	
	  @PostMapping("/create")
	    public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks)throws Exception {
		  logger.info("TasksController: Post Mapping");
	            Tasks createdTask = tasksService.createTask(tasks);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
	        
	    }
	  
	  @GetMapping("/get/{id}")
	    public ResponseEntity<Tasks> getTaskById(@PathVariable String id) throws NotFoundException {
		  logger.info("TasksController: Get by id Mapping");
	            Tasks task = tasksService.getTaskById(id);
	            return ResponseEntity.status(HttpStatus.OK).body(task);  
	    }
	  
	  @GetMapping("/getAll")
	    public ResponseEntity<List<Tasks>> getAllTasks() {
		  logger.info("TasksController: getAll Mapping");
	        List<Tasks> tasks = tasksService.getAllTasks();
	        return ResponseEntity.status(HttpStatus.OK).body(tasks);
	    }
	  
	  @PutMapping("/update/{id}")
	    public ResponseEntity<Tasks> updateTask(@PathVariable String id,@RequestBody Tasks tasks)throws NotFoundException {
		  logger.info("TasksController: put Mapping");
	            Tasks updatedTask = tasksService.updateTaskById(id, tasks);
	            return ResponseEntity.status(HttpStatus.OK).body(updatedTask);
	    }
	  
	  @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteTask(@PathVariable String id) throws NotFoundException{
		  logger.info("TasksController: delete Mapping");
	            tasksService.deleteTaskById(id);
	            return ResponseEntity.status(HttpStatus.OK).body("Success");
	  }
	  
	  
	  //API for Fuzzy Search
	  
	  @GetMapping("/getByKeyword")
	    public ResponseEntity<List<Tasks>> getAllTasks(@RequestParam(required = false) String search) {
	        List<Tasks> tasks;

	        if (search != null && !search.isEmpty()) {
	            tasks = tasksService.searchTasksByKeyword(search);
	        } else {
	            tasks = tasksService.getAllTasks();
	        }

	        return ResponseEntity.ok().body(tasks);
	    }
	  

}
