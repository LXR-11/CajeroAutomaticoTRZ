package Cajero;



public class Cliente {

	 Tarjeta tarjeta;
     int cuit;


     /**
      * Se registra un nuevo cliente en el banco con un alias, un pin, y una tarjeta asociada con un cuit.
      * Todos los datos ya estan verificados.
      */
     public Cliente (Tarjeta tarjeta, int cuit) {
         this.tarjeta = tarjeta;
         this.cuit = cuit;
     }

     public int getCuit () {
         return cuit;
     }
}
