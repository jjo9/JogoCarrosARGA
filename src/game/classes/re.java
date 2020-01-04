/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

import game.Contracts.ClassificationContract;
import game.Contracts.PilotContract;
import game.Contracts.RaceResultsComparator;

/**
 *
 * @author isabe
 */
public class re implements ClassificationContract {

    private double bestLap;
    private int totalLaps;
    private double totalTime;
    private double[] timesALL;

    private String level;
    private Piloto piloto;
    private Veiculo veiculo;

    public re(int totalLaps, String level, Piloto piloto, Veiculo veiculo) {
        this.totalLaps = totalLaps;
        this.level = level;
        this.piloto = piloto;
        this.veiculo = veiculo;

        this.timesALL = new double[totalLaps];
    }

    @Override
    public String getLevel() {
        return this.level;
    }

    @Override
    public void setLevel(String string) { //especifica o nivel da classificacao
        this.level = string;
    }

    @Override
    public void setTotalLaps(int i) {
        this.totalLaps = i;
    }

    @Override
    public PilotContract getPilot() {
        return this.piloto;
    }

    @Override
    public int getTotalLaps() {
        return this.totalLaps;
    }

    @Override
    public void setPilot(PilotContract pc) {
        this.piloto = (Piloto) pc;
    }

    @Override
    public VehicleAbstract getVehicle() {
        return this.veiculo;
    }

    @Override
    public void setVehicle(VehicleAbstract va) {
        this.veiculo = (Veiculo) va;
    }

    @Override
    public double getBestLap() {

        double bestLap = this.timesALL[0];

        for (int x = 1; x < this.totalLaps; x++) {
            if (this.timesALL[x] < bestLap) {
                bestLap = this.timesALL[x];
            }
        }

        return bestLap;
    }

    @Override
    public void addLap(double d) {
        int i = 0;
        while (this.timesALL[i] != 0) {
            i++;
        }
        this.timesALL[i] = d;
    }

    @Override
    public double getTotalTime() {
        return this.totalTime;
    }

    @Override
    public void setTotalTime(double d) {
        this.totalTime = d;
    }

    @Override
    public int compareTo(RaceResultsComparator rrc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
