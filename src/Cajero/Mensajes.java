package Cajero;
//
public class Mensajes {

	public Mensajes() {}



	public void bienvenida() {

		System.out.println(
		  "*************************************************************"
		+ "\n"
		+ "\n                    BANCO"
		+ "\n              TRANZAS DE CODIGO"
		+ "\n"
		+ "\nBIENVENIDO/A"
		+ "\n"
		+ "\nIntroduzca su numero de Tarjeta: "
		+"\n"
		+ "*************************************************************");
	}
	
	public void pinTarjeta() {
		System.out.println(
				"*************************************************************"
				+ "\nINTRODUZCA SU PIN"
				+ "\n*************************************************************");
	}
	
	public void menuPrincipal() {
		 
		System.out.println(
				 "*************************************************************"
				+"\nQUE DESEA HACER?"
				+"\n"
				+"\n	1. Consultar"
				+"\n	2. Extraccion"
				+"\n	3. Comprar Dolares"
				+"\n	4. Depositar"
				+"\n	5. Transferencia"
				+"\n"
				+"\n*************************************************************");
		
		
	}
	
	public void consultas() {
		System.out.println(
				  "*************************************************************"
				+"\n"
				+"\n	1. Consultar Saldo"
				+"\n	2. Consultar Alias"
				+"\n	3. Consultar ultimos movimientos."
				+ "\n"		
				+"\n"
				+"\n*************************************************************");
	}
	
	public void cantidadDeMovimientos() {
		System.out.println(
				  "*************************************************************"
				+"\n"
				+"\n	Introduzca la cantidad de ultimos movimientos que "
				+ "\n	desea consultar."
				+ "\n"
				+ "\n	Si el numero es mayor a los movimientos hechos se"
				+ "\n	mostraran todos los movimientos de la cuenta."
				+"\n"
				+"\n*************************************************************");
	}
	
	public void tipoDeCuenta() {
		System.out.println(
				  "*************************************************************"
				+"\n"
				+"\n	1. Caja de ahorro ARS"
				+"\n	2. Caja de ahorro USD"
				+"\n	3. Cuenta corriente"
				+"\n"
				+"\n*************************************************************");
	}
	
	public void tipoDeCuentasEnARS() {
		System.out.println(
				   "*************************************************************"
				+"\n"
				+"\n	1. Caja de ahorro ARS"
				+"\n	2. Cuenta corriente"
				+"\n"
				+"\n*************************************************************");
	}
	
	public void transferenciaAlias() {
		System.out.println(
				   "*************************************************************"
				+"\n"
				+"\n	Introduzca el Alias de la cuenta del destinatario"
				+"\n"
				+"\n*************************************************************");
	}
	
	public void monto() {
		System.out.println(
				   "*************************************************************"
				+"\n"
				+"\n	Introduzca el monto"
				+"\n"
				+"\n*************************************************************");
	}
	
									//CONSULTAS//
	public void saldo(double saldo) {
		System.out.println(
				   "*************************************************************"
				+"\n"
				+"\n	Su saldo es: "+saldo
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************");
	}
	
	public void alias(Cuenta cuenta) {
		System.out.println(
				    "*************************************************************"
				+"\n"
				+"\n	Su alias es: "+cuenta.getAlias()
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************");
	}
	
							//OPERACIONES EXITOSAS//
	public void comprarDolaresExitoso(int valor, double valorFinal) {
		System.out.println(
				    "*************************************************************"
				+"\n"
				+"\n	Operacion exitosa"
				+"\n"
				+"\n	Con "+valor+"ARS compraste "+valorFinal+" USD"
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************");
	}
	public void extraerExitoso(int valor) {
		System.out.println(
				    "*************************************************************"
				+"\n"
				+"\n	Se extrajo correctamente "+valor+"ARS"
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************");
	}
	public void transferenciaExitosa(int valor) {
		System.out.println(
				   "*************************************************************"
					+"\n"
					+"\n	Transferiste correctamente: "+valor+ " ARS"
					+"\n"
					+"\n	QUE DESEA HACER?"
					+"\n"
					+"\n	1.Revertir transferencia."
					+"\n	2.Imprimir ticket."
					+"\n	3.Salir."
					+"\n*************************************************************");
	}
	public void depositoExitoso() {
		System.out.println(
				   "*************************************************************"
					+"\n"
					+"\n	Depositaste correctamente."
					+"\n"
					+"\n"
					+"\n	Desea imprimir ticket?"
					+"\n	1. Si"
					+"\n	2. No"
					+"\n*************************************************************");
	}

}