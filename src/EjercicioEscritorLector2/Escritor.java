package EjercicioEscritorLector2;

public class Escritor extends Thread{
    //Atributo necesario para saber cuantas vueltas tienen que dar los
    //lectores
    public static int NUM_ESCRITORES = 0;
    //string estático para las letras
    private static String LETRAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    //Atributos de la clase
    private String nombre;
    private RecursoCompartido buffer;
    
    //Constructor
    public Escritor(String nombre, RecursoCompartido recurso){
        this.nombre = nombre;
        this.buffer = recurso;
        setName(nombre);
        NUM_ESCRITORES++;
    }
    
    //Método run
    @Override
    public void run(){
        char letra = ' ';
        for(int i = 0; i < 10; i++){
            try {
                int numAleatorio = (int)(Math.random() * (LETRAS.length()-1)+1);
                letra = LETRAS.charAt(numAleatorio);
                buffer.insertarLetra(letra);
                int time = (int)(Math.random() * (3000-1000)+1000);
                Thread.sleep(time);
            } catch (InterruptedException ex) {
                
            }
        }
    }
}
