package Cajero;

public class Extraccion extends MovimientosEnPantalla {

	private Dispensador dispenser;

	public Extraccion(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
		this.dispenser = new Dispensador();
	}

	public void ejecutar() {
		todosLosMensajes.tipoDeCuentasEnARS();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {
		case 1: // ARS
			extraerARS();
			cerrarTodo();
			break;

		case 2: // CC
			extraerCC();
			cerrarTodo();
			break;

		default:
			valorInvalidoIntroducido();

		}
	}

	public void extraerARS() {
		todosLosMensajes.monto();
		int monto = entrada.nextInt();
		try {
			if (usuario.verificarCuentaEnCliente(1)) { // VERIFICA QUE HAYA CAJA

				if (usuario.cajaDelClienteARS.retirarEfectivo(monto)) {

					System.out.println(dispenser.retirarBillete(monto));
					todosLosMensajes.extraerExitoso(monto);

					// escribe nuevo movimiento en el sistema
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto,
							usuario.cajaDelClienteARS.alias);
					todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo() + monto;
					nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
					todasLasCuentas.modificar.modificarSaldo("01", usuario.cajaDelClienteARS.getAlias(),
							antiguoSaldo, 0, nuevoSaldo);

					this.opcion = entrada.nextInt();
					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirExtraccion(usuario.cajaDelClienteARS, monto);
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

	}

	public void extraerCC() {
		todosLosMensajes.monto();
		int monto = entrada.nextInt();
		try {
			if (usuario.verificarCuentaEnCliente(3)) {

				if (usuario.cuentaCorrienteDelCliente.retirarEfectivo(monto)) {

					System.out.println(this.dispenser.retirarBillete(monto));
					todosLosMensajes.extraerExitoso(monto);
					this.opcion = entrada.nextInt();

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto,
							usuario.cuentaCorrienteDelCliente.alias);
					todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo() + monto;
					this.nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
					double descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();
					todasLasCuentas.modificar.modificarSaldo("02",
							usuario.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo, descubierto,
							nuevoSaldo);

					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirExtraccion(usuario.cuentaCorrienteDelCliente, monto);
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
	}

}
