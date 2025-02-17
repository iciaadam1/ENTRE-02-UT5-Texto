import java.util.Arrays;
import java.util.Scanner;

/**
 * Un objeto de esta clase guarda en un array las diferentes
 * palabras que hay en un texto
 *
 * Cada palabra es un objeto Palabra que guarda la palabra (como String)
 * y su frecuencia de aparici�n en el texto
 *
 * El array guarda como m�ximo n palabras distintas
 *
 *
 */
public class Texto {

    private Palabra[] palabras;
    private int total;

    /**
     * Constructor
     * Crea el array al tama�o n
     * e inicializa adecuadamente el resto de atributos
     */
    public Texto(int n) {
        palabras = new Palabra[n];
        total = 0;
    }

    /**
     *
     * @return true si el texto est� completo
     */
    public boolean textoCompleto() {
        return total == palabras.length;
    }

    /**
     *
     * @return el n� de palabras distintas aparecidas en
     * el texto y guardadas en el array
     */
    public int totalPalabras() {
        return total;
    }

    /**
     * Dada una l�nea de texto conteniendo diferentes palabras
     * el m�todo extre las palabras y las inserta en el array
     *
     * Las palabras en la l�nea est�n separadas por uno o varios espacios
     * seguidos, o por el punto o por la coma
     * Puede haber espacios al comienzo y final de la l�nea
     *
     * Ej - "   a single      type.  " contiene tres palabras: a single type
     *      "y un mozo de campo y plaza  "  contiene 7 palabras
     *
     * Antes de insertar una palabra habr� que comprobar que no se
     * ha a�adido previamente. 
     * Si ya se ha a�adido solo hay que incrementar su frecuencia de
     * aparici�n 
     * Si no est� la palabra y hay sitio en el array para una nueva
     * se inserta de forma que quede ordenada (!!no se ordena, se
     * inserta en orden!!)
     *  
     * Hay que usar como m�todos de ayuda estaPalabra() e
     * insertarPalabraEnOrden()
     */
    public void addPalabras(String linea) {
        String[] lineas = linea.trim().split("[.\\,\\s]+");
        for(int i = 0; i < lineas.length; i++){
            if(estaPalabra(lineas[i]) != -1){
                palabras[estaPalabra(lineas[i])].incrementar();
            }
            else if(total == palabras.length){
                System.out.println("No se pueden a�adir mas palabras");
            }
            else{
                insertarPalabraEnOrden(lineas[i]);

            }
        }

    }

    /**
     *  dada una palabra devuelve la posici�n en la que se
     *  encuentra en el array o -1 si no est�
     *
     *  Indiferente may�sculas y min�sculas
     */
    public int estaPalabra(String palabra) {
        for(int i = 0; i < total; i++){
            if((palabras[i].getPalabra().toLowerCase()).equals(palabra.toLowerCase()) ){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param palabra inserta la palabra en el lugar adecuado
     *                de forma que el array palabras quede ordenado
     *                alfab�ticamente
     *  Solo hay que insertar en este m�todo, se asume que la palabra
     *                no est� y que es posible a�adirla
     *
     */
    private void insertarPalabraEnOrden(String palabra) {
        Palabra pal = new Palabra(palabra);
        if(total == 0){
            palabras[0] = pal; 
            total ++;
        }
        int i = total - 1;
        while (palabras[i].getPalabra() == palabra) {
            palabras[i + 1] = palabras[i];
            i -- ;
        }
        palabras[i + 1] = pal; 
        total ++;

    }

    /**
     * Representaci�n textual del array de palabras
     * Cada palabra y su frecuencia de aparici�n
     * Se muestran en l�neas de 5 en 5 palabras
     * (ver enunciado)
     *
     * De forma eficiente ya que habr� muchas concatenaciones
     *
     *
     */
    public String toString() {
        String str = "";
        for(int i = 0; i < total; i++){
            for(int j = 0; j < 5; j++){
                if(i < total){
                    str += palabras[i] + " ( "+ palabras[i].getFrecuencia() + ")";
                    i++;
                }
            }
            str += "\n";
        }
        return str;

    }

    /**
     *  Devuelve la palabra de la posici�n p
     *  Si p es incorrecto se devuelve null
     *      
     */
    public Palabra getPalabra(int p) {
        if(p < 0 || p >= total){
            return null;
        }
        return palabras[p];
    }

    /**
     *
     * @return un array de cadenas con las palabras del texto
     * capitalizadas de forma alterna
     */
    public String[] capitalizarAlterna() {
        String alterna[] = new String[palabras.length];
        for(int i = 0; i < total; i++){
            alterna[i] = Utilidades.capitalizarAlterna(palabras[i].getPalabra());
        }
        return alterna;
    }

    /**
     *
     * @return un array de cadenas con las palabras que tienen letras
     * repetidas
     */
    public String[] palabrasConLetrasRepetidas() {
        int pos = 0;
        String repetidas[] = new String[palabras.length];
        for(int i = 0; i < total; i++){
            if(Utilidades.tieneLetrasRepetidas(palabras[i].getPalabra())){
                repetidas[pos] = palabras[i].getPalabra();
                pos ++;
            }
        }
        return repetidas;    
    }

    /**
     *
     * @return un array con la frecuencia de palabras de cada longitud
     * La palabra m�s larga consideraremos de longitud 15
     *
     */
    public int[] calcularFrecuenciaLongitud() {
        int pos = 0;
        int frecuencia[] = new int[palabras.length];
        for (int i = 0; i < total; i++){
            for(int j = 0; j < 15; j++){
                if(palabras[i].getPalabra().length() == j){
                    frecuencia[j] ++;
                }
            }
        }
        return frecuencia;
    }

    /**
     *
     * @param frecuencia se borra del array palabras aquellas de frecuencia
     *                   menor a la proporcionada
     * @return el n de palabras borradas
     */
    public int borrarDeFrecuenciaMenor(int frecuencia) {
        int borradas = 0;
        for(int i = 0; i < total; i++){
            if(palabras[i].getFrecuencia() < frecuencia){
                for(int j = total; j > i; j--){
                    palabras[i] = palabras[i+1];
                    borradas ++;
                }
            }
        }

        return borradas;
    }

    /**
     *  Lee de un fichero un texto formado por una
     *  serie de l�neas.
     *  Cada l�nea contiene varias palabras separadas
     *  por espacios o el . o la ,
     *
     */
    public void leerDeFichero(String nombre) {
        Scanner sc = new Scanner(
                this.getClass().getResourceAsStream(nombre));
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            this.addPalabras(linea);
        }
        sc.close();

    }
}
