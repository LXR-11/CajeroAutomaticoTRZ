package Cajero;
//
public class CajaDeAhorroARS extends Cuenta implements Operacion {
	
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

	public boolean retirarEfectivo(double valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
			if (valor % 100 == 0) {
				this.saldo = saldo - valor;
				Movimiento mov = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, valor, this.alias);
				agregarMovimiento(mov);
				return true;
			} else {
				throw new ErroresDeCuenta("Solo se puede retirar divisores de 100");
			}
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}

	public boolean saldoSuficiente(double saldoAretirar) {

		return ( ( saldoAretirar <= this.saldo ) && ( saldoAretirar > 0 ) );
	}

	public double comprarDolares(double valorARS, Cliente cliente) throws ErroresDeCuenta {
		if (cliente.verificarCuentaEnCliente(2)) {
			if (saldoSuficiente(valorARS/this.valorDelDolar)) {
				try {
					double impuestoPais = (((30 * valorARS) / 100)/super.valorDelDolar); // 30% Del valor
					double USDcomprados = (valorARS / super.valorDelDolar) - impuestoPais;

					cliente.cajaDelClienteUSD.depositar(USDcomprados);
					this.saldo -= valorARS;
					Movimiento mov = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, valorARS, this.alias);
					agregarMovimiento(mov);

					return USDcomprados;

				} catch (ErroresDeCuenta e) {
					e.printStackTrace();
				}
			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}

		} else {
			throw new ErroresDeCuenta("Usted no posee una Caja de Ahorro en USD");
		}
		return -1;
	}

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



}