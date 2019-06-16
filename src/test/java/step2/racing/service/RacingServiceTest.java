package step2.racing.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import step2.racing.dto.RacingInfo;
import step2.racing.dto.RacingResult;
import step2.racing.random.StubRandomGenerator;

import static org.assertj.core.api.Assertions.assertThat;

class RacingServiceTest {

    private RacingService racingService;
    private StubRandomGenerator stubRandomGenerator = new StubRandomGenerator(0);

    @ParameterizedTest(name = "레이싱 후에 결과가 정상적으로 저장된다. (carCount={0}, attempts={0})")
    @CsvSource({"3, 6",
            "4, 3",
            "2, 2"})
    void run(int carCount, int attempts) {

        RacingInfo racingInfo = RacingInfo.of(carCount, attempts);
        racingService = new RacingService(racingInfo, stubRandomGenerator);

        RacingResult racingResult = racingService.run();

        assertThat(racingResult.length()).isEqualTo(attempts);
        assertThat(racingResult.getCarPosition(0).sizeOfCars()).isEqualTo(carCount);
    }
}