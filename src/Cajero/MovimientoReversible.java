package Cajero;
//
public class MovimientoReversible extends Movimiento {

	protected String aliasDelDestinatario;
	
	//Hereda todos los atributos y guarda el alias de transferencia por si se necesita
	MovimientoReversible(TipoDeMovimiento movimiento, double saldoInvolucrado, String miAlias, String aliasDestinatario){
		super(movimiento,saldoInvolucrado,miAlias);
		this.aliasDelDestinatario = aliasDestinatario;
	}

}