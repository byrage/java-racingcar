package racing.service;

import racing.dto.*;
import racing.random.RandomGenerator;
import racing.random.RealRandomGenerator;

import java.util.stream.IntStream;

public class RacingService {

    public static final int RANDOM_NUMBER_BOUND = 10;
    public static final int START_UNIQUE_CAR_NUMBER = 1;
    private static final int START_RACE_COUNT = 1;

    private final RacingInfo racingInfo;
    private final RandomGenerator randomGenerator;

    public RacingService(RacingInfo racingInfo) {

        this(racingInfo, new RealRandomGenerator(RANDOM_NUMBER_BOUND));
    }

    public RacingService(RacingInfo racingInfo, RandomGenerator randomGenerator) {

        this.racingInfo = racingInfo;
        this.randomGenerator = randomGenerator;
    }

    public RacingResult run() {

        EntireCars entireCars = EntireCars.createCars(racingInfo.getCarCount());

        return race(entireCars);
    }

    private RacingResult race(EntireCars entireCars) {

        RacingResult racingResult = new RacingResult();
        racingResult.addCurrentCarPosition(entireCars);

        IntStream.range(START_RACE_COUNT, racingInfo.getAttempts())
                .forEach(currentRaceCount -> {
                    raceEntireCars(entireCars);
                    racingResult.addCurrentCarPosition(entireCars);
                });

        return racingResult;
    }

    private void raceEntireCars(EntireCars entireCars) {

        entireCars.stream().forEach(car -> car.race(randomGenerator.getRandomIntValue()));
    }
}