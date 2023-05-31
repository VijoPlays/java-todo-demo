package com.java.vijo.todoapi.javatodoapi.controller;

import com.java.vijo.todoapi.javatodoapi.database.TodoRepository;
import com.java.vijo.todoapi.javatodoapi.model.TodoObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller for TodoObjects.
 */
@RestController()
public class TodoController {

    /**
     * Attempts to retrieve a TodoObject.
     *
     * @param id: The ID of the object.
     * @return returns the following codes:
     *  - 200: If a TodoObject could be found.
     *  - 204: If no TodoObject could be found.
     *  - 400: If the provided ID was not of the correct format.
     */
    @GetMapping("/todo")
    public ResponseEntity<?> getTodo(@RequestParam("id") String id) {
        try {
            Optional<TodoObject> todo = TodoRepository.getTodo(UUID.fromString(id));

            if(todo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.ok(todo);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("The provided ID was not of UUID format!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a new TodoObject.
     *
     * @param todoObject: Receives a TodoObject to create.
     * @return returns 201 and the TodoObject that was just created.
     */
    @PostMapping("/todo")
    public ResponseEntity<TodoObject> createTodo(@RequestBody TodoObject todoObject) {
        TodoObject createdTodo = TodoRepository.createTodo(todoObject);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodo);
    }

    /**
     * Attempts to update an existing TodoObject.
     *
     * @param todoObject: Receives a TodoObject to update.
     * @return returns the following codes:
     *  - 200: If a TodoObject could be found.
     *  - 204: If no TodoObject could be found.
     */
    @PatchMapping("/todo")
    public ResponseEntity<TodoObject> updateTodo(@RequestBody TodoObject todoObject) {
        TodoObject updatedTodo = TodoRepository.updateTodo(todoObject);

        if(updatedTodo == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(todoObject);
        }
    }
}
