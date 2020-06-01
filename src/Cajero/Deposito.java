package Cajero;

public final class Deposito extends MovimientosEnPantalla{
	private double monto;
	
	public Deposito(Cliente usuario, ArchivoDeCuentas todasLasCuentas){
		super(usuario, todasLasCuentas);
		this.todasLasCuentas=todasLasCuentas;
	}
	public void ejecutar() {
		
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		todosLosMensajes.monto();
		monto = entrada.nextDouble();
		
		switch (tipoDeCuenta) {
		case 1: // ARS
			depositarEnCajaDeAhorroARS();
			cerrarTodo();

			break;

		case 2: // USD
			depositarEnCajaDeAhorroUSD();
			cerrarTodo();
			break;

		case 3: // CC
			depositarEnCuentaCorriente();
			cerrarTodo();
			break;
			
			default:
				valorInvalidoIntroducido();
		}
	}

	public void depositarEnCajaDeAhorroARS() {
		try {
			if (usuario.cajaDelClienteARS.depositar(monto)) {
				todosLosMensajes.depositoExitoso();
				opcion = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
						usuario.cajaDelClienteARS.alias);
				this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				// Guarda las cosas
				this.antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo() - monto;
				this.nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
				this.todasLasCuentas.modificar.modificarSaldo("01",
						usuario.cajaDelClienteARS.getAlias(), antiguoSaldo, 0, nuevoSaldo);

				if (this.opcion == 1) { // GENERA TICKET
					this.generarTicket = new Ticket();
					this.generarTicket.escribirDeposito(usuario.cajaDelClienteARS, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					cerrarTodo();
				}
			}
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}
	}

	public void depositarEnCajaDeAhorroUSD() {
		try {
			if (usuario.cajaDelClienteUSD.depositar(monto)) {
				todosLosMensajes.depositoExitoso();
				opcion = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
						usuario.cajaDelClienteUSD.alias);
				todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				// Guarda las cosas
				antiguoSaldo = usuario.cajaDelClienteUSD.consultarSaldo() - monto;
				nuevoSaldo = usuario.cajaDelClienteUSD.consultarSaldo();
				todasLasCuentas.modificar.modificarSaldo("03",
						usuario.cajaDelClienteUSD.getAlias(), antiguoSaldo, 0, nuevoSaldo);

				if (opcion == 1) { // GENERA TICKET
					generarTicket = new Ticket();
					generarTicket.escribirDeposito(usuario.cajaDelClienteUSD, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					cerrarTodo();
				}
			}
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}
	}

	public void depositarEnCuentaCorriente() {
		try {
			if (usuario.cuentaCorrienteDelCliente.depositar(monto)) {
				todosLosMensajes.depositoExitoso();
				opcion = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
						usuario.cuentaCorrienteDelCliente.alias);
				todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);
				// Guarda las cosas
				antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo() - monto;
				nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
				double descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();
				todasLasCuentas.modificar.modificarSaldo("02",
						usuario.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo,
						descubierto, nuevoSaldo);

				if (opcion == 1) { // GENERA TICKET
					generarTicket = new Ticket();
					generarTicket.escribirDeposito(usuario.cuentaCorrienteDelCliente,
							monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					entrada.close();
					System.exit(0);
				}
			}
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}
	}
}
