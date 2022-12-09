public class Grafo {
    private Arista[] aristas;
    int sigEspacioVacio;

    /**
     * Grafo Constructor - Genera un nuevo gráfico vacio con un maximo de aristas establecidos.
     *
     * @param numeroAristas Maximo de aristas en el grafo.
     */
    public Grafo(int numeroAristas) {
        this.aristas = new Arista[numeroAristas];
        this.sigEspacioVacio = 0; 

        for(int i = 0; i < numeroAristas; i++) {
            this.aristas[i] = new Arista(0, 0, 0);
        }
    } 

    
    /**
     * Método insertarArista - Intenta insertar una arista dentro del grafo. Si no puede insertarse no salta error.
     *
     * @param a Arista a insertar
     */
    public void insertarArista(Arista a) {
        if(!this.listaLlena() && this.existeArista(a) == -1) {
            int i = 0;

            while(i < this.sigEspacioVacio && a.vaDespuesDe(this.aristas[i])) {
                i++;
            }

            for(int j = this.sigEspacioVacio; j > i; j--) {
                this.aristas[j] = new Arista(this.aristas[j - 1]);
            }

            this.aristas[i] = new Arista(a);

            this.sigEspacioVacio++;
        }
    }

    
    /**
     * Método eliminarArista - Elimina una arista si existe dentro del grafo.
     *
     * @param a Arista a eliminar
     */
    public void eliminarArista(Arista a) {
        if(!this.listaVacia()) {
            int indice = this.existeArista(a);

            if(indice != -1) {
                for(int i = indice + 1; i < this.sigEspacioVacio; i++) {
                    this.aristas[i - 1] = new Arista(this.aristas[i]);
                }

                this.sigEspacioVacio--;
            }
        }
    }

    
    /**
     * Método existeArista - Comprueba si existe una arista dentro del grafo.
     *
     * @param a Arista a comprobar.
     * @return Indice de la arista si existe o -1 si no existe.
     */
    private int existeArista(Arista a) {        
        int i = -1;        

        if(!this.listaVacia()) {
            boolean existe = false;

            while(i < this.sigEspacioVacio && !existe) {
                i++;

                existe = this.aristas[i].igualQue(a);
            }

            if(!existe) {
                i = -1;
            }
        }

        return i;
    }

    
    /**
     * Método dibujarGrafo - Dibuja por pantalla el grafo.
     */
    public void dibujarGrafo() {
        System.out.println("\fNodo Inicial | Nodo Final | Peso ");
        System.out.println("---------------------------------\n");

        for(int i = 0; i < this.sigEspacioVacio; i++) {
            System.out.printf("%-13d %-12d %3.3f\n", 
                this.aristas[i].obtenerNodoInicial(),
                this.aristas[i].obtenerNodoFinal(),
                this.aristas[i].obtenerPeso());
        }
        
        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    
    /**
     * Método listaVacia - Comprueba si la lista esta vacia.
     *
     * @return True si esta vacia, sino False.
     */
    private boolean listaVacia() {
        return this.sigEspacioVacio == 0;
    }

    /**
     * Método listaLlena - Comprueba si la lista esta llena.
     *
     * @return True si esta llena, sino False;
     */
    private boolean listaLlena() {
        return this.sigEspacioVacio == this.aristas.length;
    }
}
