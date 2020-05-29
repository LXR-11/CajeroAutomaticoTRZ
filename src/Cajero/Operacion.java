package Cajero;
//
public interface Operacion {
	boolean retirarEfectivo(double valor) throws ErroresDeCuenta;
	double comprarDolares(double valor, Cliente cliente) throws ErroresDeCuenta;
	boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta;
	boolean saldoSuficiente(double saldoAretirar);
}
