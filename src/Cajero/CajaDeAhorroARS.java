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

		return (saldoAretirar <= this.saldo);
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

	public void transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if(clienteAtransferir.cajaDelClienteARS!=null) {
				if(saldoSuficiente(valor)) {
					clienteAtransferir.cajaDelClienteARS.depositar(valor);
					System.out.println(Mensajes.transferenciaExitosa(valor));
				}
				else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			}
			else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta en ARS");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
	}

}