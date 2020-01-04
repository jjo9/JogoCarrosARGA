/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.classes;

import game.collections.ListManagementContract;

public class ClassificationManagement implements ListManagementContract, game.collections.ClassificationManagementContract {

    private Classificacao[] classificacao;
    private int count;

    public ClassificationManagement() {
        this.classificacao = new Classificacao[0];
    }

    
    /*
    
        int[] novo;
        novo = new int[1];
        
        System.out.println("->>"+novo.length);
        novo[0] = 20;
        System.out.println("->>"+novo[0]);
    
    */
    @Override

    public boolean addObject(Object o) {
        /*
        if (o != null) {
            this.classificacao[this.count] = (Classificacao) o;
            this.count++;
            return true;
        }
        return false;
        */
        
        boolean re = false;
        
        if(o != null){
            this.increaseSize();
            this.classificacao[this.classificacao.length-1] = (Classificacao) o;
            re = true;
        }
        
        return re;
    }

    @Override
    public Object removeObject(int i) {
        /*
        if (i >= 0) {
            
            for (int ii = i; ii < this.count-1; ii++) {
                this.classificacao[ii] = this.classificacao[ii + 1];
            }
            this.count--;
            return true;
        }
        return false;
        */
        
        Object re = null;
        
        if(this.classificacao.length > 0){
            re = this.classificacao[i];
            this.classificacao[i]=null;
            this.decreaseSize();
        }
        
        return re;
    }

    @Override
    public Object getObject(int i) {
        return this.classificacao[i];
    }

    @Override
    public int findObject(Object o) {
        for (int ii = 0; ii < this.count; ii++) {
            if (this.classificacao[ii] == (Classificacao) o) {
                return ii;
            }
        }
        return -1;
    }

    public Classificacao[] getClassificacao() {
        return this.classificacao;
    }

    
    @Override
    public int size() {
        /*if(this.classificacao == null){
            return 0;
        }*/
        return this.classificacao.length;
    }
    
    @Override
    public void sort() {
        // estou aqui usar o compare to :P
        Classificacao temp;
        
        for (int i = 0; i < this.size(); i++) {
            for (int j = 1; j < (this.size() - i); j++) {

                if (this.classificacao[j - 1].compareTo(this.classificacao[j]) < 0) {
                    temp = this.classificacao[j - 1];
                    this.classificacao[j - 1] = this.classificacao[j];
                    this.classificacao[j] = temp;
                }

            }
        }
    }
        
    

    @Override
    public void increaseSize() {
        Classificacao[] temp = new Classificacao[this.size()+1];
        if(this.classificacao.length != 0){
            //System.arraycopy(this.classificacao, 0, temp, 0,this.size()+1);
            for(int x= 0;x<this.classificacao.length;x++){
                temp[x]=this.classificacao[x];
            }
        }
        
        //this.classificacao = new Classificacao[this.size()+1];
        
        //temp[temp.length-1]=null;
        this.classificacao = temp;
    }

    @Override
    public void decreaseSize() {
        Classificacao[] temp = new Classificacao[this.classificacao.length-1];
        int f = 0;
        for(int x = 0;x<this.size();x++){
            
            if(this.classificacao[x] == null){
                f++;
            }
            temp[x] = this.classificacao[x+f];
        }
        this.classificacao = temp;
    }
    
    public void removeLast(){
        Classificacao[] temp = new Classificacao[this.classificacao.length-1];
        
        for(int x = 0;x<temp.length;x++){
            temp[x] = this.classificacao[x];
        }
        this.classificacao = temp;
    }

}
