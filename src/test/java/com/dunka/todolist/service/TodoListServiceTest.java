package com.dunka.todolist.service;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class TodoListServiceTest {
    private static TodoListService todoListService;
    private static TodoListRepository todoListRepository;
    private List<Todo> todos = new ArrayList<>();
    private Todo todo;
    private Todo todo2;
    private Todo todo3;

    @BeforeAll
    static void init() {
        todoListRepository = mock(TodoListRepository.class);
        todoListService = new TodoListService(todoListRepository);
    }

    @BeforeEach
    void initData() {
        todo = new Todo(1, "Kiki", false);
        todo2 = new Todo(2, "lili", true);
        todo3 = new Todo(3, "lala", false);
        todos.add(todo);
        todos.add(todo2);
        todos.add(todo3);
    }

    @Test
    void should_return_get_all_todo_when_get_given_none() {
        given(todoListRepository.findAll()).willReturn(todos);
        List<Todo> foundTodos= todoListService.findAll();
        assertIterableEquals(todos,foundTodos);
    }

    @Test
    void should_return_todo_when_post_given_todo() {
        given(todoListRepository.save(todo )).willReturn(todo );
        Todo savedTodo = todoListService.save(todo);
        assertEquals(todo,savedTodo);
    }

    @Test
    void should_return_todo_when_put_given_id_and_todo() {
        Integer id = 1;
        given(todoListRepository.findById(id)).willReturn(Optional.of(todo ));
        given(todoListRepository.save(todo2)).willReturn(todo2);
        Todo putTodo = todoListService.putTodo(id,todo2);
        assertEquals(todo2,putTodo);
    }
    @Test
    void should_return_todo_when_delete_given_id() {
        Integer id = 1;
//        given(todoListRepository.findById(id)).willReturn(Optional.of(todo ));
        doAnswer(invocation -> null).when(todoListRepository).deleteById(id);
        todoListService.deleteById(id);
        verify(todoListRepository).deleteById(id);
    }
}
