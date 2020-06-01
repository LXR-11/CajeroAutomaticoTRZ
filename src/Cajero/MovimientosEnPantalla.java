package Cajero;

import java.util.Scanner;

public class MovimientosEnPantalla {
	protected Movimiento nuevoMovimiento;
	protected ArchivoDeCuentas todasLasCuentas;
	protected Mensajes todosLosMensajes;
	protected Cliente usuario;
	protected Ticket generarTicket;
	protected Scanner entrada;
	protected int opcion, deseaImprimir, tipoDeCuenta;
	protected double antiguoSaldo,nuevoSaldo;
	
	public MovimientosEnPantalla(Cliente usuario, ArchivoDeCuentas todasLasCuentas) {
		this.usuario=usuario;
		this.todasLasCuentas = todasLasCuentas;
		this.todosLosMensajes= new Mensajes();
		entrada = new Scanner(System.in);
	}
	
	public void ejecutarLaEleccionDeMovimiento(int opcionElegida) {

		switch (opcionElegida) {

		case 1: //Consulta
			Consulta misConsultas = new Consulta(usuario, todasLasCuentas);	
			misConsultas.ejecutarMisConsultas();
			
			break;

		case 2: //Extraer
			Extraccion extraccion= new Extraccion(usuario, todasLasCuentas);
			extraccion.ejecutar();		
			break;

		case 3: //Comprar Dolares
			CompraUSD compra= new CompraUSD(usuario, todasLasCuentas);
			compra.ejecutar();
			break;

		case 4: //Depositar
			Deposito deposito = new Deposito(usuario, todasLasCuentas);
			deposito.ejecutar();
			break;
			
		case 5: //Transferencia
			Transferencia transferencia = new Transferencia(usuario, todasLasCuentas);
			transferencia.ejecutar();
			break;
			
		default:
			valorInvalidoIntroducido();
			break;
		}

	}
	
	
	public void valorInvalidoIntroducido() {
		System.out.println(
				"*************************************************************"
				+ "\n"
				+ "\nLo sentimos, ha introducido un valor invalido."
				+ "\nCerraremos el programa, intente nuevamente."
				+ "\n"
				+"*************************************************************");
		cerrarTodo();
	}
	public void cerrarTodo() {
		System.out.println("Saliendo...");
		this.entrada.close();
		System.exit(0);
	}


}
