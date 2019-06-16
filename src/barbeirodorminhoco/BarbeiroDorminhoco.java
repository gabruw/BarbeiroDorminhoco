package barbeirodorminhoco;

public class BarbeiroDorminhoco {
    public static void main(String[] args) {
        Barbeiro barbeiro1 = new Barbeiro("João", 2, 5);
        
        Thread threadBarbeiro1 = new Thread(barbeiro1);
        
        threadBarbeiro1.start();
    }
}