package com.taskTracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.taskTracker.entity.Tasks;
import com.taskTracker.repository.TasksRepository;
import com.taskTracker.service.TasksService;

@SpringBootTest
class TaskTrackerApplicationTests {

	 @Mock
	 private TasksRepository taskRepository;

	 @InjectMocks
	 private TasksService taskService;
	 
	 
	 @Test
	 public void testCreateTask() {
		 
		 Tasks task1 = new Tasks("Task1","Create APIs","Create CRUD operations", LocalDate.of(2023,8,07));
		 
		 when(taskRepository.save(task1)).thenReturn(task1);
		 Tasks actualTask1= taskService.createTask(task1);
		 assertEquals(task1, actualTask1);
	 }
	 
	 
	 @Test
	 public void testGetTaskById() {
		 
		 Tasks task1 = new Tasks("Task1","Create APIs","Create CRUD operations", LocalDate.of(2023,8,07));
		 
		 when(taskRepository.findById(task1.getId())).thenReturn(Optional.of(task1));
		 Tasks actualtask = taskService.getTaskById("Task1");
		 assertEquals(task1, actualtask);
		 
	 }
	 
	 
	 @Test
	 public void testGetAllTasks() {
		 
		 Tasks task1 = new Tasks("Task1","Create APIs","Create CRUD operations", LocalDate.of(2023,8,7));
		 Tasks task2 = new Tasks("Task2","Error Handling","Implement proper Error Handling", LocalDate.of(2023,8,8));
		 Tasks task3 = new Tasks("Task3","Testing","Design test cases", LocalDate.of(2023,8,9));
		 
		 List<Tasks> list = new ArrayList<>();
		 list.add(task1);
		 list.add(task2);
		 list.add(task3);
		 
		 when(taskRepository.findAll()).thenReturn(list);
		 List<Tasks> actualList = taskService.getAllTasks();
		 assertEquals(list, actualList);
	 }
	 
	 
	 @Test
	 public void testUpdateTaskById() {
		 
		   Tasks existingTask = new Tasks("Task1", "Create APIs", "Create CRUD operations", LocalDate.of(2023, 8, 7));
		    Tasks updatedTask = new Tasks("Task1", "Design APIs", "Updated Description", LocalDate.of(2023, 8, 8));

		    
		    when(taskRepository.findById(existingTask.getId())).thenReturn(Optional.of(existingTask));

		   
		    when(taskRepository.save(existingTask)).thenReturn(updatedTask);

		    Tasks modifiedTask = taskService.updateTaskById(existingTask.getId(), updatedTask);

		    assertEquals(updatedTask, modifiedTask);
		}
	 
	 
	 @Test
	 public void testDeleteTaskById() {
		 
		 Tasks task1 = new Tasks("Task1","Create APIs","Create CRUD operations", LocalDate.of(2023,8,07));
		 
		 when(taskRepository.save(task1)).thenReturn(task1);
		 
		    taskRepository.deleteById(task1.getId());

		    Tasks actualTask = taskRepository.findById(task1.getId()).orElse(null);

		    assertNull(actualTask);
	 }
	 
}