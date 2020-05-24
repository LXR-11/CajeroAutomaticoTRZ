package Cajero;



public class CajaDeAhorroARS extends Cuenta implements Operacion {
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

	public void retirarEfectivo(int valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
			this.saldo = -valor;
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}

	public boolean saldoSuficiente(int saldoAretirar) {

		return (saldoAretirar >= this.saldo);
	}

	public void comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta {

		if (cliente.cajaDelClienteUSD != null) {
			if (valor >= this.saldo / this.valorDelDolar * valor) {
				try {
					cliente.cajaDelClienteUSD.depositar(valor);
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

	public void transferir(Cuenta cuentaAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if(saldoSuficiente(valor)) {
				cuentaAtransferir.depositar(valor);
			}
			else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
	}

}