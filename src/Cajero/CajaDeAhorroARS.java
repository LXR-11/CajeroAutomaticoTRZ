package Cajero;
//
public class CajaDeAhorroARS extends CajaARS{

	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

	//Se reescribe ya que el parametro de verificacion es distinto en las cuentas
	@Override
	public boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if (clienteAtransferir.verificarCuentaEnCliente(1)) {
				if (saldoSuficiente(valor)) {
					clienteAtransferir.cajaDelClienteARS.depositar(valor);
					this.saldo -= valor;
					String destinatarioAlias = clienteAtransferir.cajaDelClienteARS.alias;
					MovimientoReversible mov = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS, valor,
							this.alias, destinatarioAlias);
					agregarMovimiento(mov);
					return true;

				} else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			} else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta en ARS");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Se reescribe ya que esta caja no tiene en cuenta el descubierto
	@Override
	public boolean saldoSuficiente(double saldoAretirar) {

		return ( ( saldoAretirar <= this.saldo ) && ( saldoAretirar > 0 ) );
	}



}