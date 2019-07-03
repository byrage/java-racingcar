package calculator.domain;

import calculator.enums.OperationSymbol;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static calculator.utils.StringUtils.isConsistOnlyWhiteSpace;

public class Tokens {

    private static final int MIN_TOKENS_SIZE = 3;
    private List<Token> tokens;

    private Tokens(List<Token> tokens) {
        validateTokenSize(tokens);
        validateSymbol(tokens);

        this.tokens = tokens;
    }

    public static Tokens of(List<Token> tokens) {

        return new Tokens(tokens);
    }

    public static Tokens of(String input) {

        return Tokens.of(IntStream.range(0, input.length())
                                 .mapToObj(index -> String.valueOf(input.charAt(index)))
                                 .filter(s -> !isConsistOnlyWhiteSpace(s))
                                 .map(Token::of)
                                 .collect(Collectors.toList()));
    }

    private void validateTokenSize(List<Token> tokens) {

        if (tokens.size() < MIN_TOKENS_SIZE || !isOddNumber(tokens.size())) {
            throw new IllegalArgumentException("연산자의 개수가 유효하지 않습니다.");
        }
    }

    private void validateSymbol(List<Token> tokens) {

        IntStream.range(0, tokens.size())
                .filter(this::isOddNumber)
                .mapToObj(tokens::get)
                .forEach(OperationSymbol::from);
    }

    private boolean isOddNumber(int number) {

        return number % 2 == 1;
    }

    public int calculate() {

        int result = Integer.valueOf(tokens.get(0).getToken());

        for (int i = 1; i < tokens.size(); i += 2) {
            result = calculate(tokens.get(i), result, Integer.valueOf(tokens.get(i + 1).getToken()));
        }

        return result;
    }

    private int calculate(Token symbol, int num1, int num2) {

        return OperationSymbol.from(symbol).apply(num1, num2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tokens tokens1 = (Tokens) o;
        return Objects.equals(tokens, tokens1.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }
}
