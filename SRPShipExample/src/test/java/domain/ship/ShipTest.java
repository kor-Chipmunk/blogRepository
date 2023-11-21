package domain.ship;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import domain.ship.Ship;

class ShipTest {

    private PrintStream standardOut;
    private OutputStream captor;

    @BeforeEach
    void init() {
        standardOut = System.out;
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @AfterEach
    void printOutput() {
        System.setOut(standardOut);
        System.out.println(output());
    }

    String output() {
        return captor.toString();
    }

    @Test
    void integrateTest() {
        final FuelTank fuelTank = new FuelTank(400);
        final CrewManager crewManager = new CrewManager(50);
        final SupplyHold supplyHold = new SupplyHold(crewManager, 1_000);
        final Engine engine = new Engine(fuelTank, 10);

        final Ship ship = new Ship(
                fuelTank,
                supplyHold,
                crewManager,
                engine
        );

        fuelTank.load(10);
        supplyHold.load(10);
        crewManager.load(10);

        supplyHold.allocateToCrew();

        engine.runForHours(4);

        ship.report();

        assertThat(output()).contains(
                "엔진을 4시간 동안 돌립니다!",
                "현재 연료는 370L 남아 있습니다.",
                "현재 물자는 950명 분이 남아 있습니다.",
                "현재 선원 60명이 있습니다."
        );
    }
}
