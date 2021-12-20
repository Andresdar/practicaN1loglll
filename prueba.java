/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package matriz.tripleta;

/**
 *
 * @author danie
 */
public class prueba {
    public static void main(String[] args) throws Exception {
        MatrizEnTripleta matriz1 = new MatrizEnTripleta(5, 5);
        matriz1.setCelda(1, 1, -5);
        matriz1.setCelda(5, 2, -2);
        matriz1.setCelda(4, 4, 1);
        System.out.println(matriz1);
        MatrizEnTripleta matriz2 = new MatrizEnTripleta(5, 5);
        matriz2.setCelda(1, 1, 5);
        matriz2.setCelda(5, 2, 2);
        System.out.println(matriz2);
        MatrizEnTripleta matriz3 = new MatrizEnTripleta(5, 5);
        matriz3 = matriz3.suma(matriz1, matriz2);
        //System.out.println(matriz3);
        
        String a = matriz3.toString();
        System.out.println(a);
    }
}
