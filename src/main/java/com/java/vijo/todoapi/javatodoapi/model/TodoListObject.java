package com.java.vijo.todoapi.javatodoapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A list containing multiple TodoObjects.
 */
@Getter
@Setter
public class TodoListObject {

    /**
     * The ID of the object.
     */
    private final UUID id = UUID.randomUUID();
    /**
     * The title of the object that the user can see.
     */
    private String title;
    /**
     * The IDs of the TodoObjects that are a part of this list.
     */
    private List<UUID> todos;

    /**
     * Constructor.
     *
     * @param title: The title of the TodoObject.
     * @param todos: A list of Todos.
     */
    public TodoListObject(String title, List<UUID> todos) {
        this.title = title;
        this.todos = todos;
    }

    /**
     * Default constructor.
     * Initializes todos as an empty Arraylist.
     *
     * @param title: The title of the TodoListObject.
     */
    public TodoListObject(String title) {
        this(title, new ArrayList<>());
    }
}
