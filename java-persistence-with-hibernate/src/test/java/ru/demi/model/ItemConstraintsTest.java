package ru.demi.model;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.*;

public class ItemConstraintsTest {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldReturnNotNullConstraint() {
        Date future = Date.from(Instant.now().plus(Duration.ofDays(2)));
        Item item = new Item(null, future);

        Set<ConstraintViolation<Item>> constraintViolations = validator.validate(item);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Item> constraintViolation = constraintViolations.iterator().next();
        assertEquals("name", constraintViolation.getPropertyPath().iterator().next().getName());
        assertEquals("должно быть задано", constraintViolation.getMessage());
    }

    @Test
    public void shouldReturnSizeConstraint() {
        Date future = Date.from(Instant.now().plus(Duration.ofDays(2)));
        Item item = new Item("n", future);

        Set<ConstraintViolation<Item>> constraintViolations = validator.validate(item);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Item> constraintViolation = constraintViolations.iterator().next();
        assertEquals("name", constraintViolation.getPropertyPath().iterator().next().getName());
        assertEquals("размер должен быть между 2 и 255", constraintViolation.getMessage());
    }

    @Test
    public void shouldReturnFutureConstraint() {
        Date past = Date.from(Instant.now().minus(Duration.ofDays(2)));
        Item item = new Item("some", past);

        Set<ConstraintViolation<Item>> constraintViolations = validator.validate(item);

        assertEquals(1, constraintViolations.size());
        ConstraintViolation<Item> constraintViolation = constraintViolations.iterator().next();
        assertEquals("auctionEnd", constraintViolation.getPropertyPath().iterator().next().getName());
        assertEquals("должно быть в будущем", constraintViolation.getMessage());
    }
}