package Cajero;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CajeroAutomatico {
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private Dispensador dispensador;
	private int numeroDeTarjeta, pin,deseaImprimir;
	private Ticket generarTicket;
	private Movimiento nuevoMovimiento;

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
				long cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
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
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteARS);
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 2: // USD
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteUSD);
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 3: // CC
							System.out.println(todosLosMensajes
									.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cuentaCorrienteDelCliente);
								System.out.println("Ticket generado correctamente.");
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
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
										clienteIngresado.cajaDelClienteARS.getAlias());
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 2: // USD
							System.out.println(this.todosLosMensajes.alias(clienteIngresado.cajaDelClienteUSD));
							this.deseaImprimir = entrada.nextInt();
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
										clienteIngresado.cajaDelClienteUSD.getAlias());
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 3: // CC
							System.out.println(this.todosLosMensajes.alias(clienteIngresado.cuentaCorrienteDelCliente));
							this.deseaImprimir = entrada.nextInt();
							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
										clienteIngresado.cuentaCorrienteDelCliente.getAlias());
								System.out.println("Ticket generado correctamente.");
							}
							break;
						}
						System.out.println("Saliendo...");
						entrada.close();
						System.exit(0);
						break;

						//////////////// CONSULTAR ULTIMOS MOVIMIENTOS ////////////////

					case 3:
						System.out.println(todosLosMensajes.cantidadDeMovimientos());	// pregunta cuantos mov quiere consultar
						int cantidadDeMovimientos = entrada.nextInt();
						// pregunta tipo de cuenta
						System.out.println(todosLosMensajes.tipoDeCuenta());
						tipoDeCuenta = entrada.nextInt();

						switch (tipoDeCuenta) {

						case 1: // ARS
							List<Movimiento> ultimosMovEnARS = clienteIngresado.cajaDelClienteARS
							.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
							int length = ultimosMovEnARS.size();
							for (int a = 0; a < length; a++) {
								System.out.println(ultimosMovEnARS.get(a).imprimirMovimiento());
							}
							break;

						case 2: // USD
							List<Movimiento> ultimosMovEnUSD = clienteIngresado.cajaDelClienteUSD
							.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
							int lengthUSD = ultimosMovEnUSD.size();
							for (int b = 0; b < lengthUSD; b++) {
								System.out.println(ultimosMovEnUSD.get(b).imprimirMovimiento());
							}
							break;

						case 3: // CC
							List<Movimiento> ultimosMovEnCC = clienteIngresado.cuentaCorrienteDelCliente
							.mostrarHastaXUltimosMovimientos(cantidadDeMovimientos);
							int lengthCC = ultimosMovEnCC.size();
							for (int c = 0; c < lengthCC; c++) {
								System.out.println(ultimosMovEnCC.get(c).imprimirMovimiento());
							}
							break;

						default:
							System.out.println("Valor invalido. Saliendo...");
							entrada.close();
							System.exit(0);
							break;
						}
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
						try{
							if (clienteIngresado.verificarCuentaEnCliente(1)) { // VERIFICA QUE HAYA CAJA

								if(clienteIngresado.cajaDelClienteARS.retirarEfectivo(monto)) {

									System.out.println(this.dispensador.retirarBillete(monto));
									System.out.println(todosLosMensajes.extraerExitoso(monto));

									//escribe nuevo movimiento en el sistema
									nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto, clienteIngresado.cajaDelClienteARS.alias);
									this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

									this.deseaImprimir = entrada.nextInt();
									if (this.deseaImprimir == 1) { // GENERA TICKET
										this.generarTicket = new Ticket();
										this.generarTicket.escribirExtraccion(clienteIngresado.cajaDelClienteARS, monto);
										System.out.println("Ticket generado correctamente.");
									}
								}
							}
						} catch(ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}

						break;

					case 2: // USD
						System.out.println("No se puede retirar efectivo desde una Caja de ahorro en USD");
						break;

					case 3: // CC
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						try {
							if (clienteIngresado.verificarCuentaEnCliente(3)) {

								if(clienteIngresado.cuentaCorrienteDelCliente.retirarEfectivo(monto)) {

									System.out.println(this.dispensador.retirarBillete(monto));
									System.out.println(todosLosMensajes.extraerExitoso(monto));
									this.deseaImprimir = entrada.nextInt();

									//escribe movimiento en el txt
									nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto, clienteIngresado.cuentaCorrienteDelCliente.alias);
									this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

									if (this.deseaImprimir == 1) { // GENERA TICKET
										this.generarTicket = new Ticket();
										this.generarTicket.escribirExtraccion(clienteIngresado.cuentaCorrienteDelCliente,
												monto);
										System.out.println("Ticket generado correctamente.");
									}
								}
							}
						}catch(ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}
					}
					System.out.println("Saliendo...");
					entrada.close();
					System.exit(0);
					break;

				default:					
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
						try {
							double valorFinalUSD = clienteIngresado.cajaDelClienteARS.comprarDolares(monto,
									clienteIngresado);
							System.out.println(todosLosMensajes.comprarDolaresExitoso(monto, valorFinalUSD));

							this.deseaImprimir = entrada.nextInt();

							//escribe movimiento en el txt
							nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto, clienteIngresado.cajaDelClienteARS.alias);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirCompraUSD(clienteIngresado.cajaDelClienteARS, monto);
								System.out.println("Ticket generado correctamente.");
							}
						} catch (ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}

						break;

					case 2: // USD
						System.out.println("No se puede comprar USD desde esta cuenta");
						break;

					case 3: // CC
						System.out.println(todosLosMensajes.monto());
						monto = entrada.nextInt();
						try {
							double valorFinalUSD = clienteIngresado.cuentaCorrienteDelCliente.comprarDolares(monto,
									clienteIngresado);
							System.out.println(todosLosMensajes.comprarDolaresExitoso(monto, valorFinalUSD));
							this.deseaImprimir = entrada.nextInt();

							//escribe movimiento en el txt
							nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto, clienteIngresado.cuentaCorrienteDelCliente.alias);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

							if (this.deseaImprimir == 1) { // GENERA TICKET
								this.generarTicket = new Ticket();
								this.generarTicket.escribirCompraUSD(clienteIngresado.cuentaCorrienteDelCliente, monto);
								System.out.println("Ticket generado correctamente.");
							}
						} catch (ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}
						break;
					default:
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

						try {
							if (clienteIngresado.cajaDelClienteARS.depositar(monto)) {
								System.out.println(todosLosMensajes.depositoExitoso());
								deseaImprimir = entrada.nextInt();

								//escribe movimiento en el txt
								nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto, clienteIngresado.cajaDelClienteARS.alias);
								this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

								if (this.deseaImprimir == 1) { // GENERA TICKET
									this.generarTicket = new Ticket();
									this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteARS, monto);
									System.out.println("Ticket generado correctamente.");
								}
							}
						} catch (ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}

						break;

					case 2: // USD
						try {
							if (clienteIngresado.cajaDelClienteARS.depositar(monto)) {
								System.out.println(todosLosMensajes.depositoExitoso());
								deseaImprimir = entrada.nextInt();

								//escribe movimiento en el txt
								nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto, clienteIngresado.cajaDelClienteUSD.alias);
								this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

								if (this.deseaImprimir == 1) { // GENERA TICKET
									this.generarTicket = new Ticket();
									this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteUSD, monto);
									System.out.println("Ticket generado correctamente.");
								}
							}
						} catch (ErroresDeCuenta e) {
							System.out.println(e.getMessage());
						}
						break;

					case 3: // CC
						try {
							if (clienteIngresado.cajaDelClienteARS.depositar(monto)) {
								System.out.println(todosLosMensajes.depositoExitoso());
								deseaImprimir = entrada.nextInt();

								//escribe movimiento en el txt
								nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto, clienteIngresado.cuentaCorrienteDelCliente.alias);
								this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

								if (this.deseaImprimir == 1) { // GENERA TICKET
									this.generarTicket = new Ticket();
									this.generarTicket.escribirDeposito(clienteIngresado.cuentaCorrienteDelCliente,
											monto);
									System.out.println("Ticket generado correctamente.");
								}
							}
						} catch (ErroresDeCuenta e) {
							System.out.println(e.getMessage());
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
						long cuitDestinatario = this.todasLasCuentas.getArchivoCliente().getAliasConCuit()
								.get(aliasDestinatario);
						Cliente clienteDestinatario = this.todasLasCuentas.getArchivoTarjetas().getClientesConCuit()
								.get(cuitDestinatario);

						switch (tipoDeCuenta) {
						case 1: // ARS

							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();
							try {
								if (clienteIngresado.cajaDelClienteARS.transferir(clienteDestinatario, monto)) {

									System.out.println(todosLosMensajes.transferenciaExitosa(monto));
									int revertirOTicket = entrada.nextInt();

									switch (revertirOTicket) {
									case 1: // REVIERTE TRANSFERENCIA
										Cuenta destinataria = this.todasLasCuentas
										.encontrarCuentaPorAlias(aliasDestinatario);
										double saldo = (double) monto;
										clienteIngresado.cajaDelClienteARS.revertirUltimaTransferencia(saldo,
												destinataria);
										System.out.println("Se ha revertido con exito.");
										break;
									case 2: // GENERAR TICKET

										//escribe movimiento en el txt
										MovimientoReversible miMovimiento = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS, monto, clienteIngresado.cajaDelClienteARS.alias,aliasDestinatario);
										this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

										this.generarTicket = new Ticket();
										this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
										System.out.println("Ticket generado correctamente.");
										break;
									default:
										break;
									}
								}

							} catch (ErroresDeCuenta e) {
								System.out.println(e.getMessage());
							}

							break;
						case 2: // CC

							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();
							try {
								if (clienteIngresado.cuentaCorrienteDelCliente.transferir(clienteDestinatario, monto)) {

									System.out.println(todosLosMensajes.transferenciaExitosa(monto));
									int revertirOTicket = entrada.nextInt();
									switch (revertirOTicket) {
									case 1: // REVIERTE TRANSFERENCIA
										Cuenta destinataria = this.todasLasCuentas
										.encontrarCuentaPorAlias(aliasDestinatario);
										double saldo = (double) monto;
										clienteIngresado.cuentaCorrienteDelCliente.revertirUltimaTransferencia(saldo,
												destinataria);
										System.out.println("Se ha revertido con exito.");

									case 2: // GENERAR TICKET

										//escribe movimiento en el txt
										MovimientoReversible miMovimiento = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS, monto, clienteIngresado.cuentaCorrienteDelCliente.alias,aliasDestinatario);
										this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

										this.generarTicket = new Ticket();
										this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
										System.out.println("Ticket generado correctamente.");
										break;
									default:
										break;
									}
								}

							} catch (ErroresDeCuenta e) {
								System.out.println(e.getMessage());
							}

							break;
						}
					}
					System.out.println("Saliendo...");
					entrada.close();
					System.exit(0);
					break;
				}

			}

			else {
				System.out.println("Usuario no encontrado");
			}
		}catch(

				InputMismatchException e)
		{
			System.out.println("Numero Invalido");
		}finally
		{
			entrada.close();
		}

	}}
