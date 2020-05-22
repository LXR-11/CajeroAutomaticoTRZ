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
				+"\n	1. Consulta"
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
				+"\n	3. Consultar ultimos movimientos"		
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
	
	public String transferenciaAlias() {
		return  "*************************************************************"
				+"\n"
				+"\n	Introduzca el Alias del destinatario"
				+"\n"
				+"\n*************************************************************";
	}
	
	public String monto() {
		return  "*************************************************************"
				+"\n"
				+"\n	Introduzca el Monto"
				+"\n"
				+"\n*************************************************************";
	}
	
	
	public String saldo(double saldo) {
		return  "*************************************************************"
				+"\n"
				+"\n	Su saldo es: "+saldo
				+"\n"
				+"\n*************************************************************";
	}
	
	public String alias(String alias) {
		return  "*************************************************************"
				+"\n"
				+"\n	Su alias es: "+alias
				+"\n"
				+"\n*************************************************************";
	}
}