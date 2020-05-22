package Cajero;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CajeroAutomatico {
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private Dispensador dispensador;
	private int numeroDeTarjeta, pin;

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

				System.out.println(this.todosLosMensajes.menuPrincipal());

				int opcionElegida = entrada.nextInt();
				switch (opcionElegida) {

				///////////////////// CONSULTA /////////////////////

				case 1:
					System.out.println(todosLosMensajes.consultas());
					int opcionConsultas = entrada.nextInt();
					switch (opcionConsultas) {

					//////////////// CONSULTAR SALDO ////////////////
					case 1:
						System.out.println(todosLosMensajes.tipoDeCuenta());
						int tipoDeCuentaConsulta = entrada.nextInt();
						switch (tipoDeCuentaConsulta) {

						//////////////// CONSULTAR SALDO ARS ////////////////

						case 1:
							System.out.println(clienteIngresado.cajaDelClienteARS.consultarSaldo());
							break;

							//////////////// CONSULTAR SALDO USD ////////////////

						case 2:
							System.out.println(todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo()));
							break;

							//////////////// CONSULTAR SALDO CUENTA CORRIENTE ////////////////

						case 3:
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo()));
							break;
						}
						break;


						//////////////// EXTRAER ////////////////
					case 2:
						System.out.println(todosLosMensajes.tipoDeCuenta());
						int tipoDeCuentaExtraccion = entrada.nextInt();
						switch (tipoDeCuentaExtraccion) {

						//////////////// EXTRAER EN CAJA ARS ////////////////

						case 1:
							System.out.println(todosLosMensajes.monto());
							int montoConsultaARS = entrada.nextInt();
							if(clienteIngresado.cajaDelClienteARS != null) {		//VERIFICA QUE HAYA CAJA
								if(clienteIngresado.cajaDelClienteARS.saldoSuficiente(montoConsultaARS)) {		//VERIFICA QUE TENGA SALDO
									if(this.dispensador.hayDinero(montoConsultaARS)) {		//VERIFICA QUE EL DISPENSADOR TENGA DINERO
										clienteIngresado.cajaDelClienteARS.depositar(montoConsultaARS);
										System.out.println(this.dispensador.retirarBillete(montoConsultaARS));
									}
									else {
										System.out.println("El dispensador no tiene dinero");
									}
								}
								else {
									System.out.println("No posee dinero en la cuenta");
								}
							}
							else {
								System.out.println("No posee cuenta en ARS");
							}
							break;
							
						//////////////// EXTRAER EN CAJA USD ////////////////
							
						case 2:
							System.out.println("No se puede retirar efectivo desde una Caja de ahorro en USD");
							break;
							
						//////////////// EXTRAER EN CUENTA CORRIENTE ////////////////
						case 3:
							System.out.println("TODAVIA FALTA IMPLEMENTAR");
							break;
						}

					case 3:
					}

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
