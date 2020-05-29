package Cajero;
//
import java.util.Objects;

public class Tarjeta {


	
	
	

	private int numeroDeTarjeta;
	
	private int pin;

	public Tarjeta (int numeroDeTarjeta, int pin) {

		if ( ( contadorDeCaracteres(numeroDeTarjeta) == 8 )  && ( contadorDeCaracteres(pin) == 4 ) ) {

			this.numeroDeTarjeta = numeroDeTarjeta;

			this.pin = pin;

		} else {

			System.out.println("Ocurrio un error, por favor recordar: "
					+ "/n- El numero de la tarjeta debe ser numerico y tener una longitud de 9 digitos"
					+ "/n- El PIN debe ser numerico y tener una longitud de 4 digitos");

		}
	}

	public int getNumeroDeTarjeta(){
		
		return this.numeroDeTarjeta;
		
	}

	private int getPin() {
		
		return this.pin;
		
	}

	private int contadorDeCaracteres(int numero) {
		
		int cifras = 0;
		
		while (numero!=0) {
			
			numero/=10;
			
			cifras++;
			
		}
		
		return cifras;
		
	}
	
	
	@Override
	public boolean equals(Object obj) {
		boolean iguales=this==obj;
		if(! iguales && obj instanceof Tarjeta) {
			Tarjeta objeto = (Tarjeta)obj;
			iguales=(this.numeroDeTarjeta == objeto.numeroDeTarjeta && this.pin == objeto.pin);
		}
		return iguales;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.numeroDeTarjeta);
	}
	
}
