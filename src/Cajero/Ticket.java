package Cajero;
//
import java.io.*;


public class Ticket {
	BufferedWriter escritor;
	private MensajesTicket escrituraDelTicket;

	public Ticket() {
		this.escrituraDelTicket = new MensajesTicket();
	}

	public void escribirTransferencia(String alias, double monto) {
		try {
			 escritor = new BufferedWriter(new FileWriter("TRANSFERENCIA-"+alias + ".txt"));
			escritor.write(this.escrituraDelTicket.transferencia(monto));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirConsulta(int numeroTarjeta, String alias) {
		try {
			 escritor = new BufferedWriter(new FileWriter("CONSULTA-"+numeroTarjeta+ "ALIAS.txt"));
			escritor.write(this.escrituraDelTicket.alias(alias));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirExtraccion(Cuenta cuenta, double valor) {
		try {
			 escritor = new BufferedWriter(new FileWriter("EXTRACCION-"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.extraer(cuenta, valor));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirCompraUSD(Cuenta cuenta, double valor) {
		try {
			 escritor = new BufferedWriter(new FileWriter("COMPRA-"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.comprarUSD(cuenta, valor));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirDeposito(Cuenta cuenta, double monto) {
		try {
			 escritor = new BufferedWriter(new FileWriter("COMPRA"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.depositar(cuenta, monto));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void escribirSaldo(Cuenta cuenta) {
		try {

			escritor = new BufferedWriter(new FileWriter("CONSULTA-"+cuenta.getAlias()+ ".txt"));
			escritor.write(this.escrituraDelTicket.saldo(cuenta));
			escritor.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void escribirSegunMovimientosNoReversibles(Movimiento mov, Cuenta cuenta) {
		
		switch(mov.movimiento) {
		case DEPOSITO:
			escribirDeposito(cuenta,mov.saldoInvolucrado);
			break;
		case COMPRADEDOLARES:
			escribirCompraUSD(cuenta,mov.saldoInvolucrado);
		case RETIRAREFECTIVO:
			escribirExtraccion(cuenta,mov.saldoInvolucrado);
			
		default:
			break;
		}
	}
	
}
