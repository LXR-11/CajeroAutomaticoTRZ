# CajeroAutomaticoTRZ

### Nombres de los integrantes del grupo :
•Aguilera Agustín •Ambrosetti Ramiro •Soto Luciano • Noguera Sol
 _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
 
### Decisiones de diseño tomadas :
Se pueden ver en el UML. La intención del diseño es almacenar Clientes(con sus cuentas y tarjetas) con el fin de buscarlos de forma rapida y eficiente a la hora de solicitar el ingreso de tarjeta, además de conservar la prolijidad en el código. Para esto, se usaron Mapas con sus debidos metodos (Redefiniendo Equals y HashCode en caso de ser necesario).
 _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
### Descripción de cada archivo *.java* comprendido en solución del problema:

  •ArchivoDeClientes: Lee el archivo *ArchivoDeClientes.txt*, separa los datos y los almacena en un Mapa Alias|Cuit.
  
  •ArchivoDeTarjetas: Lee el archivo *ArchivoDeTarjetas.txt*, separa los datos y los almacena en dos mapas: Cuit|Cliente y Tarjeta|Cuit.
  
  •ArchivoDeCuentas: Dentro tiene un ArchivoDeTarjetas y ArchivoDeClientes. Lee el archivo *ArchivoDeCuentas.txt*, separa los datos , asocia a cada Cliente con una Cuenta y los pone en el mapa Cuit|Cliente.
  
  •Cliente: Cada cliente posee una Tarjeta, un cuit, y una cuenta(opcional). Se crea con una tarjeta y un cuit. Tiene métodos para asociar un cliente con una cuenta.
  
  •Tarjeta: Se crea una tarjeta con numero y pin.
  
  •Cuenta: CLASE ABSTRACTA. Se crea con saldo y alias (ARS y USD), o con saldo, alias y descubierto(CC). Metodo de consultar saldo.
  
   •Operacion: INTERFACE: Retirar efectivo, Comprar dolares , transferir, saldoSuficiente.
    
   •CajaDeAhorroARS: Hereda de Cuenta. Se crea con un saldo(positivo) y un alias. Implementa los metodos <Operacion> y lanza excepciones (personalizadas) en caso de ser nacesario. 
      
   •CuentaCorriente: Hereda de Cuenta. Se crea con un saldo, un alias y un descubierto. Implementa los metodos <Operacion> y lanza excepciones (personalizadas) en caso de ser nacesario.
      
   •CajaDeAhorroUSD: Hereda de Cuenta. Se crea con un saldo y un alias.
    
  •ErroresDeCuenta: Excepciones personalizadas para todas las operaciones y construcciones de cuentas.
  
  •Dispensador: Se crea un dispensador con el dinero que dice en el enunciado. Posee un mapa Billete|Cantidad. Metodo para retirarBillete que devuelve (si tiene) la cantidad deseada con billetes ordenados de mayor a menor. Metodo para llenar el dispensador.
  
  •ErroresDeDispensador: Excepciones personalizadas para todas las operaciones de dispensador.
  
  •Mensajes: Todos los mensajes que apareceran en la pantalla.
  
  •CajeroAutomatico: Usando todas las clases necesarias, crea el cajero con todas sus opciones.
  
  •Main: Crea un cajero automatico y lo ejecuta.  
   _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
  
  Conclusiones: 
   _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _  _ _ _ _ _ _ _ _ 
  
  

![TRZ](https://user-images.githubusercontent.com/55515042/82707534-a227f380-9c52-11ea-885d-fd140fc44223.jpg)
