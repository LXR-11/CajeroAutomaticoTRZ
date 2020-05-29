package Cajero;
//
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class ArchivoDeClientes {
	
	private	Map <String, Long> aliasCuit;
	
	
	public ArchivoDeClientes() throws FileNotFoundException, IOException {
		this.aliasCuit = new HashMap<String,Long>();		//Creo un mapa k=alias | v= cuit
		
		clientesAsociados();
		
		
	}
	

	private void clientesAsociados() throws IOException  {
 
		//Extraer datos de los .txt
		BufferedReader lecturaArchivoCliente = new BufferedReader(new FileReader("ArchivoClientes.txt"));

		String lineaArchivoClientes = lecturaArchivoCliente.readLine();

		while ((lineaArchivoClientes != null)) {
			

			lineaArchivoClientes.trim();

			String separadorArchivoTarjetas[] = lineaArchivoClientes.split(",");

			long cuit = Long.parseLong(separadorArchivoTarjetas[0]);
			

			String alias = separadorArchivoTarjetas[1];

			//Poner valores en el mapa
			this.aliasCuit.put(alias, cuit);
			
			lineaArchivoClientes = lecturaArchivoCliente.readLine();
			
			
		}
		lecturaArchivoCliente.close();
	}
	
	
	public Map<String,Long> getAliasConCuit(){
		return this.aliasCuit;
	}

}