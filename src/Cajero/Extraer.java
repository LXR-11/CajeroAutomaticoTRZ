package Cajero;

import java.util.Scanner;

public class Extraer extends MovimientosEnPantalla{
	protected ArchivoDeCuentas todasLasCuentas;
	protected Dispensador dispenser;
	public Extraer(Cliente usuario, int opcion, Dispensador dispensadorDelMain, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, opcion);
		this.todasLasCuentas=todasLasCuentas;
		this.dispenser=dispensadorDelMain;
	}
	
		
	
	public void ejecutar() {
		switch(this.opcion) {
		case 1:		//ARS
			ExtraerARS extraerARS = new ExtraerARS(usuario,opcion, dispenser, todasLasCuentas);
			extraerARS.extraer();
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;

		case 2: 	//USD
			System.out.println("No se puede extraer USD. Saliendo...");
			entrada.close();
			System.exit(0);
			break;

		case 3:		//CC
			ExtraerCC extraerCC = new ExtraerCC(usuario,opcion, dispenser, todasLasCuentas);
			extraerCC.extraer();
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;
			
		default:
			System.out.println("Valor invalido, Saliendo...");
			entrada.close();
			System.exit(0);

		}
	}

}
