package Cajero;

public class CajaAhorroUSD extends Cuenta implements Operacion{

	public CajaAhorroUSD(double saldo, String alias) throws Exception {
		super(saldo, alias);

	}
	public void retirarEfectivo(int valor) {
		if(valor>this.saldo) {
			this.saldo=-valor;
			System.out.println("Se extrajo correctamente $"+valor);
		}
		else {
			System.out.println("No se pudo extraer. Valor invalido");

		}
	}
	public void comprarDolares(int valor) {}
	public void transferir(String alias, int valor) {}


}
