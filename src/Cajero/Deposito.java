package Cajero;

public final class Deposito extends MovimientosEnPantalla{
	private ArchivoDeCuentas todasLasCuentas;
	Ticket generarTicket;
	private Movimiento nuevoMovimiento;
	private double antiguoSaldo,nuevoSaldo;
	
	public Deposito(Cliente usuario, int opcion, ArchivoDeCuentas todasLasCuentas){
		super(usuario, opcion);
		this.todasLasCuentas=todasLasCuentas;
	}
	public void ejecutar() {
		todosLosMensajes.monto();
		double monto = entrada.nextDouble();
		switch (opcion) {
		case 1: // ARS

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
						System.out.println("Saliendo..");
						entrada.close();
						System.exit(0);
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}

			break;

		case 2: // USD
			try {
				if (usuario.cajaDelClienteUSD.depositar(monto)) {
					todosLosMensajes.depositoExitoso();
					opcion = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
							usuario.cajaDelClienteUSD.alias);
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = usuario.cajaDelClienteUSD.consultarSaldo() - monto;
					this.nuevoSaldo = usuario.cajaDelClienteUSD.consultarSaldo();
					this.todasLasCuentas.modificar.modificarSaldo("03",
							usuario.cajaDelClienteUSD.getAlias(), antiguoSaldo, 0, nuevoSaldo);

					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirDeposito(usuario.cajaDelClienteUSD, monto);
						System.out.println("Ticket generado correctamente.");
					} else {
						entrada.close();
						System.exit(0);
					}
				}
			} catch (ErroresDeCuenta e) {
				System.out.println(e.getMessage());
			}
			break;

		case 3: // CC
			try {
				if (usuario.cuentaCorrienteDelCliente.depositar(monto)) {
					todosLosMensajes.depositoExitoso();
					opcion = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.DEPOSITO, monto,
							usuario.cuentaCorrienteDelCliente.alias);
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);
					// Guarda las cosas
					this.antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo() - monto;
					this.nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
					double descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();
					this.todasLasCuentas.modificar.modificarSaldo("02",
							usuario.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo,
							descubierto, nuevoSaldo);

					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirDeposito(usuario.cuentaCorrienteDelCliente,
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
			break;
			
			default:
				System.out.println("Valor invalido.");
				entrada.close();
				System.exit(0);
				break;
		}
	}
}
