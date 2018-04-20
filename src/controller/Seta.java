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
public class Seta {
    
    private String name;
    private ArrayList<Value> values = new ArrayList<>();
    
    public Seta(String[] elements) {
        this.name = elements[elements.length-1];
        addValue(elements);
    }

    public void addValue(String[] elements) {
        values.add(new Value(elements));
    }

    public String getName() {
        return this.name;
    }
    
    public int getMuestras() {
        return this.values.size();
    }

    public int getNParameters() {
        return this.values.get(0).getSize();
    }

    double[] centrosIniciales() {
        double[] solution = new double[getNParameters()];
        for(int i = 0; i < solution.length; i++)
            solution[i] = 0;
        for(Value v: values) {
            solution = v.sumParameters(solution);
        }
        for(int i = 0; i < solution.length; i++)
            solution[i] = solution[i] / values.size();
        return solution;
    }
}
