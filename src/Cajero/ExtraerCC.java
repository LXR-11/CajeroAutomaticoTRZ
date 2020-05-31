package Cajero;

public final class  ExtraerCC extends Extraer{
	Dispensador dispenser;
	private Ticket generarTicket;
	private double antiguoSaldo,nuevoSaldo;
	private Movimiento nuevoMovimiento;


	public ExtraerCC(Cliente usuario, int opcion, Dispensador dispensadorDelMain, ArchivoDeCuentas todasLasCuentas) {
		super(usuario,opcion,dispensadorDelMain,todasLasCuentas);
		this.dispenser=dispensadorDelMain;
		this.generarTicket=new Ticket();
	}
	
	public void extraer() {
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
					this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					// Guarda las cosas
					this.antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo() + monto;
					this.nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
					double descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();
					this.todasLasCuentas.modificar.modificarSaldo("02",
							usuario.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo, descubierto,
							nuevoSaldo);

					if (this.opcion == 1) { // GENERA TICKET
						this.generarTicket = new Ticket();
						this.generarTicket.escribirExtraccion(usuario.cuentaCorrienteDelCliente, monto);
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
