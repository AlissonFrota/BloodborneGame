package Entitys;

public class LevelStatsTable {
    private final double[][] stats;

    public LevelStatsTable(double[][] statsMatrix) {
        this.stats = statsMatrix;
    }

    public double getHealth(int level) {
        return stats[level][2];
    }
}
