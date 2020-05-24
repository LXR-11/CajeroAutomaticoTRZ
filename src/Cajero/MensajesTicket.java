package Cajero;

import java.util.Date;

public class MensajesTicket {
	public MensajesTicket() {}

	public String transferencia(double monto, double saldo) {
		Date objDate = new Date();
		return  "*************************************************************"
		+"\n	Tipo: TRANSFERENCIA"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Importe involcrado: "+monto
		+"\n	Nuevo saldo: "+saldo
		+"\n*************************************************************";
	}
	public String alias(String alias) {
		Date objDate = new Date();
		
		return  "*************************************************************"
		+"\n	Tipo: CONSULTA"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Su alias es: "+alias
		+"\n*************************************************************";
	}
	public String extraer(Cuenta cuenta, double valor) {
		Date objDate = new Date();
		return  "*************************************************************"
		+"\n	Tipo: EXTRACCION"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Retiraste: "+valor+"ARS"
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String comprarUSD(Cuenta cuenta, double valor) {
		Date objDate = new Date();
		double impuestoPais = ((30 * valor) / 100);
		
		return  "*************************************************************"
		+"\n	Tipo: COMPRA USD"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Compraste: "+((valor/cuenta.valorDelDolar) - impuestoPais)+"USD"
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String depositar(Cuenta cuenta, double monto) {
		Date objDate = new Date();
		
		return  "*************************************************************"
		+"\n	Tipo: DEPOSITO"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Depositaste: "+monto+"."
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String saldo(Cuenta cuenta) {
		Date objDate = new Date();
		return  "*************************************************************"
		+"\n	Tipo: CONSULTA"
		+"\n	Fecha: "+objDate.toString()+"." 
		+"\n	Su saldo es: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
}

