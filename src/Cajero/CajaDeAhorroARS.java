package Cajero;
///
public class CajaDeAhorroARS extends CajaARS{

	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

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

	@Override
	public boolean saldoSuficiente(double saldoAretirar) {
		return ( ( saldoAretirar <= this.saldo ) && ( saldoAretirar > 0 ) );
	}

}