//Laurene Coste

//Por Hacer:
// corregir porque se queda sin hacer nada una vez dentro - errores en la funcion buscaYQuita (y en caen?)
// Anadir ventana.disminuir... a la funcion buscaYQuita

package practica2.juego;

import practica2.utils.ventanas.ventanaJuego.*;
import java.io.*;

public class DeustoCrash2 {

	/** Coge el objeto de juego de cade elemento del tablero y lo coloca en la posicion adecuada del tablero en ventana
	 * @param TableroCaramelos
	 * @param VentanaJuego
	 */
	public static void addTableroVentana(TableroCaramelos2 TableroCaramelos, VentanaJuegoTablero VentanaJuego){
		int numFilas = TableroCaramelos.getNumFilas();
		int numColumnas = TableroCaramelos.getNumColumnas();

		for (int fil = 0; fil<numFilas; fil++) {
			for (int col = 0; col<numColumnas; col++) {
				CoordTablero tempCoord = new CoordTablero(fil, col);
				CarameloUD2 tempCaramelo = TableroCaramelos.getCaramelo(tempCoord);
				VentanaJuego.addObjeto(tempCaramelo.getObjetoDeJuego(), tempCoord);
			}
		}
	}


	/** Metodo para comprobar si quedan movimientos posibles, aka si hay dos caramelos contiguos.
	 * @param tableroC El tablero en que estan los caramelos.
	 * @return returns true si hay movimientos posibles, false si no
	 */
	public static boolean hayMovimientosPosibles(TableroCaramelos2 tableroC) {
		int numFilas = tableroC.getNumFilas();
		int numColumnas = tableroC.getNumColumnas();
		boolean hayPosible = false;

	
		for (int fil=1; fil<numFilas-1; fil++) {
			for (int col=1; col<numColumnas-1; col++) {
				if (tableroC.getCaramelo(new CoordTablero(fil, col))!= null && ((tableroC.getCaramelo(new CoordTablero(fil, col+1))!=null )|| tableroC.getCaramelo(new CoordTablero(fil, col-1))!=null )
						|| (tableroC.getCaramelo(new CoordTablero(fil+1, col))!=null )|| (tableroC.getCaramelo(new CoordTablero(fil-1, col))!=null )){
					hayPosible = true;
				}
			}
		}
		return hayPosible;
	}

