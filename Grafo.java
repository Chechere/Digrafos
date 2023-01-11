public class Grafo {
    private Arista[] aristas;
    private int sigEspVacio;

    /**
     * Constructor Grafo - Genera un nuevo grafo
     *                     vacio con un maximo de
     *                     aristas establecidos.
     *
     * @param numeroAristas Maximo de aristas en el grafo.
     */
    public Grafo(int aristas) {
        this.aristas = new Arista[aristas];
        this.sigEspVacio= 0;

        for(int i = 0; i < aristas; i++) {
            this.aristas[i] = null;
        }
    }

    /**
     * Constructor Grafo - Clona un grafo.
     * 
     * @param grafo Grafo a clonar.
     */
    public Grafo(Grafo grafo) {
        this.aristas = new Arista[grafo.aristas.length];
        this.sigEspVacio = grafo.sigEspVacio;

        for(int i = 0; i < this.aristas.length; i++) {
            this.aristas[i] = new Arista(grafo.aristas[i]);
        }
    }

    /**
     * Método leerGrafo - Genera un grafo con un maximo
     *                    numero de aristas, determinado
     *                    por el usuario (Entre 1 y 1000).
     *
     * @return Un nuevo grafo vacio.
     */
    public static Grafo leerGrafo() {
        int aristas;

        do {
            aristas = Teclado.leerEntero("Numero de aristas: ");
        } while(aristas < 1 || aristas > 1000);

        return new Grafo(aristas);
    }

    /**
     * Método insertarArista - Intenta insertar una arista dentro del grafo.
     * Si no puede insertarse no salta error.
     *
     * @param a Arista a insertar
     */
    public void insertarArista(Arista a) {
        if(a != null && !this.listaLlena() && this.existeArista(a) == -1) {
            int i = 0;

            while(i < this.sigEspVacio && !this.aristaVaDespuesDe(i, a)) {
                i++;
            }

            for(int j = this.sigEspVacio; j > i; j--) {
                this.aristas[j] = new Arista(this.aristas[j - 1]);
            }

            this.aristas[i] = new Arista(a);
            this.sigEspVacio++;
        }
    }

    /**
     * Método eliminarArista - Elimina una arista si 
     *                         existe dentro del grafo.
     *
     * @param a Arista a eliminar
     */
    public void eliminarArista(Arista a) {
        if(a != null && !this.listaVacia()) {
            int indice = this.existeArista(a);

            if(indice != -1) {
                for(int i = indice + 1; i < this.sigEspVacio; i++) {
                    this.aristas[i - 1] = new Arista(this.aristas[i]);
                }

                this.sigEspVacio--;
            }
        }
    }

    /**
     * Método existeArista - Comprueba si existe una arista dentro del grafo.
     *
     * @param a Arista a comprobar.
     * @return Indice de la arista si existe o -1 si no existe.
     * 
     * @remarks Solo comprueba si existe una arista con el mismo nodo
     *          inicial y final, no comprueba si tienen el mismo peso.
     */
    private int existeArista(Arista a) {
        int indice = -1;

        if(a != null && !this.listaVacia()) {
            int i = 0, m, s = this.sigEspVacio - 1;
            
            while(i != s) {
                m = (i + s) / 2;
                
                if(this.aristaVaDespuesDe(m, a)) {
                    s = m;
                } else {
                    i = m + 1;
                }
            }
            
            if(this.aristas[i].mismaDireccionQue(a)) {
                indice = i;
            }
        }

        return indice;
    }

    /**
     * Método obtenerPesoArista - Devuelve el peso de una arista dada.
     * 
     * @param a Arista a buscar.
     * 
     * @return El peso de la arista o -1 si no existe.
     */
    public double obtenerPesoArista(Arista a) {
        double peso = -1.0;
        
        if(a != null && !this.listaVacia()) {
            int indice = this.existeArista(a);

            if(indice != -1) {
                peso = this.aristas[indice].peso();
            }
        }

        return peso;
    }

    /**
     * Método dibujarGrafo - Dibuja por pantalla el grafo.
     */
    public void dibujarGrafo() {
        System.out.println("\fNodo Inicial | Nodo Final | Peso ");
        System.out.println("---------------------------------\n");

        for(int i = 0; i < this.sigEspVacio; i++) {
            System.out.printf("%-13d %-12d %3.3f%n",
                this.aristas[i].nodoInicial(),
                this.aristas[i].nodoFinal(),
                this.aristas[i].peso());
        }
    }

    /**
     * Método modificarPesoArista - Cambia el peso de una arista dada.
     * 
     * @param a Arista con el nuevo peso.
     */
    public void modificarPesoArista(Arista a) {
        if(a != null && !this.listaVacia()) {
            int indice = this.existeArista(a);

            if(indice != -1) {
                this.aristas[indice] = new Arista(a);
            }
        }
    }

    /**
     * Método bucles - Cuenta la cantidad de aristas cuyo nodo inicial sea el final.
     * 
     * @return Número de bucles en el grafo.
     */
    public int bucles() {
        int bucles = 0;
        
        if(!this.listaVacia()) {
            for(int i = 0; i < this.sigEspVacio; i++) {
                if(this.aristas[i].nodoInicial() == this.aristas[i].nodoFinal()) {
                    bucles++;
                }
            }
        }

        return bucles;
    }

    /**
     * Método nodosTerminales - Cuenta la cantidad de nodos de los
     *                          cuáles ninguna arista parta de ellos.
     *
     * @return La cantidad de nodos terminales en el grafo.
     */
    public int nodosTerminales() {
        int terminales = 0;

        if(!this.listaVacia()) {
            for(int i = 0; i < this.sigEspVacio; i++) {
                boolean repetido = false;
                int j = i - 1;
                
                while(j >= 0 && !repetido) {
                    if(this.aristas[j].nodoFinal() == this.aristas[i].nodoFinal()) {
                        repetido = true;
                    }
                    
                    j--;
                }
                
                if(!repetido) {
                    j = 0;
                    
                    while(j < this.sigEspVacio && 
                        this.aristas[j].nodoInicial() < this.aristas[i].nodoFinal()) {                        
                        j++;
                    }
                    
                    if(j == this.sigEspVacio || this.aristas[j].nodoInicial() != this.aristas[i].nodoFinal()) {
                        terminales++;
                    }
                }
            }
        }

        return terminales;
    }

    /**
     * Método fusionarCon - Fusiona este grafo con otro dado.
     * 
     * @param grafo Grafo a fusionar con este.
     * 
     * @return La fusión de ambos grafos.
     */
    public Grafo fusionarCon(Grafo grafo) {
        Grafo resultado = this;
        
        if(grafo != null && !grafo.listaVacia()) {
            resultado = new Grafo(this.aristas.length + grafo.aristas.length);

            int i = 0, j = 0;

            while(i < this.sigEspVacio && j < grafo.sigEspVacio) {
                if(this.aristaVaDespuesDe(i, grafo.aristas[j])) {
                    resultado.insertarArista(grafo.aristas[j]);
                    
                    j++;
                } else if(this.aristas[i].mismaDireccionQue(grafo.aristas[j])) {
                    if(this.aristas[i].peso() < grafo.aristas[j].peso()) {
                        resultado.insertarArista(this.aristas[i]);
                    } else {
                        resultado.insertarArista(grafo.aristas[j]);
                    }
                    
                    i++;
                    j++;
                } else {
                    resultado.insertarArista(this.aristas[i]);
                    
                    i++;
                }

                i++;
            }

            for(; i < this.sigEspVacio; i++) {
                resultado.insertarArista(this.aristas[i]);
            }

            for(; j < grafo.sigEspVacio; j++) {
                resultado.insertarArista(grafo.aristas[j]);
            }
        }

        return resultado;
    }

    /**
     * Método gradoNodo - Devuelve el número de aristas que
     *                    tienen el mismo nodo Inicial.
     *
     * @param nodo Nodo del queremos saber su grado.
     *
     * @return el grado del Nodo
     */
    public int gradoNodo(int nodo) {
        int aristas = 0;
        
        int i = 0;
        while(i < this.sigEspVacio && this.aristas[i].nodoInicial() <= nodo) {
            if(this.aristas[i].nodoInicial() == nodo) {
                aristas++;
            }
            
            i++;
        }
        return aristas;
    }

    /**
     * Método subGrafo - Genera un grafo apartir de las
     *                   aristas que salen de un nodo central.
     *
     * @param nodoCentral nodo central del que generar el grafo.
     *
     * @return el subgrafo.
     */
    public Grafo subGrafo(int nodoCentral) {
        Grafo subGrafo = new Grafo(this.aristas.length);

        if(!this.listaVacia()) {
            int i = 0;
            
            while(i < this.sigEspVacio && this.aristas[i].nodoInicial() <= nodoCentral) {
                if(this.aristas[i].nodoInicial() == nodoCentral) {
                    subGrafo.insertarArista(new Arista(this.aristas[i]));
                }
                
                i++;
            }
        }

        return subGrafo;
    }

    /**
     * Método alcanzabilidadNodo - Genera por pantalla una lista
     *                             con todos los nodos alcanzables
     *                             por un nodo especifico.
     *
     * @param nodo Nodo que queremos saber a cuantos nodos llega.
     *
     * @remarks Esta función busca por distancia, es decir, si hay
     *          dos formas de llegar a un mismo nodo, el programa
     *          escogerá aquella que recorra menos distancia
     *          (Tenga que pasar por menos aristas).
     */
    public void alcanzabilidadNodo(int nodo) {
        NodoAlcanzable[] nodos = new NodoAlcanzable[this.sigEspVacio];
        int sigNodoVacio = 0;

        int i = 0;
        while(i < this.sigEspVacio && this.aristas[i].nodoInicial() <= nodo) {
            if(this.aristas[i].nodoInicial() == nodo) {
                nodos[sigNodoVacio] = new NodoAlcanzable(this.aristas[i]);
                sigNodoVacio++;
            }

            i++;
        }

        int nodosTotales, inicio = 0;
        i = 0;

        while(i < sigNodoVacio) {
            int j = 0;

            while(j < this.sigEspVacio &&
                    this.aristas[j].nodoInicial() <= nodos[i].nodoFinal()) {

                if(nodos[i].nodoFinal() == this.aristas[j].nodoInicial() &&
                    nodo != this.aristas[j].nodoFinal()) {
                    int dist = nodos[i].distancia() + 1;
                    double peso = nodos[i].pesoAcumulado() + this.aristas[j].peso();

                    boolean existe = false;
                    int k = 0;
                    while(k < sigNodoVacio && !existe) {
                        if(nodos[k].nodoFinal() == this.aristas[j].nodoFinal()) {
                                existe = true;
                        } else {
                            k++;
                        }
                    }

                    if(!existe || nodos[k].distancia() > dist) {
                        nodos[k] = new NodoAlcanzable(nodo,
                                                    this.aristas[j].nodoFinal(),
                                                    dist,
                                                    peso);

                        if(!existe) {
                            sigNodoVacio++;
                        }
                    }
                }

                j++;
            }

            i++;
        }

        System.out.println("\f Nodo Inicial | Nodo Final | Distancia | Peso ");
        System.out.println("----------------------------------------------\n");
        for(i = 0 ; i < sigNodoVacio; i++) {
            System.out.printf("%-13d  %-12d  %-10d  %3.3f%n",
                                nodos[i].nodoInicial(),
                                nodos[i].nodoFinal(),
                                nodos[i].distancia(),
                                nodos[i].pesoAcumulado());
        }
    }

    /**
     * Método aristaVaDespuesDe - Comprueba si una arista de la lista
     *                            va despues de otra dada.
     *
     * @param indice Indice de la arista en la lista a comprobar.
     *
     * @param a Arista de referencia.
     *
     * @return True si de la lista va despues de a, sino False.
     *         Tambien devuelve False si la arista a es nulo
     *         o el indice no esta en el intervalo [0, sigEspVacio).
     *
     * @remark Tambien devuelve False si ambas tienen el mismo 
     *         nodo inicial y final
     */
    private boolean aristaVaDespuesDe(int indice, Arista a) {
        boolean vaDespues = false;

        if(indice >= 0 && indice < this.sigEspVacio && a != null) {
            if(this.aristas[indice].nodoInicial() > a.nodoInicial()) {
                vaDespues = true;
            } else if(this.aristas[indice].nodoInicial() == a.nodoInicial() && 
                        this.aristas[indice].nodoFinal() > a.nodoFinal()) {
                vaDespues = true;
            }
        }

        return vaDespues;
    }

    /**
     * Método listaLlena - Comprueba si la lista esta llena.
     *
     * @return True si esta llena, sino False;
     */
    private boolean listaLlena() {
        return this.sigEspVacio == this.aristas.length;
    }

    /**
     * Método listaVacia - Comprueba si la lista esta vacia.
     *
     * @return True si esta vacia, sino False.
     */
    private boolean listaVacia() {
        return this.sigEspVacio == 0;
    }
}