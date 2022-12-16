public class Principal {
    public static void main(String[] args) {
        char nada;        
        int aristas;

        do {
            aristas = Teclado.leerEntero("Numero de aristas: ");
        }while(aristas < 1 || aristas > 1000);

        Grafo grafoT, grafoM;

        grafoT = new Grafo(aristas);
        grafoM = null;

        int accion;
        do {
            escribirMenu();

            accion = Teclado.leerEntero("\nAccion: ");

            switch(accion) {
                case 1:
                    boolean guardar = true;
                    
                    System.out.println("\fEste grafo se guardara en memoria");
                                        
                    if(grafoM != null) {
                        char eleccion;
                        
                        do {
                            eleccion = Teclado.leerCaracter(
                                "Ya hay un grafo en memoria, ¿Desea sobreescribirlo? ");
                        }while(eleccion != 'S' && eleccion != 's' &&
                                eleccion != 'N' && eleccion != 'n');
                                
                        guardar = eleccion == 'S' || eleccion == 's';
                    } 
                    
                    if(guardar) {
                        grafoM = grafoT;
                        
                        do {
                            aristas = Teclado.leerEntero("Numero de aristas nuevo grafo: ");
                        }while(aristas < 1 || aristas > 1000);
                        
                        grafoT = new Grafo(aristas);
                    }
                    break;
                    
                case 2:
                    grafoT.insertarArista(obtenerNuevaArista(true));
                    break;
                    
                case 3:
                    grafoT.eliminarArista(obtenerNuevaArista(false));
                    break;
                    
                case 4:
                    grafoT.dibujarGrafo();
                    nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
                    break;
                    
                case 5:
                    break;
                    
                case 6:
                    grafoT.modificarPesoArista(obtenerNuevaArista(true));
                    break;
                    
                case 7:
                    int bucles = grafoT.bucles();                    
                    System.out.println("\nHay " + bucles + " bucle/s en el grafo.");                    
                    nada = Teclado.leerCaracter("\nPulsa <ENTER> para continuar");
                    break;
                    
                case 8:
                    break;
                    
                case 9:
                    int terminales = grafoT.nodosTerminales();
                    System.out.println("\nHay " + terminales + " nodos terminales.");
                    break;
                    
                case 10:
                    break;
                    
                case 11:
                    break;
                    
                case 12:
                    break;
                    
                case 13:
                    break;  
            }

        }while(accion != 14);
    }

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
    
    private static Arista obtenerNuevaArista(boolean preguntarPeso) {
        int nodoInicial = Teclado.leerEntero("Nodo inicial: ");
        int nodoFinal = Teclado.leerEntero("Nodo final: ");
        double peso;
        
        if(preguntarPeso) {
            peso = Teclado.leerReal("Peso: ");
        } else {
            peso = 0;
        }
        
        return new Arista(nodoInicial, nodoFinal, peso);
    }
}