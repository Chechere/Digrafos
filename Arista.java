public class Arista {
    private double peso;
    private int nodoI, nodoF;

    /**
     * Constructor Arista - Genera una arista entre dos nodos.
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

    /**
     * Constructor Arista - Clona una arista.
     *
     * @param a Arista a clonar.
     */
    public Arista(Arista a) {
        this.nodoI = a.nodoI;
        this.nodoF= a.nodoF;
        this.peso = a.peso;
    }

    /**
     * Método - obtenerNodoInicial
     *
     * @return Nodo del que parte la arista.
     */
    public int nodoInicial() {
        return this.nodoI;
    }

    /**
     * Método - obtenerNodoFinal
     *
     * @return Nodo al que llega la arista.
     */
    public int nodoFinal() {
        return this.nodoF;
    }

    /**
     * Método - obtenerPeso
     *
     * @return Coste de ir de un nodo a otro.
     */
    public double peso() {
        return this.peso;
    }

    /**
     * Método mismaDireccionQue - Comprueba si las aristas
     *                            tienen el mismo inicio y
     *                            final.
     *                            
     * @param a Arista a comprobar.
     * 
     * @return True si esta arista y la arista a tienen la
     *         misma dirección, sino False.
     */
    public boolean mismaDireccionQue(Arista a) {
        return this.nodoI == a.nodoI &&
                this.nodoF == a.nodoF;
    }
    
    /**
     * Método aristaVaDespuesDe - Comprueba si esta arista
     *                            va despues de otra dada.
     *
     * @param a Arista de referencia.
     *
     * @return True si esta arista va despues de la arista a, 
     *         sino False. Tambien devuelve False si la 
     *         arista a es nulo o si ambas son tienen mismo
     *         nodo inicial y final.     
     */
    public boolean vaDespuesDe(Arista a) {
        boolean vaDespues = false;
        
        if(a != null) {
            if(this.nodoI > a.nodoI) {
                vaDespues = true;
            } else if(this.nodoI == a.nodoI &&
                        this.nodoF > a.nodoF) {
                vaDespues = true;
            }
        }
        
        return false;    
    }
}