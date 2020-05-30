package Cajero;

public abstract class CajaARS extends Cuenta implements Operacion  {

	//Caja de ahorro en ARS
	public CajaARS (double saldo, String alias) throws ErroresDeCuenta {
		super(saldo,alias);
	}
	//Cuenta Corriente
	public CajaARS(double saldo, String alias, double descubierto) throws ErroresDeCuenta {
		super(saldo,alias,descubierto);
	}
	
	//Las hijas deben reescribir estos metodos
	public abstract boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta;
	public abstract boolean saldoSuficiente(double saldoAretirar);
	
	
	public boolean retirarEfectivo(double valor) throws ErroresDeCuenta {
		if (saldoSuficiente(this.saldo)) {
			if(valor%100==0) {
			this.saldo = saldo - valor;
			Movimiento mov = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, valor,this.alias);
			agregarMovimiento(mov);
			return true;
			}
			else {
				throw new ErroresDeCuenta("Solo se puede retirar divisores de 100");

			}
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}
	


	public double comprarDolares(double valorARS, Cliente cliente) throws ErroresDeCuenta {
		if (cliente.verificarCuentaEnCliente(2)) {
			if (saldoSuficiente(this.saldo/this.valorDelDolar)) {
				try {
					double impuestoPais = (((30 * valorARS) / 100)/super.valorDelDolar); // 30% Del valor
					double USDcomprados = (valorARS / super.valorDelDolar) - impuestoPais;
				
					cliente.cajaDelClienteUSD.depositar(USDcomprados);
					this.saldo -= valorARS;
					Movimiento mov = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES, valorARS, this.alias);
					agregarMovimiento(mov);

					return USDcomprados;
					
				} catch (ErroresDeCuenta e) {
					e.printStackTrace();
				}
			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}

		} else {
			throw new ErroresDeCuenta("Usted no posee una Caja de Ahorro en USD");
		} 
		return -1;
	}
}