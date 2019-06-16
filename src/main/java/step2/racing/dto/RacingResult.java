package step2.racing.dto;

import java.util.ArrayList;
import java.util.List;

public class RacingResult {

    private List<CarPosition> carPositions = new ArrayList<>();

    public CarPosition getCarPosition(int raceCount) {

        return carPositions.get(raceCount);
    }

    public void add(CarPosition carPosition) {

        carPositions.add(carPosition);
    }

    public int length() {

        return carPositions.size();
    }

}
