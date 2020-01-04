/*
 * FALTA ACABAR ESTE A CLASSE re TEM ALGUMAS COISAS QUE PODEM SER UTILIZADAS
 */
package game.classes;

import game.Contracts.ClassificationContract;
import game.collections.ClassificationManagementContract;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.json.simple.parser.ParseException;

public class RaceResults extends RaceResultsAbstract {

    private ClassificationManagement classificationManagement;
    private final int SIZE = 100;
    private String nomeNivel;  // nome do nivel

    /*
     por que é que tem isto ? para a tabela final acho que só precisamos de
    ter 7 posicoes não é preciso gravar os que ficam de fora
     */
    public RaceResults() {
        this.classificationManagement = new ClassificationManagement();

    }

    public String getNomeNivel() {
        return nomeNivel;
    }

    public void setNomeNivel(String nomeNivel) {
        this.nomeNivel = nomeNivel;
    }
    
    @Override
    public ClassificationManagementContract getClassificationManagementContract() {
        this.classificationManagement.sort();
        //System.out.println("size-->"+this.classificationManagement.size());
        if(this.classificationManagement.size()> 7){
            this.classificationManagement.removeLast();
        }
        return (ClassificationManagementContract) this.classificationManagement;
    }

    @Override
    public boolean saveResultsToFile() throws IOException, ParseException {

        boolean re = false;
        /*
        o processo de gravar será o seguinte:
        imaginemos que temos um array com os resultados
        este tem que estar ordenado
        depois passamos por todas as posicoes ate um maximo de 7
        e vamos insrindo no obj json
        depois é que gravamos
         */

 /**/
        JSONObject obj = new JSONObject();
        JSONArray results = new JSONArray();
        /*tudo isto terá um for para poder inserir todos
        NOTA!: que temos que fazer new da positionSetails e linha pois
        estes nao podem ter informacao do anterior (o obj é o final
        e o resutls é o array em si por isso tambem tem de estar fora para nao o
        apagarmos)*/
        for (int x = 0; x < this.classificationManagement.getClassificacao().length; x++) {

            JSONObject positionDetails = new JSONObject();
            JSONObject linha = new JSONObject();

            positionDetails.put("Vehicle", this.classificationManagement.getClassificacao()[x].getVehicle().getName());
            positionDetails.put("PilotName", this.classificationManagement.getClassificacao()[x].getPilot().getName());
            positionDetails.put("PilotId", this.classificationManagement.getClassificacao()[x].getPilot().getId());
            //System.out.println("sfHAHDH " + this.classificationManagement.getClassificacao()[x].getBestLap());
            positionDetails.put("BestLap", this.classificationManagement.getClassificacao()[x].getBestLap());
            positionDetails.put("TotalLaps", this.classificationManagement.getClassificacao()[x].getTotalLaps());
            positionDetails.put("BestTimes", this.classificationManagement.getClassificacao()[x].getTotalTime());

            linha.put("Position", x + 1);
            linha.put("positionDetails", positionDetails);

            results.add(linha);

            if (x == 6) {
                break;
            }
        }

        obj.put("Results", results);
        

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("gameResults\\result" + this.getNomeNivel()+ ".txt")) {    //    MUDAR ESTE AQUI
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            //System.out.println("\nJSON Object: " + obj);
            re = true;
        } catch (IOException ex) {
            System.out.println("erro ao gravar resultados");
        }

        return re;

    }

    public Object[] g(JSONArray j) {
        Object[] temp = new Object[j.size()];
        for (int i = 0; i < j.size(); i++) {
            temp[i] = (Object) j.get(i);
        }
        return temp;
    }

    /**
     *
     * @return
     */
    @Override
    public ClassificationManagementContract loadResultsFromFile() {

        ClassificationManagementContract re = new ClassificationManagement();

        try {
            FileReader reader = new FileReader("gameResults\\result" + this.getNomeNivel() + ".txt");
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            //System.out.println("tree" + jsonObject.toJSONString()); //usado para imprimir a cena toda

            JSONArray Results = (JSONArray) jsonObject.get("Results");

            //System.out.println("---->" + Results);

            for (int x = 0; x < this.g(Results).length; x++) {

                //System.out.println("AAAAAAAAAAAaa" + this.g(Results).length);

                //System.out.println(".-.-." + ((JSONObject) this.g(Results)[x]).get("Position"));
                JSONObject positionDetailsJSON = (JSONObject) ((JSONObject) this.g(Results)[x]).get("positionDetails");

                //System.out.println(".-.-." + positionDetailsJSON.get("Vehicle"));
                VehicleAbstract veiculoTemp = new Veiculo((String) positionDetailsJSON.get("Vehicle"));

                //System.out.println("---->" + positionDetailsJSON.get("TotalLaps"));

                Classificacao claTemp;

                claTemp = new Classificacao((int) (long) positionDetailsJSON.get("TotalLaps"));
                claTemp.setBestLap((double) positionDetailsJSON.get("BestLap"));
                //System.out.println("safda " + claTemp.getBestLap());
                claTemp.setVehicle(veiculoTemp);
                claTemp.setTotalTime((double) positionDetailsJSON.get("BestTimes"));

                Piloto piltoTemp = new Piloto((int) (long) positionDetailsJSON.get("PilotId"), (String) positionDetailsJSON.get("PilotName"));

                claTemp.setPilot(piltoTemp);

                re.addObject(claTemp);

                /*
                System.out.println(".-.-." + positionDetailsJSON.get("PilotName"));
                System.out.println(".-.-." + positionDetailsJSON.get("PilotId"));
                System.out.println(".-.-." + positionDetailsJSON.get("BestLap"));
                System.out.println(".-.-." + positionDetailsJSON.get("TotalLaps"));
                System.out.println(".-.-." + positionDetailsJSON.get("BestTimes"));
                */
            }

        } catch (IOException | ParseException e) {
            System.out.println("erro ao ler o ficheiro de Resultados!");
        }

        return re;
    }

    /**
     * Metodo para colocar a negrito a classificacao da corrida atual
     *
     * @return
     */
    @Override
    public ClassificationContract mapLoadingResults() { // funciona ?
        return this.classificationManagement.getClassificacao()[this.classificationManagement.getClassificacao().length - 1];
    }

    @Override
    public void addClassification(ClassificationContract cc) {
        this.classificationManagement.addObject((ClassificationContract) cc);
    }

}
