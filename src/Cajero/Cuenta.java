package Cajero;


public abstract class Cuenta{
    protected String alias;
      protected double saldo,valorDelDolar;


        public Cuenta(double saldo, String alias)throws ErroresDeCuenta{
            if(saldoPositivo(saldo)) {
                this.saldo=saldo;
                this.alias=alias;
                this.valorDelDolar=103;

            }
            else {
                throw new ErroresDeCuenta("No se puede crear una cuenta con saldo negativo");
            }
        }
        
        public Cuenta(double saldo, String alias, double descubierto) throws ErroresDeCuenta{
        	if(saldo+descubierto>=0) {
        		this.saldo=saldo;
                this.alias=alias;
                this.valorDelDolar=103;
        	}
        	else {
        		throw new ErroresDeCuenta("Error al crear cuenta corriente. Saldo insuficiente en base al descubierto");
        	}
        }





        public double consultarSaldo(){
            return this.saldo;
        }

        public String getAlias(){
            return this.alias;
        }

        private  boolean saldoPositivo(double numero){
            if(numero<0){
                return false;
            }
            return true;

        }


        public void depositar (double monto)throws ErroresDeCuenta {
            if(monto>0) {
                this.saldo+=monto;
                System.out.println(Mensajes.deposito());
            }

            else {
                throw new ErroresDeCuenta("Monto invalido");
            }
        }

}