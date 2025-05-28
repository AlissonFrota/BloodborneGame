package Entitys;

public class LevelStatsTable {
    private final double[][] stats;

    public LevelStatsTable(double[][] statsMatrix) {
        this.stats = statsMatrix;
    }
    public double getHealth(int level) {
        return stats[level][2];
    }
    public double getStamina(int level){
        return stats[level][3];
    }
    public double getDiscovery(int level){
        return stats[level][7];
    }
    public double getDefence(int level){
        return stats[level][4];
    }
    public double getSlowPoisonResist(int level){
        return stats[level][5];
    }
    public double getRapidPoisonResist(int level){
        return stats[level][6];
    }
    public double getFrenzyResist(int level){
        return stats[level][8];
    }
    public double getBeastHood(int level){
        return stats[level][9];
    }
}
