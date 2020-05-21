package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{
	public CuentaCorriente(double saldo, String alias) throws Exception {
		super(saldo, alias);

	}
	
	public void retirarEfectivo(int valor) {}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}
}
