package step2.racing.model;

import step2.racing.random.RandomGenerator;

public class Car {

    private int position;

    /**
     * 차량번호
     */
    private final int number;
    private final RandomGenerator randomGenerator;

    public static final int DEFAULT_POSITION = 1;
    private static final int BOUND = 10;
    private static final int MOVE_VALUE = 4;

    private Car(int number, RandomGenerator randomGenerator) {

        this.position = DEFAULT_POSITION;

        this.number = number;
        this.randomGenerator = randomGenerator;
    }

    public static Car of(int number, RandomGenerator randomGenerator) {

        return new Car(number, randomGenerator);
    }

    public void race() {

        int randomInt = randomGenerator.getRandomIntValue(BOUND);
        if (isMove(randomInt)) {
            position++;
        }
    }

    private boolean isMove(int randomInt) {

        return randomInt >= MOVE_VALUE;
    }

    public int checkPosition() {

        return position;
    }
}
