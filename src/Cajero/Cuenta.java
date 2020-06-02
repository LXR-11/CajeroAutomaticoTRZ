package Cajero;
///
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public abstract class Cuenta{
	
      protected String alias;
      protected double saldo;
      protected double valorDelDolar;
      protected Stack<Movimiento> movimientosDeCuenta= new Stack<Movimiento>();

      
        public Cuenta(double saldo, String alias)throws ErroresDeCuenta{
            if(saldoPositivo(saldo)) {
                this.saldo=saldo;
                this.alias=alias;
                this.valorDelDolar=100;

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
        
        public List<Movimiento> mostrarHastaXUltimosMovimientos(int ultimosMov){
        	
        	
        	if(movimientosDeCuenta.size() < ultimosMov) {
        		//si me pasan un numero mas grande lo convierto en el tamaño de la pila
        		ultimosMov = movimientosDeCuenta.size();
        	}     	
        	//crea una copia de los movimientos, los agrega a una lista y los devuelve
	        	Stack<Movimiento> auxiliar = new Stack<Movimiento>();
	        	Iterator<Movimiento> miIterador = auxiliar.iterator();
	        	List<Movimiento> resultado = new LinkedList<Movimiento>();
	        	Iterator<Movimiento> iteradorDeCuenta = movimientosDeCuenta.iterator();
	        	while (iteradorDeCuenta.hasNext()) {
	        		auxiliar.push(movimientosDeCuenta.pop());
	        	}
	        	int contador=0;
	        	while(miIterador.hasNext() && contador < ultimosMov) {
	        		resultado.add(auxiliar.pop());
	        		contador++;
	        	}
	        	
	        	return resultado;
        
        	
        }
        
        public void revertirUltimaTransferencia ( double saldoInvolucrado, Cuenta destinatario ) {
        	this.saldo += saldoInvolucrado;
        	
        	
        	
        	destinatario.saldo -= saldoInvolucrado;
        	//remueve el ultimo movimiento 
        	movimientosDeCuenta.pop();
        	
        }
        
        public boolean depositar (double monto) throws ErroresDeCuenta {
            if(monto>0) {
                this.saldo += monto;
                Movimiento mov = new Movimiento(TipoDeMovimiento.DEPOSITO,monto,this.alias);
                agregarMovimiento(mov);
                return true;
            } else {
            	throw new ErroresDeCuenta("Debe depositar un valor mayor a 0.");
            }
        }
        public String tipoDeCuenta(Cliente cliente) {
    		if ( cliente.verificarCuentaEnCliente(1) ){
    			return "01";
    		} else if ( cliente.verificarCuentaEnCliente(3) ) {
    			return "02";
    		} else {
    			return "03";
    		}
    	}

}
