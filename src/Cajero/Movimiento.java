package Cajero;

public class Movimiento {

	protected TipoDeMovimiento movimiento;
	protected double saldoInvolucrado;
	
	Movimiento(TipoDeMovimiento movimiento, double saldoInvolucrado){
		this.movimiento = movimiento;
		this.saldoInvolucrado = saldoInvolucrado;
	}
	
	public String imprimirMovimiento() {
		return movimiento.toString() + ", " + saldoInvolucrado;
	}
	
	public TipoDeMovimiento getTipo() {
		return movimiento;
	}

}
