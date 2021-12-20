/*
 * Copyright 2019 Carlos Alejandro Escobar Marulanda ealejandro101@gmail.com
 * Permission is hereby granted, free of charge, to any person 
 * obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without 
 * restriction, including without limitation the rights to use, 
 * copy, modify, merge, publish, distribute, sublicense, and/or 
 * sell copies of the Software, and to permit persons to whom the 
 * Software is furnished to do so, subject to the following 
 * conditions:
 * The above copyright notice and this permission notice shall 
 * be included in all copies or substantial portions of the 
 * Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING 
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR 
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package matriz.tripleta;

import matriz.util.Tripleta;

public class MatrizEnTripleta {

    protected Tripleta[] tripletas;

    /**
     * Construye una matriz en tripleta vacia, con un tamaño de arreglo del
     * unico caso y este es que la todas las posiciones esten llenas con cero
     *
     * @param f
     * @param c
     */
    public MatrizEnTripleta(int f, int c) {
        this.tripletas = new Tripleta[1];
        Tripleta configuracion = new Tripleta(f, c, (int) 0);
        tripletas[0] = configuracion;
    }

    /**
     * Fijar un valor valido de Cero en una celda
     *
     * @param filaDestino
     * @param columnaDestino
     * @param datoDestino
     * @throws java.lang.Exception
     */
    public void setCelda(int filaDestino, int columnaDestino, double datoDestino) throws Exception {
        Tripleta configuracion = tripletas[0];

        /**
         * Valido limites
         */
        int filas = configuracion.getF();
        int columnas = configuracion.getC();

        if (filaDestino <= 0 || filas < filaDestino || columnaDestino <= 0 || columnas < columnaDestino) {
            throw new Exception("Esta fuera de los limites de la matriz");
        }

        /**
         * Fijamos el valor
         */
        double cantidadDatosActual = (double) configuracion.getV();
        Tripleta celdaDestino = null;
        double posicionInsertar = cantidadDatosActual + 1;
        for (int r = 1; cantidadDatosActual >= r; r++) {
            Tripleta tripletaRecorrido = tripletas[r];
            int filaRecorrido = tripletaRecorrido.getF();
            if (filaDestino > filaRecorrido) {
                // No hago nada
            } else if (filaDestino == filaRecorrido) {
                int columnaRecorrido = tripletaRecorrido.getC();
                if (columnaDestino > columnaRecorrido) {
                    // No hago nada  
                } else if (columnaDestino == columnaRecorrido) {
                    // La celda esta en el arreglo de tripletas
                    // Se debe realizar reemplazo del valor
                    celdaDestino = tripletaRecorrido;
                } else {
                    posicionInsertar = r;
                    break;
                }
            } else {
                posicionInsertar = r;
                break;
            }
        }

        /**
         * Valido si encontre la celda en el arreglo
         */
        if (celdaDestino != null) {
            celdaDestino.setV(datoDestino);
        } else {
            // Realiza crecimiento dinamico del arreglo de tripletas
            // Copiando en un nuevo arreglo todas las tripletas
            celdaDestino = new Tripleta(filaDestino, columnaDestino, datoDestino);
            Tripleta[] nuevasTripletas = new Tripleta[(int)cantidadDatosActual + 1 + 1];
            configuracion.setV(cantidadDatosActual + 1);
            for (int i = 0; i < nuevasTripletas.length; i++) {
                if (i < posicionInsertar) {
                    nuevasTripletas[i] = tripletas[i];
                } else if (i == posicionInsertar) {
                    nuevasTripletas[i] = celdaDestino;
                } else {
                    nuevasTripletas[i] = tripletas[i - 1];
                }
            }
            tripletas = nuevasTripletas;
        }
    }

    public void setTripleta(int cv, Tripleta t) {
        tripletas[cv] = t;
    }

    @Override
    public String toString() {
        StringBuilder cadena = new StringBuilder();
        // Obtengo la configuración de la matriz, fr y cr y la cantidadValores
        Tripleta configuracion = this.tripletas[0];
        int cantidadFilasMatriz = configuracion.getF();
        int cantidadColumnasMatriz = configuracion.getC();
        double valoresDiferentesCero = configuracion.getV();

        int posicionArreglo = 1;

        cadena.append("\t");
        for (int columnasVirtuales = 1; columnasVirtuales <= cantidadColumnasMatriz; columnasVirtuales++) {
            cadena.append(columnasVirtuales + "\t");
        }
        cadena.append("\n");
        // Recorrido por una matriz virtual m x n
        for (int filasVirtuales = 1; filasVirtuales <= cantidadFilasMatriz; filasVirtuales++) {
            cadena.append(" " + filasVirtuales + " \t");
            for (int columnasVirtuales = 1; columnasVirtuales <= cantidadColumnasMatriz; columnasVirtuales++) {
                if (posicionArreglo <= valoresDiferentesCero) {
                    // Estoy en una posicion valida en el arreglo
                    Tripleta posibleTripletaMostrar = tripletas[posicionArreglo];
                    int filaCeldaMostrar = posibleTripletaMostrar.getF();
                    int columnaCeldaMostrar = posibleTripletaMostrar.getC();
                    if (filasVirtuales == filaCeldaMostrar) {
                        if (columnasVirtuales == columnaCeldaMostrar) {
                            Object valorCeldaMostrar = posibleTripletaMostrar.getV();
                            if (valorCeldaMostrar != null) {
                                cadena.append(valorCeldaMostrar + "\t");
                            } else {
                                cadena.append("0.0" + "\t");
                            }
                            posicionArreglo++;
                        } else {
                            cadena.append("0.0" + "\t");
                        }
                    } else {
                        cadena.append("0.0" + "\t");
                    }
                } else {
                    cadena.append("0.0" + "\t");
                }
            }
            cadena.append("\n");
        }

        return cadena.toString();
    }

    public Tripleta[] getTripletas() {
        return tripletas;
    }

    public int numeroTripletas() {
        return (int) tripletas[0].getV();
    }
    
    public int numeroFilas(){
        return (int) tripletas[0].getF();
    }
    
    public int numeroColumnas(){
        return (int) tripletas[0].getC();
    }
    
    public Tripleta retornaTripleta(int i){
        return tripletas[i];
    }
    
    public void asignaNumeroTripletas(int i){
        tripletas[0].setV(i);
        tripletas = new Tripleta[i+2];
    }
    
    
    public MatrizEnTripleta suma(MatrizEnTripleta matrizA, MatrizEnTripleta matrizB) throws Exception{
        int filasA, colA, filasB, colB, tripletasA, tripletasB, i, j, k, fi, fj, ci, cj;
        double vi, vj, ss;
        Tripleta ti, tj, tx;
        filasA = matrizA.numeroFilas();
        colA = matrizA.numeroColumnas();
        tripletasA = matrizA.numeroTripletas();
        filasB = matrizB.numeroFilas();
        colB = matrizB.numeroColumnas();
        tripletasB = matrizB.numeroTripletas();
        if(filasA != filasB || colA != colB){
            return null;
        }
        //ti = new Tripleta(filasA, colA, 0);
        MatrizEnTripleta c = new MatrizEnTripleta(filasA, colA);
        i = 1;
        j = 1;
        k = 0;
        while(i <= tripletasA && j <= tripletasB){
            ti = matrizA.retornaTripleta(i);
            tj = matrizB.retornaTripleta(j);
            fi = ti.getF();
            fj = tj.getF();
            k = k + 1;
            //c.asignaNumeroTripletas(k);
            switch(comparar(fi, fj)){
                case -1:
                    //c.setTripleta(k, ti);
                    c.setCelda(ti.getF(), ti.getC(), ti.getV());
                    i = i + 1;
                    break;
                case 1:
                    //c.setTripleta(k, tj);
                    c.setCelda(tj.getF(), tj.getC(), tj.getV());
                    j = j + 1;
                    break;
                case 0:
                    ci = ti.getC();
                    cj = tj.getC();
                    switch(comparar(ci, cj)){
                        case -1:
                            //c.setTripleta(k, ti);
                            c.setCelda(ti.getF(), ti.getC(), ti.getV());
                            i = i + 1;
                            break;
                        case 1:
                            //c.setTripleta(k, tj);
                            c.setCelda(tj.getF(), tj.getC(), tj.getV());
                            j = j + 1;
                            break;
                        case 0:
                            vi = (Double) ti.getV();
                            vj = (Double) tj.getV();
                            ss = vi + vj;
                            if(ss != 0){
                                //tx = new Tripleta(fi, ci, ss);
                                //c.setTripleta(k, tx);
                                c.setCelda(fi, ci, ss);
                            } else {
                                k = k - 1;
                            }
                            i = i + 1;
                            j = j + 1;
                            break;
                    }
            }
        }
        while (i <= tripletasA){
            ti = matrizA.retornaTripleta(i);
            k = k + 1;
            //c.asignaNumeroTripletas(k);
            //c.setTripleta(k, ti);
            c.setCelda(ti.getF(), ti.getC(), ti.getV());
            i = i + 1;
        }
        
        while(j <= tripletasB){
            tj = matrizB.retornaTripleta(j);
            k = k + 1;
            //c.asignaNumeroTripletas(k);
            //c.setTripleta(k, tj);
            c.setCelda(tj.getF(), tj.getC(), tj.getV());
            j = j + 1;
        }
        return c;
    }
    private int comparar(int numero1, int numero2) {
        if (numero1 < numero2) {
            return -1;
        }
        if (numero1 > numero2) {
            return 1;
        }
        return 0;
    }

}
