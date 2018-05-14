/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adrián-Trabajo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Seta> lista_setas = new ArrayList<>();
        String file = "";
        
        try {
            System.out.println("Introduzca el nombre del fichero con los datos (con la extensión .txt) de los atributos");
            file = br.readLine();
            
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = in.readLine();
            while(line != null) {
                String[] elements = line.split(",");
                boolean exists = false;
                for(int i = 0; i < lista_setas.size(); i++) {
                    if(elements[elements.length-1].equals(lista_setas.get(i).getName())) {
                        exists = true;
                        lista_setas.get(i).addValue(elements);
                    }
                }
                
                if(!exists) {
                    Seta s = new Seta(elements);
                    s.addValue(elements);
                    lista_setas.add(s);
                }
                line = in.readLine();
            }
            in.close();
            
            KMeans KmAlgorithm = new KMeans(lista_setas);
            
            KmAlgorithm.entrenamiento();
            
            System.out.println("Ahora se especificarán 3 ficheros con pruebas de setas \n");
            for(int i = 0; i < 3; i++) {
                System.out.println("Introduzca el nombre de una prueba (con .txt) de los ejemplos");
                file = br.readLine();

                in = new BufferedReader(new FileReader(file));
                line = in.readLine();
                String[] elementos = line.split(",");
                System.out.println("La seta es de la clase " + elementos[elementos.length-1]);
                
                ArrayList<String> prueba = new ArrayList<>(Arrays.asList(elementos));
                System.out.println(KmAlgorithm.clasificacion(prueba));
            }
           
        } catch(FileNotFoundException e) {
            System.out.println("Fichero no encontrado");
        } catch (IOException ex) {
            System.out.println("No se ha leído el fichero correctamente");
        }  
        
        System.out.println("Pulse Enter para terminar...");
        
        try {
            br.readLine();
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
