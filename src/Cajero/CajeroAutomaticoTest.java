package Cajero;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.IOException;


//lucho probando 1 tarjeta: 12345678,1234
//lucho probando 2 tarjeta: 56789012,5678
public class CajeroAutomaticoTest {


	private ArchivoDeCuentas todasLasCuentas;
	private Tarjeta tarjetaIngresada,tarjetaIngresadaDos;
	long cuitDelUsuario,cuitDelUsuarioDos;
	Cliente clienteTest1,clienteTest2;

	@Before
	public void initObjects() throws FileNotFoundException, IOException {

		//Inicializo los objetos para realizar las pruebas

		todasLasCuentas=new ArchivoDeCuentas();
		tarjetaIngresada = new Tarjeta(12345678,1234);
		cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
		clienteTest1 = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuario);
		tarjetaIngresadaDos = new Tarjeta(56789012,5678);
		cuitDelUsuarioDos = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresadaDos);
		clienteTest2 = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuarioDos);

	}
	@Test
	public void chequearSaldoActual() throws FileNotFoundException, IOException {

		//Chequea el saldo de las cajas del  cliente1
		assertEquals(2000, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);  
		assertNotEquals(6000, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);  
		assertEquals(28, clienteTest1.cajaDelClienteUSD.consultarSaldo(),0.5);
		assertNotEquals (80,clienteTest1.cajaDelClienteUSD.consultarSaldo(),0.5);
		assertEquals(-1521.9, clienteTest1.cuentaCorrienteDelCliente.consultarSaldo(),0.5);
		assertNotEquals(2000, clienteTest1.cuentaCorrienteDelCliente.consultarSaldo(),0.5);

		//Chequea el saldo de las cajas del cliente2
		assertEquals(4500, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);  
		assertNotEquals(2000, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);  
		assertEquals(400, clienteTest2.cajaDelClienteUSD.consultarSaldo(),0.1);
		assertNotEquals(1000, clienteTest2.cajaDelClienteUSD.consultarSaldo(),0.1);
		assertEquals(4500, clienteTest2.cuentaCorrienteDelCliente.consultarSaldo(),0.5);
		assertNotEquals(8400, clienteTest2.cuentaCorrienteDelCliente.consultarSaldo(),0.5);
	}

	@Test
	public void pruebaTransferir() throws Exception{

		//Realiza la transferencia
		clienteTest1.cajaDelClienteARS.transferir(clienteTest2,clienteTest2.cajaDelClienteARS.getAlias(), 500);

		// Consulta si se realizo correctamente la trasnferencia 
		assertEquals(1500, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertEquals(5000, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);
	}

	@Test
	public void pruebaRevertirTransferencia () throws ErroresDeCuenta{
		//Realiza la transferencia
	//	clienteTest1.cajaDelClienteARS.transferir(clienteTest2, 500);
		// Consulta si se realizo correctamente la trasnferencia 
		assertEquals(2000, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertEquals(4500, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);

		//revierte la transferencia anterior
		clienteTest1.cajaDelClienteARS.revertirUltimaTransferencia(500, clienteTest2.cajaDelClienteARS);
		assertEquals(2500, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);

	}

	@Test
	public void pruebaDepositar ()  throws Exception {

		//Deposita dinero en la cuenta
		clienteTest1.cajaDelClienteARS.depositar(5000);
		clienteTest2.cajaDelClienteARS.depositar(5000);

		//Consulta si el dinero esta en la cuenta
		assertEquals(7000.00,clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertNotEquals(10000.00,clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertEquals(9500.00,clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);
		assertNotEquals(10000.00,clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);

	}

	@Test
	public void pruebaSaldoSuficiente () throws Exception {

		//comprueba que tiene saldo
		assertEquals(true, clienteTest1.cajaDelClienteARS.saldoSuficiente(1200));
		assertEquals(true, clienteTest2.cajaDelClienteARS.saldoSuficiente(2400));

		//Comprueba que no tiene saldo
		assertNotEquals(true, clienteTest1.cajaDelClienteARS.saldoSuficiente(10000));
		assertNotEquals(true, clienteTest1.cajaDelClienteARS.saldoSuficiente(8200));




	}

	@Test
	public void pruebaRetirarEfectivo () throws Exception {

		//Retiro efectivo
		clienteTest1.cajaDelClienteARS.retirarEfectivo(1500);
		clienteTest2.cajaDelClienteARS.retirarEfectivo(1500);

		//Consulta de saldo
		assertEquals(500, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertEquals(3000, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);
		assertNotEquals(20000, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertNotEquals(12000, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);



	}

	@Test
	public void pruebaComprarDolares() throws Exception {

		//Compra Dolares clienteUno
		assertEquals(7, clienteTest1.cajaDelClienteARS.comprarDolares(1000, clienteTest1),01);
		assertNotEquals(20, clienteTest1.cajaDelClienteARS.comprarDolares(1000, clienteTest1),01);
		//Compra Dolares clienteDos
		assertEquals(13.5, clienteTest2.cajaDelClienteARS.comprarDolares(2000, clienteTest1),05);
		assertNotEquals(7, clienteTest2.cajaDelClienteARS.comprarDolares(2000, clienteTest1),01);

	}

	@Test
	public void pruebasGet () {

		//getCuit
		assertEquals(203020061, clienteTest1.getCuit()); 
		assertNotEquals(202020061, clienteTest1.getCuit()); 
		assertEquals(271060482, clienteTest2.getCuit());
		assertNotEquals(281060482, clienteTest2.getCuit());

		//getAlias
		assertEquals("isla.pez.arbol", clienteTest1.cajaDelClienteARS.getAlias());
		assertEquals("ramon.perro", clienteTest1.cajaDelClienteUSD.getAlias());
		assertEquals("lucho.probando.segundo.ars", clienteTest2.cajaDelClienteARS.getAlias());
		assertEquals("lobo.luna.pasto", clienteTest2.cajaDelClienteUSD.getAlias());

		assertNotEquals("isla.arbol", clienteTest1.cajaDelClienteARS.getAlias());
		assertNotEquals("ramon.perro.gato", clienteTest1.cajaDelClienteUSD.getAlias());
		assertNotEquals("lucho.isla", clienteTest2.cajaDelClienteARS.getAlias());
		assertNotEquals("lobo.sol.campo", clienteTest2.cajaDelClienteUSD.getAlias());



	}


	@Test(expected = ErroresDeCuenta.class)
	public void pruebaExcepciones() throws ErroresDeCuenta {

		clienteTest1.cajaDelClienteARS.transferir(clienteTest2, clienteTest2.cajaDelClienteARS.getAlias(), 100000);
		clienteTest2.cajaDelClienteARS.transferir(clienteTest1, clienteTest2.cuentaCorrienteDelCliente.getAlias(),10000000);

		assertEquals(100000000, clienteTest1.cajaDelClienteARS.consultarSaldo(),0.1);
		assertEquals(30000000, clienteTest2.cajaDelClienteARS.consultarSaldo(),0.1);

	}


	@Test

	public void pruebaVerificarCuentaEnCliente () {

		//Verifica que hay cuenta existente en los clientes.
		assertEquals(true, clienteTest1.verificarCuentaEnCliente(1));
		assertEquals(true, clienteTest1.verificarCuentaEnCliente(2));
		assertEquals(true, clienteTest2.verificarCuentaEnCliente(1));
		assertEquals(true, clienteTest2.verificarCuentaEnCliente(2));
	}
}
