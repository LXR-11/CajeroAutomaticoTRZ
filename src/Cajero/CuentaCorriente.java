package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{

	private double descubierto;
	
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo, alias, descubierto);
		this.descubierto=descubierto;
	}
	
	public boolean retirarEfectivo(double valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
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
			if (saldoSuficiente(valorARS/this.valorDelDolar)) {
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
	
	public boolean transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if(clienteAtransferir.verificarCuentaEnCliente(3)) {
				if(saldoSuficiente(valor)) {
					clienteAtransferir.cuentaCorrienteDelCliente.depositar(valor);
					this.saldo-=valor;
					
					String destinatarioAlias = clienteAtransferir.cuentaCorrienteDelCliente.alias;
					MovimientoReversible mov = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS,valor,this.alias,destinatarioAlias);
					agregarMovimiento(mov);
					return true;
				}
				else {
					throw new ErroresDeCuenta("Saldo insuficiente");
				}
			}
			else {
				throw new ErroresDeCuenta("El destinatario no posee una cuenta corriente");
			}
		} catch (ErroresDeCuenta e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public double getDescubierto() {
		return this.descubierto;
	}
	
	public boolean saldoSuficiente(double saldoAretirar) {
		boolean retorno=false;;
		if((this.saldo>=0-this.descubierto) && (saldoAretirar>0) ){
			retorno=true;
		}
		return retorno;
	}
	
}
