package com.java.vijo.todoapi.javatodoapi.controller;

import com.java.vijo.todoapi.javatodoapi.database.TodoListRepository;
import com.java.vijo.todoapi.javatodoapi.model.TodoListObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Controller for TodoListObjects.
 */
@RestController()
public class TodoListController {

    /**
     * Attempts to retrieve a TodoListObject.
     *
     * @param id: The ID of the object.
     * @return returns the following codes:
     *  - 200: If a TodoListObject could be found.
     *  - 204: If no TodoListObject could be found.
     *  - 400: If the provided ID was not of the correct format.
     */
    @GetMapping("/todoList")
    public ResponseEntity<?> getTodoList(@RequestParam("id") String id) {
        try {
            Optional<TodoListObject> todoList = TodoListRepository.getTodoList(UUID.fromString(id));

            if (todoList.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.ok(todoList);
            }
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>("The provided ID was not of UUID format!", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Creates a new TodoListObject.
     *
     * @param todoList: Receives a TodoListObject to create.
     * @return returns 201 and the TodoListObject that was just created.
     */
    @PostMapping("/todoList")
    public ResponseEntity<TodoListObject> createTodoList(@RequestBody TodoListObject todoList) {
        TodoListObject createdTodoList = TodoListRepository.createTodoList(todoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoList);
    }

    /**
     * Attempts to update an existing TodoListObject.
     *
     * @param todoList: Receives a TodoListObject to update.
     * @return returns the following codes:
     *  - 200: If a TodoListObject could be found.
     *  - 204: If no TodoListObject could be found.
     */
    @PatchMapping("/todoList")
    public ResponseEntity<TodoListObject> updateTodoList(@RequestBody TodoListObject todoList) {
        TodoListObject updatedTodoList = TodoListRepository.updateTodoList(todoList);

        if(updatedTodoList == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(updatedTodoList);
        }
    }
}
