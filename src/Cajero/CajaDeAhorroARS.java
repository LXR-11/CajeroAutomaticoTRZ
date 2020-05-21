package Cajero;

public class CajaDeAhorroARS extends Cuenta implements Operacion{
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo,alias);
	}

	
	public void retirarEfectivo(int valor) {}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}

}

