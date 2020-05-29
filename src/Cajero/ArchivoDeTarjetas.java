package Cajero;
import java.io.*;
import java.util.*;
//s
public class ArchivoDeTarjetas {

	private	Map <Long, Cliente> cuitCliente;
	private Map <Tarjeta, Long> tarjetaCuit;

	public ArchivoDeTarjetas() throws FileNotFoundException, IOException {
		this.cuitCliente = new HashMap<Long,Cliente>();			//Creo un mapa k = CUIT | v = Cliente
		this.tarjetaCuit = new HashMap<Tarjeta, Long>();
		listaDeClientes();

	}

	private void listaDeClientes() throws FileNotFoundException, IOException {

		//Separo el TXT en valores
		BufferedReader lecturaArchivoTarjetas = new BufferedReader(new FileReader("ArchivoTarjetas.txt"));

		String lineaArchivoTarjetas = lecturaArchivoTarjetas.readLine();

		while ((lineaArchivoTarjetas != null)) {

			lineaArchivoTarjetas.trim();

			String separadorArchivoTerjetas[] = lineaArchivoTarjetas.split(",");

			int numeroDeTarjeta = Integer.parseInt(separadorArchivoTerjetas[0]);

			int pin = Integer.parseInt(separadorArchivoTerjetas[1]);

			long cuit = Long.parseLong(separadorArchivoTerjetas[2]);
			
			//Creo Clientes
			Tarjeta tarjeta = new Tarjeta(numeroDeTarjeta, pin);

			Cliente cliente = new Cliente(tarjeta, cuit);
			
			//Integro los clientes en el mapa
			this.cuitCliente.put(cuit, cliente);
			this.tarjetaCuit.put(tarjeta, cuit);

			lineaArchivoTarjetas = lecturaArchivoTarjetas.readLine();

		}
		lecturaArchivoTarjetas.close();
	}

	public Map<Tarjeta,Long> getCuitConTarjeta(){
		return this.tarjetaCuit;
	}
	public Map <Long, Cliente> getClientesConCuit() {

		return this.cuitCliente;
	}

}