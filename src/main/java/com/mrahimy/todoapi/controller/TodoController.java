package com.mrahimy.todoapi.controller;

import com.mrahimy.todoapi.model.Todo;
import com.mrahimy.todoapi.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonMerge;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoRepository todoRepository;

	@GetMapping
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		return todoRepository.findById(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody @JsonMerge Todo updatedTodo){

		return todoRepository.findById(id)
			.map(e -> {
				e.setTitle(updatedTodo.getTitle());
				e.setDescription(updatedTodo.getDescription());
				e.setCompleted(updatedTodo.isCompleted());
				return ResponseEntity.ok(todoRepository.save(e));
			})
		.orElse(ResponseEntity.notFound().build());
	}

}
