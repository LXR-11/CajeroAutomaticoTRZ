package Cajero;

public class Movimiento {

	TipoDeMovimiento movimiento;
	double saldoInvolucrado;
	String aliasDelDestinatario;
	
	Movimiento(TipoDeMovimiento movimiento, double saldoInvolucrado){
		this.movimiento = movimiento;
		this.saldoInvolucrado = saldoInvolucrado;
		this.aliasDelDestinatario = null;
	}
	
	//Para la transferencia necesita guardar el alias tambien
	Movimiento(TipoDeMovimiento movimiento, double saldoInvolucrado, String alias){
		this.movimiento = movimiento;
		this.saldoInvolucrado = saldoInvolucrado;
		this.aliasDelDestinatario = alias;
		
	}

	public TipoDeMovimiento getTipo() {
		return movimiento;
	}

	public double getSaldoInvolucrado() {
		return saldoInvolucrado;
	}
	
	public String getAliasDelDestinatario() {
		return this.aliasDelDestinatario;
	}
	
	public String imprimirMovimientoMenosTransferencia() {
		return movimiento.toString() + ", " + saldoInvolucrado;
	}
    public String imprimirTransferencia() {
			 return toString() + " depositados a " + aliasDelDestinatario;
    }
}
