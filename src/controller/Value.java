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
public class Value extends ArrayList<Value> {

    ArrayList<String> parameter = new ArrayList<>();
    
    public Value(String[] elements) {
        for(int i = 0; i < elements.length-1; i++) {
            this.parameter.add(elements[i]);
        }
    }
    
}
