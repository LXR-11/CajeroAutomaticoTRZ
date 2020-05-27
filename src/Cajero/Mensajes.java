package Cajero;

public class Mensajes {

	public Mensajes() {}



	public String bienvenida() {

		return "*************************************************************"
		+ "\n"
		+ "\n                    BANCO"
		+ "\n              TRANZAS DE CODIGO"
		+ "\n"
		+ "\nBIENVENIDO/A"
		+ "\n"
		+ "\nIntroduzca su numero de Tarjeta: "
		+"\n"
		+ "*************************************************************";
	}
	
	public String pinTarjeta() {
		return "*************************************************************"
				+ "\nINTRODUZCA SU PIN"
				+ "\n*************************************************************";
	}
	
	public String menuPrincipal() {
		 
				return "*************************************************************"
				+"\nQUE DESEA HACER?"
				+"\n"
				+"\n	1. Consultar"
				+"\n	2. Extraccion"
				+"\n	3. Comprar Dolares"
				+"\n	4. Depositar"
				+"\n	5. Transferencia"
				+"\n"
				+"\n*************************************************************";
		
		
	}
	


	
	public String consultas() {
		return  "*************************************************************"
				+"\n"
				+"\n	1. Consultar Saldo"
				+"\n	2. Consultar Alias"
				+"\n	3. Consultar ultimos movimientos."
				+ "\n"		
				+"\n"
				+"\n*************************************************************";
	}
	
	public String cantidadDeMovimientos() {
		return  "*************************************************************"
				+"\n"
				+"\n	Introduzca la cantidad de ultimos movimientos que "
				+ "\n	desea consultar."
				+ "\n"
				+ "\n	Si el numero es mayor a los movimientos hechos se"
				+ "\n	mostraran todos los movimientos de la cuenta."
				+"\n"
				+"\n*************************************************************";
	}
	
	public String tipoDeCuenta() {
		return  "*************************************************************"
				+"\n"
				+"\n	1. Caja de ahorro ARS"
				+"\n	2. Caja de ahorro USD"
				+"\n	3. Cuenta corriente"
				+"\n"
				+"\n*************************************************************";
	}
	
	public String tipoDeCuentaParaTrasferir() {
		return  "*************************************************************"
				+"\n"
				+"\n	1. Caja de ahorro ARS"
				+"\n	2. Cuenta corriente"
				+"\n"
				+"\n	(No se pueden realizar transferencias en USD)"
				+"\n*************************************************************";
	}
	
	public String transferenciaAlias() {
		return  "*************************************************************"
				+"\n"
				+"\n	Introduzca el Alias de la cuenta del destinatario"
				+"\n"
				+"\n*************************************************************";
	}
	
	public String monto() {
		return  "*************************************************************"
				+"\n"
				+"\n	Introduzca el monto"
				+"\n"
				+"\n*************************************************************";
	}
	
									//CONSULTAS//
	public String saldo(double saldo) {
		return  "*************************************************************"
				+"\n"
				+"\n	Su saldo es: "+saldo
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************";
	}
	
	public String alias(Cuenta cuenta) {
		return  "*************************************************************"
				+"\n"
				+"\n	Su alias es: "+cuenta.getAlias()
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************";
	}
	public String ultimoMovimientoNoReversible(String movimiento) {

		return  "*************************************************************"
				+"\n"
				+"\n	Su ultimo movimiento fue: "
				+ "\n"+movimiento
				+"\n 	Esta operacion no es reversible."
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************";
	}
	
	public String ultimoMovimientoReversible(String movimiento) {

		return  "*************************************************************"
				+"\n"
				+"\n	Su ultimo movimiento fue: "
				+ "\n"+movimiento
				+"\n"
				+"\n	QUE DESEA HACER?"
				+"\n"
				+"\n	1. Imprimir ticket."
				+"\n	2. Revertir Transaccion."
				+"\n"
				+"\n*************************************************************";
	}
	
							//OPERACIONES EXITOSAS//
	public String comprarDolaresExitoso(int valor, double valorFinal) {
		double impuestoPais = ((30 * valor) / 100);
		return  "*************************************************************"
				+"\n"
				+"\n	Operacion exitosa"
				+"\n"
				+"\n	Con "+valor+"ARS compraste "+valorFinal+" USD"
				+"\n 	debido al impuesto pais del 30% de "+impuestoPais+"ARS"
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************";
	}
	public String extraerExitoso(int valor) {
		return  "*************************************************************"
				+"\n"
				+"\n	Se extrajo correctamente"+valor+"ARS"
				+"\n"
				+"\n	Desea imprimir ticket?"
				+"\n"
				+"\n	1. Si"
				+"\n	2. No"
				+"\n*************************************************************";
	}
	public String transferenciaExitosa(int valor) {
		 return "*************************************************************"
					+"\n"
					+"\n	Transferiste correctamente: "+valor+ " ARS"
					+"\n"
					+"\n	QUE DESEA HACER?"
					+"\n"
					+"\n	1.Revertir transferencia."
					+"\n	2.Imprimir ticket."
					+"\n*************************************************************";
	}
	public String depositoExitoso() {
		 return "*************************************************************"
					+"\n"
					+"\n	Depositaste correctamente."
					+"\n"
					+"\n"
					+"\n	Desea imprimir ticket?"
					+"\n	1. Si"
					+"\n	2. No"
					+"\n*************************************************************";
	}

}