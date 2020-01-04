/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

import game.Contracts.PilotContract;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Veiculo extends game.classes.VehicleAbstract {

    /**
     * Nome
     */
    private String name;
    /**
     * Peso
     */
    private double w;
    /**
     * Altura
     */
    private double h;
    /**
     * Tipo
     */
    private String type;
    /**
     * Modelo
     */
    private String model;
    /**
     * Limites
     */
    private double[] bounds;
    /**
     * Travao
     */
    private double bBreak;
    /**
     * Piloto
     */
    private Piloto piloto;
    /**
     * Path do JSON
     */
    private String caminhoVeiculo;
    /**
     * Path da imagem
     */
    private String caminhoImagem;
    /**
     *
     *
     */
    private double speedv;
    private double directionv;

    // ja vinha que se podia ter estes dois construtores
    public Veiculo(String name) {
        super(name);

    }

    public Veiculo(String name, double w, double h) {
        super(name, w, h);
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getW() {
        return this.w;
    }

    @Override
    public double getH() {
        return this.h;
    }

    /**
     * Método responsável por retornar o travão do veículo
     *
     * @return Travao do veiculo
     */
    @Override
    public double getBreakPadVehicle() {
        return this.bBreak;
    }

    @Override
    public double getSpeedVehicle() {
        return this.speedv;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPilot(PilotContract pilot) { //isto é possivel ou defino piloto com a class PilotContract ???
        this.piloto = (Piloto) pilot;

    }

    @Override
    public void setW(double w) {
        this.w = w;
    }

    @Override
    public void setH(double h) {
        this.h = h;
    }

    @Override
    public PilotContract getPilot() {
        return this.piloto;
    }

    @Override
    public int getDirectionVehicle() {
        return (int) this.directionv;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Nome: ").append(this.name);
        str.append("\nPeso:").append(this.w);
        str.append("\nAltura: ").append(this.h);
        str.append("\nTipo: ").append(this.type);
        str.append(
                "\nModelo:").append(this.model);
        str.append("\nBounds: ");
        for (int i = 0; i
                < this.bounds.length;
                i++) {
            str.append(this.bounds[i]).append(", ");
        }
        str.append("\nSpeed: ").append(this.speed);
        str.append("\nDirecao:").append(this.getDirectionVehicle());
        str.append("\nBreak: ").append(this.bBreak);
        str.append("\nPathToVeiculo: ").append(this.caminhoVeiculo);
        str.append("\nPathToImage: ").append(this.caminhoImagem);

        return str.toString();
    }

    public double[] g(JSONArray j) {
        double[] temp = new double[j.size()];
        for (int i = 0; i < j.size(); i++) {
            temp[i] = (double) j.get(i);
        }
        return temp;
    }

    @Override
    public boolean mappingBounds(String file) {
        boolean re = false;

        //System.out.println("Working Directory = "+System.getProperty("user.dir")); //usado para ver a diretoria atual
        try {

            FileReader reader = new FileReader(file);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //System.out.println("tree"+jsonObject.toJSONString()); //usado para imprimir a cena toda
            this.type = (String) jsonObject.get("Type");
            this.model = (String) jsonObject.get("Model");

            JSONArray Bounds = (JSONArray) jsonObject.get("Bounds");
            this.setBounds(g(Bounds));

            this.speedv = ((double) jsonObject.get("Speed"));
            this.directionv = (long) jsonObject.get("Direction");
            this.bBreak = ((double) jsonObject.get("Break"));

            //this.setBounds(this.bounds);
            re = true;

        } catch (IOException | ParseException e) {
            System.out.println("erro ao ler o ficheiro de mapa!");
        }
        return re;
    }
}
