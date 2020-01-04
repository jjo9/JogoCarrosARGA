/*
* Nome: José Crinsato Lomba Magalhães
* Número: 8160169
* Turma: LSIRC
*
* Nome: Maria Isabel Baptista da Silva
* Número: 8160193
* Turma: LSIRC
 */
package game.classes;

import game.Contracts.ClassificationContract;
import game.collections.ClassificationManagementContract;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

public class JogoCarrosARGA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

        System.out.println("ARGA Car Racing Started Loading !");

        RacingGame jogo = new RacingGame();

        Veiculo veiculo1 = new Veiculo("Carro Vermelho", 2.2, 3.2);
        veiculo1.setPathToImage("gameAssets\\vehicleImages\\sideCarMoto.png");
        veiculo1.mappingBounds("gameAssets\\vehicleJSON\\bike.txt");

        
        
        
        String nomeDoNivel = "level2";
        
        
        
        Level nivel1 = new Level("gameAssets\\levelImages\\"+nomeDoNivel+".png");
        nivel1.mappingBounds("gameAssets\\levelsJSON\\"+nomeDoNivel+".txt");

        Piloto piloto1 = new Piloto(233, "furia");//criar carro piloto e pista acho que tenho fazer implement dos abstratos

        veiculo1.setPilot(piloto1);

        //System.out.println("------ isto é veiculo ------");
        //System.out.println("veiculo" + veiculo1.toString());
        //System.out.println("------ isto é nivel/mapa ------");
        //System.out.println(nivel1.toString());
        int numeroDeVoltas = 1;

        jogo.addCar(veiculo1);
        jogo.addLevel(nivel1);
        jogo.addNumberOfLaps(numeroDeVoltas);

        //c.setLevel(string);
        RaceResults race = new RaceResults();
        Classificacao c = new Classificacao(numeroDeVoltas);

        
        race.setNomeNivel(nomeDoNivel);
        
        //System.out.println("------------");
        ClassificationManagementContract vv = race.loadResultsFromFile();
        vv.toString();
        ClassificationManagement name = (ClassificationManagement) vv;
        //System.out.println("------------");

        // tou aqui
        
        //vv.sort();
        for (int i = 0; i < vv.size(); i++) {
            race.addClassification((ClassificationContract) vv.getObject(i));
        }
        
        //jogo.setClassification();
        jogo.setClassification(c);

        jogo.setRaceResults(race);

        try {
            jogo.startGame();
            //race.getClassificationManagementContract().sort();
            //jogo.setRaceResults(race);
            race.saveResultsToFile();
        } catch (Exception ex) {

            System.out.println("Erro ao carregar o jogo");
            Logger.getLogger(JogoCarrosARGA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }

}
