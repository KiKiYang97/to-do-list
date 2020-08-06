package com.dunka.todolist.controller;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @GetMapping
    public List<Todo> findAllTodoList() {
        return todoListService.findAll();
    }

    @PostMapping
    public Todo addTodo(@RequestBody Todo todo) {
        return todoListService.save(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@RequestBody Todo newTodo,@PathVariable Integer id) {
        return todoListService.putTodo(id,newTodo);
    }

    @DeleteMapping("/{id}")
    public String deleteTodo(@PathVariable Integer id) {
        return todoListService.deleteById(id);
    }
}
