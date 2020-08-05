package com.dunka.todolist.service;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Todo> findAll() {
        return todoListRepository.findAll();
    }

    public Todo save(Todo todo) {
        return null;
    }
}
