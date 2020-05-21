package Cajero;


public abstract class Cuenta {
	protected String alias;
	   protected double saldo,valorDelDolar;


	    public Cuenta(double saldo, String alias){
	        this.saldo=saldo;
	        this.alias=alias;
	    }
	    
	    public Cuenta(double saldo, String alias, double descubierto) {
	    	
	    }


	    public abstract void depositar(int monto);

	    public double consultarSaldo(){
	        return this.saldo;
	    }

	    public String consultarAlias(){
	        return this.alias;
	    }

	    public  double validarSaldo(double numero){
	        double retorno=numero;
	        if(numero<0){
	            retorno=-1;
	        }
	        return retorno;

	    }
}
