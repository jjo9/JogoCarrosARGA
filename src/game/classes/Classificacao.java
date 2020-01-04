/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

import game.Contracts.PilotContract;
import game.Contracts.RaceResultsComparator;

/**
 *
 * @author isabe
 */
public class Classificacao implements game.Contracts.ClassificationContract {

    private String leverl;
    private int TotalLaps;
    private PilotContract piloto;
    private VehicleAbstract vehicle;
    private double bestLap;
    private double[] lap;
    private double totaltime;
    private int count;

    Classificacao(int total) {
        this.lap = new double[total];
        this.TotalLaps = total;
        this.count = 0;
    }

    @Override
    public String getLevel() {
        return this.leverl;
    }

    @Override
    public void setLevel(String string) {
        this.leverl = string;
    }

    @Override
    public void setTotalLaps(int i) {
        this.TotalLaps = i;
    }

    @Override
    public PilotContract getPilot() {
        return this.piloto;
    }

    @Override
    public int getTotalLaps() {
        return this.TotalLaps;
    }

    @Override
    public void setPilot(PilotContract pc) {
        this.piloto = pc;
    }

    @Override
    public VehicleAbstract getVehicle() {
        return this.vehicle;
    }

    @Override
    public void setVehicle(VehicleAbstract va) {
        this.vehicle = va;
    }

    @Override
    public double getBestLap() {
        if (this.lap[0] == 0) {
            return this.bestLap;
        } else {
            double t = this.lap[0];

            for (int i = 1; i < this.TotalLaps; i++) {
                if (this.lap[i] < t) {
                    t = this.lap[i];
                }
            }

            this.bestLap = t;
            return this.bestLap;
        }
    }

    @Override
    public void addLap(double d) {// acho que o erro está aqui

        this.lap[this.count] = d;
        this.count++;

    }

    @Override
    public double getTotalTime() {
        return this.totaltime;
    }

    @Override
    public void setTotalTime(double d) {
        this.totaltime = d;
    }

    @Override
    public int compareTo(RaceResultsComparator rrc) {

        int re = 0;

        //a comparacao é feita atravez do melhor tempo !
        /*
        superior a 0 se o objeto a comparar for superior a obj inferior a 0 se o objeto a comparar for inferior a obj 0 se os dois objetos forem iguais
         */
 
        if(this.bestLap < ((Classificacao)rrc).getBestLap()){
            re = 1;
        }else if(this.bestLap > ((Classificacao)rrc).getBestLap()  ){
            re = -1;
        }

         
        return re;
    }

    public void setBestLap(double bestLap) {
        this.bestLap = bestLap;
    }

}
