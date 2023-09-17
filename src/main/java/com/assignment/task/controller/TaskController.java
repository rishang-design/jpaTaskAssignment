package com.assignment.task.controller;


import com.assignment.task.Repository.TaskRepo;
import com.assignment.task.entity.Task;
import com.assignment.task.Exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/")
public class TaskController {
	
	
    //implemented the repository
    @Autowired
    private TaskRepo taskRepository ;
    //list of all task
    @GetMapping("/task")
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }
    //get task by id
    @GetMapping("/task/{id}")
    public Optional<Task> getTaskById(@PathVariable ("id") Integer id){
        return taskRepository.findById(id);

    }


    @PostMapping("/task")
    public Task addTask(@RequestBody Task task){
        return taskRepository.save(task);
    }

    @PutMapping("/task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id,@RequestBody Task updateTask){
    	
    	Task orElseThrow = this.taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
    //	System.out.println(orElseThrow);
        Task task=taskRepository.findById(id).orElse(null);
        if(task==null){
            return ResponseEntity.notFound().build();
        }

        task.setStatus(updateTask.getStatus());
        task.setDescription(updateTask.getDescription());
        task.setTitle(updateTask.getTitle());


        taskRepository.save(task);
        return ResponseEntity.ok(task);
    }

        @DeleteMapping("task/{id}")
        public Task deleteTask(@PathVariable("id") int id){
        	
        	this.taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
            this.taskRepository.deleteById(id);

            return null;
        }

}
