package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{

	private double descubierto;
	
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo, alias, descubierto);
		this.descubierto=descubierto;
	}
	
	public void retirarEfectivo(int valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
			this.saldo = -valor;
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}
	
	public void comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta {

		if (cliente.verificarCuentaEnCliente(2)) {
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
			if(clienteAtransferir.verificarCuentaEnCliente(3)) {
				if(saldoSuficiente(valor)) {
					clienteAtransferir.cuentaCorrienteDelCliente.depositar(valor);
					System.out.println(Mensajes.transferenciaExitosa(valor));
				}
				else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			}
			else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta corriente");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
	}


	public boolean saldoSuficiente(int saldoAretirar) {
		boolean retorno=false;;
		if((this.saldo>=0-this.descubierto) && (saldoAretirar>0) ){
			retorno=true;
		}
		return retorno;
	}
	
}
