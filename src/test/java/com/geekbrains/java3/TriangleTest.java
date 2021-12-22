package com.geekbrains.java3;

import HomeWork4.NotTriangle;
import HomeWork4.TriangleSquare;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;

public class TriangleTest {

/* private static Logger logger = (Logger) LoggerFactory.getLogger(TriangleSquare.class);

    @BeforeAll
    public void beforeAll() {
       logger.info("Метод выполнился 1 раз, перед всеми тестами");
       logger.trace("trace level log");
       logger.error("error log message");

    }*/
// c логгером тест всегда падает :-( поэтому строки выше закомментированы
    @Test
    public void exceptionNotTriangle() {
     assertThatExceptionOfType(NotTriangle.class).isThrownBy(() -> TriangleSquare.squareIsValid(13, 4, 5));
    }

}
