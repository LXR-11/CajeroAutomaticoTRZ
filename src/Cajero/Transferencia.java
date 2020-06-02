package Cajero;

public final class Transferencia extends MovimientosEnPantalla implements Reversible {

	private int monto;
	private String aliasDestinatario;
	private Cliente clienteDestinatario;
	private double antiguoSaldoReceptor,nuevoSaldoReceptor, descubierto;
	private String tipoDeCuentaDestinatario;
	
	public Transferencia(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
	}

	public void ejecutar() {
		
		todosLosMensajes.tipoDeCuentasEnARS();
		tipoDeCuenta = entrada.nextInt();
		
		todosLosMensajes.transferenciaAlias();
		aliasDestinatario = entrada.next();
		
		if (this.todasLasCuentas.getArchivoCliente().getAliasConCuit().containsKey(aliasDestinatario)) { 
			//Pregunta si existe cuit con ese alias.
			long cuitDestinatario = todasLasCuentas.getArchivoCliente().getAliasConCuit()
					.get(aliasDestinatario);
			clienteDestinatario = todasLasCuentas.getArchivoTarjetas().getClientesConCuit()
					.get(cuitDestinatario);

			switch (tipoDeCuenta) {
			case 1: // ARS
				transferirDesdeCajaEnARS();
				cerrarTodo();
				break;
			case 2: // CC
				transferirDesdeCuentaCorriente();
				cerrarTodo();
				break;

			default:
				valorInvalidoIntroducido();
				break;
			}
		} else {
			System.out.println("No se ha encontrado un alias con ese nombre.");
		}

	}

