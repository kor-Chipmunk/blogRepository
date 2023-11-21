package domain.ship;

public class SupplyHold {
    private final CrewManager crewManager;
    private int suppliesAmount;

    public SupplyHold(
            final CrewManager crewManager,
            final int suppliesAmount
    ) {
        validateCrewManager(crewManager);
        validateSuppliesAmount(suppliesAmount);

        this.crewManager = crewManager;
        this.suppliesAmount = suppliesAmount;
    }

    private void validateCrewManager(final CrewManager crewManager) {
        if (crewManager == null) {
            throw new IllegalArgumentException("CrewManager 객체는 null 이 될 수 없습니다.");
        }
    }

    private void validateSuppliesAmount(final int suppliesAmount) {
        if (suppliesAmount < 0) {
            throw new IllegalArgumentException("물자량은 음수가 될 수 없습니다.");
        }
    }

    public void report() {
        System.out.println("현재 물자는 %d명 분이 남아 있습니다.".formatted(suppliesAmount));
    }

    public void load(final int loadSuppliesAmount) {
        suppliesAmount += loadSuppliesAmount;
    }

    public void allocateToCrew() {
        if (isLackOfSuppliesAmount()) {
            System.out.println("물자가 부족하기 때문에 배분할 수 없습니다.");
            return;
        }

        suppliesAmount -= crewManager.getCrewCount();
    }

    private boolean isLackOfSuppliesAmount() {
        return suppliesAmount < crewManager.getCrewCount();
    }
}
