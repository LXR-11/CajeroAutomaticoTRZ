package Cajero;
///
public class CuentaCorriente extends CajaARS{

	private double descubierto;

	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo, alias, descubierto);
		this.descubierto=descubierto;
	}

	//Se reescribe ya que el parametro de verificacion es distinto en las cuentas
	@Override
	public boolean transferir(Cliente clienteAtransferir, String alias, int valor) throws ErroresDeCuenta {
		if ( clienteAtransferir.cajaDelClienteARS.getAlias().equals(alias) ) {
			if (saldoSuficiente(valor)) {
				clienteAtransferir.cajaDelClienteARS.depositar(valor);
				this.saldo -= valor;
				Movimiento mov = new Movimiento(TipoDeMovimiento.TRANSFERENCIAENPESOS, valor,
						this.alias);
				agregarMovimiento(mov);
				return true;

			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}

		} else if ( clienteAtransferir.cuentaCorrienteDelCliente.getAlias().equals(alias) ){
			if (saldoSuficiente(valor)) {
				clienteAtransferir.cuentaCorrienteDelCliente.depositar(valor);
				this.saldo -= valor;
				Movimiento mov = new Movimiento(TipoDeMovimiento.TRANSFERENCIAENPESOS, valor,
						this.alias);
				agregarMovimiento(mov);
				return true;
			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}
		} else {
			throw new ErroresDeCuenta("No se pueden realizar transferencias a ese alias");
		}
	}
	//Se reescribe ya que debe tener en cuenta el descubierto
	@Override
	public boolean saldoSuficiente(double saldoAretirar) {
		if ( ( ( this.saldo - saldoAretirar ) >= (-this.descubierto) )  && ( saldoAretirar > 0) ){
			return true;
		} else {
			return false;
		}
	}

	public double getDescubierto() {
		return this.descubierto;
	}


}
