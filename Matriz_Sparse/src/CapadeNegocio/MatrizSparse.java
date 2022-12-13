/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapadeNegocio;

import java.util.Arrays;

/**
 *
 * @author fernando Angulo
 */
public class MatrizSparse {
    
    
    // paso1 Atributos de la clase
    int VFilaColumna[];//Vectorde Filas y Columnas
    //-----------------------------------------
    int VEntero[]; //Vector de Entero
    float VNumerador[]; //Vector de Numerador
    float VDenominador[]; //Vector de Denonminador
//----------------------------------------------------------
    int Nfila;  // Numero de filas
    int Ncolumna;// Numero de Columnas
    float ElementoPredominante;
    int dim; //contador de elementos 
    
    //paso 2 Constructor     5         5        0
    public MatrizSparse(int Nfil,int Ncol,float ep){
        VFilaColumna=new int[5];
        VEntero=new int[5];
        VNumerador=new float[5];
        VDenominador=new float[5];
        this.Nfila=Nfil;//
        this.Ncolumna=Ncol;
        this.ElementoPredominante=ep;
        dim=-1;
      
    }
    /*VFC [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]
      VD  [0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0 |0]   */
    
    
    //A.Set(Fila,Columna,Entero,Numerador,Denominador)
     //cargar,Insertar,Set
    //                   3          3            2              5               7 
    public void Set(int fila,int Columna,int entero, float numerador,float denominador){
        if ((fila>Nfila) || (Columna>Ncolumna)) {
            System.out.println("Error:Fila y Columna Fuera de Rango");
            System.exit(1);
        }else{
            int FC=(fila-1)*Ncolumna+Columna;//(3-1)*5+3=13
            int posicion=Existe(FC);//devolver -1 si no tiene datos esa posicion y 0 caso contrario
            //(Denominador*Entero+Numerador)/Denominador  
            
            
            
            //--Caso 1
            if ((posicion==-1)
                    &&(entero!=ElementoPredominante)
                    &&(numerador!=ElementoPredominante)
                    &&(denominador!=ElementoPredominante)) {  //no Vacio && 4!=0
                dim++;
                VFilaColumna[dim]=FC;
                VEntero[dim]=entero;//2
                VNumerador[dim]=numerador;//5
                VDenominador[dim]=denominador;//7
                
                
            }else{
                //--Caso 2
                if ((posicion!=-1)
                    &&(entero!=ElementoPredominante)
                    &&(numerador!=ElementoPredominante)
                    &&(denominador!=ElementoPredominante)) { //posicion 0!=-1 && 0!=0
                VEntero[posicion]=entero;
                VNumerador[posicion]=numerador;
                VDenominador[posicion]=denominador;
                     
                }else{
                    //--Caso 3
                    if ((posicion!=-1)){ //posicion 0!=-1 && 0==0
                        if ((entero==ElementoPredominante)
                            ||(numerador==ElementoPredominante)
                            ||(denominador==ElementoPredominante)) {
                            
                                for (int i = posicion; i <=dim; i++) {
                                    VFilaColumna[i]=VFilaColumna[i+1];
                                    VEntero[i]=VEntero[i+1];
                                    VNumerador[i]=VNumerador[i+1];
                                    VDenominador[i]=VDenominador[i+1];
                                }
                       
                             
                        }
                     dim--;
                    }
                }
                
            }
        }
    }
      //devolver -1 si no tiene datos esa posicion y 0 caso contrario    dim=-1  
    public int Existe(int FC) {
        int i=0;
        while ((i<=dim) && (VFilaColumna[i] !=FC)) {            
            i++;
        }
        if (i>dim) {//0>-1
            return -1;
        }else{
            return 0;
        }
    }
                     //3           3
                   //     3           3
    public String Get(int fila,int Columna){
        int FC=(fila-1)*Ncolumna+Columna; ////(3-1)*5+3=13
        int posicion=Existe(FC);  // si es -1 es vacia esa poscion caso contrario 0 tiene valor
        if(posicion!=-1){
            float numero=((VDenominador[posicion]*VEntero[posicion]+VNumerador[posicion])/VDenominador[posicion]);
           // float numero=deMixtoafracion(VEntero[posicion], VNumerador[posicion], VDenominador[posicion]);
            
            if (numero>0) {
                return "+"+VEntero[posicion]+" "+VNumerador[posicion]+"/"+VDenominador[posicion];
            }else{
                return "-"+VEntero[posicion]+" "+VNumerador[posicion]+"/"+VDenominador[posicion];
            }
            
        }else{
            return ""+ElementoPredominante;
        }
        
    }
    
    
    public float deMixtoafracion(int entero, float numerador,float denominador){
       float nuevoNumerador= ((denominador*entero+numerador));
        return  nuevoNumerador/denominador;
        
    }
    
    
    public String toString(){
        String S="";
        for (int i = 1; i <=Nfila; i++) {
            for (int j = 1; j <= Ncolumna; j++) {
                S=S+Get(i, j)+" ";
            }
            S=S+"\n";
        }
        return S;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void main(String[] args) {
        MatrizSparse A=new MatrizSparse(5, 5, 0);
        A.Set(3, 3, 2, 5, 7);
        //System.out.println("---"+A.deMixtoafracion(2, 5, 7));
         System.out.println(A.toString());
            // A.Set(3, 3, 5);
             // System.out.println(A.toString());
              //      A.Set(3, 3, 0);
               //     System.out.println(A.toString());

    }

    

    
}
