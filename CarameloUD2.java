//Laurene Coste

package practica2.juego;
import practica2.utils.ventanas.ventanaJuego.*;

public class CarameloUD2 {

	CoordTablero CoordTablero;
	String color;
	ObjetoDeJuego ObjetoDeJuego;


	/** Constructor de un objeto de clase caramelo
	 * Para los colores Azul, Verde, Rojo, y Magenta asocia cada caramelo a un objeto de juego correspondiente
	 * @param posFila Numero de fila en que esta
	 * @param posColumna Numero de columna en que esta
	 * @param color Color del caramelo
	 * @param ventanaJuego ventana en la que se visualisaran los objetos de juego de los caramelos
	 */
	public CarameloUD2(CoordTablero CoordTablero, String color, VentanaJuegoTablero ventanaJuego) {
		this.CoordTablero = CoordTablero;
		
		this.color = color;
		
		if (color.equalsIgnoreCase("azul")){
			this.ObjetoDeJuego = new ObjetoDeJuego("UD-blue.png", true, ventanaJuego.getAnchoCasilla(), ventanaJuego.getAltoCasilla());
		} else
		if (color.equalsIgnoreCase("rojo")){
			this.ObjetoDeJuego = new ObjetoDeJuego("UD-red.png", true, ventanaJuego.getAnchoCasilla(), ventanaJuego.getAltoCasilla());
		} else
		if (color.equalsIgnoreCase("magenta")){
			this.ObjetoDeJuego = new ObjetoDeJuego("UD-magenta.png", true, ventanaJuego.getAnchoCasilla(), ventanaJuego.getAltoCasilla());
		} else
		if (color.equalsIgnoreCase("verde")){
			this.ObjetoDeJuego = new ObjetoDeJuego("UD-green.png", true, ventanaJuego.getAnchoCasilla(), ventanaJuego.getAltoCasilla());
		}
	}


	public ObjetoDeJuego getObjetoDeJuego() {
		return ObjetoDeJuego;
	}
	
	public String getColor() {
		return this.color;
	}

	public CoordTablero getPosicionTablero() {
		return this.CoordTablero;
	}
	
	public void setPosicionTablero(CoordTablero nuevaCoordTablero) {
		this.CoordTablero = nuevaCoordTablero;
	}


}
