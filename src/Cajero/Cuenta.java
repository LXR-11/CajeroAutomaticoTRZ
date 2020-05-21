package Cajero;


public abstract class Cuenta {
	protected String alias;
	   protected double saldo,valorDelDolar;


	    public Cuenta(double saldo, String alias) throws Exception{
	    	if(saldoPositivo(saldo)) {
	    		this.saldo=saldo;
	    		this.alias=alias;
	 	       
	    	}
	    	else {
	    		throw new Exception("No se puede crear una cuenta con saldo negativo");
	    	}
	    	
	       
	        
	    }
	    




	    public double consultarSaldo(){
	        return this.saldo;
	    }

	    public String consultarAlias(){
	        return this.alias;
	    }

	    private  boolean saldoPositivo(double numero){
	        if(numero<0){
	            return false;
	        }
	        return true;

	    }
	    
	    
	    public void depositar(int monto) {
			if(monto>0) {
				this.saldo=+monto;
			}
			
			else {
				System.out.println("Monto invalido");
			}
		}
	    
}
