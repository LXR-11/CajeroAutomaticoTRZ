package Cajero;

public final class Transferencia extends MovimientosEnPantalla{
	private ArchivoDeCuentas todasLasCuentas;
	Ticket generarTicket;

	public Transferencia(Cliente usuario, int opcion, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, opcion);
		this.todasLasCuentas=todasLasCuentas;
	}

	public void ejecutar() {
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

			switch (opcion) {
			case 1: // ARS

				todosLosMensajes.monto();
				int monto = entrada.nextInt();
				try {
					if (usuario.cajaDelClienteARS.transferir(clienteDestinatario, monto)) {

						todosLosMensajes.transferenciaExitosa(monto);
						int revertirOTicket = entrada.nextInt();

						switch (revertirOTicket) {
						case 1: // REVIERTE TRANSFERENCIA
							Cuenta destinataria = this.todasLasCuentas
							.encontrarCuentaPorAlias(aliasDestinatario);
							double saldo = (double) monto;
							usuario.cajaDelClienteARS.revertirUltimaTransferencia(saldo,
									destinataria);
							System.out.println("Se ha revertido con exito la transferencia"
									+ " "+ saldo+"ARS a "+ aliasDestinatario);

							break;
						case 2: // GENERAR TICKET

							// escribe movimiento en el txt
							MovimientoReversible miMovimiento = new MovimientoReversible(
									TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,
									usuario.cajaDelClienteARS.alias, aliasDestinatario);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

							this.generarTicket = new Ticket();
							this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
							System.out.println("Ticket generado correctamente.");
							break;
						default:
							System.out.println("Valor invalido");
							entrada.close();
							System.exit(0);
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
					if (usuario.cuentaCorrienteDelCliente.transferir(clienteDestinatario, monto)) {

						todosLosMensajes.transferenciaExitosa(monto);
						int revertirOTicket = entrada.nextInt();
						switch (revertirOTicket) {
						case 1: // REVIERTE TRANSFERENCIA
							Cuenta destinataria = this.todasLasCuentas
							.encontrarCuentaPorAlias(aliasDestinatario);
							double saldo = (double) monto;
							usuario.cuentaCorrienteDelCliente.revertirUltimaTransferencia(saldo,
									destinataria);
							System.out.println("Se ha revertido con exito.");
							break;

						case 2: // GENERAR TICKET

							// escribe movimiento en el txt
							MovimientoReversible miMovimiento = new MovimientoReversible(
									TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,
									usuario.cuentaCorrienteDelCliente.alias, aliasDestinatario);
							this.todasLasCuentas.getArchivoMovimientos().escribirMovimiento(miMovimiento);

							this.generarTicket = new Ticket();
							this.generarTicket.escribirTransferencia(aliasDestinatario, monto);
							System.out.println("Ticket generado correctamente.");
							break;
						default:
							System.out.println("Valor invalido");
							entrada.close();
							System.exit(0);
							break;
						}
					}

				} catch (ErroresDeCuenta e) {
					System.out.println(e.getMessage());
				}

				break;

			default:
				System.out.println("Valor invalido");
				entrada.close();
				System.exit(0);
				break;
			}
		} else {
			System.out.println("No se ha encontrado un alias con ese nombre.");
		}

	}
}
