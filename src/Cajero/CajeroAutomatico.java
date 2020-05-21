package Cajero;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CajeroAutomatico {
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private int numeroDeTarjeta, pin;

	public CajeroAutomatico() {
		try {
			todasLasCuentas= new ArchivoDeCuentas();

		}
		catch(Exception e) {
			System.out.println("Error al leer archivos");
		}
		todosLosMensajes=new Mensajes();
		entrada= new Scanner (System.in);

	}

	public void iniciar() {
		todosLosMensajes.bienvenida();

		try{
			this.numeroDeTarjeta= entrada.nextInt();
			todosLosMensajes.pinTarjeta();
			this.pin=entrada.nextInt();
			System.out.println("Muy bien, tu numero de tarjeta es" + this.numeroDeTarjeta + " y tu pin es: " + this.pin);

		}
		catch(InputMismatchException e){
			System.out.println("Numero Invalido");
		}


	}
}