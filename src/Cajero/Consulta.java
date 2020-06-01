package Cajero;

public class Consulta extends MovimientosEnPantalla{

	public Consulta(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		super(usuario, todasLasCuentas);
		generarTicket = new Ticket();
	}
	
	public void ejecutarMisConsultas() {
		todosLosMensajes.consultas();
		opcion = entrada.nextInt();
		
		switch(opcion) {
		
		case 1:		//Saldo
			ConsultaSaldo consultaSaldo = new ConsultaSaldo(usuario, todasLasCuentas);
			consultaSaldo.consultar();
			cerrarTodo();
			break;

		case 2: 	//Alias
			ConsultaAlias consultaAlias = new ConsultaAlias(usuario, todasLasCuentas);
			consultaAlias.consultar();
			cerrarTodo();
			break;

		case 3:		//Ultimos Movimientos
			ConsultaMovimiento consultaMov = new ConsultaMovimiento(usuario, todasLasCuentas);
			consultaMov.consultar();
			cerrarTodo();
			break;
			
		default:
			valorInvalidoIntroducido();
			break;
		}

	}
}
