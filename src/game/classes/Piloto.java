/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

public class Piloto implements game.Contracts.PilotContract, java.io.Serializable {

    /**
     * Nome do Piloto
     */
    private String name;
    /**
     * Identificação do Piloto
     */
    private int id;

    /**
     * Metodo construtor responsável por instanciar os seguinte paramentos
     *
     * @param name Nome do piloto
     * @param id Identificação do piloto
     */
    public Piloto(int id, String name) {

        this.name = name;
        this.id = id; // se tivermos tempo veificar id e nome com throw exception se nao for bom
    }

    /**
     * Metodo responsável por mostrar devolver o nome do Piloto
     *
     * @return Nome do Piloto
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Metodo modificar o nome do Piloto
     *
     * @param name Nome do Piloto
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Metodo modificar o numero de Identificação do Piloto
     *
     * @return Id do Piloto
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Metodo modificar o id do Piloto
     *
     * @param id Identificador do Piloto
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por devolver uma String com a informaçao do Piloto
     *
     * @return String - informação do piloto
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Nome: ").append(this.name);
        str.append("Id: ").append(this.id);

        return str.toString();
    }
}
