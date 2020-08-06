package com.dunka.todolist.service;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.springframework.beans.BeanUtils;
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
        return todoListRepository.save(todo);
    }

    public Todo putTodo(Integer id, Todo todo2) {
        Todo todo = todoListRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(todo2,todo);
        return todoListRepository.save(todo);
    }

    public String deleteById(Integer id) {
        todoListRepository.deleteById(id);
        return null;
    }
}
