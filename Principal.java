public class Principal {
    public static void main(String[] args) {
        int aristas;

        do {
            aristas = Teclado.leerEntero("Numero de aristas: ");
        }while(aristas < 1 || aristas > 1000);

        Grafo grafoT, grafoM;

        grafoT = new Grafo(aristas);
        grafoM = new Grafo(aristas);

        int accion;
        do {
            escribirMenu();

            accion = Teclado.leerEntero("\nAccion: ");

            switch(accion) {
                case 1:                    
                    break;
                case 2:
                    grafoT.insertarArista(obtenerNuevaArista());
                    break;
                case 3:
                    grafoT.eliminarArista(obtenerNuevaArista());
                    break;
                case 4:
                    grafoT.dibujarGrafo();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
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
    
    private static Arista obtenerNuevaArista() {
        int nodoInicial = Teclado.leerEntero("Nodo inicial: ");
        int nodoFinal = Teclado.leerEntero("Nodo final: ");
        double peso = Teclado.leerReal("Peso: ");
        
        return new Arista(nodoInicial, nodoFinal, peso);
    }
}