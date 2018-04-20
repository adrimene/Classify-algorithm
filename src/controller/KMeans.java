/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

/**
 *
 * @author Adrián-Trabajo
 */
public class KMeans {

    private ArrayList<Seta> setas;
    private int NMuestras = 0;
    private double allowence = 0.01;
    private int exp = 2;
    private double[][] v;
    
    KMeans(ArrayList<Seta> lista_setas) {
        this.setas = lista_setas;
        int val = this.setas.get(0).getNParameters();
        v = new double[setas.size()][val];
        for(int i = 0; i < lista_setas.size(); i++) {
            this.NMuestras += lista_setas.get(i).getMuestras();
            v[i] = lista_setas.get(i).centrosIniciales();
        }
    }
    
}
