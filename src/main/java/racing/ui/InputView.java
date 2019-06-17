package racing.ui;

import racing.inputter.Inputter;
import racing.util.StringUtil;

import java.io.PrintStream;
import java.util.List;

public class InputView {

    private static final String ASK_CAR_NAMES_MESSAGE = "경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).";
    private static final String ASK_ATTEMPTS_MESSAGE = "시도할 회수는 몇 회 인가요?";

    private static final String CAR_NAMES_DELIMITER = ",";

    private final Inputter inputter;
    private final PrintStream printStream;

    public InputView(Inputter inputter) {
        this.inputter = inputter;
        this.printStream = System.out;
    }

    public List<String> askCarNames() {

        printAskMessage(ASK_CAR_NAMES_MESSAGE);
        return StringUtil.split(inputter.getStringValue(), CAR_NAMES_DELIMITER);
    }

    public int askAttempts() {

        printAskMessage(ASK_ATTEMPTS_MESSAGE);
        return inputter.getIntValue();
    }

    private void printAskMessage(String message) {

        printStream.println(message);
    }
}
