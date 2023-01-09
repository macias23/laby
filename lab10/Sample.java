import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Sample {
    public int divide(int a, int b) throws ArithmeticException {
        return a / b; }
    public double divide(double a, double b) {
        return a / b;
    }
    private static Stream<Arguments> numbers() {
        return Stream.of(arguments(20,2),arguments(20,5),arguments(20,4),arguments(20,1));
    }
    private static Stream<Arguments> doubles() {
        return Stream.of(arguments(20.5,2.1),arguments(23.7,3.0),arguments(15.4,4),arguments(20,1));
    }
    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () -> divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }
    @ParameterizedTest
    @MethodSource({"numbers"})
    void divideInt(int i,int y) {
        assertEquals(i/y,divide(i,y));
    }

    @ParameterizedTest
    @MethodSource({"doubles"})
    void divideDoubles(double i, double y) {
        assertEquals(i/y,divide(i,y));
    }
}