package Cajero;

public class CompraUSD extends MovimientosEnPantalla{
	
	private double antiguoSaldoUSD,nuevoSaldoUSD;

	public CompraUSD(Cliente usuario,  ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
	}
	public void ejecutar() {
		
		todosLosMensajes.tipoDeCuentasEnARS();
		tipoDeCuenta = entrada.nextInt();
		
		switch (tipoDeCuenta) {
		case 1: // ARS
			comprarDesdeCajaDeAhorroARS();
			cerrarTodo();
			break;

		case 2: // CC
			comprarDesdeCuentaCorriente();
			cerrarTodo();
			break;

		default:
			valorInvalidoIntroducido();
			break;
		}
	}

	public void comprarDesdeCajaDeAhorroARS() {
		todosLosMensajes.monto();
		int monto = entrada.nextInt();
		try {
			// Guarda las cosas

			antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
			antiguoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();

			double numero = usuario.cajaDelClienteARS.comprarDolares(monto, usuario);

			todosLosMensajes.comprarDolaresExitoso(monto, numero);

			nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
			nuevoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();

			todasLasCuentas.modificar.modificarSaldo("01", usuario.cajaDelClienteARS.getAlias(),
					antiguoSaldo, 0, nuevoSaldo);
			todasLasCuentas.modificar.modificarSaldo("03", usuario.cajaDelClienteUSD.getAlias(),
					antiguoSaldoUSD, 0, nuevoSaldoUSD);

			deseaImprimir = entrada.nextInt();

			// escribe movimiento en el txt
			nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
					usuario.cajaDelClienteARS.alias);
			this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirCompraUSD(usuario.cajaDelClienteARS, monto);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void comprarDesdeCuentaCorriente() {
		todosLosMensajes.monto();
		int monto = entrada.nextInt();
		try {

			antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
			antiguoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();

			double numero = usuario.cuentaCorrienteDelCliente.comprarDolares(monto,
					usuario);

			todosLosMensajes.comprarDolaresExitoso(monto, numero);

			nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
			double descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();
			nuevoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();

			todasLasCuentas.modificar.modificarSaldo("02", usuario.cuentaCorrienteDelCliente.getAlias(),
					antiguoSaldo, descubierto, nuevoSaldo);
			todasLasCuentas.modificar.modificarSaldo("03", usuario.cajaDelClienteUSD.getAlias(),
					antiguoSaldoUSD, 0, nuevoSaldoUSD);

			deseaImprimir = entrada.nextInt();

			// escribe movimiento en el txt
			nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
					usuario.cuentaCorrienteDelCliente.alias);
			todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

			if (deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				generarTicket.escribirCompraUSD(usuario.cuentaCorrienteDelCliente, monto);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}	
	}
}
