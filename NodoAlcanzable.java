public class NodoAlcanzable {
    private int nodoI, nodoF, dist;
    private double pesoAcum;

    /**
     * Genera un nuevo nodoAlcanzable.
     *
     * @param nodoI Nodo que alcanza al nodoF.
     *
     * @param nodoF Nodo que es alcanzable por el nodoI.
     *
     * @param dist Numero de aristas por las que hay que
     *             pasar para ir desde el nodoI hasta el
     *             nodoF.
     *
     * @param pesoAcum Suma de los pesos de las aristas
     *                 por las que hay que pasar para
     *                 ir desde nodoI hasta nodoF.
     */
    public NodoAlcanzable(int nodoI, int nodoF, int dist, double pesoAcum) {
        this.nodoI = nodoI;
        this.nodoF = nodoF;
        this.dist = dist;
        this.pesoAcum = pesoAcum;
    }

    /**
     * Genera un nuevo nodoAlcanzable con los datos de
     * la arista y con distancia 1.
     *
     * @param a Arista a usar para generar el nodoAlcanzable.
     */
    public NodoAlcanzable(Arista a) {
        this.nodoI = a.nodoInicial();
        this.nodoF = a.nodoFinal();
        this.dist = 1;
        this.pesoAcum = a.peso();
    }

    /**
     * Método nodoInicial.
     *
     * @return El nodo que puede alcanzar al nodo Final.
     */
    public int nodoInicial() {
        return this.nodoI;
    }

    /**
     * Método nodoFinal.
     *
     * @return El nodo que es alcanzable por el nodo Inicial.
     */
    public int nodoFinal() {
        return this.nodoF;
    }

    /**
     * Método distancia.
     *
     * @return Numero de aristas por las que hay que pasar
     *         para ir desde el nodo Inicial hasta el nodo
     *         Final.
     */
    public int distancia() {
        return this.dist;
    }

    /**
     * Metodo pesoAcumulado.
     *
     * @return Suma de los pesos de las aristas por las
     *         que hay que pasar para ir desde nodoI
     *         hasta nodoF.
     */
    public double pesoAcumulado() {
        return this.pesoAcum;
    }
}