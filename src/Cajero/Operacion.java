package Cajero;

public interface Operacion {
	void retirarEfectivo(int valor);
	void comprarDolares(int valor, Cliente cliente);
	void transferir(Cuenta cuentaAtransferir, int valor);
	boolean saldoSuficiente(int saldoAretirar);
}
