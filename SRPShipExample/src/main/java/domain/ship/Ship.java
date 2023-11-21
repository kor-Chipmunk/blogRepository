package domain.ship;

public final class Ship {
    private final FuelTank fuelTank;
    private final SupplyHold supplyHold;
    private final CrewManager crewManager;
    private final Engine engine;

    public Ship(
            final FuelTank fuelTank,
            final SupplyHold supplyHold,
            final CrewManager crewManager,
            final Engine engine
    ) {
        validateFuelTank(fuelTank);
        validateSupplyHold(supplyHold);
        validateCrewManager(crewManager);
        validateEngine(engine);

        this.fuelTank = fuelTank;
        this.supplyHold = supplyHold;
        this.crewManager = crewManager;
        this.engine = engine;
    }

    private void validateFuelTank(final FuelTank fuelTank) {
        if (fuelTank == null) {
            throw new IllegalArgumentException("FuelTank 객체는 null 이 될 수 없습니다.");
        }
    }

    private void validateSupplyHold(final SupplyHold supplyHold) {
        if (supplyHold == null) {
            throw new IllegalArgumentException("SupplyHold 객체는 null 이 될 수 없습니다.");
        }
    }

    private void validateCrewManager(final CrewManager crewManager) {
        if (crewManager == null) {
            throw new IllegalArgumentException("CrewManager 객체는 null 이 될 수 없습니다.");
        }
    }

    private void validateEngine(final Engine engine) {
        if (engine == null) {
            throw new IllegalArgumentException("Engine 객체는 null 이 될 수 없습니다.");
        }
    }

    public void report() {
        fuelTank.report();
        supplyHold.report();
        crewManager.report();
    }
}
