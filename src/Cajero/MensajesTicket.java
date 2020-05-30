package Cajero;
import java.text.SimpleDateFormat;
//
import java.util.Date;

public class MensajesTicket {
	public MensajesTicket() {}
	
	private Date objDate;
	private  SimpleDateFormat objSDF;
	private String fecha;
	
	public String transferencia(double monto) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
        
		return  "*************************************************************"
		+"\n	Tipo: TRANSFERENCIA"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Importe involcrado: "+monto
		+"\n*************************************************************";
	}
	public String alias(String alias) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
		
		return  "*************************************************************"
		+"\n	Tipo: CONSULTA"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Su alias es: "+alias
		+"\n*************************************************************";
	}
	public String extraer(Cuenta cuenta, double valor) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
		return  "*************************************************************"
		+"\n	Tipo: EXTRACCION"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Retiraste: "+valor+"ARS"
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String comprarUSD(Cuenta cuenta, double valor) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
		
		return  "*************************************************************"
		+"\n	Tipo: COMPRA USD"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Compraste: "+(( valor / ( cuenta.valorDelDolar + 30) ) )+"USD"
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String depositar(Cuenta cuenta, double monto) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
		
		return  "*************************************************************"
		+"\n	Tipo: DEPOSITO"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Depositaste: "+monto+"."
		+"\n	Nuevo saldo: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
	public String saldo(Cuenta cuenta) {
		objDate = new Date();
        objSDF = new SimpleDateFormat("yyyy-MM-dd");
        fecha = objSDF.format(objDate);
		return  "*************************************************************"
		+"\n	Tipo: CONSULTA"
		+"\n	Fecha: "+fecha+"." 
		+"\n	Su saldo es: "+cuenta.consultarSaldo()
		+"\n*************************************************************";
	}
}

