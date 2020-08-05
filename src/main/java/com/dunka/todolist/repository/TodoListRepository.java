package com.dunka.todolist.repository;

import com.dunka.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListRepository extends JpaRepository<Todo,Integer> {
}
