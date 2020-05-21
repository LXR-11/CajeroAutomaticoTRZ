package Cajero;

public class CajaAhorroUSD extends Cuenta implements Operacion{
	
	public CajaAhorroUSD(double saldo, String alias) throws Exception {
		super(saldo, alias);
		
	}
	public void retirarEfectivo(int valor) {}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}
	
	
}
