package EjercicioEscritorLector2;

public class Lector extends Thread{ 
    //Atributos necesario para conocer el número de vueltas del for
    public static int NUM_LECTORES = 0;
    private static int NUM_VUELTAS = 0;
    
    //Atributos de la clase
    private String nombre;
    private RecursoCompartido buffer;
    
    //Constructor
    public Lector(String nombre, RecursoCompartido recurso){
        this.nombre = nombre;
        this.buffer = recurso;
        setName(nombre);
        //para saber cuantas vueltas tiene que dar en caso de más de un lector y/o escritor
        NUM_LECTORES++;        
    }
    
    //Metodo run
    @Override
    public void run(){
        calcularNumVueltas();
        for(int i = 0; i < NUM_VUELTAS; i++){
            try {
                //Llamamos al metodo de la clase
                buffer.leerLetra();
                //Mandamos a dormir el procesador para que sea mas real
                int time = (int) (Math.random() * (5000-2000)+2000);
                Thread.sleep(time);
            } catch (InterruptedException ex) {

            }
        } 
    }
    
    private void calcularNumVueltas(){
        NUM_VUELTAS = (Escritor.NUM_ESCRITORES*10)/NUM_LECTORES;
    }
}
