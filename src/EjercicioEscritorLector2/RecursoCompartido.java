package EjercicioEscritorLector2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecursoCompartido {
    private File myFile;
    private boolean leyendo;
    private boolean escribiendo;
    private FileWriter fw;
    private FileReader fr;
    
    public  RecursoCompartido(String rutaArchivo){
        this.myFile = new File(rutaArchivo);
        try {
            fr = new FileReader(this.myFile);
        } catch (FileNotFoundException ex) {            
        }
        this.leyendo = false;
        this.escribiendo = true;
    }

    public synchronized void leerLetra(){
        int num = 0;
        try {            
            //Si hay un escritor escribiendo, mando a esperar a los lectores              
            while(escribiendo == true){
                try {
                    wait();
                    System.out.println(Thread.currentThread().getName() + " está esperando a que el escritor acabe");  
                } catch (InterruptedException ex) {
                }
            }
            //Leo la letra escrita por el escritor
            num = fr.read();
            //si me devuelve -1 significa que ha llegado al final antes que el escritor haya escrito algo
            //y por tanto hay que volver a esperar a que lea algo que no sea -1
            while(num == -1){
                try {
                    wait();
                    num = fr.read();
                } catch (InterruptedException ex) {
                }                     
            }   
            //Mostramos la letra leida y avisamos al escritor de que ya puede volver a escribir
            System.out.println(Thread.currentThread().getName() + " ha leido " + ((char)num));
            leyendo = false;
            notify();          
                     
        }catch(IOException ex) {          
        }        
    }
    
    public synchronized void insertarLetra(char letra){
        //Mientras que el lector este leyendo el escritor tiene que esperar
        while(leyendo == true){
            try {
                wait();
                System.out.println(Thread.currentThread().getName() + " está esperando a que el lector acabe");  
            } catch (InterruptedException ex) {
            }
        }
        try {
            fw = new FileWriter(myFile, true);
        } catch (IOException ex) {           
        }
        
        //muestro la letra y aviso a los lectores de que ya pueden leer
        try {            
            fw.write(letra);
            System.out.println(Thread.currentThread().getName() + " ha escrito " + letra);             
            
            escribiendo = false;
            notifyAll();
            
            fw.close();
        } catch (IOException ex) {
        } 
    }
}
