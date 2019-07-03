package calculator;

import calculator.domain.Tokens;
import calculator.utils.StringUtils;

public class Calculator {

    public int process(String input) {

        validateInput(input);
        Tokens tokens = tokenize(input);
        return tokens.calculate();
    }

    Tokens tokenize(String input) {

        return Tokens.of(input);
    }

    private static void validateInput(String input) {

        if (input == null || StringUtils.isConsistOnlyWhiteSpace(input)) {
            throw new IllegalArgumentException("입력값이 유효하지 않습니다.");
        }
    }
}
