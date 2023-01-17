public class ListaNodosAlcanzables {
    private int nodo;
    private NodoAlcanzable[] nodos;
    private int sigEspVacio;

    /**
     * Constructor ListaNodosAlcanzables - Genera una
     *                                     lista con los
     *                                     nodos a los que
     *                                     puede alcanzar
     *                                     un nodo
     *                                     especifico.
     *
     * @param nodo Nodo que alcanza al resto de nodos de
     *             la lista.
     *
     * @param numNodos Maximo de nodos a los que puede
     *                 alcanzar.
     */
    public ListaNodosAlcanzables(int nodo, int numNodos) {
        this.nodo = nodo;

        this.nodos = new NodoAlcanzable[numNodos];
        this.sigEspVacio = 0;

        for(int i = 0; i < numNodos; i++) {
            this.nodos[i] = null;
        }
    }

    /**
     * Metodo insertarNodoDist - Inserta un nodo dentro de
     *                           la lista atendiendo a
     *                           criterios de minima
     *                           distancia.
     *
     * @param nodo Nodo a insertar dentro de la lista.
     */
    public void insertarNodoDist(NodoAlcanzable nodo) {
        if(!this.listaLlena()) {
            int i = 0;

            while(i < this.sigEspVacio && this.nodos[i].nodo() != nodo.nodo() &&
                    nodo.vaDespuesDe(this.nodos[i])) {

                i++;
            }

            if(i < this.sigEspVacio && this.nodos[i].nodo() == nodo.nodo()) {
                if(this.nodos[i].distancia() > nodo.distancia()) {
                    this.nodos[i] = new NodoAlcanzable(nodo);
                }
            } else {
                for(int j = this.sigEspVacio; j > i; j--) {
                    this.nodos[j] = this.nodos[j - 1];
                }

                this.nodos[i] = new NodoAlcanzable(nodo);
                this.sigEspVacio++;
            }
        }
    }

    /**
     * Metodo insertarNodoDist - Inserta un nodo dentro de
     *                           la lista atendiendo a
     *                           criterios de minimo peso.
     *
     * @param nodo Nodo a insertar dentro de la lista.
     */
    public void insertarNodoPeso(NodoAlcanzable nodo) {
        if(!this.listaLlena()) {
            int i = 0;

            while(i < this.sigEspVacio && this.nodos[i].nodo() != nodo.nodo() &&
                    nodo.vaDespuesDeBis(this.nodos[i])) {

                i++;
            }

            if(i < this.sigEspVacio && this.nodos[i].nodo() == nodo.nodo()) {
                if(this.nodos[i].peso() > nodo.peso()) {
                    this.nodos[i] = new NodoAlcanzable(nodo);
                }
            } else {
                for(int j = this.sigEspVacio; j > i; j--) {
                    this.nodos[j] = this.nodos[j - 1];
                }

                this.nodos[i] = new NodoAlcanzable(nodo);
                this.sigEspVacio++;
            }
        }
    }

    /**
     * Metodo nodo - Devuelve un nodo alcanzable de la
     *               lista de nodos.
     *
     * @param pos Posicion en la lista del nodo a buscar.
     *
     * @return El nodo buscado o null si la posicion no es
     *         valida.
     */
    public NodoAlcanzable nodo(int pos) {
        NodoAlcanzable n = null;

        if(!this.listaVacia() && pos >= 0 && pos < this.sigEspVacio) {
            n = new NodoAlcanzable(this.nodos[pos]);
        }

        return n;
    }

    /**
     * Metodo escribirLista - Muestra por pantalla la
     *                        lista de nodos alcanzables.
     */
    public void escribirLista() {
        System.out.println("\fNodos alcanzables por el nodo: " + this.nodo);

        System.out.println("\n   Nodo    | Distancia | Peso Total");
        System.out.println("-----------------------------------\n");

        for(int i = 0; i < this.sigEspVacio; i++) {
            System.out.printf("%-11d %-11d %-12.3f%n",
                                this.nodos[i].nodo(),
                                this.nodos[i].distancia(),
                                this.nodos[i].peso());
        }
    }

    /**
     * Metodo listaLlena - Comprueba si la lista esta
     *                     llena.
     *
     * @return True si esta llena, sino false.
     */
    private boolean listaLlena() {
        return this.sigEspVacio == this.nodos.length;
    }

    /**
     * Metodo listaVacia - Comprueba si la lista esta 
     *                     vacia.
     *
     * @return True si esta vacia, sino False.
     */
    private boolean listaVacia() {
        return this.sigEspVacio == 0;
    }
}