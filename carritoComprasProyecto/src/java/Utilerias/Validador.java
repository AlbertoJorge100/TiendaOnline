/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilerias;

/**
 *
 * @author DARQ
 */
public class Validador {
    public String cod1;
    public String cod2;
    public String cod3;
    
    public int cod1CantLetras;
    public int cod2CantLetras;
    public int cod3CantLetras;
    
    public int cod1CantNumeros;
    public int cod2CantNumeros;
    public int cod3CantNumeros;
    
    public int cod1Ganador;
    public int cod2Ganador;
    public int cod3Ganador;
    
    public Validador(String cod1, String cod2, String cod3){
        this.cod1 = cod1;
        this.cod2 = cod2;
        this.cod3 = cod3;
    }
    
    public boolean Validar(boolean isLetra){
        //Obtenemos la cantidad de Letras
        this.cod1CantLetras = ContadorLetras(cod1);
        this.cod2CantLetras = ContadorLetras(cod2);
        this.cod3CantLetras = ContadorLetras(cod3);
        //Obtenemos la cantidad de Numeros
        this.cod1CantNumeros = ContadorNumeros(cod1);
        this.cod2CantNumeros = ContadorNumeros(cod2);
        this.cod3CantNumeros = ContadorNumeros(cod3);
        //determinamos el ganador
        this.determinarGanador(isLetra);
        return true;
    }
    private void determinarGanador(boolean isLetra){
        //Validamos si el ganador sera por letra
        if(isLetra){
            //Validamos las letras
            if(this.cod1CantLetras > this.cod2CantLetras ){
                if(this.cod1CantLetras > this.cod3CantLetras);
                this.cod1Ganador = 1;
            }else if(this.cod2CantLetras > this.cod3CantLetras ){
                this.cod2Ganador = 1;
            }else{
                this.cod3Ganador = 1;
            }
        }else{
            //Validamos los Numeros
            if(this.cod1CantNumeros > this.cod2CantNumeros ){
                if(this.cod1CantNumeros > this.cod3CantNumeros);
                this.cod1Ganador = 1;
            }else if(this.cod2CantNumeros > this.cod3CantNumeros ){
                this.cod2Ganador = 1;
            }else{
                this.cod3Ganador = 1;
            }
        }
    }
    private int ContadorLetras(String cod){
        int contLetras = 0;
        for (int i = 0; i < cod.length(); i++) {
            try {
                String valor = new String(new char[] {cod.charAt(i)});
                int isNumero = Integer.parseInt( valor );
            } catch (Exception e) {
                contLetras++;
            }
        }
        return contLetras;
    }
    private int ContadorNumeros(String cod){
        int contNumeros = 0;
        for (int i = 0; i < cod.length(); i++) {
            try {
                String valor = new String(new char[] {cod.charAt(i)});
                int isNumero = Integer.parseInt( valor );
                contNumeros++;
            } catch (Exception e) {
                //...
            }
        }
        return contNumeros;
    }
}

