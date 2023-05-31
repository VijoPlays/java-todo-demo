package com.java.vijo.todoapi.javatodoapi.database;

import com.java.vijo.todoapi.javatodoapi.model.TodoObject;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository class for Todos.
 */
public final class TodoRepository {

    /**
     * InMemory store of the TodoObjects.
     */
    private static final List<TodoObject> todos = new ArrayList<>();

    /**
     * Retrieves a TodoObject from the database.
     *
     * @param id: The ID of the TodoObject to be retrieved.
     * @return returns either an empty Object (TodoObject not found), or the TodoObject.
     */
    public static Optional<TodoObject> getTodo(UUID id) {
        return todos.stream().filter(todo -> todo.getId().equals(id)).findFirst();
    }

    /**
     * Creates a new TodoObject, based on the provided data and adds it to the database.
     *
     * @param todoObject The TodoObject to be created. Ignores the provided ID.
     * @return returns the created TodoObject.
     */
    public static TodoObject createTodo(TodoObject todoObject) {
        TodoObject newTodo = new TodoObject(todoObject.getTitle(), todoObject.isDone());
        todos.add(newTodo);

        return newTodo;
    }

    /**
     * Updates an existing TodoObject, based on the provided data.
     *
     * @param todoObject The TodoObject to be updated. The provided ID is used to find the object, the other data is used to update it.
     * @return returns the updated TodoObject, or null (if none could be found).
     */
    @Nullable
    public static TodoObject updateTodo(TodoObject todoObject) {
        for (int i = 0; i < todos.size(); i++) {
            if(todos.get(i).getId() == todoObject.getId()) {
                TodoObject newTodo = new TodoObject(todoObject.getTitle(), todoObject.isDone());

                //Replace TodoObject
                todos.remove(todos.get(i));
                todos.add(newTodo);

                return newTodo;
            }
        }
        return null;
    }
}
