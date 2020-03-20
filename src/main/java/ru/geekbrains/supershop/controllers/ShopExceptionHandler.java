package ru.geekbrains.supershop.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;

import ru.geekbrains.supershop.exceptions.InternalServerError;
import ru.geekbrains.supershop.exceptions.ProductNotFoundException;

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class ShopExceptionHandler {

    // TODO ДЗ - оформить страницу ошибки 404

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProductNotFoundException.class)
    public String handleProductNotFoundException(final ProductNotFoundException ex) {
//        log.error("Product not found thrown", ex);
        return "error";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public String handleInvalidUUIDParameter(final ConstraintViolationException ex, Model model) {
        ex.printStackTrace();
        model.addAttribute("error", ex.toString());
        return "invaliduuid";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerError.class)
    public String handleInvalidUUIDParameter(final InternalServerError ex) {
        return "error";
    }
}