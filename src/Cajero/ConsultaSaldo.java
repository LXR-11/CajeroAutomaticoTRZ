package Cajero;

public class ConsultaSaldo extends Consulta{

	public ConsultaSaldo(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
		generarTicket = new Ticket();
	}

	public void consultar() {
		super.todosLosMensajes.tipoDeCuenta();
		tipoDeCuenta = entrada.nextInt();
		switch(tipoDeCuenta) {
		case 1:	//ARS 
			todosLosMensajes.saldo(super.usuario.cajaDelClienteARS.consultarSaldo());
			this.deseaImprimir = super.entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(super.usuario.cajaDelClienteARS);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		case 2:		//USD
			todosLosMensajes.saldo(this.usuario.cajaDelClienteUSD.consultarSaldo());
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(this.usuario.cajaDelClienteUSD);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;

		case 3:	//CC
			todosLosMensajes.saldo(this.usuario.cuentaCorrienteDelCliente.consultarSaldo());
			this.deseaImprimir = entrada.nextInt();
			if (this.deseaImprimir == 1) { // GENERA TICKET
				generarTicket = new Ticket();
				this.generarTicket.escribirSaldo(this.usuario.cuentaCorrienteDelCliente);
				System.out.println("Ticket generado correctamente.");
			} else {
				cerrarTodo();
			}
			break;
		}
	}
}
