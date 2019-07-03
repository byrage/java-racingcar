package calculator.enums;

import calculator.domain.Token;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class OperationSymbolTest {

    @ParameterizedTest(name = "연산기호 변환. `{0}`")
    @ValueSource(strings = {"+", "-", "*", "/"})
    void from(String supportedOperation) {

        assertThatCode(() -> OperationSymbol.from(Token.of(supportedOperation))).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "연산기호 변환 시 exception 발생. `{0}`")
    @ValueSource(strings = {"%", ",", ".", "&", "!"})
    void fromException(String unsupportedOperation) {

        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> OperationSymbol.from(Token.of(unsupportedOperation)));
    }
}