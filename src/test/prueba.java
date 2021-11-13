package test;

import java.util.Scanner;

import methods.Casilla;
import methods.metodos;

import java.util.ArrayList;
 
public class prueba {
 
	private static Scanner entrada;

	public static void main(String[] args) {
		
		entrada = new Scanner(System.in);
		System.out.print("Introducir la ruta del archivo de texto: ");
		String ruta = entrada.nextLine();

		long inicio = System.currentTimeMillis();
		ArrayList<Casilla> clicks = metodos.buscaminas(ruta);
		long fin = System.currentTimeMillis();
		
		if (clicks != null) {
			System.out.print("La mejor solución es: ");
			metodos.mostrarListaCasillas(clicks);
			System.out.println("Se resolvió en " + clicks.size() + " clicks");
			
			double tiempoDeEjecucion = (double) (fin - inicio);
			System.out.println("Se tardó en resolverse " + tiempoDeEjecucion + " milisegundos");
		}
			
		
	}
}