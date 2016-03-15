//Laurene Coste

package practica2.juego;
import practica2.utils.ventanas.ventanaJuego.*;
import java.util.Random;

public class TableroCaramelos2 {

	private CarameloUD2 tablero[][];
	private int numFilas;
	private int numColumnas;


	public TableroCaramelos2(int numFilas, int numColumnas, VentanaJuegoTablero ventanaJuego ) {
		tablero = new CarameloUD2[numFilas][numColumnas];

		this.numFilas = numFilas;
		this.numColumnas = numColumnas;


		for (int fil = 0; fil<numFilas; fil++) {
			for (int col = 0; col<numColumnas; col++) {
				CoordTablero CoordTabl = new CoordTablero(fil, col);
				tablero[fil][col] = new CarameloUD2(CoordTabl, randomColor(), ventanaJuego);
			}
		}
	}

	public int getNumFilas() {
		return numFilas;
	}

	public int getNumColumnas() {
		return numColumnas;
	}


	private static String randomColor() {
		Random randomCall = new Random();
		int randomColor = randomCall.nextInt(4);
		if (randomColor == 0) {
			return "Magenta";
		} else	if (randomColor == 1) {
			return "Verde";
		} else if (randomColor == 2) {
			return "Rojo";
		} else {
			return "Azul";
		}
	}

	public void setCaramelo(CarameloUD2 c, CoordTablero CoordTablero, VentanaJuegoTablero ventanaJuego) {
		int posFil = CoordTablero.getFila();
		int posCol = CoordTablero.getColumna();
		tablero[posFil][posCol] = c;
		c.setPosicionTablero(CoordTablero);
		ventanaJuego.setPosTablero(c.getObjetoDeJuego(), CoordTablero);
	}

	public CarameloUD2 getCaramelo(CoordTablero coordTablero) {
		int posFil = coordTablero.getFila();
		int posCol = coordTablero.getColumna();
		
		CarameloUD2 temp = tablero[0][0];
		
		if (posFil> this.numFilas-1 || posCol> this.numColumnas-1){
			temp = null;
		} else{
			temp = tablero[posFil][posCol];
		}
		return temp;
	}

	public void quitaCaramelo(CoordTablero coordTablero, VentanaJuegoTablero ventanaJuego) {
		int posFil = coordTablero.getFila();
		int posCol = coordTablero.getColumna();
		ObjetoDeJuego temp = tablero[posFil][posCol].getObjetoDeJuego();
		ventanaJuego.removeObjeto(temp);
		tablero[posFil][posCol] = null;		

	}

	public void mueveCaramelo(CoordTablero coordOrigen, CoordTablero coordDestino, VentanaJuegoTablero ventanaJuego) {
		int posFilOrigen = coordOrigen.getFila();
		int posColOrigen = coordOrigen.getColumna();
		int posFilDestino = coordDestino.getFila();
		int posColDestino = coordDestino.getColumna();

		CarameloUD2 temporario = tablero[posFilOrigen][posColOrigen];
		tablero[posFilDestino][posColDestino] = temporario;
		tablero[posFilOrigen][posColOrigen] = null;
		tablero[posFilDestino][posColDestino].setPosicionTablero(coordDestino);

		ventanaJuego.setPosTablero(temporario.getObjetoDeJuego(), coordDestino);
	}

	public boolean intercambioCaramelos(CoordTablero coordOrigen, CoordTablero coordDestino, VentanaJuegoTablero ventanaJuego) {
		int posFilOrigen = coordOrigen.getFila();
		int posColOrigen = coordOrigen.getColumna();

		int posFilDestino = coordDestino.getFila();
		int posColDestino = coordDestino.getColumna();

		CarameloUD2 temporario1 = tablero[posFilOrigen][posColOrigen];
		CarameloUD2 temporario2 = tablero[posFilDestino][posColDestino];
		tablero[posFilDestino][posColDestino] = temporario1;
		tablero[posFilOrigen][posColOrigen] = temporario2;
		tablero[posFilDestino][posColDestino].setPosicionTablero(coordDestino);
		tablero[posFilOrigen][posColOrigen].setPosicionTablero(coordOrigen);

		ventanaJuego.cambiaObjeto(temporario1.getObjetoDeJuego(), temporario2.getObjetoDeJuego());
		ventanaJuego.cambiaObjeto(temporario2.getObjetoDeJuego(), temporario1.getObjetoDeJuego());
		
		boolean haIntercambiado = true;
		return haIntercambiado;
	}

	public String getColorSimple(int posFila, int posCol) {
		if (tablero[posFila][posCol] == null) {
			return "-";
		} else if (tablero[posFila][posCol].color.equalsIgnoreCase("Azul")) {
			return "a";
		} else if (tablero[posFila][posCol].color.equalsIgnoreCase("Magenta")) {
			return "g";
		} else if (tablero[posFila][posCol].color.equalsIgnoreCase("Verde")) {
			return "v";
		} else {
			return "r";
		}
	}

}
