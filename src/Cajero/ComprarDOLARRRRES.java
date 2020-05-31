package Cajero;

public class ComprarDOLARRRRES extends MovimientosEnPantalla{
	protected ArchivoDeCuentas todasLasCuentas;
	private int monto, deseaImprimir;
	private double antiguoSaldo,antiguoSaldoUSD,nuevoSaldo,nuevoSaldoUSD;
	Movimiento nuevoMovimiento;
	Ticket generarTicket;
	
	
	
	public ComprarDOLARRRRES(Cliente usuario, int opcion, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, opcion);
		this.todasLasCuentas=todasLasCuentas;
	}
	public void ejecutar() {
		switch (opcion) {
		case 1: // ARS
			todosLosMensajes.monto();
			monto = entrada.nextInt();
			try {
				// Guarda las cosas
				this.antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
				this.antiguoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();
				double numero = usuario.cajaDelClienteARS.comprarDolares(monto, usuario);

				System.out.println(numero);
				todosLosMensajes.comprarDolaresExitoso(monto, numero);

				this.nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
				this.nuevoSaldoUSD = usuario.cajaDelClienteUSD.consultarSaldo();

				this.todasLasCuentas.modificar.modificarSaldo("01", usuario.cajaDelClienteARS.getAlias(),
						antiguoSaldo, 0, nuevoSaldo);
				this.todasLasCuentas.modificar.modificarSaldo("03", usuario.cajaDelClienteUSD.getAlias(),
						antiguoSaldoUSD, 0, nuevoSaldoUSD);

				this.deseaImprimir = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
						usuario.cajaDelClienteARS.alias);
				this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				if (this.deseaImprimir == 1) { // GENERA TICKET
					this.generarTicket = new Ticket();
					this.generarTicket.escribirCompraUSD(usuario.cajaDelClienteARS, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					System.out.println("Saliendo...");
					entrada.close();
					System.exit(0);
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
				double valorFinalUSD = usuario.cuentaCorrienteDelCliente.comprarDolares(monto,
						usuario);
				todosLosMensajes.comprarDolaresExitoso(monto, valorFinalUSD);
				this.deseaImprimir = entrada.nextInt();

				// escribe movimiento en el txt
				nuevoMovimiento = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, monto,
						usuario.cuentaCorrienteDelCliente.alias);
				this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

				if (this.deseaImprimir == 1) { // GENERA TICKET
					this.generarTicket = new Ticket();
					this.generarTicket.escribirCompraUSD(usuario.cuentaCorrienteDelCliente, monto);
					System.out.println("Ticket generado correctamente.");
				} else {
					System.out.println("Saliendo...");
					entrada.close();
					System.exit(0);
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Valor invalido.");
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;
		}
	}
}
