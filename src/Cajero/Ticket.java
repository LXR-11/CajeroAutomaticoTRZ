package Cajero;

import java.io.*;


public class Ticket {
	private MensajesTicket escrituraDelTicket;

	public Ticket() {
		this.escrituraDelTicket = new MensajesTicket();
	}

	public void escribirTicketTransferencia(String alias, double monto, double saldo) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(alias + ".txt"));
			escritor.write(this.escrituraDelTicket.transferencia(monto, saldo));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirTicketAlias(int numeroTarjeta, String alias) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("CONSULTA"+numeroTarjeta+ "ALIAS.txt"));
			escritor.write(this.escrituraDelTicket.alias(alias));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirExtraccion(Cuenta cuenta, double valor) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("EXTRACCION"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.extraer(cuenta, valor));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirCompraUSD(Cuenta cuenta, double valor) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("COMPRA"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.comprarUSD(cuenta, valor));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirDeposito(Cuenta cuenta, double monto) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("COMPRA"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.depositar(cuenta, monto));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirSaldo(Cuenta cuenta) {
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter("CONSULTA"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.saldo(cuenta));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
