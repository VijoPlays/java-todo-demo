package com.java.vijo.todoapi.javatodoapi.database;

import com.java.vijo.todoapi.javatodoapi.model.TodoListObject;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for TodoLists.
 */
public final class TodoListRepository {

    /**
     * InMemory store of the TodoListObjects.
     */
    private static final List<TodoListObject> todoLists = new ArrayList<>();

    /**
     * Retrieves a TodoListObject from the database.
     *
     * @param id: The ID of the TodoListObject to be retrieved.
     * @return returns either an empty Object (TodoListObject not found), or the TodoListObject.
     */
    public static Optional<TodoListObject> getTodoList(UUID id) {
        return todoLists.stream().filter(todoList -> todoList.getId().equals(id)).findFirst();
    }

    /**
     * Creates a new TodoListObject, based on the provided data and adds it to the database.
     * 
     * @param todoList The TodoListObject to be created. Ignores the provided ID.
     * @return returns the created TodoListObject.
     */
    public static TodoListObject createTodoList(TodoListObject todoList) {
        TodoListObject newTodo = new TodoListObject(todoList.getTitle(), todoList.getTodos());
        todoLists.add(newTodo);

        return newTodo;
    }

    /**
     * Updates an existing TodoListObject, based on the provided data.
     * 
     * @param todoList The TodoListObject to be updated. The provided ID is used to find the object, the other data is used to update it.
     * @return returns the updated TodoListObject, or null (if none could be found).
     */
    @Nullable
    public static TodoListObject updateTodoList(TodoListObject todoList) {
        for (int i = 0; i < todoLists.size(); i++) {
            if(todoLists.get(i).getId() == todoList.getId()) {
                TodoListObject newTodoList = new TodoListObject(todoList.getTitle(), todoList.getTodos());

                //Replace TodoListObject
                todoLists.remove(todoLists.get(i));
                todoLists.add(newTodoList);

                return newTodoList;
            }
        }
        return null;
    }
}
