package Cajero;

public class CajaDeAhorroARS extends Cuenta implements Operacion{
	public CajaDeAhorroARS(double saldo, String alias) throws Exception {
		super(saldo,alias);
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
	
	public void comprarDolares(int valor) {
		if(valor>=this.saldo/this.valorDelDolar*valor) {
			//HAY QUE USAR DOS SALDOS? UNO EN PESOS Y OTRO EN ARS
			//O HACER UNA TRANSFERENCIA A LA OTRA CUENTA SI POSEE?
		}
	}
	public void transferir(String alias, int valor) {
		
	}

}

