package com.dunka.todolist.integration;

import com.dunka.todolist.model.Todo;
import com.dunka.todolist.repository.TodoListRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private final Integer id = 1;
    private String todoInfo;
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
        todoInfo = "{\n" +
                "    \"content\": \"1234\",\n" +
                "    \"status\": true\n" +
                "}";
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

    @Test
    void should_post_todo_when_hit_post_todo_end_point_given_todo() throws Exception {
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoInfo)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print());

    }

    @Test
    void should_return_msg_when_hit_delete_todo_end_point_given_id() throws Exception {
        todoListRepository.saveAll(todoList);
        mockMvc.perform(delete("/todos/" + id))
                .andExpect(jsonPath("$").value("DELETE_SUCCESS"))
                .andDo(print());
    }

}
