package com.dunka.todolist.service;

import com.dunka.todolist.exception.NoSuchDataException;
import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {
    public static final String DELETE_FAIL = "DELETE_FAIL";
    public static final String DELETE_SUCCESS = "DELETE_SUCCESS";
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

    public Todo putTodo(Integer id, Todo todo2) throws NoSuchDataException {
        todo2.setId(id);
        Todo todo = todoListRepository.findById(id).orElse(null);
        if (todo == null) {
            throw new NoSuchDataException();
        } else {
            if(todo2.getContent()!=null) todo.setContent(todo2.getContent());
            if(todo2.getStatus()!=null) todo.setStatus(todo2.getStatus());
        }
        return todoListRepository.save(todo);
    }

    @Transactional
    public String deleteById(Integer id) {
        todoListRepository.deleteById(id);
        Optional<Todo> todo = todoListRepository.findById(id);
        if (todo == null || !todo.isPresent()) {
            return DELETE_SUCCESS;
        } else {
            return DELETE_FAIL;
        }
    }
}
