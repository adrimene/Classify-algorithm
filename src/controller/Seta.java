/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

public class Seta {
    
    private String name;
    private ArrayList<ArrayList<String>> values = new ArrayList<>();
    private int NParameters;
    
    public Seta(String[] elements) {
        this.name = elements[elements.length-1];
        this.values = new ArrayList<>();
        this.NParameters = elements.length-1;
    }

    public void addValue(String[] elements) {
        ArrayList<String> value = new ArrayList<>();
        for(int i = 0; i < elements.length-1; i++) {
            value.add(elements[i]);
        }
        values.add(value);
    }

    public String getName() {
        return this.name;
    }
    
    public int getMuestras() {
        return this.values.size();
    }

    public int getNParameters() {
        return this.NParameters;
    }

    public ArrayList<String> getValue(int idx) {
        return this.values.get(idx);
    }
}
