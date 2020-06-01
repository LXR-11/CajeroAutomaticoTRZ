package Cajero;

import java.util.InputMismatchException;
import java.util.Scanner;


public class CajeroAutomatico {
	
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private int numeroDeTarjeta, pin;
	private Cliente clienteIngresado;
	private Tarjeta tarjetaIngresada;

	public CajeroAutomatico() {
		try {
			todasLasCuentas = new ArchivoDeCuentas();

		} catch (Exception e) {
			System.out.println("Error al leer archivos");
		}
		todosLosMensajes = new Mensajes();
		entrada = new Scanner(System.in);
	}

	public void iniciar() {

		todosLosMensajes.bienvenida();

		try {
			this.numeroDeTarjeta = entrada.nextInt();
			todosLosMensajes.pinTarjeta();
			this.pin = entrada.nextInt();

			///// Creamos una tarjeta con los valores dados/////

			this.tarjetaIngresada = new Tarjeta(this.numeroDeTarjeta, this.pin);

			boolean existeElUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta()
					.containsKey(tarjetaIngresada);

			if (existeElUsuario) {
				long cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
				this.clienteIngresado = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuario);

				todosLosMensajes.menuPrincipal();
				int opcionElegida = entrada.nextInt();

				MovimientosEnPantalla ejecucionDelPrograma = new MovimientosEnPantalla(clienteIngresado,todasLasCuentas);
				ejecucionDelPrograma.ejecutarLaEleccionDeMovimiento(opcionElegida);

			} else {
				System.out.println("Usuario no encontrado");
			}
		
		
		} catch (InputMismatchException e) {
			System.out.println(
			"*************************************************************"
			+ "\n"
			+ "\nLo sentimos, ha introducido un valor invalido."
			+ "\nCerraremos el programa, intente nuevamente."
			+ "\n"
			+"*************************************************************");
			this.entrada.close();
			System.exit(0);
		}
	}
}
