package Cajero;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class CajeroAutomatico {
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private Dispensador dispensador;
	private int numeroDeTarjeta, pin,opcion;
	private Cliente clienteIngresado;
	private int tipoDeCuenta, monto, opcionElegida;
	private Tarjeta tarjetaIngresada;
	private long cuitDelUsuario;

	public CajeroAutomatico() {
		try {
			todasLasCuentas = new ArchivoDeCuentas();

		} catch (Exception e) {
			System.out.println("Error al leer archivos");
		}
		todosLosMensajes = new Mensajes();
		entrada = new Scanner(System.in);
		dispensador = new Dispensador();
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
				cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
				this.clienteIngresado = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuario);

				todosLosMensajes.menuPrincipal();

				opcionElegida = entrada.nextInt();

				switch (opcionElegida) {

				///////////////////// CONSULTAS /////////////////////

				case 1:
					todosLosMensajes.consultas();
					opcion = entrada.nextInt();
					MovimientosEnPantalla movimientosEnPantalla= new MovimientosEnPantalla(clienteIngresado, opcion);
					movimientosEnPantalla.ejecutar();					
					break;

				//////////////// EXTRAER ////////////////
				case 2:
					todosLosMensajes.tipoDeCuenta();
					opcion = entrada.nextInt();
					Extraer extraccion= new Extraer(clienteIngresado, opcion, this.dispensador, this.todasLasCuentas);
					extraccion.ejecutar();		
					break;

				//////////////// COMPRAR USD ////////////////

				case 3:
					todosLosMensajes.tipoDeCuenta();
					opcion = entrada.nextInt();
					ComprarDOLARRRRES compra= new ComprarDOLARRRRES(clienteIngresado, opcion, this.todasLasCuentas);
					compra.ejecutar();
					break;

				//////////////// DEPOSITAR ////////////////
				case 4:
					todosLosMensajes.tipoDeCuenta();
					opcion = entrada.nextInt();
					Deposito deposito = new Deposito(clienteIngresado, opcion, this.todasLasCuentas);
					deposito.ejecutar();
					break;
					
				//////////////// TRANSFERENCIA////////////////
				case 5:
					
					todosLosMensajes.tipoDeCuenta();
					opcion = entrada.nextInt();
					Transferencia transferencia = new Transferencia(clienteIngresado, opcion, this.todasLasCuentas);
					transferencia.ejecutar();
					break;
					
				default:
					System.out.println("Valor invalido");
				}

			}

			else {
				System.out.println("Usuario no encontrado");
			}
		} catch (InputMismatchException e) {

			System.out.println("Valor Invalido."
					+ "\nSaliendo...");
		} 
	}
}
