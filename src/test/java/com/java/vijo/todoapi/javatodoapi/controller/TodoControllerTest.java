package com.java.vijo.todoapi.javatodoapi.controller;

import com.java.vijo.todoapi.javatodoapi.database.TodoRepository;
import com.java.vijo.todoapi.javatodoapi.model.TodoObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodoControllerTest {

    private final TodoController todoController = new TodoController();

    //Get Tests
    @Test
    void get_Existent_TodoID_Returns_OK() {
        //Arrange
        TodoObject todo = TodoRepository.createTodo(new TodoObject("someOtherTitle"));

        //Act
        @SuppressWarnings("unchecked")
        ResponseEntity<Optional<TodoObject>> res = (ResponseEntity<Optional<TodoObject>>) todoController.getTodo(todo.getId().toString());

        //Assert
        assertEquals(Objects.requireNonNull(res.getBody()).orElse(null), todo);
    }

    @Test
    void get_NonExistent_TodoID_Returns_NoContent() {
        //Arrange

        //Act
        ResponseEntity<?> res = todoController.getTodo(UUID.randomUUID().toString());

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    //Create Tests
    @Test
    void create_Todo_Returns_Created() {
        //Arrange
        TodoObject todo = new TodoObject("someTitle");

        //Act
        ResponseEntity<TodoObject> res = todoController.createTodo(todo);

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todo));
    }

    @Test
    void get_Invalid_TodoID_Returns_BadRequest() {
        //Arrange

        //Act
        ResponseEntity<?> res = todoController.getTodo("someInvalidID");

        //Assert
        assertEquals(HttpStatus.BAD_REQUEST, res.getStatusCode());
    }

    //Update Tests
    @Test
    void update_Existent_Todo_Returns_Created() {
        //Arrange
        TodoObject todo = TodoRepository.createTodo(new TodoObject("someOtherTitle"));
        todo.setDone(true);

        //Act
        ResponseEntity<TodoObject> res = todoController.updateTodo(todo);

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .ignoringFields("body.id")
            .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todo));
    }

    @Test
    void update_NonExistent_Todo_Returns_NoContent() {
        //Arrange

        //Act
        ResponseEntity<TodoObject> res = todoController.updateTodo(new TodoObject("someOtherTitle"));

        //Assert
        assertThat(res)
            .usingRecursiveComparison()
            .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }
}