package com.dunka.todolist.integration;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoListRepository todoListRepository;

    private List<Todo> todoList;
    private Todo todo;
    private Integer id;

    @AfterEach
    void tearDown() {
        todoListRepository.deleteAll();
    }

    @BeforeEach
    void initData() {
        todoList = new ArrayList<>();
        todo = new Todo("111", true);
        Todo todo2 = new Todo("222");
        Todo todo3 = new Todo("333");
        todoList.add(todo);
        todoList.add(todo2);
        todoList.add(todo3);
    }

    @Test
    void should_get_todo_list_when_hit_get_employee_end_point_given_nothing() throws Exception {
        todoListRepository.saveAll(todoList);
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].content").value("111"))
                .andExpect(jsonPath("$[0].status").value(true));
    }
}