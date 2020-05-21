package Cajero;



public class Cliente {

	Tarjeta tarjeta;
	private int cuit;
	CajaAhorroUSD cajaDelClienteUSD;
	CajaDeAhorroARS cajaDelClienteARS;
	CuentaCorriente cuentaCorrienteDelCliente;


	/**
	 * Se registra un nuevo cliente en el banco con un alias, un pin, y una tarjeta asociada con un cuit.
	 * Todos los datos ya estan verificados.
	 */
	public Cliente (Tarjeta tarjeta, int cuit) {
		this.tarjeta = tarjeta;
		this.cuit = cuit;

	}


	public int getCuit () {
		return cuit;
	}



	public void asociarAhorroARS(double saldo,String alias) throws Exception {
		this.cajaDelClienteARS = new CajaDeAhorroARS(saldo, alias);
	}
	public void asociarAhorroUSD(double saldo, String alias) throws Exception {
		this.cajaDelClienteUSD = new CajaAhorroUSD(saldo,alias);
	}
	public void asociarCorriente(double saldo, String alias, double descubierto) throws Exception {
		this.cuentaCorrienteDelCliente=new CuentaCorriente(saldo, alias, descubierto);
	}


	public int compareTo(Cliente clienteAcomparar) {
		if(clienteAcomparar.getCuit()==this.cuit) {
			return 0;
		}
		else if(this.cuit<clienteAcomparar.getCuit()) {
			return -1;
		}
		else {
			return 1;

		}
	}
}
