package Cajero;
import java.io.*;
import java.util.*;
//

public class ArchivoDeTarjetas {

	private	Map <Integer, Cliente> cuitCliente;

	public ArchivoDeTarjetas() throws FileNotFoundException, IOException {
		this.cuitCliente = new HashMap<Integer,Cliente>();			//Creo un mapa k=CUIT | v=Cliente
		listaDeClientes();

	}

	
	
	
	private void listaDeClientes() throws FileNotFoundException, IOException {

		
		//Separo el TXT en valores
		BufferedReader lecturaArchivoTerjetas = new BufferedReader(new FileReader("ArchivoTarjetas.txt"));

		String lineaArchivoTerjetas = lecturaArchivoTerjetas.readLine();

		while ((lineaArchivoTerjetas != null)) {

			lineaArchivoTerjetas.trim();

			String separadorArchivoTerjetas[] = lineaArchivoTerjetas.split(",");

			int numeroDeTarjeta = Integer.parseInt(separadorArchivoTerjetas[0]);

			int pin = Integer.parseInt(separadorArchivoTerjetas[1]);

			int cuit = Integer.parseInt(separadorArchivoTerjetas[2]);
			
			//Creo Clientes
			Tarjeta tarjeta = new Tarjeta(numeroDeTarjeta, pin);

			Cliente cliente = new Cliente(tarjeta, cuit);
			
			//Integro los clientes en el mapa
			cuitCliente.put(cuit, cliente);

			lineaArchivoTerjetas = lecturaArchivoTerjetas.readLine();

		}
	}

	public Map <Integer, Cliente> getClientesConCuit() {

		return this.cuitCliente;

	}
	


}