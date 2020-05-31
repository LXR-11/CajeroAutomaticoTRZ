package Cajero;

public class ConsultaAlias extends MovimientosEnPantalla{
	private int deseaImprimir;
	private Ticket generarTicket;
	public ConsultaAlias(Cliente usuario,int opcion) {
		super(usuario,opcion);
	}

	public void consultar() {
		todosLosMensajes.tipoDeCuenta();
		int tipo = entrada.nextInt();
		switch (tipo) {
		case 1: // ARS
			todosLosMensajes.alias(usuario.cajaDelClienteARS);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(),
						usuario.cajaDelClienteARS.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				System.out.println("Saliendo...");
				entrada.close();
				System.exit(0);
				break;
			}

			break;

		case 2: // USD
			todosLosMensajes.alias(usuario.cajaDelClienteUSD);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(),
						usuario.cajaDelClienteUSD.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				System.out.println("Saliendo...");
				entrada.close();
				System.exit(0);
				break;
			}

			break;

		case 3: // CC
			todosLosMensajes.alias(usuario.cuentaCorrienteDelCliente);
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				this.generarTicket = new Ticket();
				this.generarTicket.escribirConsulta(usuario.tarjeta.getNumeroDeTarjeta(),
						usuario.cuentaCorrienteDelCliente.getAlias());
				System.out.println("Ticket generado correctamente.");
			} else {
				System.out.println("Saliendo...");
				entrada.close();
				System.exit(0);
				break;
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
