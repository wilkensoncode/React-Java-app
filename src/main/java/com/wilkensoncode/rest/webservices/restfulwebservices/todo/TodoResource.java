package com.wilkensoncode.rest.webservices.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class TodoResource {

    @Autowired
    private TodoHardcodedService todoService;

    //GET all todos for a user
    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoService.findAll();
    }

    //GET a todoById
    @GetMapping("/users/{username}/todos/{id}")
    public  Todo getTodo(@PathVariable String username,
                         @PathVariable long id) {

        return todoService.findById(id);
    }

    //Update a todo
    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,
                                           @PathVariable long id,
                                           @RequestBody Todo todo){

        Todo updateTodo = todoService.save(todo);
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    //Add a todo
    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Todo> postTodo(@PathVariable String username,
                                           @RequestBody Todo todo){

        Todo createdTodo = todoService.save(todo);

        URI uri= ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/{id}")
                    .buildAndExpand(createdTodo.getId())
                    .toUri();

        return  ResponseEntity.created(uri).build();
    }

    //DELETE a todoById
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
                                           @PathVariable long id){

        Todo todo = todoService.deleteById(id);
        if (todo != null) {

            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
