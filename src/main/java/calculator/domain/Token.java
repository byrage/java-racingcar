package calculator.domain;

import java.util.Objects;

public class Token {

    private String token;

    public static Token of(String tokenValue) {

        if(tokenValue == null || tokenValue.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        Token token = new Token();
        token.token = String.valueOf(tokenValue);
        return token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token1 = (Token) o;
        return Objects.equals(token, token1.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }
}
