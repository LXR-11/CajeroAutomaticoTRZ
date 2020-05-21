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
		return  "*************************************************************"
				+"\nQUE DESEA HACER?"
				+"\n"
				+"\n	1. Consulta"
				+"\n	2. Extraccion"
				+"\n	3. Comprar Dolares"
				+"\n	4. Depositar"
				+"\n"
				+"\n*************************************************************";
	}
}