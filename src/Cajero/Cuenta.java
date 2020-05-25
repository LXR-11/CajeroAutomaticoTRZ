package Cajero;

import java.util.Stack;

public abstract class Cuenta{
	
      protected String alias;
      protected double saldo;
      protected double valorDelDolar;
      protected Stack<Movimiento> movimientosDeCuenta = new Stack<Movimiento>();

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
        
        
        public void agregarMovimiento(Movimiento mov) {
        	this.movimientosDeCuenta.push(mov);
        }
        
        public void revertirUltimaTransferencia(double saldoInvolucrado, Cuenta destinatario) {
        	this.saldo += saldoInvolucrado;
        	destinatario.saldo -= saldoInvolucrado;
        	//remueve el ultimo movimiento 
        	movimientosDeCuenta.remove(movimientosDeCuenta.size());
        	
        }
        
        public void depositar (double monto)throws ErroresDeCuenta {
            if(monto>0) {
                this.saldo+=monto;
                System.out.println(Mensajes.depositoExitoso());
                Movimiento mov = new Movimiento(TipoDeMovimiento.DEPOSITO,monto);
                agregarMovimiento(mov);
            }

            else {
                throw new ErroresDeCuenta("Monto invalido");
            }
        }

}