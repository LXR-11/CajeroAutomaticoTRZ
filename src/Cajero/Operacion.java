package Cajero;

public interface Operacion {
	boolean retirarEfectivo(int valor) throws ErroresDeCuenta;
	double comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta;
	boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta;
	boolean saldoSuficiente(int saldoAretirar);
}
