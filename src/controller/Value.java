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
public class Value {

    private ArrayList<String> parameter;
    
    public Value(String[] elements) {
        this.parameter = new ArrayList<>();
        for(int i = 0; i < elements.length-1; i++) {
            this.parameter.add(elements[i]);
        }
    }

    double[] sumParameters(double[] solution) {
        for(int i = 0; i < parameter.size(); i++) {
           solution[i] += Double.parseDouble(parameter.get(i));
        }
        return solution;
    }

    int getSize() {
        return parameter.size();
    }
}
