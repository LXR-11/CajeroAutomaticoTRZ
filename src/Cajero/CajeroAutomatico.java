package Cajero;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//
public class CajeroAutomatico {

	double antiguoSaldo, antiguoSaldoUSD, nuevoSaldo, nuevoSaldoUSD, descubierto;
	private ArchivoDeCuentas todasLasCuentas;
	private Mensajes todosLosMensajes;
	private Scanner entrada;
	private Dispensador dispensador;
	private int numeroDeTarjeta, pin, deseaImprimir;
	private Ticket generarTicket;
	private Movimiento nuevoMovimiento;
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
					int opcionConsultas = entrada.nextInt();
					switch (opcionConsultas) {

					//////////////// CONSULTAR SALDO ////////////////
					case 1:
						consultarSaldo();
						break;
					//////////////// CONSULTAR ALIAS ////////////////
					case 2:
						consultarAlias();
						break;

					//////////////// CONSULTAR ULTIMOS MOVIMIENTOS ////////////////
					case 3:
						consultarUltimosMovimientos();
						break;

					default:
						System.out.println("Valor invalido.");
						cerrarTodo();
						break;
					}
					break;

				//////////////// EXTRAER ////////////////
				case 2:
					ejecutarExtraer();
					break;

				//////////////// COMPRAR USD ////////////////

				case 3:
					ejecutarCompraUSD();
					break;

				//////////////// DEPOSITAR ////////////////
				case 4:
					ejecutarDeposito();
					break;
					
				//////////////// TRANSFERENCIA////////////////
				case 5:
					ejecutarTransferencia();
					break;
					
