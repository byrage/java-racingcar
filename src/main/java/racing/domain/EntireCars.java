package racing.domain;

import racing.random.ValueGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EntireCars {

    private List<Car> cars = new ArrayList<>();

    private EntireCars() {}

    public static EntireCars of(List<String> carNames) {

        if (carNames == null || carNames.isEmpty()) {
            throw new IllegalArgumentException("차량명은 1개 이상이어야 합니다.");
        }

        EntireCars entireCars = new EntireCars();
        entireCars.cars = carNames.stream().map(Car::of).collect(Collectors.toList());
        return entireCars;
    }

    public void race(ValueGenerator valueGenerator) {

        cars.forEach(car -> car.race(valueGenerator.generateIntValue()));
    }

    public List<Car> getCars() {

        return cars;
    }
}
