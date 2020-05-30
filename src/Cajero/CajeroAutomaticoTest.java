package Cajero;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
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

		todasLasCuentas=new ArchivoDeCuentas();
		tarjetaIngresada = new Tarjeta(12345678,1234);
		cuitDelUsuario = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresada);
		clienteTest1 = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuario);
		tarjetaIngresadaDos = new Tarjeta(56789012,5678);
		cuitDelUsuarioDos = todasLasCuentas.getArchivoTarjetas().getCuitConTarjeta().get(tarjetaIngresadaDos);
		clienteTest2 = todasLasCuentas.getArchivoTarjetas().getClientesConCuit().get(cuitDelUsuarioDos);

	}

	@Test
	public void testConsultaDeSaldo() throws Exception{






	}
	@Test
	public void chequearSaldoActual() throws FileNotFoundException, IOException {




	}


	public void pruebaTransferir ()  throws Exception {




	}






	@Test
	public void pruebaSaldoSuficiente () throws Exception {

		CajaDeAhorroARS caja1 = new CajaDeAhorroARS(10000, "isla.pez.arbol");

		assertEquals(true, caja1.saldoSuficiente(2000));
		assertEquals(false, caja1.saldoSuficiente(40000));
		assertEquals(true, caja1.saldoSuficiente(1200));
		assertEquals(false, caja1.saldoSuficiente(100000));
		assertEquals(true, caja1.saldoSuficiente(140));



	}


	public void pruebaRetirarEfectivo () throws Exception {

		CajaDeAhorroARS caja1 = new CajaDeAhorroARS(10000, "isla.pez.arbol");

		assertEquals(false, caja1.retirarEfectivo(12000));
		assertEquals(true, caja1.retirarEfectivo(8000));

	}


	public void pruebaComprarDolares() throws Exception {







	}

}