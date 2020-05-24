package Cajero;

public interface Operacion {
	void retirarEfectivo(int valor) throws ErroresDeCuenta;
	void comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta;
	void transferir(Cuenta cuentaAtransferir, int valor) throws ErroresDeCuenta;
	boolean saldoSuficiente(int saldoAretirar);
}
