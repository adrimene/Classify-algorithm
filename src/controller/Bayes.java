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
public class Bayes {
    
    private ArrayList<Seta> setas;
    private int NMuestras;
    private ArrayList<ArrayList<Float>> centros;
    
    Bayes(ArrayList<Seta> lista_setas) {
        setas = lista_setas;
        NMuestras = 0;
        
        for(int i = 0; i < lista_setas.size(); i++) {
            NMuestras += lista_setas.get(i).getMuestras();
        }
        centros = new ArrayList();
    }
    
    public void entrenamiento() {
        centros = calcM();
        
        /*boolean itera;
        int laps = -1;
        float[][] p = new float[setas.get(0).getMuestras()*setas.size()][setas.size()];
        do {
            // Recalcula las pertencias
            int count = 0;
            for(int i = 0; i < this.setas.size(); i++) {
                for(int j = 0; j < this.setas.get(i).getMuestras(); j++) {
                    p[count] = calcP(this.setas.get(i).getValue(j));
                    count++;
                }
            }
            // Recalcula los centros y calcula la diferencia
            float[] diferencia = ajustaCentros(p);
            
            // Si la diferencia < tolerancia.
            itera = false;
            for(int i = 0; i < diferencia.length; i++) {
                if(diferencia[i] > this.tolerancia)
                    itera = true;
            }
        } while (itera);*/
    }
    
    public String clasificacion(ArrayList<String> prueba) {
        String res = "";
        
        float[] d = calcD(prueba);
        
        int idx = 0;
        for(int i = 1; i < d.length; i++) {
            if(d[i] < d[idx])
                idx = i;
        }
        
        res = "Según el algoritmo de Bayes la seta es de la clase " + this.setas.get(idx).getName() + "\nLas distancias son:\n";
        
        for(int i = 0; i < d.length; i++) {
            res += "Clase " + this.setas.get(i).getName() + ": " + d[i] + "\n";
        }
        
        return res;
    }

    private ArrayList<ArrayList<Float>> calcM() {
        ArrayList<ArrayList<Float>> m = new ArrayList();
        for(int i = 0; i < setas.size(); i++){
            m.add(new ArrayList());
            for(int j = 0; j < setas.get(i).getNParameters(); j++)
                m.get(i).add((float) 0);
        }
        for(int i = 0; i < setas.size(); i++)
            for(int j = 0; j < setas.get(i).getMuestras(); j++)
                for(int k = 0; k < setas.get(i).getNParameters(); k++)
                    m.get(i).set(k, (Float.parseFloat(setas.get(i).getValue(j).get(k))/setas.get(i).getMuestras())+m.get(i).get(k));
        return m;
    }
    
    private float[] calcD(ArrayList<String> value) {
        float[] d = new float[setas.size()];
        
        for(int i = 0; i < d.length; i++) {
            float res = 0;
            for (int j = 0; j < this.centros.size(); j++) {
                res += Math.pow(Float.parseFloat(value.get(j))-this.centros.get(i).get(j), 2);
            }
            d[i] = (float) Math.sqrt(res);
        }
        
        return d;
    }
 /*   
    private float[] ajustaCentros(float[][] p) {
        float[][] v = new float[this.v.length][this.v[0].length];
        float[] auxDivisor = new float[this.v.length];
        float[] diferencia = new float[this.v.length];
        int count;
        for(int i = 0; i < v.length; i++) {
            count = 0;
            auxDivisor[i] = 0;
            for(int l = 0; l < this.v[0].length; l++) {
                v[i][l] = 0;
            }
            for(int j = 0; j < this.setas.size(); j++) {
                for(int k = 0; k < this.setas.get(j).getMuestras(); k++) {
                    for(int l = 0; l < this.setas.get(j).getNParameters(); l++) {
                        v[i][l] += Math.pow(p[count][i], b) * Float.parseFloat(this.setas.get(j).getValue(k).get(l));
                    }
                    auxDivisor[i] += (Math.pow(p[count][i], b));
                    count++;
                }
            }
            for(int l = 0; l < this.v[0].length; l++) {
                v[i][l] = v[i][l] / auxDivisor[i];    
            }
            diferencia[i] = calcularDiferencia(this.v[i], v[i]);
        }
        this.v = v;
        return diferencia;
    }

    private float calcularDiferencia(float[] oV, float[] nV) {
        float aux = 0;
        for(int i = 0; i < oV.length; i++) {
            aux += Math.pow((nV[i]-oV[i]), 2);
        }
        return (float) Math.sqrt(aux);
    }*/
}
