package ru.geekbrains.supershop.validation;

import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@Slf4j
public class UUIDValidator implements ConstraintValidator<ValidUUID, String> {
    @Override
    public boolean isValid(final String uuidstring, final ConstraintValidatorContext context) {
        try{
            UUID uuid = UUID.fromString(uuidstring);
            return true;
        }catch (IllegalArgumentException e){
            log.error("UUID not valid " + uuidstring);
            return false;
        }

    }
}
