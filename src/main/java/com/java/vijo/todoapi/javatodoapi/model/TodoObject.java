package com.java.vijo.todoapi.javatodoapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * A TodoObject describing a task a user wants to do.
 */
@Setter
@Getter
public class TodoObject {

    /**
     * The ID of the object.
     */
    private final UUID id = UUID.randomUUID();
    /**
     * The title of the object that the user can see.
     */
    private String title;
    /**
     * Whether this task is marked as done or not.
     */
    private boolean done;

    /**
     * Constructor.
     *
     * @param title: The title of the TodoObject.
     * @param done: Whether the TodoObject is done or not.
     */
    public TodoObject(String title, boolean done) {
        this.title = title;
        this.done = done;
    }

    /**
     * Default constructor.
     * Sets done to 'false'.
     *
     * @param title: The title of the TodoObject.
     */
    public TodoObject(String title) {
        this(title, false);
    }
}
