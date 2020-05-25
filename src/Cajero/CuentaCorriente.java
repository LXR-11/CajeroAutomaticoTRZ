package Cajero;

public class CuentaCorriente extends Cuenta implements Operacion{

	private double descubierto;
	
	public CuentaCorriente(double saldo, String alias, double descubierto) throws Exception {
		super(saldo, alias, descubierto);
		this.descubierto=descubierto;
	}
	
	public void retirarEfectivo(int valor) throws ErroresDeCuenta {
		if (saldoSuficiente(valor)) {
			if(valor%100==0) {
			this.saldo = -valor;
			System.out.println(Mensajes.extraerExitoso(valor));
			
			Movimiento mov = new Movimiento(TipoDeMovimiento.RETIRAREFECTIVO, valor);
			agregarMovimiento(mov);
			
			}
			else {
				throw new ErroresDeCuenta("Solo se puede retirar divisores de 100");

			}
		} else {
			throw new ErroresDeCuenta("Saldo insuficiente");
		}
	}
	
	public void comprarDolares(int valor, Cliente cliente) throws ErroresDeCuenta {

		if (cliente.verificarCuentaEnCliente(2)) {
			if (valor >= this.saldo / this.valorDelDolar * valor) {
				try {
					double impuestoPais= ((30*valor)/100);	//30% Del valor
					cliente.cajaDelClienteUSD.depositar(valor/super.valorDelDolar - impuestoPais);
					this.saldo-=valor;
					System.out.println(Mensajes.comprarDolaresExitoso(valor, impuestoPais, valor/super.valorDelDolar-impuestoPais));
					
					Movimiento mov = new Movimiento(TipoDeMovimiento.COMPRADEDOLARES,valor);
					agregarMovimiento(mov);
					
				} catch (ErroresDeCuenta e) {

					e.printStackTrace();
				}
			} else {
				throw new ErroresDeCuenta("Saldo insuficiente");
			}

		} else {
			throw new ErroresDeCuenta("Usted no posee una Caja de Ahorro en USD");
		}

	}
	
	public void transferir(Cliente clienteAtransferir, int valor) throws ErroresDeCuenta {
		try {
			if(clienteAtransferir.verificarCuentaEnCliente(3)) {
				if(saldoSuficiente(valor)) {
					clienteAtransferir.cuentaCorrienteDelCliente.depositar(valor);
					this.saldo-=valor;
					System.out.println(Mensajes.transferenciaExitosa(valor));
					
					String destinatarioAlias = clienteAtransferir.cuentaCorrienteDelCliente.alias;
					MovimientoReversible mov = new MovimientoReversible(TipoDeMovimiento.TRANSFERENCIAENPESOS,valor,destinatarioAlias);
					agregarMovimiento(mov);
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
	}

	public boolean saldoSuficiente(int saldoAretirar) {
		boolean retorno=false;;
		if((this.saldo>=0-this.descubierto) && (saldoAretirar>0) ){
			retorno=true;
		}
		return retorno;
	}
	
}
