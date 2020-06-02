# CajeroAutomaticoTRZ

### Nombres de los integrantes del grupo :
•Aguilera Agustín 

•Ambrosetti Ramiro 

•Soto Luciano 

•Noguera Sol
 _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
 
### Decisiones de diseño tomadas :
Se pueden ver en el UML. La intención del diseño es almacenar Clientes(con sus cuentas y tarjetas) con el fin de buscarlos de forma rapida y eficiente a la hora de solicitar el ingreso de tarjeta, además de conservar la prolijidad en el código. Para esto, se usaron Mapas con sus debidos metodos (Redefiniendo Equals y HashCode en caso de ser necesario).
 _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
### Descripción de cada archivo *.java* comprendido en solución del problema:

  •`ArchivoDeClientes`: Lee el archivo *ArchivoDeClientes.txt*, separa los datos y los almacena en un Mapa Alias|Cuit.
  
  •`ArchivoDeTarjetas`: Lee el archivo *ArchivoDeTarjetas.txt*, separa los datos y los almacena en dos mapas: Cuit|Cliente y Tarjeta|Cuit.
  
  •`ArchivoDeCuentas`: Dentro tiene un ArchivoDeTarjetas y ArchivoDeClientes. Lee el archivo *ArchivoDeCuentas.txt*, separa los datos , asocia a cada Cliente con una Cuenta y los pone en el mapa Cuit|Cliente.
  
  •`ArchivoDeMovimientos`: Encargada de leer y asignar los movimientos a todas las Cuentas
  
  •`CajaARS`: Hereda de Cuenta. Posee las acciones que se ejecutaran en sus clases hijas (CajaDeAhorroARS y CuentaCorriente)
  
  
  •`Cliente`: Cada cliente posee una Tarjeta, un cuit, y una cuenta(opcional). Se crea con una tarjeta y un cuit. Tiene métodos para asociar un cliente con una cuenta.
  
  •`Tarjeta`: Se crea una tarjeta con numero y pin.
  
  •`Cuenta`: CLASE ABSTRACTA. Se crea con saldo y alias (ARS y USD), o con saldo, alias y descubierto(CC). Metodo de consultar saldo.
  
   •`Operacion`: INTERFACE: Retirar efectivo, Comprar dolares , transferir, saldoSuficiente.
    
   •`CajaDeAhorroARS`: Hereda de Cuenta. Se crea con un saldo(positivo) y un alias. Implementa los metodos <Operacion> y lanza excepciones (personalizadas) en caso de ser nacesario. 
      
   •`CuentaCorriente`: Hereda de CajaARS. Se crea con un saldo, un alias y un descubierto. Implementa los metodos <Operacion> y lanza excepciones (personalizadas) en caso de ser nacesario.
      
   •`CajaDeAhorroUSD`: Hereda de Cuenta. Se crea con un saldo y un alias.
   
   •`MovimientosEnPantalla`: Encargada de brindarle al usuario todas las operaciones que puede realizar.
   
   •`Deposito`: Hereda de MovimientosEnPantalla. Se encarga de todo lo referido con deposito de efectivo.
   
   •`Extraccion`: Hereda de MovimientosEnPantalla. Se encarga de todo lo referido con la extracción de efectivo.
   
   •`Transferencia`: Hereda de MovimientosEnPantalla. Se encarga de todo lo referido a la transferencia de dinero entre distintas cuentas.
   
   •`Mensajes`: Contiene todos los mensajes que verá el usuario.
   
   •`Ticket`: Encargada de crear un ticket cuando se desea.
   
   •`MensajeTicket`: Contiene la informacion que tendrá el ticket.
   
   •`Movimiento`: Destinada a los movimientos que tendrá cada cuenta.
   
   <<`Opreacion`>>: Interface de las Operaciones de cada cuenta.
   
   •`TipoDeMovimiento`: Enum que especifica los movimientos que se pueden realizar.
   
   •`CompraUSD`: Hereda de MovimientosEnPantalla. Se encarga de todo lo referido con la compra de dolares.
    
  •`ErroresDeCuenta`: Excepciones personalizadas para todas las operaciones y construcciones de cuentas.
  
  •`Dispensador`: Se crea un dispensador con el dinero que dice en el enunciado. Posee un mapa Billete|Cantidad. Metodo para retirarBillete que devuelve (si tiene) la cantidad deseada con billetes ordenados de mayor a menor. Metodo para llenar el dispensador.
  
  •`ErroresDeDispensador`: Excepciones personalizadas para todas las operaciones de dispensador.
  
  •`Mensajes`: Todos los mensajes que apareceran en la pantalla.
  
  •`CajeroAutomatico`: Usando todas las clases necesarias, crea el cajero con todas sus opciones.
  
  •`Main`: Crea un cajero automatico y lo ejecuta.  
   _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
  
  Conclusiones: A cada integrande del grupo, este trabajo práctico lo obligó a adquirir nuevos conocimientos para cumplir con su labor de forma eficiente, y generando el minimo consumo de recursos posible. La herramienta de Git mejoró mucho el flujo de trabajo.
   En cuanto al Cajero, fue diseñado de forma tal que se le puedan agregar mas funcionalidades a futuro, y se puedan cambiar cosas a fin de mejorar el rendimiento del mismo, tanto como la experiencia del usuario.
   Por ultimo: En siguientes versiones se podría implementar funcionalidades tales como: Crear un nuevo cliente, el cual sea capaz de abrir sus propias Cuentas; además de un menú con la posibilidad de regresar al paso anterior.
   _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
  
  

![TRZ](https://user-images.githubusercontent.com/55515042/82707534-a227f380-9c52-11ea-885d-fd140fc44223.jpg)
