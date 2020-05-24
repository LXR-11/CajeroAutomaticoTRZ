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
}

