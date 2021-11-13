/*

Trabajo Practico Obligatorio: Buscaminas

Código hecho por:
 		Bianca Lucila Sztafij; LU: 1102702
		Juan Pablo Pieczocha; LU: 1103622
		Augusto Ariel Mancuello; LU: 1104732

Profesores:
		Hernan Javier Morello
		Ricardo Abraham Wehbe
		Ricardo Fabian Orosco
  
Materia: Programacion III
Turno: Martes - Mañana
 */


package methods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class metodos {
	
	
	public static ArrayList<Casilla> buscaminas (String ruta) { 
		File ar = null;
        FileReader fr = null;
        BufferedReader br = null;
 
        try {
        	//Levanta el archivo
			ar = new File (ruta);
			fr = new FileReader (ar);
			br = new BufferedReader(fr);
			
			//Saco los datos de la primer línea
			String linea1 = br.readLine();
			String[] datos = linea1.split(",");
			int fila = Integer.parseInt(datos[0]);
			int col = Integer.parseInt(datos[1]);
			int cantBombas = Integer.parseInt(datos[2]);
			
			//Creo la matriz y la lista de pendientes
			ArrayList<Casilla> pendientes = new ArrayList<Casilla>();
			Casilla[][] m = new Casilla[fila][col];
			for (int i = 0; i < fila; i++) {
				String linea = br.readLine();
				String[] numeros = linea.split(" ");
				for (int j = 0; j < col; j++) {
					char a = numeros[j].charAt(0);
					Casilla pos = new Casilla(i, j, a);
					m[i][j] = pos;
					pendientes.add(pos);
				}
			}
			
			//mostrarMatriz(m);
			
			br.close(); //Cierro el archivo ya que no se lo utiliza durante lo que queda de la funci�n
			
			ArrayList<Casilla> solucion = new ArrayList<Casilla>();
			HashSet<Casilla> bombas = new HashSet<Casilla>();
			
			/*
			System.out.print("Pendientes: ");
			mostrarListaCasillas(pendientes);
			 */
			
			//Recorro la matriz hasta buscar la solución que lleve menos clicks
			for (int i =0; i < m.length; i++) {
				for (int j = 0; j < m[0].length; j++) {
					ArrayList<Casilla> solucionParcial = new ArrayList<Casilla>();
					//System.out.println("Casilla actual: ["+ (m[i][j].fila + 1) + ", " + (m[i][j].columna + 1) + "]");
					if (solucion.size() != 1 && !bombas.contains(m[i][j]))
						solucion = jugar(m, m[i][j], pendientes, cantBombas, bombas, solucionParcial, solucion);
				}
			}
			
			return solucion;
			
        } catch (IOException | NullPointerException e) {
        	
        	System.out.println("Se ha producido un error, verifique que el archivo sea adecuado para la ejecución del programa.");
        
        }
        
        return null;
	}
	

	private static ArrayList<Casilla> jugar(Casilla[][] m, Casilla c, ArrayList<Casilla> pendientes, int cantBombas,
			HashSet<Casilla> bombas, ArrayList<Casilla> clicks, ArrayList<Casilla> solucionFinal) {
		if (c.numero != 'X') {
			ArrayList<Casilla> revelados = new ArrayList<Casilla>();
			clicks.add(c);
			revelar(m, c, revelados, pendientes);
			
			/*
			mostrarMatrizJugada(m, pendientes);
			System.out.print("Clicks: ");
			mostrarListaCasillas(clicks);
			 */
			
			if (pendientes.size() == cantBombas) { //Encontró una nueva solución
					solucionFinal = new ArrayList<Casilla>(clicks);
					
					/*
					System.out.print("Solución más optima por el momento: ");
					mostrarListaCasillas(solucionFinal);
					System.out.println("Tamaño: " + solucionFinal.size());
					*/
					
			} else {
				
				/*
				System.out.print("Pendientes: ");
				mostrarListaCasillas(pendientes);
				 */
				
				for (int i =0; i < m.length; i++) {
					if (!(clicks.size() + 1 >= solucionFinal.size() && solucionFinal.size() != 0))
						for (int j = 0; j < m[0].length; j++) {
							//System.out.println("Casilla actual: ["+ (m[i][j].fila + 1) + ", " + (m[i][j].columna + 1) + "]");
							if (!(clicks.size() + 1 >= solucionFinal.size() && solucionFinal.size() != 0) && 
									!bombas.contains(m[i][j]) && pendientes.contains(m[i][j]))
									solucionFinal = jugar(m, m[i][j], pendientes, cantBombas, bombas, clicks, solucionFinal);
						}
				}
				
			}
			
			ocultar(m, revelados, pendientes);
			clicks.remove(c);
			
			/*
			mostrarMatrizJugada(m, pendientes);
			System.out.print("Clicks: ");
			mostrarListaCasillas(clicks);
			 */
			
		} else {
			
			bombas.add(c); //Al encontrar una bomba, lo agrego al conjunto de bombas así no tengo que volver a pasar de nuevo por ahí
			
		}
		
		return solucionFinal;
		
	}
	
	//Se revela la casilla en el parámetro, si la casilla resulta ser un 0 revelará todas las casillas adyacentes
	private static void revelar(Casilla[][] m, Casilla c, ArrayList<Casilla> revelados, ArrayList<Casilla> pendientes) {
		revelados.add(c);
		pendientes.remove(c);
		if (c.numero == '0')
			for ( int x = -1; x <= 1; x++) {
				int i = c.fila + x;
				if (i >= 0 && i < m.length) {
					for (int y = -1; y <= 1; y++) {
						int j = c.columna + y;
						if (j >= 0 && j < m[0].length) {
							if (pendientes.contains(m[i][j]))
								revelar(m, m[i][j], revelados, pendientes);
						}
					}
				}
			}
	}
	
	//Las casillas que fueron reveladas en el llamado, son ocultadas
	private static void ocultar(Casilla[][] m, ArrayList<Casilla> revelados, ArrayList<Casilla> casillas) {
		Iterator<Casilla> iterador = revelados.iterator();
		while(iterador.hasNext()){
			Casilla x = iterador.next();
			casillas.add(x);
		}
	}
	
	//Muestra toda la matriz descubierta
	public static void mostrarMatriz(Casilla[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(" " + m[i][j].numero + " ");
			}
			System.out.println();
		}
	}
	
	//Muestra la matriz desde la perspectiva del jugador, las casillas no reveladas se marcan con un asterisco
	public static void mostrarMatrizJugada(Casilla[][] m, ArrayList<Casilla> pendientes) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (pendientes.contains(m[i][j])) {
					System.out.print("| * |");
				} else {
					System.out.print("| " + m[i][j].numero + " |");
				}
			}
			System.out.println();
		}
	}
	
	//Muestra el resultado final
	public static void mostrarListaCasillas(ArrayList<Casilla> lista) {
		Iterator<Casilla> iterador = lista.iterator();
		System.out.print("[ ");
		while(iterador.hasNext()){
			Casilla x = iterador.next();
			System.out.print("["+ (x.fila + 1) + ", " + (x.columna + 1) + "]");
			if (!iterador.hasNext()) {
				System.out.println(" ]");
			} else {
				System.out.print(", ");
			}
		}
	}
	
}
