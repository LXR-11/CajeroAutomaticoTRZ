package Cajero;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ArchivoDeClientes {
	
	private	Map <String, Integer> aliasCuit;
	
	
	public ArchivoDeClientes() throws FileNotFoundException, IOException {
		this.aliasCuit = new HashMap<String,Integer>();		//Creo un mapa k=alias | v= cuit
		
		clientesAsociados();
		
		
	}
	

	private void clientesAsociados() throws FileNotFoundException, IOException {

		//Extraer datos de los .txt
		BufferedReader lecturaArchivoCliente = new BufferedReader(new FileReader("ArchivoTerjetas.txt"));

		String lineaArchivoClientes = lecturaArchivoCliente.readLine();

		while ((lineaArchivoClientes != null)) {

			lineaArchivoClientes.trim();

			String separadorArchivoTerjetas[] = lineaArchivoClientes.split(",");

			int cuit = Integer.parseInt(separadorArchivoTerjetas[0]);

			String alias = separadorArchivoTerjetas[1];

			//Poner valores en el mapa
			this.aliasCuit.put(alias, cuit);
			
			lineaArchivoClientes = lecturaArchivoCliente.readLine();

		}
	}
	
	
	public Map<String,Integer> getAliasConCuit(){
		return this.aliasCuit;
	}

}