	public void transferirDesdeCajaEnARS() {
		todosLosMensajes.monto();
		monto = entrada.nextInt();
		try {
			this.antiguoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
			// Antiguo saldo cliente ingresado
			if (usuario.cajaDelClienteARS.transferir(clienteDestinatario,aliasDestinatario, monto)) {
				
				escribirEnTxtTransferenciaDesdeARS();

				todosLosMensajes.transferenciaExitosa(monto);
				int revertirOTicket = entrada.nextInt();
					switch (revertirOTicket) {
					case 1: // REVIERTE TRANSFERENCIA
						revertir(usuario.cajaDelClienteARS);
						revertirEnTxtTransferenciaDesdeARS();
						break;
					case 2: // GENERAR TICKET
	
						// escribe movimiento en el txt
						nuevoMovimiento = new Movimiento(TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,usuario.cajaDelClienteARS.alias);
						todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);
	
						generarTicket = new Ticket();
						generarTicket.escribirTransferencia(aliasDestinatario, monto);
						System.out.println("Ticket generado correctamente.");
						break;
					case 3:
						cerrarTodo();
						break;
					default:
						valorInvalidoIntroducido();
						break;
					}
			} 
		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}


	}
	
	public void transferirDesdeCuentaCorriente() {
		todosLosMensajes.monto();
		monto = entrada.nextInt();
		try {
			this.antiguoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
			// Antiguo saldo cliente ingresado
			if (usuario.cuentaCorrienteDelCliente.transferir(clienteDestinatario,aliasDestinatario, monto)) {
				
				escribirEnTxtTransferenciaDesdeCC();
				
				todosLosMensajes.transferenciaExitosa(monto);
				int revertirOTicket = entrada.nextInt();
				switch (revertirOTicket) {
				case 1: // REVIERTE TRANSFERENCIA
					revertir(usuario.cuentaCorrienteDelCliente);
					revertirEnTxtTransferenciaDesdeCC();
					break;

				case 2: // GENERAR TICKET

					// escribe movimiento en el txt
					nuevoMovimiento = new Movimiento(
							TipoDeMovimiento.TRANSFERENCIAENPESOS, monto,
							usuario.cuentaCorrienteDelCliente.alias);
					todasLasCuentas.getArchivoMovimientos().escribirMovimiento(nuevoMovimiento);

					generarTicket = new Ticket();
					generarTicket.escribirTransferencia(aliasDestinatario, monto);
					System.out.println("Ticket generado correctamente.");
					break;
					
				default:
					valorInvalidoIntroducido();
					break;
				}
			}

		} catch (ErroresDeCuenta e) {
			System.out.println(e.getMessage());
		}

	}
	
	public void escribirEnTxtTransferenciaDesdeARS() {
		this.nuevoSaldo = usuario.cajaDelClienteARS.consultarSaldo();
		// Nuevo saldo cliente ingresado
		this.todasLasCuentas.modificar.modificarSaldo("01", usuario.cajaDelClienteARS.getAlias(), antiguoSaldo, 0, nuevoSaldo);
		
		if ( clienteDestinatario.cajaDelClienteARS.getAlias().equals(aliasDestinatario) ){
			antiguoSaldoReceptor = ( clienteDestinatario.cajaDelClienteARS.consultarSaldo() - monto ); // Antiguo saldo cliente receptor
			nuevoSaldoReceptor = clienteDestinatario.cajaDelClienteARS.consultarSaldo(); // Nuevo saldo cliente receptor
			tipoDeCuentaDestinatario = "01";
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, antiguoSaldoReceptor, 0, nuevoSaldoReceptor);
		} else if ( clienteDestinatario.cuentaCorrienteDelCliente.getAlias().equals(aliasDestinatario) ) {
			antiguoSaldoReceptor = ( clienteDestinatario.cuentaCorrienteDelCliente.consultarSaldo() - monto ); // Antiguo saldo cliente receptor
			nuevoSaldoReceptor = clienteDestinatario.cuentaCorrienteDelCliente.consultarSaldo(); // Nuevo saldo cliente receptor
			tipoDeCuentaDestinatario = "02";
			descubierto = clienteDestinatario.cuentaCorrienteDelCliente.getDescubierto();
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, antiguoSaldoReceptor, descubierto, nuevoSaldoReceptor);
		} else {
			System.out.println("No se puede realizar transferencias a una cuenta en dolares");
		}
	}
	
	public void escribirEnTxtTransferenciaDesdeCC() {
		this.nuevoSaldo = usuario.cuentaCorrienteDelCliente.consultarSaldo();
		// Nuevo saldo cliente ingresado
		descubierto = usuario.cuentaCorrienteDelCliente.getDescubierto();

		this.todasLasCuentas.modificar.modificarSaldo("01", usuario.cuentaCorrienteDelCliente.getAlias(), antiguoSaldo, descubierto, nuevoSaldo);
		String tipoDeCuentaDestinatario = null;
		if ( clienteDestinatario.cajaDelClienteARS.getAlias().equals(aliasDestinatario) ){
			antiguoSaldoReceptor = ( clienteDestinatario.cajaDelClienteARS.consultarSaldo() - monto );
			// Antiguo saldo cliente receptor
			nuevoSaldoReceptor = clienteDestinatario.cajaDelClienteARS.consultarSaldo();
			// Nuevo saldo cliente receptor
			tipoDeCuentaDestinatario = "01";
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, antiguoSaldoReceptor, 0, nuevoSaldoReceptor);
		} else if ( clienteDestinatario.cuentaCorrienteDelCliente.getAlias().equals(aliasDestinatario) ) {
			antiguoSaldoReceptor = ( clienteDestinatario.cuentaCorrienteDelCliente.consultarSaldo() - monto );
			// Antiguo saldo cliente receptor
			nuevoSaldoReceptor = clienteDestinatario.cuentaCorrienteDelCliente.consultarSaldo();
			// Nuevo saldo cliente receptor
			tipoDeCuentaDestinatario = "02";
			descubierto = clienteDestinatario.cuentaCorrienteDelCliente.getDescubierto();
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, antiguoSaldoReceptor, descubierto, nuevoSaldoReceptor);
		} else {
			System.out.println("No se puede realizar transferencias a una cuenta en dolares");
		}
	}
	
	public void revertirEnTxtTransferenciaDesdeARS() {
		this.todasLasCuentas.modificar.modificarSaldo("01", usuario.cajaDelClienteARS.getAlias(), nuevoSaldo, 0, antiguoSaldo);
		if (clienteDestinatario.cajaDelClienteARS.getAlias().equals(aliasDestinatario) ) {
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, nuevoSaldoReceptor, 0, antiguoSaldoReceptor);
		} else if ( clienteDestinatario.cuentaCorrienteDelCliente.getAlias().equals(aliasDestinatario) ) {
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, nuevoSaldoReceptor, 0, antiguoSaldoReceptor);
		}
	}
	
	public void revertirEnTxtTransferenciaDesdeCC() {
		this.todasLasCuentas.modificar.modificarSaldo("02", usuario.cuentaCorrienteDelCliente.getAlias(), nuevoSaldo, descubierto, antiguoSaldo);
		if ( clienteDestinatario.cajaDelClienteARS.getAlias().equals(aliasDestinatario ) ) {
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, nuevoSaldoReceptor, 0, antiguoSaldoReceptor);
		} else if ( clienteDestinatario.cuentaCorrienteDelCliente.getAlias().equals(aliasDestinatario) ){
			this.todasLasCuentas.modificar.modificarSaldo(tipoDeCuentaDestinatario, aliasDestinatario, nuevoSaldoReceptor, descubierto, antiguoSaldoReceptor);
		}
	}
	
	public void revertir(Cuenta miCuenta) {
		Cuenta destinataria = todasLasCuentas.encontrarCuentaPorAlias(aliasDestinatario);
		double monto_ = (double) monto;
		miCuenta.revertirUltimaTransferencia(monto_,
				destinataria);
		System.out.println("Se ha revertido con exito la transferencia"
				+ " "+ monto_+"ARS a "+ aliasDestinatario);

	}
}
