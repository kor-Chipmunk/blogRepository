package domain.ship;

public class CrewManager {
    private int crewCount;

    public CrewManager(final int crewCount) {
        validateCrewCount(crewCount);

        this.crewCount = crewCount;
    }

    private void validateCrewCount(final int crewCount) {
        if (crewCount < 0) {
            throw new IllegalArgumentException("선원들의 수는 음수가 될 수 없습니다.");
        }
    }

    public void report() {
        System.out.println("현재 선원 %d명이 있습니다.".formatted(crewCount));
    }

    public void load(final int loadCrewCount) {
        crewCount += loadCrewCount;
    }

    public int getCrewCount() {
        return crewCount;
    }
}
