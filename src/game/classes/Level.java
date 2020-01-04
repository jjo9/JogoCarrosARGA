/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

import game.Contracts.LevelContract;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author isabe
 */
public class Level implements LevelContract {

    /**
     * Nome do nível
     */
    private String name;
    /**
     * Valores que tracam o lado da pista
     */
    private double[] upperBound;
    /**
     * Valores que tracam o outro lado da pista
     */
    private double[] lowerBound;
    /**
     * Valores que tracam as varias bandeirinhas da pista
     */
    private double[] checkpoints;
    /**
     * Valores que traçam a linha de inicio
     */
    private double[] startCar;
    /**
     * Path do Mapa
     */
    private String caminhoMapa;
    /**
     * Path da Imagem do Mapa
     */
    private String caminhoImagem;

    public Level(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String string) {
        this.name = string;
    }

    public double[] g(JSONArray j) {
        double[] temp = new double[j.size()];
        for (int i = 0; i < j.size(); i++) {
            temp[i] = (double) j.get(i);
        }
        return temp;
    }

    @Override
    public boolean mappingBounds(String string) throws IOException {
        boolean re = false;

        //System.out.println("Working Directory = "+System.getProperty("user.dir")); //usado para ver a diretoria atual
        try {
            FileReader reader = new FileReader(string);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //System.out.println("tree"+jsonObject.toJSONString()); //usado para imprimir a cena toda
            long t = (long) jsonObject.get("Level");
            this.name = String.valueOf(t);

            JSONArray UpperBound = (JSONArray) jsonObject.get("UpperBound");
            this.upperBound = this.g(UpperBound);

            JSONArray LowerBound = (JSONArray) jsonObject.get("LowerBound");
            this.lowerBound = this.g(LowerBound);

            JSONArray Checkpoints = (JSONArray) jsonObject.get("Checkpoints");
            this.checkpoints = this.g(Checkpoints);

            JSONArray StartCar = (JSONArray) jsonObject.get("StartCar");
            this.startCar = this.g(StartCar);

            re = true;

        } catch (IOException | ParseException e) {
            System.out.println("erro ao ler o ficheiro de mapa!");
        }
        return re;
    }

    @Override
    public String getPathToImage() {
        return this.caminhoImagem;
    }

    @Override
    public void setPathToImage(String string) {
        this.caminhoImagem = string;
    }

    @Override
    public double[] getLowerBounds() {
        return this.lowerBound;
    }

    @Override
    public double[] getUpperBounds() {
        return this.upperBound;
    }

    @Override
    public double[] getCheckPoints() {
        return this.checkpoints;
    }

    @Override
    public double[] getStartCar() {
        return this.startCar;
    }

}
