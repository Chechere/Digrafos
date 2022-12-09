public class Grafo {
    private Arista[] aristas;
    int sigEspacioVacio;
    
    public Grafo(int numeroAristas) {
        this.aristas = new Arista[numeroAristas];
        this.sigEspacioVacio = 0; 
        
        for(int i = 0; i < numeroAristas; i++) {
            this.aristas[i] = new Arista(0, 0, 0);
        }
    } 
    
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
    
    public void dibujarGrafo() {
        System.out.println("Nodo Inicial | Nodo Final | Peso ");
        System.out.println("---------------------------------\n");
        
        for(int i = 0; i < this.sigEspacioVacio; i++) {
            System.out.printf("%13d %12d %3.3f", 
                                this.aristas[i].obtenerNodoInicial(),
                                this.aristas[i].obtenerNodoFinal(),
                                this.aristas[i].obtenerPeso());
        }
    }
    
    private boolean listaVacia() {
        return this.sigEspacioVacio == 0;
    }
    
    private boolean listaLlena() {
        return this.sigEspacioVacio == this.aristas.length;
    }
}
