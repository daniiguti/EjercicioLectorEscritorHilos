package EjercicioEscritorLector2;

public class NewMain {

    public static void main(String[] args) {
        RecursoCompartido recursoCompartido = new RecursoCompartido("C:\\Users\\Usuario\\Desktop\\prueba\\prueba.txt");
        
        Escritor escritor1 = new Escritor("Escritor 1", recursoCompartido);
        escritor1.setPriority(1);
        
        Lector lector1 = new Lector("Lector 1",  recursoCompartido);
        lector1.setPriority(3);
        Lector lector2 = new Lector("Lector 2", recursoCompartido);
        lector2.setPriority(3);       
        
        escritor1.start();
        lector1.start();
        lector2.start();
    }
}
