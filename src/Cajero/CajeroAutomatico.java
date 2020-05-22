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
			todasLasCuentas = new ArchivoDeCuentas();

		} catch (Exception e) {
			System.out.println("Error al leer archivos");
		}
		todosLosMensajes = new Mensajes();
		entrada = new Scanner(System.in);

	}

	public void iniciar() {
		System.out.println(todosLosMensajes.bienvenida());

		try {
			this.numeroDeTarjeta = entrada.nextInt();
			System.out.println(todosLosMensajes.pinTarjeta());
			this.pin = entrada.nextInt();
			///// Creamos una tarjeta con los valores dados/////
			Tarjeta tarjetaIngresada = new Tarjeta(this.numeroDeTarjeta, this.pin);

			boolean existeElUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta()
					.containsKey(tarjetaIngresada);

			if (existeElUsuario) {
				int cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
				Cliente clienteIngresado = todasLasCuentas.getArchivoTarjetas().getClientesConCuit()
						.get(cuitDelUsuario);

				System.out.println(this.todosLosMensajes.tipoDeCuenta());
				
				int tipoDeCuenta = entrada.nextInt();
				

				System.out.println(this.todosLosMensajes.menuPrincipal());

				int opcionElegida = entrada.nextInt();
				switch (opcionElegida) {

				case 1:
					System.out.println(todosLosMensajes.consultas());
					System.out.println(todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(clienteIngresado).cajaDelClienteARS.consultarSaldo());
					break;

				case 2:
					System.out.println(todosLosMensajes.monto());
					break;

				case 3:
					System.out.println(todosLosMensajes.monto());
					break;

				case 4:
					System.out.println(todosLosMensajes.monto());
					break;

				case 5:
					System.out.println(todosLosMensajes.transferenciaAlias());
					break;

				default:
					System.out.println("Opcion invalida");
				}

			}

			else {
				System.out.println("Usuario no encontrado");
			}
		} catch (InputMismatchException e) {
			System.out.println("Numero Invalido");
		}

	}
}