				default:
					System.out.println("Valor invalido");
					cerrarTodo();
				}

			}

			else {
				System.out.println("Usuario no encontrado");
			}
		} catch (InputMismatchException e) {

			System.out.println("Valor Invalido."
					+ "\nSaliendo...");

		} finally {

			entrada.close();
		}

	}

	public void cerrarTodo() {
		System.out.println("Saliendo...");
		entrada.close();
		System.exit(0);
	}

	public void consultarSaldo() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {

		case 1: // ARS

			todosLosMensajes.saldo(clienteIngresado.cajaDelClienteARS.consultarSaldo());
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteARS);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		case 2: // USD
			todosLosMensajes.saldo(clienteIngresado.cajaDelClienteUSD.consultarSaldo());
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(clienteIngresado.cajaDelClienteUSD);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		case 3: // CC
			todosLosMensajes.saldo(clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo());
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(clienteIngresado.cuentaCorrienteDelCliente);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;
		default:
			System.out.println("Valor invalido.");
			cerrarTodo();
			break;
		}
	}

	public void consultarAlias() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {
		case 1: // ARS
			todosLosMensajes.alias(clienteIngresado.cajaDelClienteARS);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
						clienteIngresado.cajaDelClienteARS.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}

			break;

		case 2: // USD
			todosLosMensajes.alias(clienteIngresado.cajaDelClienteUSD);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
						clienteIngresado.cajaDelClienteUSD.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}

			break;

		case 3: // CC
			todosLosMensajes.alias(clienteIngresado.cuentaCorrienteDelCliente);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(tarjetaIngresada.getNumeroDeTarjeta(),
						clienteIngresado.cuentaCorrienteDelCliente.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		default:
			System.out.println("Valor invalido.");
			cerrarTodo();
			break;
		}
	}

	public void consultarUltimosMovimientos() {

		todosLosMensajes.cantidadDeMovimientos(); // pregunta cuantos mov quiere consultar
		int cantidadDeMovimientos = entrada.nextInt();
		// pregunta tipo de cuenta
		todosLosMensajes.tipoDeCuenta();
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
			System.out.println("Valor invalido.");
			cerrarTodo();
			break;
		}
	}

	public void ejecutarExtraer() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {
		case 1: // ARS
			todosLosMensajes.monto();
			monto = entrada.nextInt();
			try {
				if (clienteIngresado.verificarCuentaEnCliente(1)) { // VERIFICA QUE HAYA CAJA

					if (clienteIngresado.cajaDelClienteARS.retirarEfectivo(monto)) {

						System.out.println(this.dispensador.retirarBillete(monto));
						todosLosMensajes.extraerExitoso(monto);

						// escribe nuevo movimiento en el sistema
						nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto,
								clienteIngresado.cajaDelClienteARS.alias);
						this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

						// Guarda las cosas
						this.antiguoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo() + monto;
						this.nuevoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo();
						this.todasLasCuentas.modificar.modificarSaldo("01",
								clienteIngresado.cajaDelClienteARS.getAlias(), antiguoSaldo, 0, nuevoSaldo);

						this.deseaImprimir = entrada.nextInt();
						if (this.deseaImprimir == 1) { // GENERA TICKET
							this.generarTicket = new Ticket();
							this.generarTicket.escribirExtraccion(clienteIngresado.cajaDelClienteARS, monto);
							System.out.println("Ticket generado correctamente.");
						} else {
							cerrarTodo();
						}
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			} catch (ErroresDeDispensador e) {
				System.out.println(e.getMessage());
			}

			break;

		case 2: // USD
			System.out.println("No se puede retirar efectivo desde una Caja de ahorro en USD");
			break;

		case 3: // CC
			todosLosMensajes.monto();
			monto = entrada.nextInt();
			try {
				if (clienteIngresado.verificarCuentaEnCliente(3)) {

					if (clienteIngresado.cuentaCorrienteDelCliente.retirarEfectivo(monto)) {

						System.out.println(this.dispensador.retirarBillete(monto));
						todosLosMensajes.extraerExitoso(monto);
						this.deseaImprimir = entrada.nextInt();

						// escribe movimiento en el txt
						nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto,
								clienteIngresado.cuentaCorrienteDelCliente.alias);
						this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

						// Guarda las cosas
						this.antiguoSaldo = clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo() + monto;
						this.nuevoSaldo = clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo();
						this.descubierto = clienteIngresado.cuentaCorrienteDelCliente.getDescubierto();
						this.todasLasCuentas.modificar.modificarSaldo("02",
								clienteIngresado.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo, descubierto,
								nuevoSaldo);

						if (this.deseaImprimir == 1) { // GENERA TICKET
							this.generarTicket = new Ticket();
							this.generarTicket.escribirExtraccion(clienteIngresado.cuentaCorrienteDelCliente, monto);
							System.out.println("Ticket generado correctamente.");
						} else {
							cerrarTodo();
						}
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			} catch (ErroresDeDispensador e) {
				System.out.println(e.getMessage());
			}
			break;

		default:
			System.out.println("Valor invalido.");
			cerrarTodo();
			break;
		}
	}

	public void ejecutarCompraUSD() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {
		case 1: // ARS
			todosLosMensajes.monto();
			monto = entrada.nextInt();
			try {
				// Guarda las cosas
				this.antiguoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo();
				this.antiguoSaldoUSD = clienteIngresado.cajaDelClienteUSD.consultarSaldo();
				double numero = clienteIngresado.cajaDelClienteARS.comprarDolares(monto, clienteIngresado);

				System.out.println(numero);
				todosLosMensajes.comprarDolaresExitoso(monto, numero);

				this.nuevoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo();
				this.nuevoSaldoUSD = clienteIngresado.cajaDelClienteUSD.consultarSaldo();

				this.todasLasCuentas.modificar.modificarSaldo("01", clienteIngresado.cajaDelClienteARS.getAlias(),
						antiguoSaldo, 0, nuevoSaldo);
				this.todasLasCuentas.modificar.modificarSaldo("03", clienteIngresado.cajaDelClienteUSD.getAlias(),
						antiguoSaldoUSD, 0, nuevoSaldoUSD);

				this.deseaImprimir = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
						clienteIngresado.cajaDelClienteARS.alias);
				this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				if (this.deseaImprimir == 1) { // GENERA TICKET
					this.generarTicket = new Ticket();
					this.generarTicket.escribirCompraUSD(clienteIngresado.cajaDelClienteARS, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					cerrarTodo();
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;

		case 2: // USD
			System.out.println("No se puede comprar USD desde esta cuenta");
			break;

		case 3: // CC
			todosLosMensajes.monto();
			monto = entrada.nextInt();
			try {
				double valorFinalUSD = clienteIngresado.cuentaCorrienteDelCliente.comprarDolares(monto,
						clienteIngresado);
				todosLosMensajes.comprarDolaresExitoso(monto, valorFinalUSD);
				this.deseaImprimir = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
						clienteIngresado.cuentaCorrienteDelCliente.alias);
				this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				if (this.deseaImprimir == 1) { // GENERA TICKET
					this.generarTicket = new Ticket();
					this.generarTicket.escribirCompraUSD(clienteIngresado.cuentaCorrienteDelCliente, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					cerrarTodo();
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Valor invalido.");
			cerrarTodo();
			break;
		}
	}

	public void ejecutarDeposito() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		todosLosMensajes.monto();
		monto = entrada.nextInt();
		switch (tipoDeCuenta) {

		case 1: // ARS

			try {
				if (clienteIngresado.cajaDelClienteARS.depositar(monto)) {
					todosLosMensajes.depositoExitoso();
					deseaImprimir = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
							clienteIngresado.cajaDelClienteARS.alias);
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo() - monto;
					this.nuevoSaldo = clienteIngresado.cajaDelClienteARS.consultarSaldo();
					this.todasLasCuentas.modificar.modificarSaldo("01",
							clienteIngresado.cajaDelClienteARS.getAlias(), antiguoSaldo, 0, nuevoSaldo);

					if (this.deseaImprimir == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteARS, monto);
						System.out.println("Ticket generado correctamente.");
					} else {
						cerrarTodo();
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}

			break;

		case 2: // USD
			try {
				if (clienteIngresado.cajaDelClienteUSD.depositar(monto)) {
					todosLosMensajes.depositoExitoso();
					deseaImprimir = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
							clienteIngresado.cajaDelClienteUSD.alias);
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = clienteIngresado.cajaDelClienteUSD.consultarSaldo() - monto;
					this.nuevoSaldo = clienteIngresado.cajaDelClienteUSD.consultarSaldo();
					this.todasLasCuentas.modificar.modificarSaldo("03",
							clienteIngresado.cajaDelClienteUSD.getAlias(), antiguoSaldo, 0, nuevoSaldo);

					if (this.deseaImprimir == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirDeposito(clienteIngresado.cajaDelClienteUSD, monto);
						System.out.println("Ticket generado correctamente.");
					} else {
						cerrarTodo();
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;

		case 3: // CC
			try {
				if (clienteIngresado.cuentaCorrienteDelCliente.depositar(monto)) {
					todosLosMensajes.depositoExitoso();
					deseaImprimir = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
							clienteIngresado.cuentaCorrienteDelCliente.alias);
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo() - monto;
					this.nuevoSaldo = clienteIngresado.cuentaCorrienteDelCliente.consultarSaldo();
					this.descubierto = clienteIngresado.cuentaCorrienteDelCliente.getDescubierto();
					this.todasLasCuentas.modificar.modificarSaldo("02",
							clienteIngresado.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo,
							descubierto, nuevoSaldo);

					if (this.deseaImprimir == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirDeposito(clienteIngresado.cuentaCorrienteDelCliente,
								monto);
						System.out.println("Ticket generado correctamente.");
					} else {
						cerrarTodo();
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;
			
			default:
				System.out.println("Valor invalido.");
				cerrarTodo();
				break;
		}

	}
	
	public void ejecutarTransferencia() {
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		todosLosMensajes.transferenciaAlias();
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

				todosLosMensajes.monto();
				monto = entrada.nextInt();
				try {
					if (clienteIngresado.cajaDelClienteARS.transferir(clienteDestinatario, monto)) {

						todosLosMensajes.transferenciaExitosa(monto);
						int revertirOTicket = entrada.nextInt();

						switch (revertirOTicket) {
						case 1: // REVIERTE TRANSFERENCIA
							Cuenta destinataria = this.todasLasCuentas
									.encontrarCuentaPorAlias(aliasDestinatario);
							double saldo = (double) monto;
							clienteIngresado.cajaDelClienteARS.revertirUltimaTransferencia(saldo,
									destinataria);
							System.out.println("Se ha revertido con exito la transferencia"
									+ " "+ saldo+"ARS a "+ aliasDestinatario);

							break;
						case 2: // GENERAR TICKET

							// escribe movimiento en el txt
							MovimientoReversible miMovimiento = new MovimientoReversible(
									TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,
									clienteIngresado.cajaDelClienteARS.alias, aliasDestinatario);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

							this.generarTicket = new Ticket();
							this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
							System.out.println("Ticket generado correctamente.");
							break;
						default:
							System.out.println("Valor invalido");
							cerrarTodo();
							break;
						}
					} 
					} catch (ErroresDeCuenta e) {
					System.out.println(e.getMessage());
				}

				break;
			case 2: // CC

				todosLosMensajes.monto();
				monto = entrada.nextInt();
				try {
					if (clienteIngresado.cuentaCorrienteDelCliente.transferir(clienteDestinatario, monto)) {

						todosLosMensajes.transferenciaExitosa(monto);
						int revertirOTicket = entrada.nextInt();
						switch (revertirOTicket) {
						case 1: // REVIERTE TRANSFERENCIA
							Cuenta destinataria = this.todasLasCuentas
									.encontrarCuentaPorAlias(aliasDestinatario);
							double saldo = (double) monto;
							clienteIngresado.cuentaCorrienteDelCliente.revertirUltimaTransferencia(saldo,
									destinataria);
							System.out.println("Se ha revertido con exito.");
							break;

						case 2: // GENERAR TICKET

							// escribe movimiento en el txt
							MovimientoReversible miMovimiento = new MovimientoReversible(
									TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,
									clienteIngresado.cuentaCorrienteDelCliente.alias, aliasDestinatario);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

							this.generarTicket = new Ticket();
							this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
							System.out.println("Ticket generado correctamente.");
							break;
						default:
							System.out.println("Valor invalido");
							cerrarTodo();
							break;
						}
					}

				} catch (ErroresDeCuenta e) {
					System.out.println(e.getMessage());
				}

				break;
				
				default:
					System.out.println("Valor invalido");
					cerrarTodo();
					break;
			}
		} else {
			System.out.println("No se ha encontrado un alias con ese nombre.");
		}

	}
}
