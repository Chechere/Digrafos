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
                        System.out.println("No hay ningun grafo en memoria todavia." +
                                            "\nCree un nuevo grafo para guardar este" +
                                            " en memoria.");

                        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
                    }                   
                    break;
                
                case 12:
                    if(grafoM == null || 
                        !leerBoolean("Ya hay un grafo en memoria, " + 
                                        "¿Quiere cancelar la operación? (s/n): ")) {
                                                
                        if(grafoM != null &&
                            leerBoolean("¿Desea guardar este grafo en " + 
                                        "memoria (Sobreescribira el que " + 
                                        "ya hay)? \n" + 
                                        "Si no, sobreescribira el que tiene " + 
                                        "actualmente (s/n): ")) {
                            
                            grafoM = grafoT;
                        }
                        
                        int nodoCentral = Teclado.leerEntero("¿Nodo? ");
                        grafoT = grafoT.subGrafo(nodoCentral);
                    }
                    break;

                case 13:
                    alcanzabilidadNodoDist(grafoT);
                    break;
            }
        }while(accion != 14);
    }

    /**
     * Método escribirMenu - Muestra el menú con las
     *                       diferentes acciones que
     *                       el usuario puede realizar
     *                       para modificar el grafo.
     */
    private static void escribirMenu() {
        System.out.println("\f\t\tGrafo");
        System.out.println("---------------------------------\n");
        System.out.println("1 - Crear nuevo grafo de trabajo.");
        System.out.println("2 - Añadir una arista.");
        System.out.println("3 - Quitar una arista.");
        System.out.println("4 - Escribir grafo por pantalla.");
        System.out.println("5 - Obtener peso arista.");
        System.out.println("6 - Modificar peso arista.");
        System.out.println("7 - Obtener número de bucles del grafo.");
        System.out.println("8 - Obtener grado de un nodo.");
        System.out.println("9 - Obtener nodos terminales.");
        System.out.println("10 - Fusionar grafo de trabajo y de memoria.");
        System.out.println("11 - Intercambiar grafo de trabajo y de memoria.");
        System.out.println("12 - Generar subgrafo.");
        System.out.println("13 - Obtener nodos alcanzables por un nodo.");
        System.out.println("14 - Salir.");
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
     * Método leerBoolean - Lee la respuesta a una pregunta de Si o No.
     * 
     * @param pregunta Pregunta a realizar.
     * 
     * @return True si la respuesta es Si, sino False.
     */
    private static boolean leerBoolean (String pregunta) {
        char c;

        do {
            c = Teclado.leerCaracter(pregunta);
        } while(c != 's' && c != 'S' && c != 'n' && c != 'N');

        return c == 's' || c == 'S';
    }

    /**
     * Método fusionarGrafos - fusiona el grafo de
     *                         trabajo con el de
     *                         memoria.
     *
     * @param grafoT Grafo de trabajo.
     * @param grafoM Grafo de memoria.
     *
     * @return La fusión o el grafo de trabajo
     *         si el grafo de memoria es nulo.
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
     * @param grafo Grafo en el que buscar los
     *              nodos terminales.
     */
    private static void nodosTerminales(Grafo grafo) {
        int terminales = grafo.nodosTerminales();

        System.out.println("\nHay " + terminales + " nodos terminales.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método gradoNodo - Muestra por pantalla el
     *                    grado de un nodo de un
     *                    determinado grafo.
     *
     * @param grafo Grafo donde se encuentra el nodo.
     */
    private static void gradoNodo(Grafo grafo) {
        int nodo = Teclado.leerEntero("Nodo buscado? ");
        int grado = grafo.gradoNodo(nodo);

        System.out.println("Parten del nodo " + nodo +
                            ", un total de " + grado + " arista/s.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método bucles - Muestra por pantalla el
     *                 número de bucles que
     *                 tiene un determinado grafo.
     *
     * @param grafo Grafo donde buscar los bucles.
     */
    private static void bucles(Grafo grafo) {
        int bucles = grafo.bucles();

        System.out.println("\nHay " + bucles + " bucle/s en el grafo.");

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
    }

    /**
     * Método obtenerPeso - Muestra por pantalla el
     *                      peso de una arista de
     *                      un determinado grafo.
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
     *                       lista de todas las
     *                       aristas que tiene un
     *                       determinado grafo.
     *
     * @param grafo grafo a mostrar por pantalla.
     */
    private static void dibujarGrafo(Grafo grafo) {
        grafo.dibujarGrafo();

        char nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar.");
    }

    /**
     * Método alcanzabilidadNodoDist -
     *      Muestra por pantalla el
     *      número de nodos que son
     *      alcanzables por otro nodo
     *      de un determinado grafo.
     *
     * @param grafo Grafo donde se
     *        encuentra el nodo.
     *
     * @remarks La función busca
     *          según la menor
     *          distancia posible.
     */
    private static void alcanzabilidadNodoDist(Grafo grafo) {
        int nodo = Teclado.leerEntero("¿Nodo? ");

        grafo.alcanzabilidadNodo(nodo);

        char nada = Teclado.leerCaracter("Pulsa <ENTER> para continuar.");
    }
}