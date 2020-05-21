package Cajero;
public class Tarjeta {

        // Atributos de la tarjeta
        private int numeroDeTarjeta;
        private int pin;

        /**
         * Constructor para clientes ya registrados.
         *
         * @param numeroDeTarjeta: Numero de la tarjeta del cliente.
         * @param cuit: Numero de CUIT del cliente, asociado a la tarjeta.
         * @param pin: Numero de ping elegido por el cliente para la tarjeta.
         */
	public Tarjeta (int numeroDeTarjeta, int pin) {
            if ( ( contadorDeCaracteres(numeroDeTarjeta) == 9 )  && ( contadorDeCaracteres(pin) == 4 ) ) {
                this.numeroDeTarjeta = numeroDeTarjeta;

                this.pin = pin;
            } else {
                System.out.println("Ocurrio un error, por favor recordar: "
                        + "/n- El numero de la tarjeta debe ser numerico y tener una longitud de 9 digitos"
                        + "/n- El CUIT debe ser numerico y tener una longitud de 9 digitos"
                        + "/n- El PIN debe ser numerico y tener una longitud de 4 digitos");
            }
        }

        /**
         * Metodo para saber el numero de tarjeta.
         *
         * @return: Numero de la tarjeta.
         */
        public int getNumeroDeTarjeta(){
            return numeroDeTarjeta;
        }


        /**
         * Metodo para saber el pin de la tarjeta.
         *
         * @return: Numero de pin.
         */
        private int getPin() {
            return pin;
        }

        /**
         * Metodo de la Interfaz ContadorDeCaracteres, el cual devuelve la cantidad exacta de digitos de un numero.
         */
        public int contadorDeCaracteres(int numero) {
            int cifras = 0;
            while (numero!=0) {
                numero/=10;
                cifras++;
            }
            return cifras;
        }
}
