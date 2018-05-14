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
    private int NMuestras;
    private float tolerancia;
    private int b;
    private float[][] v;
    private float[][] d;
    
    KMeans(ArrayList<Seta> lista_setas) {
        setas = lista_setas;
        int val = setas.get(0).getNParameters();
        v = new float[setas.size()][val];
        
        NMuestras = 0;
        for(int i = 0; i < lista_setas.size(); i++) {
            NMuestras += lista_setas.get(i).getMuestras();
        }
        
        tolerancia = (float) 0.01;
        b = 2;
        
        v[0][0] = (float) 4.6;
        v[0][1] = (float) 3;
        v[0][2] = (float) 4;
        v[0][3] = (float) 0;
        v[1][0] = (float) 6.8;
        v[1][1] = (float) 3.4;
        v[1][2] = (float) 4.6;
        v[1][3] = (float) 0.7;
    }
    
    public void entrenamiento() {
        boolean itera;
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
        } while (itera);
    }
    
    public String clasificacion(ArrayList<String> prueba) {
        String res = "";
        
        float[] p = calcP(prueba);
        
        int idx = 0;
        for(int i = 1; i < p.length; i++) {
            if(p[i] > p[idx])
                idx = i;
        }
        
        res = "Según el programa la seta es de la clase " + this.setas.get(idx).getName() + "\nLas pertenencias son:\n";
        
        for(int i = 0; i < p.length; i++) {
            res += "Clase " + this.setas.get(i).getName() + ": " + p[i] + "\n";
        }
        
        return res;
    }

    private float[] calcP(ArrayList<String> value) {
        float[] p = new float[setas.size()];
        float[] d = new float[setas.size()];
        float auxD = 0;
        int exp = 1/(b-1);
        
        for(int i = 0; i < v.length; i++) {
            d[i] = calcD(this.v[i], value);
            auxD += Math.pow((1/d[i]), exp);
        }
        
        
        for(int i = 0; i < v.length; i++) {
            p[i] = ((float) Math.pow((1/d[i]), exp) / (auxD));
        }
        
        return p;
    }

    private float calcD(float[] v, ArrayList<String> value) {
        float res = 0;
        for (int i = 0; i < v.length; i++) {
            res += Math.pow(Float.parseFloat(value.get(i))-v[i], 2);
        }
        return res;
    }
    
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
    }
}
