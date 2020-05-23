package Cajero;

public class CajaDeAhorroARS extends Cuenta implements Operacion {
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo, alias);
	}

	public void retirarEfectivo(int valor) {
		if (saldoSuficiente(valor)) {
			this.saldo = -valor;
		} else {
			System.out.println("No se pudo extraer. No posee suficiente saldo en su cuenta");
		}
	}

	public boolean saldoSuficiente(int saldoAretirar) {
		return saldoAretirar >= this.saldo;
	}
	
	public void comprarDolares(int valor, Cliente cliente) {

		if (cliente.cajaDelClienteUSD != null) {
			if (valor >= this.saldo / this.valorDelDolar * valor) {
				cliente.cajaDelClienteUSD.depositar(valor);
			}
			else {System.out.println("Saldo insuficiente para realizar esta operacion");}
			
			
		} else {
			System.out.println("Usted no posee una Caja de Ahorro en USD");
		}

	}

	public void transferir(Cuenta cuentaAtransferir, int valor) {
		if(saldoSuficiente(valor)) {
			cuentaAtransferir.depositar(valor);
		}
	}

}
