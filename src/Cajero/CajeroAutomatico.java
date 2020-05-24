package Cajero;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CajeroAutomatico {
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private Dispensador dispensador;
	private int numeroDeTarjeta, pin;
	private int deseaImprimir;
	private Ticket generarTicket;

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
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteARS);
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;

						case 2: // USD
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteUSD);
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;

						case 3: // CC
							System.out.println(todosLosMensajes
									.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cuentaCorrienteDelCliente);
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;
						}
						System.out.println("Saliendo...");
						entrada.close();
						System.exit(0);
						break;

						//////////////// CONSULTAR ALIAS ////////////////
					case 2:
						System.out.println(todosLosMensajes.tipoDeCuenta());
						tipoDeCuenta = entrada.nextInt();
						switch (tipoDeCuenta) {
						case 1: // ARS
							System.out.println(this.todosLosMensajes.alias(clienteIngresado.cajaDelClienteARS));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirTicketAlias(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cajaDelClienteARS.getAlias());
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;

						case 2: // USD
							System.out.println(this.todosLosMensajes.alias(clienteIngresado.cajaDelClienteUSD));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirTicketAlias(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cajaDelClienteUSD.getAlias());
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;

						case 3: // CC
							System.out.println(this.todosLosMensajes.alias(clienteIngresado.cuentaCorrienteDelCliente));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirTicketAlias(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cuentaCorrienteDelCliente.getAlias());
								System.out.println("Ticket generado correctamente. Saliendo..");
							}
							break;
						}
						System.out.println("Saliendo...");
						entrada.close();
						System.exit(0);
						break;

						//////////////// CONSULTAR ULTIMOS MOVIMIENTOS ////////////////

					case 3:
						System.out.println("TODAVIA FALTA IMPLEMENTAR");
						System.out.println("Saliendo...");
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
							System.out.println(this.dispensador.retirarBillete(monto));
							clienteIngresado.cajaDelClienteARS.retirarEfectivo(monto);
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirExtraccion(clienteIngresado.cajaDelClienteARS, monto);
								System.out.println("Ticket generado correctamente.");
							}

						}
						break;

					case 2: // USD
						System.out.println("No se puede retirar efectivo desde una Caja de ahorro en USD");
						break;

					case 3: // CC
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						if (clienteIngresado.verificarCuentaEnCliente(3)) {
							System.out.println(this.dispensador.retirarBillete(monto));
							clienteIngresado.cuentaCorrienteDelCliente.retirarEfectivo(monto);
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirExtraccion(clienteIngresado.cuentaCorrienteDelCliente, monto);
								System.out.println("Ticket generado correctamente.");
							}
						}

						break;
					}
					System.out.println("Saliendo...");
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
						this.deseaImprimir = entrada.nextInt();
						if(this.deseaImprimir==1) {	//GENERA TICKET
							this.generarTicket=new Ticket();
							this.generarTicket.escribirCompraUSD(clienteIngresado.cajaDelClienteARS, monto);
							System.out.println("Ticket generado correctamente.");
						}
						break;

					case 2: // USD
						System.out.println("No se puede comprar USD desde esta cuenta");
						break;

					case 3: // CC
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						clienteIngresado.cuentaCorrienteDelCliente.comprarDolares(monto, clienteIngresado); // PROBAR
						this.deseaImprimir = entrada.nextInt();
						if(this.deseaImprimir==1) {	//GENERA TICKET
							this.generarTicket=new Ticket();
							this.generarTicket.escribirCompraUSD(clienteIngresado.cuentaCorrienteDelCliente, monto);
							System.out.println("Ticket generado correctamente.");
						}
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
						if(this.deseaImprimir==1) {	//GENERA TICKET
							this.generarTicket=new Ticket();
							this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteARS, monto);
							System.out.println("Ticket generado correctamente.");
						}
						
						
						break;

					case 2: // USD
						clienteIngresado.cajaDelClienteUSD.depositar(monto);
						if(this.deseaImprimir==1) {	//GENERA TICKET
							this.generarTicket=new Ticket();
							this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteUSD, monto);
							System.out.println("Ticket generado correctamente.");
						}
						break;

					case 3: // CC
						clienteIngresado.cuentaCorrienteDelCliente.depositar(monto);
						if(this.deseaImprimir==1) {	//GENERA TICKET
							this.generarTicket=new Ticket();
							this.generarTicket.escribirDeposito(clienteIngresado.cuentaCorrienteDelCliente, monto);
							System.out.println("Ticket generado correctamente.");
						}
						break;
					}
					System.out.println("Saliendo...");
					entrada.close();
					System.exit(0);
					break;

					//////////////// TRANSFERENCIA////////////////
				case 5:
					System.out.println(todosLosMensajes.tipoDeCuenta());
					tipoDeCuenta = entrada.nextInt();
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

						switch (tipoDeCuenta) {
						case 1: // ARS

							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();
							clienteIngresado.cajaDelClienteARS.transferir(clienteDestinatario, monto);

							this.generarTicket=new Ticket();
							this.generarTicket.escribirTicketTransferencia(aliasDestinatario, monto, clienteIngresado.cajaDelClienteARS.consultarSaldo());
							System.out.println("TODAVIA FALTA IMPLEMENTAR: REESCRIBIR EL TXT DE SALDO DE CUENTAS");
							break;
						case 2: // USD
							System.out.println("No se puede realizar transferencias en USD");
							break;
						case 3: // CC
							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();
							clienteIngresado.cuentaCorrienteDelCliente.transferir(clienteDestinatario, monto);
							System.out.println("TODAVIA FALTA IMPLEMENTAR: REESCRIBIR EL TXT DE SALDO DE CUENTAS");
							break;
						}
					}
					System.out.println("Saliendo...");
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
