package Cajero;
//
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Random;

public class ModificarArchivoDeCuenta {

	File archivoDeCuenta;

	public ModificarArchivoDeCuenta(){
		this.archivoDeCuenta = new File ("C:\\Users\\Dell\\git\\CajeroAutomatico.TRZ\\ArchivoCuentas.txt");
	}

	public void modificarSaldo(String tipoDeCuenta, String alias, double antiguoSaldo, double descubierto, double nuevoSaldo ) {
		DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
		separadoresPersonalizados.setDecimalSeparator('.');
		DecimalFormat convertidor = new DecimalFormat("#.00", separadoresPersonalizados);
		if ( ( tipoDeCuenta.equals("01") ) || ( tipoDeCuenta.equals("03") ) ) {
			String lineaOriginal = ( tipoDeCuenta + "," + alias + "," + convertidor.format(antiguoSaldo) );
			String lineaNueva = ( tipoDeCuenta + "," + alias + "," + convertidor.format(nuevoSaldo) );
			modificar(this.archivoDeCuenta, lineaOriginal, lineaNueva);
		} else if ( ( tipoDeCuenta.equals("02") ) ) {
			String lineaOriginal = ( tipoDeCuenta + "," + alias + "," + convertidor.format(antiguoSaldo) + "," + convertidor.format(descubierto) );
			String lineaNueva = ( tipoDeCuenta + "," + alias + "," + convertidor.format(nuevoSaldo) + "," + convertidor.format(descubierto) );
			modificar(this.archivoDeCuenta, lineaOriginal, lineaNueva);
		}
	}

	private void Escribir( File archivo, String texto) {
		BufferedWriter escritor;
		try {
			if ( !archivo.exists() ) {
				archivo.createNewFile();
			}
			escritor = new BufferedWriter(new FileWriter(archivo,true));
			escritor.write(texto+"\n");
			escritor.close();
		} catch(Exception e) {
			System.out.println( "No se pudo escribir el archivo" );
		}
	}

	private void borrar (File archivo) {
		try {
			if ( archivo.exists() ) {
				archivo.delete();
			}
		} catch(Exception e) {
			System.out.println( "No se pudo borrar el archivo" );
		}
	}

	private void modificar ( File archivoOrginal, String textoOriginal, String textoNuevo ) {
		Random numAleatorio = new Random(3816L);
		String direccionArchivoNuevo = archivoOrginal.getParent()+"/auxiliar"+String.valueOf(Math.abs(numAleatorio.nextInt()))+".txt";
		File archivoNuevo= new File(direccionArchivoNuevo);
		BufferedReader escritor;

		try {
			if ( archivoOrginal.exists() ) {
				escritor = new BufferedReader(new FileReader(archivoOrginal));
				String linea;

				while ( ( linea=escritor.readLine() ) != null ) {
					if ( linea.equals( textoOriginal ) ) {
						Escribir(archivoNuevo,textoNuevo);
					} else {
						Escribir(archivoNuevo,linea);
					}
				}
				escritor.close();
				borrar(archivoOrginal);
				archivoNuevo.renameTo(archivoOrginal);

			} else {
				System.out.println( "El archivo no existe,," );
			}
		} catch (Exception e) {
			System.out.println( "No se pudo modificar el archivo" );
		}
	}
}