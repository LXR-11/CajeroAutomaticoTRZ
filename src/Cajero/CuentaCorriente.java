package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{
	private double descubierto;
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo+descubierto, alias);
		

	}
	
	public void retirarEfectivo(int valor) {}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}
}
