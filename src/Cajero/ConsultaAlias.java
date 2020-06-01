package Cajero;

public class ConsultaAlias extends Consulta {

	public ConsultaAlias(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
		generarTicket = new Ticket();
	}

	public void consultar() {
		
		todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch (tipoDeCuenta) {
		case 1: // ARS
			todosLosMensajes.alias(usuario.cajaDelClienteARS);
			this.deseaImprimir = entrada.nextInt();
			if (deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				
				generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(),
						usuario.cajaDelClienteARS.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}

			break;

		case 2: // USD
			todosLosMensajes.alias(usuario.cajaDelClienteUSD);
			this.deseaImprimir = entrada.nextInt();
			if (deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(), usuario.cajaDelClienteUSD.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}

			break;

		case 3: // CC
			todosLosMensajes.alias(usuario.cuentaCorrienteDelCliente);
			deseaImprimir = entrada.nextInt();
			if (deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(), usuario.cuentaCorrienteDelCliente.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		default:
			valorInvalidoIntroducido();
			break;
		}
	}
}
