import java.io.*;
import java.util.*;

public class Grafo {
    private Arista[] aristas;
    private int sigEspVacio;

    /**
     * Constructor Grafo - Genera un nuevo grafo vacio con
     *                     un maximo de aristas
     *                     establecidos.
     *
     * @param numeroAristas Maximo de aristas en el grafo.
     */
    public Grafo(int aristas) {
        this.aristas = new Arista[aristas];
        this.sigEspVacio= 0;

        int tamanyo;
        if(aristas <= 2) {
            tamanyo = 2 * aristas - 1;
        } else {
            tamanyo = aristas * (aristas - 1);
        }

        for(int i = 0; i < aristas; i++) {
            this.aristas[i] = null;
        }
    }

    /**
     * Metodo leerGrafo - Genera un grafo con un maximo
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
     * Metodo insertarArista - Intenta insertar una arista
     *                         dentro del grafo.
     *
     * @param a Arista a insertar.
     */
    public void insertarArista(Arista a) {
        if(a != null && !this.listaLlena() && this.existeArista(a) == -1) {
            int i = 0;

            while(i < this.sigEspVacio && a.vaDespuesDe(this.aristas[i])) {
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
     * Metodo eliminarArista - Elimina una arista, si
     *                         existe dentro del grafo.
     *
     * @param a Arista a eliminar.
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
     * Metodo existeArista - Comprueba si existe una arista
     *                       dentro del grafo.
     *
     * @param a Arista a comprobar.
     *
     * @return Indice de la arista si existe o -1 si no 
     *         existe.
     *
     * @remarks Solo comprueba si existe una arista con el
     *          mismo nodo inicial y final, no comprueba
     *          si tienen el mismo peso.
     */
    private int existeArista(Arista a) {
        int indice = -1;

        if(a != null && !this.listaVacia()) {
            int i = 0, m, s = this.sigEspVacio - 1;

            while(i != s) {
                m = (i + s) / 2;

                if(!a.vaDespuesDe(this.aristas[m])) {
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
     * Metodo obtenerPesoArista - Devuelve el peso de una
     *                            arista dada.
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
     * Metodo dibujarGrafo - Dibuja por pantalla el grafo.
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
     * Metodo modificarPesoArista - Cambia el peso de una
     *                              arista dada.
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
     * Metodo bucles - Cuenta la cantidad de aristas cuyo
     *                 nodo inicial sea el final.
     *
     * @return Numero de bucles en el grafo.
     */
    public int bucles() {
        int bucles = 0;

        if(!this.listaVacia()) {
            for(int i = 0; i < this.sigEspVacio; i++) {
                if(this.aristas[i].nodoInicial() == 
                    this.aristas[i].nodoFinal()) {

                    bucles++;
                }
            }
        }

        return bucles;
    }

    /**
     * Metodo nodosTerminales - Cuenta la cantidad de
     *                          nodos de los cuales
     *                          ninguna arista parta de
     *                          ellos.
     *
     * @return La cantidad de nodos terminales en el grafo.
     */
    public int nodosTerminales() {
        int terminales = 0;

        if(!this.listaVacia()) {
            int[] nodos = new int[this.sigEspVacio];
            int sigNodoVacio = 0;

            for(int i = 0; i < nodos.length; i++) {
                nodos[i] = 0;
            }

            for(int i = 0; i < this.sigEspVacio; i++) {
                int j = 0;

                while(j < sigNodoVacio &&
                        nodos[j] != this.aristas[i].nodoFinal()) {

                    j++;
                }

                if(j == sigNodoVacio) {
                    nodos[sigNodoVacio] = this.aristas[i].nodoFinal();
                    sigNodoVacio++;

                    if(this.gradoNodo(this.aristas[i].nodoFinal()) == 0) {
                        terminales++;
                    }
                }
            }
        }

        return terminales;
    }

    /**
     * Metodo fusionarCon - Fusiona este grafo con otro
     *                      dado.
     *
     * @param grafo Grafo a fusionar con este.
     *
     * @return La fusion de ambos grafos.
     */
    public Grafo fusionarCon(Grafo grafo) {
        Grafo resultado = this;

        if(grafo != null && !grafo.listaVacia()) {
            resultado = new Grafo(this.aristas.length + grafo.aristas.length);

            int i = 0, j = 0;

            while(i < this.sigEspVacio && j < grafo.sigEspVacio) {
                if(this.aristas[i].vaDespuesDe(grafo.aristas[j])) {
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
     * Metodo gradoNodo - Devuelve el numero de aristas
     *                    que tienen el mismo nodo Inicial.
     *
     * @param nodo Nodo del queremos saber su grado.
     *
     * @return el grado del Nodo.
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
     * Metodo subGrafo - Genera un grafo apartir de las
     *                   aristas que salen de un nodo
     *                   central.
     *
     * @param nodoCentral Nodo central del que generar el
     *                    grafo.
     *
     * @return el subgrafo.
     */
    public Grafo subGrafo(int nodoCentral) {
        Grafo subGrafo = new Grafo(this.aristas.length);

        if(!this.listaVacia()) {
            int i = 0;

            while(i < this.sigEspVacio && 
                    this.aristas[i].nodoInicial() <= nodoCentral) {

                if(this.aristas[i].nodoInicial() == nodoCentral) {
                    subGrafo.insertarArista(new Arista(this.aristas[i]));
                }

                i++;
            }
        }

        return subGrafo;
    }

    /**
     * Metodo alcanzabilidadNodoDist - Devuelve una lista
     *                                 con los nodos
     *                                 alcanzables por un
     *                                 nodo en especifico.
     *
     * @param nodo Nodo que queremos saber a cuantos nodos
     *             llega.
     *
     * @remarks Esta funcion busca por distancia, es decir,
     *          si hay dos formas de llegar a un mismo
     *          nodo, el programa escogera aquella que
     *          recorra menos distancia (Tenga que pasar
     *          menos aristas).
     */
    public ListaNodosAlcanzables alcanzabilidadNodoDist(int nodo) {
        ListaNodosAlcanzables l = 
                            new ListaNodosAlcanzables(nodo, this.sigEspVacio);

        int i = 0;
        while(i < this.sigEspVacio && this.aristas[i].nodoInicial() <= nodo) {
            if(this.aristas[i].nodoInicial() == nodo) {
                l.insertarNodoDist(
                                new NodoAlcanzable(this.aristas[i].nodoFinal(),
                                1,
                                this.aristas[i].peso()));
            }

            i++;
        }

        i = 0;
        NodoAlcanzable n;

        while((n = l.nodo(i)) != null) {
            int j = 0;

            while(j < this.sigEspVacio && 
                    this.aristas[j].nodoInicial() <= n.nodo()) {

                if(this.aristas[j].nodoInicial() == n.nodo() &&
                    this.aristas[j].nodoFinal() != nodo) {

                    l.insertarNodoDist(
                                new NodoAlcanzable(this.aristas[j].nodoFinal(),
                                n.distancia() + 1,
                                n.peso() + this.aristas[j].peso()));
                }

                j++;
            }

            i++;
        }

        return l;
    }

    /**
     * Metodo alcanzabilidadNodoPeso - Devuelve una lista
     *                                 con los nodos
     *                                 alcanzables por un
     *                                 nodo en especifico.
     *
     * @param nodo Nodo que queremos saber a cuantos nodos
     *             llega.
     *
     * @remarks Esta funcion busca por distancia, es decir,
     *          si hay dos formas de llegar a un mismo
     *          nodo, el programa escogera aquella que
     *          tenga menor peso total.
     */
    public ListaNodosAlcanzables alcanzabilidadNodoPeso(int nodo) { 
        ListaNodosAlcanzables l = 
                            new ListaNodosAlcanzables(nodo, this.sigEspVacio);

        int i = 0;
        while(i < this.sigEspVacio && this.aristas[i].nodoInicial() <= nodo) {
            if(this.aristas[i].nodoInicial() == nodo) {
                l.insertarNodoPeso(
                                new NodoAlcanzable(this.aristas[i].nodoFinal(),
                                1,
                                this.aristas[i].peso()));
            }

            i++;
        }

        i = 0;
        NodoAlcanzable n;

        while((n = l.nodo(i)) != null) {
            int j = 0;

            while(j < this.sigEspVacio && 
                    this.aristas[j].nodoInicial() <= n.nodo()) {

                if(this.aristas[j].nodoInicial() == n.nodo() &&
                    this.aristas[j].nodoFinal() != nodo) {

                    l.insertarNodoPeso(
                                new NodoAlcanzable(this.aristas[j].nodoFinal(),
                                n.distancia() + 1,
                                n.peso() + this.aristas[j].peso()));
                }

                j++;
            }

            i++;
        }

        return l;
    }

    /**
     * Metodo guardarAristas - Guarda en un fichero de
     *                         texto todas las aristas del
     *                         grafo.
     *
     * @param nombreFichero Nombre del fichero de texto a
     *                      crear para guardar las aristas.
     */
    public void guardarAristas(String nombreFichero) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File(nombreFichero));
        } catch(FileNotFoundException fnfEx) {
            System.out.println("No se puede acceder al archivo " +
                                                                nombreFichero);
        }

        if(pw != null) {
            for(int i = 0; i < this.sigEspVacio; i++) {
                pw.printf(Locale.ENGLISH,"%-5d%-5d%-5.1f%n",
                        this.aristas[i].nodoInicial(),
                        this.aristas[i].nodoFinal(),
                        this.aristas[i].peso());
            }

            pw.close();
        }
    }

    /**
     * Metodo cargarAristas - Dado un fichero de texto,
     *                        genera un objeto grafo nuevo
     *                        con las aristas de dicho 
     *                        fichero.
     *
     * @param nombreFichero Nombre del fichero del que
     *                      leer los datos.
     *
     * @remarks Si un dato no esta bien escrito dentro del
     *          fichero, el grafo no se generara.
     *
     * @return Un nuevo grafo o null si no se ha podido
     *         generar correctamente.
     */
    public static Grafo cargarAristas(String nombreFichero) {
        Grafo grafo = null;
        Scanner sc = null;

        try {
            sc = new Scanner(new File(nombreFichero));
        } catch(FileNotFoundException fnfEx) {
            System.out.println("Error al intentar abrir el fichero: " +
                                                                nombreFichero);
        }

        if(sc != null) {
            Arista[] aristas = new Arista[1000];
            int ultimaArista = 0;

            while(sc.hasNextLine()) {
                Scanner linea = new Scanner(sc.nextLine().trim());
                linea.useLocale(Locale.ENGLISH);

                try {
                    int nodoInicial = linea.nextInt();
                    int nodoFinal = linea.nextInt();
                    double peso = linea.nextDouble();

                    aristas[ultimaArista] =
                                        new Arista(nodoInicial, nodoFinal, peso);

                    ultimaArista++;
                } catch(InputMismatchException imEx) {
                    System.out.println("Fichero corrupto");

                    char nada = Teclado.leerCaracter("Pulsa <ENTER> para " + 
                                                                    "continuar");
                }
            }

            sc.close();

            grafo = new Grafo(ultimaArista);

            for(int i = 0; i < ultimaArista; i++) {
                grafo.insertarArista(aristas[i]);
            }
        }

        return grafo;
    }

    /**
     * Metodo guardarNodoAlcanzablesDist - Guarda en un
     *                                     fichero de
     *                                     texto todos los
     *                                     nodos
     *                                     alcanzables por
     *                                     todos los nodos
     *                                     que hay en el
     *                                     grafo.
     *
     * @param nombreFichero Nombre del fichero donde
     *                      guardar los nodos alcanzables.
     *
     * @remarks Este metodo usa como criterio el minimo de
     *          distancia.
     */
    public void guardarNodosAlcanzablesDist(String nombreFichero) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File(nombreFichero));
        } catch(FileNotFoundException fnfEx) {
            System.out.println("No se puede acceder al archivo " +
                                nombreFichero);
        }

        if(pw != null) {
            if(!this.listaVacia()) {
                int ultimoNodo = this.aristas[0].nodoInicial() - 1;

                for(int i = 0; i < this.sigEspVacio; i++) {
                    if(ultimoNodo != this.aristas[i].nodoInicial()) {
                        ultimoNodo = this.aristas[i].nodoInicial();
                        ListaNodosAlcanzables nodos = 
                                        this.alcanzabilidadNodoDist(ultimoNodo);

                        int j = 0;
                        NodoAlcanzable n;

                        pw.println("NODO " + ultimoNodo);
                        while((n = nodos.nodo(j)) != null) {
                            pw.printf(Locale.ENGLISH,"%-5d%-5d%-5.1f%n",
                                        n.nodo(),
                                        n.distancia(),
                                        n.peso());

                            j++;
                        }

                        pw.println();
                    }
                }
            }

            pw.close();
        }
    }

   /**
     * Metodo guardarNodoAlcanzablesPeso - Guarda en un
     *                                     fichero de
     *                                     texto todos los
     *                                     nodos
     *                                     alcanzables por
     *                                     todos los nodos
     *                                     que hay en el
     *                                     grafo.
     *
     * @param nombreFichero Nombre del fichero donde
     *                      guardar los nodos alcanzables.
     *
     * @remarks Este metodo usa como criterio el minimo de
     *          peso.
     */
    public void guardarNodosAlcanzablesPeso(String nombreFichero) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new File(nombreFichero));
        } catch(FileNotFoundException fnfEx) {
            System.out.println("No se puede acceder al archivo " +
                                                                nombreFichero);
        }

        if(pw != null) {
            if(!this.listaVacia()) {
                int ultimoNodo = this.aristas[0].nodoInicial() - 1;

                for(int i = 0; i < this.sigEspVacio; i++) {
                    if(ultimoNodo != this.aristas[i].nodoInicial()) {
                        ultimoNodo = this.aristas[i].nodoInicial();
                        ListaNodosAlcanzables nodos = 
                                        this.alcanzabilidadNodoPeso(ultimoNodo);

                        int j = 0;
                        NodoAlcanzable n;

                        pw.println("NODO " + ultimoNodo);
                        while((n = nodos.nodo(j)) != null) {
                            pw.printf(Locale.ENGLISH,"%-5d%-5d%-5.1f%n",
                                        n.nodo(),
                                        n.distancia(),
                                        n.peso());

                            j++;
                        }

                        pw.println();
                    }
                }
            }

            pw.close();
        }
    }

    /**
     * Metodo listaLlena - Comprueba si la lista esta
     *                     llena.
     *
     * @return True si esta llena, sino False;
     */
    private boolean listaLlena() {
        return this.sigEspVacio == this.aristas.length;
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