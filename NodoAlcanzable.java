public class NodoAlcanzable {
    private int nodo;
    private int distancia;
    private double peso;

    /**
     * Constructor NodoAlcanzable - Guarda los datos de un
     *                              nodo que es alcanzable
     *                              por otro nodo.
     *
     * @param nodo Nodo que es alcanzable.
     *
     * @param distancia Numero de aristas por las que hay
     *                  que pasar para llegar al nodo.
     *
     * @param peso Peso total de todas las aristas.
     */
    public NodoAlcanzable(int nodo, int distancia, double peso) {
        this.nodo = nodo;
        this.distancia = distancia;
        this.peso = peso;
    }

    /**
     * Constructor NodoAlcanzable - Genera un clon de otro
     *                              nodo alcanzable.
     *
     * @param n Nodo a clonar.
     */
    public NodoAlcanzable(NodoAlcanzable n) {
        this.nodo = n.nodo;
        this.distancia = n.distancia;
        this.peso = n.peso;
    }

    /**
     * Metodo nodo - Nodo que es alcanzable.
     *
     * @return Valor del nodo.
     */
    public int nodo() {
        return this.nodo;
    }

    /**
     * Metodo distancia - Numero de aristas por las que
     *                    hay que pasar para llegar al
     *                    nodo.
     *
     * @return La distancia al nodo.
     */
    public int distancia() {
        return this.distancia;
    }

    /**
     * Metodo distancia - Peso total de todas las aristas.
     *
     * @return Peso total para llegar al nodo.
     */
    public double peso() {
        return this.peso;
    }

    /**
     * Metodo vaDespuesDe - Comprueba si, en una lista,
     *                      este nodo va antes que otro
     *                      dado.
     *
     * @param n Nodo para comparar.
     *
     * @return True si este va despues del dado, sino
     *         False.
     *
     * @remarks Esta funcion ordena segun la distancia,
     *          yendo antes aquellos con menor distancia.
     */
    public boolean vaDespuesDe(NodoAlcanzable n) {
        boolean vaDespues = false;

        if(this.distancia > n.distancia) {
            vaDespues = true;
        } else if(this.distancia == n.distancia &&
                    this.nodo > n.nodo) {
            vaDespues = true;
        }

        return vaDespues;
    }

    /**
     * Metodo vaDespuesDe - Comprueba si, en una lista,
     *                      este nodo va antes que otro
     *                      dado.
     *
     * @param n Nodo para comparar.
     *
     * @return True si este va despues del dado, sino
     *         False.
     *
     * @remarks Esta funcion ordena segun el peso, yendo
     *          antes aquellos con menor peso.
     */
    public boolean vaDespuesDeBis(NodoAlcanzable n) {
        boolean vaDespues = false;

        if(Math.abs(this.peso - n.peso) < 0.0001) {
            if(this.nodo > n.nodo) {
                vaDespues = true;
            }
        } else if(this.peso > n.peso) {
            vaDespues = true;
        }

        return vaDespues;
    }
}