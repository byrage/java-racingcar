package calculator;

import calculator.domain.Token;
import calculator.domain.Tokens;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class CalculatorTest {

    private Calculator calculator = new Calculator();

    @ParameterizedTest(name = "계산. {0} = {1}")
    @CsvSource(value = {
            "2 + 3 = 5",
            "2 + 3 * 4 / 2 + 5 * 9 - 8 + 2 = 129"
    }, delimiter = '=')
    void process(String input, int result) {

        assertThat(calculator.process(input)).isEqualTo(result);
    }

    @ParameterizedTest(name = "계산 시 예외발생 케이스 검증. input={0}, exceptionMessage={1}")
    @CsvSource(value = {
            "2, 연산자의 개수가 유효하지 않습니다.",
            "2 + 3 * 4 /, 연산자의 개수가 유효하지 않습니다.",
            "      , 입력값이 유효하지 않습니다.",
    })
    void processException(String input, String exceptionMessage) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> calculator.process(input))
                .withMessage(exceptionMessage);
    }

    @ParameterizedTest(name = "토큰화할때 공백 제외. input={0}")
    @MethodSource
    void tokenizeInput(String input, Tokens tokens) {

        assertThat(calculator.tokenize(input)).isEqualTo(tokens);
    }

    private static Stream<Arguments> tokenizeInput() {

        Tokens tokens = Tokens.of(Arrays.asList(Token.of("2"), Token.of("+"), Token.of("3"), Token.of("*"), Token.of("4"), Token.of("/"), Token.of("2")));

        return Stream.of(
                Arguments.of("2 + 3 * 4 / 2", tokens),
                Arguments.of("2 +      3 * 4 / 2", tokens),
                Arguments.of("     2 + 3 * 4 / 2", tokens));
    }
}