	/** Busca si hay lineas horizontales (primero) o verticales (segundo) de 3 o mas caramelos del mismo color, y si hay las quita
	 * en el tablero y en la ventana de juego
	 * @param tableroC El tablero en que estan los caramelos.
	 * @param ventanaJuego La ventana en la que esta el juego
	 * Si ha quitado lineas, devuelve true, y sino false
	 */
	public static boolean buscaYQuitaLineas(TableroCaramelos2 tableroC, VentanaJuegoTablero ventanaJuego) {
		boolean haQuitadoAlgo = false;

		// Busca y quita lineas horizontales
		int numFilas = tableroC.getNumFilas();
		int numColumnas = tableroC.getNumColumnas();
		
		for (int fil = 0; fil<numFilas; fil++) {
			for (int col = 0; col<numColumnas; col++){
				if (col<numColumnas-2 && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil, col+1) && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil, col+2)) {
					for (int contador=numColumnas-3; contador>0; contador--){
						if (col < contador && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil, col+(numColumnas-contador))) {
							tableroC.quitaCaramelo(new CoordTablero(fil, col+(numColumnas - contador)), ventanaJuego);
						}
					}
					tableroC.quitaCaramelo(new CoordTablero(fil, col), ventanaJuego);
					tableroC.quitaCaramelo(new CoordTablero(fil, col+1), ventanaJuego);
					tableroC.quitaCaramelo(new CoordTablero(fil, col+2), ventanaJuego);
					haQuitadoAlgo = true;
				}
			}
		}
		// Busca y quita lineas verticales 
		for (int col = 0; col<numColumnas; col++) {
			for (int fil = 0; fil<numFilas; fil++){
				if (fil<numFilas-2 && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil+1, col) && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil+2, col)) {
					for (int contador=numFilas-3; contador>0; contador--){
						if (fil < contador && tableroC.getColorSimple(fil, col) == tableroC.getColorSimple(fil+(numFilas-contador), col)) {
							tableroC.quitaCaramelo(new CoordTablero(fil+(numFilas - contador), col), ventanaJuego);
						}
					}
					tableroC.quitaCaramelo(new CoordTablero(fil, col), ventanaJuego);
					tableroC.quitaCaramelo(new CoordTablero(fil+1, col), ventanaJuego);
					tableroC.quitaCaramelo(new CoordTablero(fil+2, col), ventanaJuego);
					haQuitadoAlgo = true;
				}
			}
		}
		return haQuitadoAlgo;
	}


	/** Metodo para comprobar si caramelos "en suspenso" y hacerlos caer hasta el siguiente caramelo por abajo 
	 * @param tableroC El tablero en que estan los caramelos.
	 * @param ventanaJuego La ventana en que se visualiza el juego
	 * Si ha hecho caer caramelos, devuelve true, y sino false 
	 */	
	private static boolean caenLasPiezas(TableroCaramelos2 tableroC, VentanaJuegoTablero ventanaJuego) { 
		int numFilas = tableroC.getNumFilas();
		int numColumnas = tableroC.getNumColumnas();

		boolean haCaidoAlguna = true;
		while (haCaidoAlguna) {
			haCaidoAlguna = false;
			for (int col = 0; col<numColumnas; col++){
				for (int fil = (numFilas-2); fil>-1; fil--) {
					if (tableroC.getCaramelo(new CoordTablero(fil, col)) != null && tableroC.getCaramelo(new CoordTablero(fil+1, col)) == null) {
						tableroC.mueveCaramelo(new CoordTablero(fil, col), new CoordTablero(fil+1, col), ventanaJuego);
						haCaidoAlguna = true; 
					}
				}
			}
		}

		return haCaidoAlguna;
	}

	private static BufferedReader brTeclado=new BufferedReader(new InputStreamReader(System.in));

	/** Lee un entero positivo de teclado
	 * @return Entero leída, o -1 si error en la entrada
	 */
	public static int leerEnteroDeTeclado() {
		try {
			String lin = brTeclado.readLine();
			if (lin==null) return -1;
			return Integer.parseInt( lin );
		} catch (Exception e) {
			return -1;
		}
	} 


	public static void main(String[] args) {
		//Se que no esta bien hecho para nada ese bucle pero ni siquiera esa version simple me funciona!		
		int FILAS = 6;
		int COLUMNAS = 6;
		VentanaJuegoTablero ventana = new VentanaJuegoTablero(960, 720, FILAS, COLUMNAS, true);
		TableroCaramelos2 tablero = new TableroCaramelos2(FILAS, COLUMNAS, ventana);
		addTableroVentana(tablero, ventana);
		while (!ventana.isClosed()){
			ventana.esperaUnRato(300);
			buscaYQuitaLineas(tablero, ventana);
			ventana.esperaUnRato(250);
			caenLasPiezas(tablero, ventana);
			while(hayMovimientosPosibles(tablero)){
				ventana.showMessage("Juego en curso");
				buscaYQuitaLineas(tablero, ventana);
				ventana.esperaUnRato(250);
				caenLasPiezas(tablero, ventana);
				CoordTablero inicio = ventana.readInicioDrag();
				if (inicio!= null){
					CoordTablero destino = ventana.getFinalDrag();
					if (destino!= null && ((destino.getFila()==inicio.getFila() && destino.getColumna()==(inicio.getColumna()+1) )|| (destino.getFila()==inicio.getFila() && destino.getColumna()==(inicio.getColumna()-1) ) || 
							(destino.getColumna()==inicio.getColumna() && destino.getFila()==(inicio.getFila()+1) )|| (destino.getColumna()==inicio.getColumna() && destino.getFila()==(inicio.getFila()-1) ))){
						tablero.intercambioCaramelos(inicio, destino, ventana);
						ventana.esperaUnRato(250);
					}
				}
				
			}
			ventana.showMessage("No queda movimiento por hacer - Juego Terminado");
		}
		ventana.finish();
	}

}
