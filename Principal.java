public class Principal {
    public static void main(String[] args) {
        Grafo grafoT, grafoM;

        grafoT = Grafo.leerGrafo();
        grafoM = null;

        int accion;

        do {
            escribirMenu();
            accion = Teclado.leerEntero("\nAccion: ");

            switch(accion) {
                case 1:
                    System.out.println("\fEste grafo se guardara en memoria");

                    if(grafoM == null ||
                        leerBoolean("Ya hay un grafo en memoria, " +
                                    "¿Desea sobreescribirlo? (s/n): ")) {
                        grafoM = grafoT;
                        grafoT = Grafo.leerGrafo();
                    }
                    break;

                case 2:
                    grafoT.insertarArista(obtenerNuevaArista(true));
                    break;

                case 3:
                    grafoT.eliminarArista(obtenerNuevaArista(false));
                    break;

                case 4:
                    dibujarGrafo(grafoT);
                    break;

                case 5:
                    obtenerPeso(grafoT);
                    break;

                case 6:
                    grafoT.modificarPesoArista(obtenerNuevaArista(true));
                    break;

                case 7:
                    bucles(grafoT);
                    break;

                case 8:
                    gradoNodo(grafoT);
                    break;

                case 9:
                    nodosTerminales(grafoT);
                    break;

                case 10:
                    grafoT = fusionarGrafos(grafoT, grafoM);
                    break;

                case 11:
                    if(grafoM != null) {
                        Grafo temp = grafoM;

                        grafoM = grafoT;
                        grafoT = temp;
                    } else {
                        System.out.println("No hay ningun grafo en memoria " + 
                                            "todavia.\nCree un nuevo grafo" +
                                            "para guardar este en memoria.");

                        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para " +
                                                         "continuar");
                    }
                    break;

                case 12:
                    if(grafoM == null ||
                        !leerBoolean("Ya hay un grafo en memoria, " +
                                        "¿Quiere cancelar la operación? (s/n): ")
                    ) {

                        if(grafoM != null &&
                            leerBoolean("¿Desea guardar este grafo en " +
                                        "memoria (Sobreescribira el que " +
                                        "ya hay)? \nSi no, sobrescribira" +
                                        " el que tiene actualmente (s/n): ")) {

                            grafoM = grafoT;
                        }

                        int nodoCentral = Teclado.leerEntero("¿Nodo? ");
                        grafoT = grafoT.subGrafo(nodoCentral);
                    }
                    break;

                case 13:
                    alcanzabilidadNodoDist(grafoT);
                    break;

                case 14:
                    alcanzabilidadNodoPeso(grafoT);
                    break;

                case 15:
                    guardarGrafo(grafoT);
                    break;

                case 16:
                    if(leerBoolean("Se va a borrar el grafo actual " +
                                    "¿Seguro que quiere continuar? (s/n): ")) {

                        Grafo g = cargarGrafo();

                        if(g != null) {
                            grafoT = g;
                        }
                    }
                    break;

                case 17:
                    guardarNodosAlcanzablesDist(grafoT);
                    break;

                case 18:
                    guardarNodosAlcanzablesPeso(grafoT);
                    break;
            }
        } while(accion != 19);
    }

    /**
     * Método escribirMenu - Muestra el menú con las
     *                       diferentes acciones que el
     *                       usuario puede realizar para
     *                       modificar el grafo.
     */
    private static void escribirMenu() {
        System.out.println("\f\t\tGrafo" + 
                            "\n---------------------------------\n" +
                            "\n1 - Crear nuevo grafo de trabajo." +
                            "\n2 - Añadir una arista." +
                            "\n3 - Quitar una arista." +
                            "\n4 - Escribir grafo por pantalla." +
                            "\n5 - Obtener peso arista." +
                            "\n6 - Modificar peso arista." +
                            "\n7 - Obtener número de bucles del grafo." +
                            "\n8 - Obtener grado de un nodo." +
                            "\n9 - Obtener nodos terminales." +
                            "\n10 - Fusionar grafo de trabajo y de memoria." +
                            "\n11 - Intercambiar grafo de trabajo y de memoria." +
                            "\n12 - Generar subgrafo." +
                            "\n13 - Obtener nodos alcanzables por un nodo " + 
                                                              "(DISTANCIA)" +
                            "\n14 - Obtener nodos alcanzables por un nodo " + 
                                                                  "(PESO)." +
                            "\n15 - Guardar grafo en fichero." +
                            "\n16 - Cargar grafo desde fichero." +
                            "\n17 - Guardar nodos alcanzables en fichero " + 
                                                            "(DISTANCIA)." +
                            "\n18 - Guardar nodos alcanzables en fichero " + 
                                                                 "(PESO)." +
                            "\n19 - Salir.");
    }

    /**
     * Método obtenerNuevaArista - Genera una nueva arista.
     *
     * @param preguntarPeso - True si se necesita que la
     *                        arista contenga un peso,
     *                        sino False.
     *
     * @return La Arista deseada.
     */
    private static Arista obtenerNuevaArista(boolean preguntarPeso) {
        int nodoInicial = Teclado.leerEntero("Nodo inicial: ");
        int nodoFinal = Teclado.leerEntero("Nodo final: ");
        double peso = -1.0;

        if(preguntarPeso) {
            do {
                peso = Teclado.leerReal("Peso (Solo pos): ");
            } while(peso < 0);
        }

        return new Arista(nodoInicial, nodoFinal, peso);
    }

    /**
     * Método leerBoolean - Lee la respuesta a una
     *                      pregunta de "Si" o "No".
     *
     * @param pregunta Pregunta a realizar.
     *
     * @return True si la respuesta es "Si", sino False.
     */
    private static boolean leerBoolean (String pregunta) {
        char c;

        do {
            c = Teclado.leerCaracter(pregunta);
        } while(c != 's' && c != 'S' && c != 'n' && c != 'N');

        return c == 's' || c == 'S';
    }

    /**
     * Método fusionarGrafos - fusiona el grafo de trabaja
     *                         con el de memoria.
     *
     * @param grafoT Grafo de trabajo.
     * @param grafoM Grafo de memoria.
     *
     * @return La fusión o el grafo de trabajo si el grafo
     *         de memoria es nulo.
     */
    private static Grafo fusionarGrafos(Grafo grafoT, Grafo grafoM) {
        Grafo fusion = grafoT;

        if(grafoM != null) {
            fusion = grafoT.fusionarCon(grafoM);
        } else {
            System.out.println("No hay ningun grafo en memoria todavia." +
                                "\nCree un nuevo grafo para guardar este" +
                                " en memoria.");

            char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
        }

        return fusion;
    }

    /**
     * Método nodosTerminales - Muestra por pantalla la
     *                          cantidad de nodos
     *                          terminales que tiene un
     *                          grafo.
     *
     * @param grafo Grafo en el que buscar los nodos
     *              terminales.
     */
    private static void nodosTerminales(Grafo grafo) {
        int terminales = grafo.nodosTerminales();

        System.out.println("\nHay " + terminales + " nodos terminales.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método gradoNodo - Muestra por pantalla el grado de
     *                    un nodo de un determinado grafo.
     *
     * @param grafo Grafo donde se encuentra el nodo.
     */
    private static void gradoNodo(Grafo grafo) {
        int nodo = Teclado.leerEntero("Nodo buscado? ");
        int grado = grafo.gradoNodo(nodo);

        System.out.println("Parten del nodo " + nodo + ", un total de " +
                            grado + " arista/s.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método bucles - Muestra por pantalla el numero de
     *                 bucles que tiene un determinado
     *                 grafo.
     *
     * @param grafo Grafo donde buscar los bucles.
     */
    private static void bucles(Grafo grafo) {
        int bucles = grafo.bucles();

        System.out.println("\nHay " + bucles + " bucle/s en el grafo.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método obtenerPeso - Muestra por pantalla el peso
     *                      de una arista de un
     *                      determinado grafo.
     *
     * @param grafo Grafo donde se encuentra la arista.
     */
    private static void obtenerPeso(Grafo grafo) {
        double peso = grafo.obtenerPesoArista(obtenerNuevaArista(false));

        if(peso >= 0) {
            System.out.println("La arista pesa: " + peso);
        } else {
            System.out.println("La arista no existe");
        }

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar.");
    }

    /**
     * Método dibujarGrafo - Muestra por pantalla una
     *                       lista de todas las aristas
     *                       que tiene un determiando
     *                       grafo.
     *
     * @param grafo grafo a mostrar por pantalla.
     */
    private static void dibujarGrafo(Grafo grafo) {
        grafo.dibujarGrafo();

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar.");
    }

    /**
     * Metodo alcanzabilidadNodoDist - Muestra por
     *                                 pantalla el numero
     *                                 de nodos que son
     *                                 alcanzables por
     *                                 otro nodo de un
     *                                 determinado grafo.
     *
     * @param grafo Grafo donde se encuentra el nodo.
     *
     * @remarks La función busca segun la menor distancia
     *          posible.
     */
    private static void alcanzabilidadNodoDist(Grafo grafo) {
        int nodo = Teclado.leerEntero("¿Nodo? ");

        ListaNodosAlcanzables nodos = grafo.alcanzabilidadNodoDist(nodo);

        nodos.escribirLista();
        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar.");
    }

    /**
     * Metodo alcanzabilidadNodoPeso - Muestra por
     *                                 pantalla el numero
     *                                 de nodos que son
     *                                 alcanzables por
     *                                 otro nodo de un
     *                                 determinado grafo.
     *
     * @param grafo Grafo donde se encuentra el nodo.
     *
     * @remarks La función busca segun el menor peso
     *          posible.
     */
    private static void alcanzabilidadNodoPeso(Grafo grafo) {
        int nodo = Teclado.leerEntero("¿Nodo? ");

        ListaNodosAlcanzables nodos = grafo.alcanzabilidadNodoPeso(nodo);

        nodos.escribirLista();
        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar.");
    }

    /**
     * Metodo guardarGrafo - Guarda el grafo dentro de un
     *                       fichero de texto.
     *
     * @param grafo Grafo a guardar.
     */
    private static void guardarGrafo(Grafo grafo) {
        String nombre = Teclado.leerCadena("Nombre del archivo: ");

        if(!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        grafo.guardarAristas(nombre);
    }

    /**
     * Metodo cargarGrafo - Crea un grafo nuevo a partir
     *                      de un fichero de texto.
     *
     * @return Un grafo nuevo o null si no se puede
     *         generar el grafo.
     */
    private static Grafo cargarGrafo() {
        String nombre = Teclado.leerCadena("Nombre del archivo: ");
        
        if(!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }
        
        return Grafo.cargarAristas(nombre);
    }

    /**
     * Metodo guardarNodosAlcanzablesDist - Genera un
     *                                      fichero de
     *                                      texto con
     *                                      todos los
     *                                      nodos
     *                                      alcanzables
     *                                      por todos los
     *                                      nodos del
     *                                      grafo.
     *
     * @param grafo Grafo del que generar el fichero.
     *
     * @remarks Este metodo guarda los nodos alcanzables
     *          con el criterio de minima distancia.
     */
    private static void guardarNodosAlcanzablesDist(Grafo grafo) {
        String nombre = Teclado.leerCadena("Nombre del archivo: ");

        if(!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        grafo.guardarNodosAlcanzablesDist(nombre);
    }

    /**
     * Metodo guardarNodosAlcanzablesPeso - Genera un
     *                                      fichero de
     *                                      texto con
     *                                      todos los
     *                                      nodos
     *                                      alcanzables
     *                                      por todos los
     *                                      nodos del
     *                                      grafo.
     *
     * @param grafo Grafo del que generar el fichero.
     *
     * @remarks Este metodo guarda los nodos alcanzables
     *          con el criterio de minimo peso.
     */
    private static void guardarNodosAlcanzablesPeso(Grafo grafo) {
        String nombre = Teclado.leerCadena("Nombre del archivo: ");

        if(!nombre.endsWith(".txt")) {
            nombre += ".txt";
        }

        grafo.guardarNodosAlcanzablesPeso(nombre);
    }
}