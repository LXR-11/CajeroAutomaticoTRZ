package Cajero;
//
public class CuentaCorriente extends CajaARS{

	private double descubierto;
	
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo, alias, descubierto);
		this.descubierto=descubierto;
	}
	
	//Se reescribe ya que el parametro de verificacion es distinto en las cuentas
	@Override
	public boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
			if(clienteAtransferir.verificarCuentaEnCliente(3)) {
				if(saldoSuficiente(valor)) {
					clienteAtransferir.cuentaCorrienteDelCliente.depositar(valor);
					this.saldo-=valor;
					
					String destinatarioAlias = clienteAtransferir.cuentaCorrienteDelCliente.alias;
					MovimientoReversible mov = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS,valor,this.alias,destinatarioAlias);
					agregarMovimiento(mov);
					return true;
				}
				else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			}
			else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta corriente");
			}
	}
	
	//Se reescribe ya que debe tener en cuenta el descubierto
	@Override
	public boolean saldoSuficiente(double saldoAretirar) {
		if((this.saldo>=0-this.descubierto) && (saldoAretirar>0) ){
			return true;
		} else {
			return false;
		}

	}
	
	public double getDescubierto() {
		return this.descubierto;
	}
	
	
}
