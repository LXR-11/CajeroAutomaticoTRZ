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
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteARS);
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 2: // USD
							System.out.println(
									todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
								this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteUSD);
								System.out.println("Ticket generado correctamente.");
							}
							break;

						case 3: // CC
							System.out.println(todosLosMensajes
									.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo()));
							this.deseaImprimir = entrada.nextInt();
							if(this.deseaImprimir==1) {	//GENERA TICKET
								this.generarTicket=new Ticket();
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
								if(this.deseaImprimir==1) {	//GENERA TICKET
									this.generarTicket=new Ticket();
									this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cajaDelClienteARS.getAlias());
									System.out.println("Ticket generado correctamente.");
								}
								break;
	
							case 2: // USD
								System.out.println(this.todosLosMensajes.alias(clienteIngresado.cajaDelClienteUSD));
								this.deseaImprimir = entrada.nextInt();
								if(this.deseaImprimir==1) {	//GENERA TICKET
									this.generarTicket=new Ticket();
									this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cajaDelClienteUSD.getAlias());
									System.out.println("Ticket generado correctamente.");
								}
								break;
	
							case 3: // CC
								System.out.println(this.todosLosMensajes.alias(clienteIngresado.cuentaCorrienteDelCliente));
								this.deseaImprimir = entrada.nextInt();
								if(this.deseaImprimir==1) {	//GENERA TICKET
									this.generarTicket=new Ticket();
									this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(), clienteIngresado.cuentaCorrienteDelCliente.getAlias());
									System.out.println("Ticket generado correctamente.");
								}
								break;
							}
							System.out.println("Saliendo...");
							entrada.close();
							System.exit(0);
							break;

						//////////////// CONSULTAR ULTIMO MOVIMIENTO ////////////////

					case 3:
						
						System.out.println(todosLosMensajes.tipoDeCuenta());
						tipoDeCuenta = entrada.nextInt();
							
							switch (tipoDeCuenta) {
								
							case 1: //ARS
								
								Movimiento ultimoARS = clienteIngresado.cajaDelClienteARS.movimientosDeCuenta.pop();
								boolean esReversible = (ultimoARS.getTipo() == TipoDeMovimiento.TRANSFERENCIAENPESOS);
								String ultimoARSEscrito;
								
								if (esReversible) {
									MovimientoReversible reversible =(MovimientoReversible) ultimoARS;
									//si es reversible como transferencia imprime y pregunta por el ticket tambien
									ultimoARSEscrito = reversible.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoReversible(ultimoARSEscrito);
									
									int opcion1 = entrada.nextInt();
									if(opcion1 == 1) { //GENERATICKET
										generarTicket.escribirTransferencia(reversible.aliasDelDestinatario, reversible.saldoInvolucrado);
										System.out.println("Ticket generado correctamente.");
									}else if (opcion1 == 2) { //REVIERTE TRANSFERENCIA
										Cuenta destinatario = todasLasCuentas.encontrarCuentaPorAlias(reversible.aliasDelDestinatario);
										clienteIngresado.cajaDelClienteARS.revertirUltimaTransferencia(reversible.saldoInvolucrado, destinatario);
										System.out.println("Transaccion revertida correctamente.");
									} else {
										System.out.println("Opcion invalida");
									}
								} else {
									//NO ES REVERSIBLE
									ultimoARSEscrito = ultimoARS.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoNoReversible(ultimoARSEscrito);
									int opcion2 = entrada.nextInt();
									if(opcion2 == 1) { //GENERATICKET
										generarTicket.escribirSegunMovimientosNoReversibles(ultimoARS, clienteIngresado.cajaDelClienteARS);
										System.out.println("Ticket generado correctamente.");
									} else if (opcion2 == 2) {/**nada*/} else {
										System.out.println("Opcion invalida");
									}
								}
								
								break;
								 
							case 2: //USD
								Movimiento ultimoUSD = clienteIngresado.cajaDelClienteUSD.movimientosDeCuenta.pop();
								boolean esReversibleUSD = (ultimoUSD.getTipo() == TipoDeMovimiento.TRANSFERENCIAENPESOS);
								String ultimoUSDEscrito;
								
								if (esReversibleUSD) {
									MovimientoReversible reversibleUSD = (MovimientoReversible) ultimoUSD;
									//si es reversible como transferencia imprime y pregunta por el ticket tambien
									ultimoUSDEscrito = ultimoUSD.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoReversible(ultimoUSDEscrito);
									
									int opcion1 = entrada.nextInt();
									if(opcion1 == 1) { //GENERATICKET
										generarTicket.escribirTransferencia(reversibleUSD.aliasDelDestinatario, reversibleUSD.saldoInvolucrado);
										System.out.println("Ticket generado correctamente.");
									} else if (opcion1 == 2) { //REVIERTE TRANSFERENCIA
										Cuenta destinatario = todasLasCuentas.encontrarCuentaPorAlias(reversibleUSD.aliasDelDestinatario);
										clienteIngresado.cajaDelClienteUSD.revertirUltimaTransferencia(reversibleUSD.saldoInvolucrado, destinatario);
										System.out.println("Transaccion revertida correctamente.");
									} else {
										System.out.println("Opcion invalida");
									}
								} else {
									//NO ES REVERSIBLE
									ultimoUSDEscrito = ultimoUSD.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoNoReversible(ultimoUSDEscrito);
									int opcion2 = entrada.nextInt();
									if(opcion2 == 1) { //GENERATICKET
										generarTicket.escribirSegunMovimientosNoReversibles(ultimoUSD, clienteIngresado.cajaDelClienteUSD);
										System.out.println("Ticket generado correctamente.");
									} else if (opcion2 == 2) {/**nada*/} else {
										System.out.println("Opcion invalida");
									}
								}
								 break;
							
							case 3: //CC
								Movimiento ultimoCC = clienteIngresado.cuentaCorrienteDelCliente.movimientosDeCuenta.pop();
								boolean esReversibleCC= (ultimoCC.getTipo() == TipoDeMovimiento.TRANSFERENCIAENPESOS);
								String ultimoCCEscrito;
								
								if (esReversibleCC) {
									MovimientoReversible reversibleCC = (MovimientoReversible) ultimoCC;
									//si es reversible como transferencia imprime y pregunta por el ticket tambien
									ultimoCCEscrito = reversibleCC.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoReversible(ultimoCCEscrito);
									
									int opcion1 = entrada.nextInt();
									if(opcion1 == 1) { //GENERATICKET
										generarTicket.escribirTransferencia(reversibleCC.aliasDelDestinatario, reversibleCC.saldoInvolucrado);
										System.out.println("Ticket generado correctamente.");
									} else if (opcion1 == 2) { //REVIERTE TRANSFERENCIA
										Cuenta destinatario = todasLasCuentas.encontrarCuentaPorAlias(reversibleCC.aliasDelDestinatario);
										clienteIngresado.cuentaCorrienteDelCliente.revertirUltimaTransferencia(reversibleCC.saldoInvolucrado, destinatario);
										System.out.println("Transaccion revertida correctamente.");
									} else {
										System.out.println("Opcion invalida");
									}
								} else {
									//NO ES REVERSIBLE
									ultimoCCEscrito = ultimoCC.imprimirMovimiento();
									todosLosMensajes.ultimoMovimientoNoReversible(ultimoCCEscrito);
									int opcion2 = entrada.nextInt();
									if(opcion2 == 1) { //GENERATICKET
										generarTicket.escribirSegunMovimientosNoReversibles(ultimoCC, clienteIngresado.cuentaCorrienteDelCliente);
										System.out.println("Ticket generado correctamente.");
									} else if (opcion2 == 2) {/**nada*/} else {
										System.out.println("Opcion invalida");
									}
								}
								 break;

								 default:
									 System.out.println("Opcion invalida");
									 break;
							}
							
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
						long cuitDestinatario = this.todasLasCuentas.getArchivoCliente().getAliasConCuit()
								.get(aliasDestinatario);
						Cliente clienteDestinatario = this.todasLasCuentas.getArchivoTarjetas().getClientesConCuit()
								.get(cuitDestinatario);

						switch (tipoDeCuenta) {
						case 1: // ARS

							System.out.println(this.todosLosMensajes.monto());
							monto = entrada.nextInt();
							clienteIngresado.cajaDelClienteARS.transferir(clienteDestinatario, monto);

							this.generarTicket=new Ticket();
							this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
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
