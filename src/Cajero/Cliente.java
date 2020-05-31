package Cajero;


///
public class Cliente {

	Tarjeta tarjeta;
	private long cuit;
	CajaAhorroUSD cajaDelClienteUSD;
	CajaDeAhorroARS cajaDelClienteARS;
	CuentaCorriente cuentaCorrienteDelCliente;

	/**
	 * Se registra un nuevo cliente en el banco con un alias, un pin, y una tarjeta asociada con un cuit.
	 * Todos los datos ya estan verificados.
	 */
	public Cliente (Tarjeta tarjeta, long cuit) {
		this.tarjeta = tarjeta;
		this.cuit = cuit;

	}


	public long getCuit () {
		return cuit;
	}


	public void consultarAliasDeCuentaX(int cuentaAlias) throws ErroresDeCuenta  {
		
		Mensajes mensajes = new Mensajes();
		try{
			switch(cuentaAlias) {
			case 1:	//ARS

				if(verificarCuentaEnCliente(1)) {
					mensajes.alias(this.cajaDelClienteARS);
				}
				else {
					throw new ErroresDeCuenta("Usted no posee esta cuenta");
				}
				break;

			case 2:	//USD
				if(verificarCuentaEnCliente(2)){
					mensajes.alias(this.cajaDelClienteUSD);
				}
				else {
					throw new ErroresDeCuenta("Usted no posee esta cuenta");
				}
				break;
			case 3:	//CC
				if(verificarCuentaEnCliente(3)) {
					mensajes.alias(this.cuentaCorrienteDelCliente);
				}
				else {
					throw new ErroresDeCuenta("Usted no posee esta cuenta");
				}
				break;
			}
		}
		catch(ErroresDeCuenta e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param cuenta: 1ARS 2USD 3CC
	 * @return 
	 */
	public boolean verificarCuentaEnCliente(int cuenta) {
		boolean retorno=false;
		try {
			switch(cuenta) {
			case 1:	//ARS

				if(this.cajaDelClienteARS!=null) {
					retorno = true;
				}
				else {
					throw new ErroresDeCuenta("El cliente no posee esta cuenta");
				}
				break;

			case 2:	//USD
				if(this.cajaDelClienteUSD!=null) {
					retorno = true;
				}
				else {
					throw new ErroresDeCuenta("El cliente no posee esta cuenta");
				}
				break;
			case 3:	//CC
				if(this.cuentaCorrienteDelCliente!=null) {
					retorno = true;
				}
				else {
					throw new ErroresDeCuenta("El cliente no posee esta cuenta");
				}
				break;
			}

		}
		catch(ErroresDeCuenta e) {
			e.printStackTrace();
		}
		return retorno;
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




}