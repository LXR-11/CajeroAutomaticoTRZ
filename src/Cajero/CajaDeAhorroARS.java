package Cajero;

public class CajaDeAhorroARS extends Cuenta implements Operacion {
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

	public void retirarEfectivo(int valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
			if (valor % 100 == 0) {
				this.saldo = -valor;
				System.out.println(Mensajes.extraerExitoso(valor));
				Movimiento mov = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO,valor);
				agregarMovimiento(mov);
			} else {
				throw new ErroresDeCuenta("Solo se puede retirar divisores de 100");
			}
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}

	public boolean saldoSuficiente(int saldoAretirar) {

		return (saldoAretirar <= this.saldo && (saldoAretirar > 0));
	}

	public void comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta {

		if (cliente.verificarCuentaEnCliente(2)) {
			if (valor >= this.saldo / this.valorDelDolar * valor) {
				try {
					double impuestoPais = ((30 * valor) / 100); // 30% Del valor
					cliente.cajaDelClienteUSD.depositar(valor/super.valorDelDolar - impuestoPais);
					this.saldo-=valor;
					System.out.println(Mensajes.comprarDolaresExitoso(valor, impuestoPais, valor/super.valorDelDolar-impuestoPais));
					Movimiento mov = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES,valor);
					agregarMovimiento(mov);
				} catch (ErroresDeCuenta e) {

					e.printStackTrace();
				}
			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}

		} else {
			throw new ErroresDeCuenta("Usted no posee una Caja de Ahorro en USD");
		}

	}

	public void transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if (clienteAtransferir.verificarCuentaEnCliente(1)) {
				if (saldoSuficiente(valor)) {
					clienteAtransferir.cajaDelClienteARS.depositar(valor);
					this.saldo-=valor;
					System.out.println(Mensajes.transferenciaExitosa(valor));
					
					String destinatarioAlias = clienteAtransferir.cajaDelClienteARS.alias;
					MovimientoReversible mov = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS,valor,destinatarioAlias);
					agregarMovimiento(mov);
				} else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			} else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta en ARS");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
	}


}