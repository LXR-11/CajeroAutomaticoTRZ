package Cajero;

public final class ExtraerARS extends Extraer{
	Dispensador dispenser;
	private Ticket generarTicket;
	private double antiguoSaldo,nuevoSaldo;
	private Movimiento nuevoMovimiento;


	public ExtraerARS(Cliente usuario, int opcion, Dispensador dispensadorDelMain, ArchivoDeCuentas todasLasCuentas) {
		super(usuario,opcion,dispensadorDelMain,todasLasCuentas);
		this.dispenser=dispensadorDelMain;
		this.generarTicket=new Ticket();
	}

	public void extraer() {
		// ARS
		todosLosMensajes.monto();
		int monto = entrada.nextInt();
		try {
			if (usuario.verificarCuentaEnCliente(1)) { // VERIFICA QUE HAYA CAJA

				if (usuario.cajaDelClienteARS.retirarEfectivo(monto)) {

					System.out.println(this.dispenser.retirarBillete(monto));
					todosLosMensajes.extraerExitoso(monto);

					// escribe nuevo movimiento en el sistema
					nuevoMovimiento = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, monto,
							usuario.cajaDelClienteARS.alias);
					super.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo() + monto;
					nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
					this.todasLasCuentas.modificar.modificarSaldo("01",
							usuario.cajaDelClienteARS.getAlias(), antiguoSaldo, 0, nuevoSaldo);

					this.opcion = entrada.nextInt();
					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirExtraccion(usuario.cajaDelClienteARS, monto);
						System.out.println("Ticket generado correctamente.");
					} else {
						System.out.println("Saliendo...");
						entrada.close();
						System.exit(0);
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


