package domain.ship;

public class Engine {
    private final FuelTank fuelTank;
    private final int fuelConsumptionAmountPerHour;

    public Engine(
            final FuelTank fuelTank,
            final int fuelConsumptionAmountPerHour
    ) {
        validateFuelTank(fuelTank);
        validateFuelConsumptionAmountPerHour(fuelConsumptionAmountPerHour);

        this.fuelTank = fuelTank;
        this.fuelConsumptionAmountPerHour = fuelConsumptionAmountPerHour;
    }

    private void validateFuelConsumptionAmountPerHour(final int fuelConsumptionAmountPerHour) {
        if (fuelConsumptionAmountPerHour < 0) {
            throw new IllegalArgumentException("시간당 소비하는 연료량은 음수가 될 수 없습니다.");
        }
    }

    private void validateFuelTank(final FuelTank fuelTank) {
        if (fuelTank == null) {
            throw new IllegalArgumentException("연료 창고는 null 이 될 수 없습니다.");
        }
    }

    public void runForHours(final int hours) {
        validateHours(hours);

        final int consumeFuelAmount = fuelConsumptionAmountPerHour * hours;

        if (!isAvailableRunning(consumeFuelAmount)) {
            System.out.println("연료가 부족하기 때문에 엔진 작동을 시작할 수 없습니다.");
            return;
        }

        fuelTank.consume(consumeFuelAmount);
        System.out.println("엔진을 %d시간 동안 돌립니다!".formatted(hours));
    }

    private void validateHours(final int hours) {
        if (hours < 0) {
            throw new IllegalArgumentException("시간은 음수가 될 수 없습니다.");
        }
    }

    private boolean isAvailableRunning(final int consumeFuelAmount) {
        return fuelTank.getFuelAmount() >= consumeFuelAmount;
    }
}
