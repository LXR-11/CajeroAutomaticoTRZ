package Cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArchivoDeMovimientos {
		
	Map <String,Movimiento> aliasMovimiento;
	
	public ArchivoDeMovimientos() {
		
		this.aliasMovimiento = new HashMap<String,Movimiento>();

		leerArchivoMovimientos();
	}
	
	public void leerArchivoMovimientos() {
		
		try{
			BufferedReader buffer = new BufferedReader(new FileReader ("ArchivoMovimientos.txt"));
			String linea = buffer.readLine();
			
			while(linea !=null) {
				
				linea.trim();
				String [] partes = linea.split(",");
				
				String fecha = partes[0];
				String concepto = partes [1];
				TipoDeMovimiento tipo;
				tipo = TipoDeMovimiento.valueOf(concepto);
				String alias = partes[2];
				String monto = partes[3];
				int valorInvolucrado = Integer.parseInt(monto);
				
				Movimiento movimientoLeido = new Movimiento(fecha, tipo, valorInvolucrado, alias);
				
				aliasMovimiento.put(alias, movimientoLeido);
				
				buffer.close();
			}
			
		}catch(IOException e) {
			System.out.println("No se ha encontrado el archivo movimientos, lo sentimos..");
		}
	}

	
	public void escribirMovimiento(Movimiento movimiento) {
		
		try{
			//escribe en el archivo
			BufferedWriter buffer = new BufferedWriter(new FileWriter ("ArchivoMovimientos.txt"));
			
			buffer.write(movimiento.imprimirMovimiento());
			buffer.newLine();
			
			//agrega al mapa
			aliasMovimiento.put(movimiento.miAlias, movimiento);
			
			buffer.close();
			
		}catch(IOException e) {
			System.out.println("No se ha encontrado el archivo movimientos, lo sentimos..");
		}
		
		
	}
	
	public Map<String, Movimiento> getAliasMovimiento() {
		return aliasMovimiento;
	}
	
}
