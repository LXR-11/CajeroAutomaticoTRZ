package Cajero;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class ArchivoDeTarjetas {
	
	public ArchivoDeTarjetas() throws FileNotFoundException, IOException {
		
		listaDeClientes();
		
	}

	TreeSet <Tarjeta> tarjetas;

	TreeMap <Integer, Integer> validacion;

	private void listaDeClientes() throws FileNotFoundException, IOException {

		this.tarjetas = new TreeSet <Tarjeta>();

		BufferedReader lecturaArchivoTerjetas = new BufferedReader(new FileReader("ArchivoTerjetas.txt"));

		String lineaArchivoTerjetas = lecturaArchivoTerjetas.readLine();

		while ( ( lineaArchivoTerjetas != null ) ) {

			lineaArchivoTerjetas.trim();

			String separadorArchivoTerjetas [] = lineaArchivoTerjetas.split(",");

			int numeroDeTarjeta = pasarDeStrinAInt(separadorArchivoTerjetas[0]);

			int pin = pasarDeStrinAInt(separadorArchivoTerjetas[1]);
			
			int cuit = pasarDeStrinAInt(separadorArchivoTerjetas[2]);

			Tarjeta tarjeta = new Tarjeta (numeroDeTarjeta, pin );
			
			validacion.put(cuit, numeroDeTarjeta);

			tarjetas.add(tarjeta);

			lineaArchivoTerjetas = lecturaArchivoTerjetas.readLine();

		}
	}
	
	public TreeSet <Tarjeta> getTarjetas (){
		
		return this.tarjetas;
		
	}
	
	public TreeMap <Integer, Integer> getValidacion (){
		
		return this.validacion;
		
	}

	private int pasarDeStrinAInt(String string) {

		return ( Integer.parseInt(string) );

	}
}