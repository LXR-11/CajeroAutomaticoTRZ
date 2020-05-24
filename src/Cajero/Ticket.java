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
}
