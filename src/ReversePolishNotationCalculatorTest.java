import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReversePolishNotationCalculatorTest {
    static ReversePolishNotationCalculator reversePolishNotationCalculator;

    @BeforeAll
    public static void beforeAll() {
        reversePolishNotationCalculator = new ReversePolishNotationCalculator();
    }

    @Test
    public void shouldCalculateAddition() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("2 3 +");

        assertEquals(5, result);
    }

    @Test
    public void shouldCalculateSubtraction() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("3 2 -");

        assertEquals(1, result);
    }

    @Test
    public void shouldCalculateMultiplication() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("2 3 *");

        assertEquals(6, result);
    }

    @Test
    public void shouldCalculateAdditionWithExtraSpaces() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("2    3   *");

        assertEquals(6, result);
    }

    @Test
    public void shouldCalculateMultiplicationWithNegativeNumbers() {
        int result = reversePolishNotationCalculator.calculatePolishNotation("-5 -4 *");

        assertEquals(20, result);
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}