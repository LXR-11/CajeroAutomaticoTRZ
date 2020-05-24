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


        public void depositar (int monto)throws ErroresDeCuenta {
            if(monto>0) {
                this.saldo=+monto;
                System.out.println("DEPOSITADO CORRECTAMENTE");
            }

            else {
                throw new ErroresDeCuenta("Monto invalido");
            }
        }

}