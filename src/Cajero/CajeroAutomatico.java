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
				int tipoDeCuenta, monto;
				switch (opcionElegida) {

				///////////////////// CONSULTA /////////////////////

				case 1:
					System.out.println(todosLosMensajes.consultas());
					int opcionConsultas = entrada.nextInt();
					switch (opcionConsultas) {

					//////////////// CONSULTAR SALDO ////////////////
					case 1:
						System.out.println(todosLosMensajes.tipoDeCuenta());
						tipoDeCuenta = entrada.nextInt();
						switch (tipoDeCuenta) {

						case 1: // ARS
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cajaDelClienteARS.consultarSaldo()));
							break;

						case 2: // USD
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo()));
							break;

						case 3: // CC
							System.out.println(todosLosMensajes
									.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo()));
							break;
						}
						entrada.close();
						System.exit(0);
						break;

					//////////////// CONSULTAR ALIAS ////////////////
					case 2:
						System.out.println(todosLosMensajes.tipoDeCuenta());
						tipoDeCuenta = entrada.nextInt();
						switch (tipoDeCuenta) {
						case 1: // ARS
							clienteIngresado.consultarAliasDeCuentaX(1);

							break;

						case 2: // USD
							clienteIngresado.consultarAliasDeCuentaX(2);

							break;

						case 3: // CC
							clienteIngresado.consultarAliasDeCuentaX(3);
							break;
						}
						entrada.close();
						System.exit(0);
						break;

					//////////////// CONSULTAR ULTIMOS MOVIMIENTOS ////////////////

					case 3:
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						entrada.close();
						System.exit(0);
						break;
					}

					//////////////// EXTRAER ////////////////
				case 2:
					System.out.println(todosLosMensajes.tipoDeCuenta());
					tipoDeCuenta = entrada.nextInt();
					switch (tipoDeCuenta) {

					case 1: // ARS
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						if (clienteIngresado.verificarCuentaEnCliente(1)) { // VERIFICA QUE HAYA CAJA
								clienteIngresado.cajaDelClienteARS.retirarEfectivo(monto);
								System.out.println(this.dispensador.retirarBillete(monto));		
						} 
						break;

					case 2: // USD
						System.out.println("No se puede retirar efectivo desde una Caja de ahorro en USD");
						break;

					case 3: // CC
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						break;
					}
					entrada.close();
					System.exit(0);
					break;

				//////////////// COMPRAR USD ////////////////

				case 3:
					System.out.println(todosLosMensajes.tipoDeCuenta());
					tipoDeCuenta = entrada.nextInt();

					switch (tipoDeCuenta) {
					case 1: // ARS
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						clienteIngresado.cajaDelClienteARS.comprarDolares(monto, clienteIngresado);
						break;

					case 2: // USD
						System.out.println("No se puede comprar USD desde esta cuenta");
						break;

					case 3: // CC
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						break;
					}
					entrada.close();
					System.exit(0);
					break;

				//////////////// DEPOSITAR ////////////////
				case 4:
					System.out.println(todosLosMensajes.tipoDeCuenta());
					tipoDeCuenta = entrada.nextInt();
					System.out.println(todosLosMensajes.monto());
					monto = entrada.nextInt();

					switch (tipoDeCuenta) {

					case 1: // ARS
						clienteIngresado.cajaDelClienteARS.depositar(monto);
						break;

					case 2: // USD
						clienteIngresado.cajaDelClienteUSD.depositar(monto);
						break;

					case 3:
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						clienteIngresado.cuentaCorrienteDelCliente.depositar(monto);
						break;
					}
					entrada.close();
					System.exit(0);
					break;

				//////////////// TRANSFERENCIA////////////////
				case 5:
					System.out.println(todosLosMensajes.tipoDeCuenta());
					tipoDeCuenta = entrada.nextInt();
					switch (tipoDeCuenta) {
					case 1: // ARS
						System.out.println(this.todosLosMensajes.transferenciaAlias());
						String aliasDestinatario = entrada.next();
						if (this.todasLasCuentas.getArchivoCliente().getAliasConCuit().containsKey(aliasDestinatario)) { // ¿Existe
																															// cuit
																															// con
																															// ese
																															// alias?
							int cuitDestinatario = this.todasLasCuentas.getArchivoCliente().getAliasConCuit()
									.get(aliasDestinatario);
							Cliente clienteDestinatario = this.todasLasCuentas.getArchivoTarjetas().getClientesConCuit()
									.get(cuitDestinatario);
							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();

							clienteIngresado.cajaDelClienteARS.transferir(clienteDestinatario, monto);
							System.out.println("TODAVIA FALTA IMPLEMENTAR: REESCRIBIR EL TXT DE SALDO DE CUENTAS");

						}
						break;
					case 2: // USD
						System.out.println("No se puede realizar transferencias en USD");
						break;
					case 3: // CC
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						break;
					}

					entrada.close();
					System.exit(0);
					break;

				default:
					System.out.println("Opcion invalida");
				}

			}

			else {
				System.out.println("Usuario no encontrado");
			}
		} catch (

		InputMismatchException e) {
			System.out.println("Numero Invalido");
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}

	}
}
