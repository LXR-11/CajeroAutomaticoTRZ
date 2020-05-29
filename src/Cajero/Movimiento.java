package Cajero;
//
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimiento {

	protected TipoDeMovimiento movimiento;
	protected double saldoInvolucrado;
	protected String miAlias;
	protected String fecha;
	
	//para crear nuevo movimiento
	Movimiento(TipoDeMovimiento movimiento, double saldoInvolucrado, String alias){
		this.movimiento = movimiento;
		this.saldoInvolucrado = saldoInvolucrado;
		this.miAlias = alias;
		Date objDate = new Date();
        SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha = objSDF.format(objDate); 
	}
	
	//para crear movimientos leidos del txt ya existentes
	Movimiento(String fecha, TipoDeMovimiento movimiento, double saldoInvolucrado, String alias){
		this.movimiento = movimiento;
		this.saldoInvolucrado = saldoInvolucrado;
		this.miAlias = alias;
		this.fecha = fecha;
	}
	public String imprimirMovimiento() {
		return fecha + "," + movimiento.toString() + "," + miAlias + "," + saldoInvolucrado;
	}
	
	public TipoDeMovimiento getTipo() {
		return movimiento;
	}

}
