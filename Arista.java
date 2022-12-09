
/**
 * Almacena la información de la unión entro dos nodos. 
 */
public class Arista {
    private double peso;
    private int nodoI, nodoF;
        
    /**
     * Arista Constructor. Genera una arista entre dos nodos.
     *
     * @param nodoInicial De que nodo parte la arista.
     * @param nodoFinal A que nodo lleva la arista.
     * @param peso Coste de ir de un nodo al otro.
     */
    public Arista(int nodoInicial, int nodoFinal, double peso) {
        this.nodoI = nodoInicial;
        this.nodoF = nodoFinal;
        
        this.peso = peso;
    }
    
    public Arista(Arista a) {
        this.nodoI = a.nodoI;
        this.nodoF= a.nodoF;
        this.peso = a.peso;
    }
    
    /**
     * Método obtenerNodoInicial
     *
     * @return Nodo del que parte la arista.
     */
    public int obtenerNodoInicial() {
        return this.nodoI;
    }
    
    /**
     * Método obtenerNodoFinal
     *
     * @return Nodo al que llega la arista.
     */
    public int obtenerNodoFinal() {
        return this.nodoF;
    }
        
    /**
     * Método obtenerPeso
     *
     * @return Coste de ir de un nodo a otro.
     */
    public double obtenerPeso() {
        return this.peso;
    }
    
    public boolean vaDespuesDe(Arista a) {
        boolean vaAntes = false;
        
        if(this.nodoI > a.nodoI) {
            vaAntes = true;
        } else if(this.nodoI == a.nodoI && this.nodoF > a.nodoF) {
            vaAntes = true;
        }
        
        return vaAntes;
    }
    
    public boolean igualQue(Arista a) {
        return this.obtenerNodoInicial() == a.obtenerNodoInicial() &&
                this.obtenerNodoFinal() == a.obtenerNodoFinal();
    }
}