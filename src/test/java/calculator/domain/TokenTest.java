package calculator.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class TokenTest {

    @Test
    void of() {

        assertThat(Token.of("1").getToken()).isEqualTo("1");

    }

    @ParameterizedTest
    @NullAndEmptySource
    void ofException(String input) {

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Token.of(input));
    }
}