package com.dunka.todolist.config;

import com.dunka.todolist.exception.NoSuchDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String NO_SUCH_DATA = "NO_SUCH_DATA";

    @ExceptionHandler(NoSuchDataException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String NoSuchDataException() {
        return NO_SUCH_DATA;
    }
}
