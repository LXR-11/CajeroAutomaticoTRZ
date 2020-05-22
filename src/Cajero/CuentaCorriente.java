package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{
	private double descubierto;
	private double saldoInicial;
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo+descubierto, alias);
		this.saldoInicial=saldo;

	}
	
	public void retirarEfectivo(int valor) {}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}

	@Override
	public void comprarDolares(int valor, Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean saldoSuficiente(int saldoAretirar) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
