package domain.ship;

public class FuelTank {
    private int fuelAmount;

    public FuelTank(final int fuelAmount) {
        validateFuelAmount(fuelAmount);

        this.fuelAmount = fuelAmount;
    }

    private void validateFuelAmount(final int fuelAmount) {
        if (fuelAmount < 0) {
            throw new IllegalArgumentException("남아있는 연료량은 음수가 될 수 없습니다.");
        }
    }

    public void report() {
        System.out.println("현재 연료는 %dL 남아 있습니다.".formatted(fuelAmount));
    }

    public void load(final int loadFuelAmount) {
        fuelAmount += loadFuelAmount;
    }

    public void consume(final int consumedFuelAmount) {
        fuelAmount -= consumedFuelAmount;
    }

    public int getFuelAmount() {
        return fuelAmount;
    }
}
