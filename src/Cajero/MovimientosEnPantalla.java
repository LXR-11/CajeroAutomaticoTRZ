package Cajero;

import java.util.Scanner;

public class MovimientosEnPantalla {
	protected Mensajes todosLosMensajes;
	protected Cliente usuario;
	protected int opcion;
	protected Scanner entrada;
	
	public MovimientosEnPantalla(Cliente usuario, int opcion) {
		this.usuario=usuario;
		this.todosLosMensajes= new Mensajes();
		this.opcion=opcion;
		entrada = new Scanner(System.in);
	}
	
	
	public void ejecutar() {
		switch(this.opcion) {
		case 1:		//Saldo
			ConsultaSaldo consultaSaldo = new ConsultaSaldo(usuario,opcion);
			consultaSaldo.consultar();
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;

		case 2: 	//Alias
			ConsultaAlias consultaAlias = new ConsultaAlias(usuario,opcion);
			consultaAlias.consultar();
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;

		case 3:		//Ultimos Movimientos
			ConsultaMov consultaMov = new ConsultaMov(usuario,opcion);
			consultaMov.consultar();
			System.out.println("Saliendo...");
			entrada.close();
			System.exit(0);
			break;
			
		default:
			System.out.println("Valor invalido, Saliendo...");
			entrada.close();
			System.exit(0);

		}

	}


}
