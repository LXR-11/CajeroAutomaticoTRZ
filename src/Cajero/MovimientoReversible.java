package Cajero;
//
public class MovimientoReversible extends Movimiento {

	protected String aliasDelDestinatario;
	
	MovimientoReversible(TipoDeMovimiento movimiento, double saldoInvolucrado, String miAlias, String aliasDestinatario){
		super(movimiento,saldoInvolucrado,miAlias);
		this.aliasDelDestinatario = aliasDestinatario;
	}

//	@Override
//	 public String imprimirMovimiento() {
//		return super.imprimirMovimiento() + " depositados a " + aliasDelDestinatario;
//  }
}
