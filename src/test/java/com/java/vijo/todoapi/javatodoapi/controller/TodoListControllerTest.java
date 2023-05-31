package com.java.vijo.todoapi.javatodoapi.controller;

import com.java.vijo.todoapi.javatodoapi.database.TodoListRepository;
import com.java.vijo.todoapi.javatodoapi.database.TodoRepository;
import com.java.vijo.todoapi.javatodoapi.model.TodoListObject;
import com.java.vijo.todoapi.javatodoapi.model.TodoObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoListControllerTest {

    private final TodoListController todoListController = new TodoListController();

    //Get Tests
    @Test
    void get_Existent_TodoListID_Returns_OK() {
        //Arrange
        TodoListObject todoList = TodoListRepository.createTodoList(new TodoListObject("someOtherTitle"));

        //Act
        @SuppressWarnings("unchecked")
        ResponseEntity<Optional<TodoListObject>> res = (ResponseEntity<Optional<TodoListObject>>) todoListController.getTodoList(todoList.getId().toString());

        //Assert
        assertEquals(Objects.requireNonNull(res.getBody()).orElse(null), todoList);
    }

    @Test
    void get_NonExistent_TodoID_Returns_NoContent() {
        //Arrange

        //Act
        ResponseEntity<?> res = todoListController.getTodoList(UUID.randomUUID().toString());

        //Assert
        assertThat(res)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }

    //Create Tests
    @Test
    void create_Todo_Returns_Created() {
        //Arrange
        TodoListObject todoList = new TodoListObject("someTitle");

        //Act
        ResponseEntity<TodoListObject> res = todoListController.createTodoList(todoList);

        //Assert
        assertThat(res)
                .usingRecursiveComparison()
                .ignoringFields("body.id")
                .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todoList));
    }

    //Update Tests
    @Test
    void update_Existent_Todo_Returns_Created() {
        //Arrange
        TodoListObject todoList = TodoListRepository.createTodoList(new TodoListObject("someTitle"));
        todoList.setTitle("someOtherTitle");

        //Act
        ResponseEntity<TodoListObject> res = todoListController.updateTodoList(todoList);

        //Assert
        assertThat(res)
                .usingRecursiveComparison()
                .ignoringFields("body.id")
                .isEqualTo(ResponseEntity.status(HttpStatus.CREATED).body(todoList));
    }

    @Test
    void update_NonExistent_Todo_Returns_NoContent() {
        //Arrange

        //Act
        ResponseEntity<TodoListObject> res = todoListController.updateTodoList(new TodoListObject("someOtherTitle"));

        //Assert
        assertThat(res)
                .usingRecursiveComparison()
                .isEqualTo(ResponseEntity.status(HttpStatus.NO_CONTENT).body(null));
    }
}