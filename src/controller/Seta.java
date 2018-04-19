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

    void addValue(String[] elements) {
        values.add(new Value(elements));
    }

    Object getName() {
        return this.name;
    }
}
