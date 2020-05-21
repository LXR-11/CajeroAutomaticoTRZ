package Cajero;

public interface Operacion {
	void retirarEfectivo(int valor);
	void comprarDolares(int valor);
	void transferir(String alias, int valor);
}
