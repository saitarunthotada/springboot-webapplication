package com.springboot.webapplication.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandling  {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handlerDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException)
    {
        ModelAndView model = new ModelAndView();
        model.addObject("error", "Duplicate entry: Username or email is already exists");
        model.setViewName("error");
        return model;
    }
}
