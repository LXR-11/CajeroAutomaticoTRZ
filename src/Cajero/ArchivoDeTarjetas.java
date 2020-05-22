package Cajero;
import java.io.*;
import java.util.*;
//

public class ArchivoDeTarjetas {

	private	Map <Integer, Cliente> cuitCliente;
	private Map <Tarjeta, Integer> tarjetaCuit;

	public ArchivoDeTarjetas() throws FileNotFoundException, IOException {
		this.cuitCliente = new HashMap<Integer,Cliente>();			//Creo un mapa k=CUIT | v=Cliente
		this.tarjetaCuit = new HashMap<Tarjeta, Integer>();
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
			this.cuitCliente.put(cuit, cliente);
			this.tarjetaCuit.put(tarjeta, cuit);

			lineaArchivoTerjetas = lecturaArchivoTerjetas.readLine();

		}
	}

	public Map<Tarjeta,Integer> getCuitConTarjeta(){
		return this.tarjetaCuit;
	}
	public Map <Integer, Cliente> getClientesConCuit() {

		return this.cuitCliente;

	}
	


}