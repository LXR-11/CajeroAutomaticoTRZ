package Cajero;
//
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;

public class ArchivoDeCuentas {

	private ArchivoDeClientes archivoCliente;
	private ArchivoDeTarjetas archivoTarjetas;
	private ArchivoDeMovimientos archivoMovimientos;
	ModificarArchivoDeCuenta modificar = new ModificarArchivoDeCuenta();

	public ArchivoDeCuentas() throws FileNotFoundException, IOException {
		this.archivoCliente = new ArchivoDeClientes();  // Creo dos objetos, de archivoCliente y de archivoTarjeta
		this.archivoTarjetas = new ArchivoDeTarjetas(); // Se crean esos dos objetos para tener dos mapas. Uno: CUIT |
		this.modificar = new ModificarArchivoDeCuenta();	// CLIENTE y otro ALIAS | CUIT

		this.archivoMovimientos = new ArchivoDeMovimientos();

		try {
			crearCuenta();
			asociarMovimientosACuentas();
		} catch (Exception e) {
			System.out.println("Error Cuentas");
			e.printStackTrace();
		}

	}

	private void crearCuenta() throws Exception {
		
		BufferedReader lecturaArchivoCuentas = new BufferedReader(new FileReader("ArchivoCuentas.txt"));

		String lineaArchivoCuentas = lecturaArchivoCuentas.readLine();

		while ((lineaArchivoCuentas != null)) {

			lineaArchivoCuentas.trim();

			String separadorArchivoCuentas[] = lineaArchivoCuentas.split(",");
			////////////////////////////////////// EXTRAER
			////////////////////////////////////// VALORES///////////////////////////////////////////////////

			int tipoDeCuenta = Integer.parseInt(separadorArchivoCuentas[0]);

			String alias = separadorArchivoCuentas[1];

			double saldo = Double.parseDouble(separadorArchivoCuentas[2]);
			
			double descubierto = 0;
			if (tipoDeCuenta == 02) {
				descubierto = Double.parseDouble(separadorArchivoCuentas[3]);
			}

			////////////////////////////////// ASOCIAR CUENTA A
			////////////////////////////////// CLIENTES////////////////////////////////////////////////////////
			long cuitMOD = archivoCliente.getAliasConCuit().get(alias);
			switch (tipoDeCuenta) {
			case 01:
				Cliente clienteARS = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteARS.asociarAhorroARS(saldo, alias);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteARS);
				break;
			case 02:
				Cliente clienteCorriente = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteCorriente.asociarCorriente(saldo, alias, descubierto);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteCorriente);
				break;
			case 03:
				Cliente clienteUSD = archivoTarjetas.getClientesConCuit().get(cuitMOD);
				clienteUSD.asociarAhorroUSD(saldo, alias);
				archivoTarjetas.getClientesConCuit().put(cuitMOD, clienteUSD);
				break;
			}

			lineaArchivoCuentas = lecturaArchivoCuentas.readLine();
		}
		lecturaArchivoCuentas.close();
	}

	private void asociarMovimientosACuentas() {

		Stack<Movimiento> movimientos = this.archivoMovimientos.getPilaDeMovimientosTXT();
		Iterator<Movimiento> miIterador = movimientos.iterator();
		while (miIterador.hasNext()) {
			
			Movimiento auxiliar = movimientos.pop();
			Cuenta paraAsociar = encontrarCuentaPorAlias(auxiliar.miAlias);
			paraAsociar.agregarMovimiento(auxiliar);
		}

	}

	public ArchivoDeClientes getArchivoCliente() {
		return archivoCliente;
	}

	public ArchivoDeTarjetas getArchivoTarjetas() {
		return archivoTarjetas;
	}

	public ArchivoDeMovimientos getArchivoMovimientos() {
		return archivoMovimientos;
	}

	public Cuenta encontrarCuentaPorAlias(String aliasDestinatario) {
		if (archivoCliente.getAliasConCuit().containsKey(aliasDestinatario)) {
			long cuitDestinatario = archivoCliente.getAliasConCuit().get(aliasDestinatario);
			Cliente clienteDestinatario = archivoTarjetas.getClientesConCuit().get(cuitDestinatario);
			if (clienteDestinatario.cajaDelClienteARS != null
					&& clienteDestinatario.cajaDelClienteARS.getAlias().equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cajaDelClienteARS;
			} else if (clienteDestinatario.cajaDelClienteUSD != null
					&& clienteDestinatario.cajaDelClienteUSD.getAlias().equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cajaDelClienteUSD;
			} else if (clienteDestinatario.cuentaCorrienteDelCliente != null
					&& clienteDestinatario.cuentaCorrienteDelCliente.getAlias().equalsIgnoreCase(aliasDestinatario)) {
				return clienteDestinatario.cuentaCorrienteDelCliente;
			}
			else {
				return null;
			}
		}
		else {
			System.out.println("No se encontro cuenta para ese alias");
			return null;
		}
	}
}