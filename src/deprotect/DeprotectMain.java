package deprotect;

import com.google.common.io.Files;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class of DeprotectPDF
 */
public class DeprotectMain {


	/**
	 * Main method of DeprotectPDF.
	 * Read the input file and write an unprotected copy
	 * beside the original.
	 * 
	 * @param args the name of the input file
	 */
	public static void main(final String[] args) {
		
		/* Compute a canonical-path of the input file */
		final File inputFile;
		try {
			inputFile = new File(args[0]).getCanonicalFile();
		} catch (IOException ex) {
			Logger.getLogger(DeprotectMain.class.getName()).log(
					Level.SEVERE,
					"The input file path could not be constructed",
					ex);
			throw new RuntimeException(ex);
		}
		
		/* Compute the name of the output file */
		final String filenameWithoutExtension = Files.getNameWithoutExtension(inputFile.getPath());
		final File outputFile = new File(filenameWithoutExtension + "deprotected.pdf");
		
		/* Instanciate a PDF reader provided by iPDF */
		final PdfReader reader;
		try {
			reader = new PdfReader(inputFile.getPath());
		} catch (final IOException ex) {
			Logger.getLogger(DeprotectMain.class.getName()).log(
					Level.SEVERE,
					"Unexpected IOException : iText failed to read the input file",
					ex);
			throw new RuntimeException(ex);
		}
		
		try {
			final FileOutputStream outputStream = new FileOutputStream(outputFile);
		
			/* Write the output-file, and remove as many restrictions as possible */
			PdfEncryptor.encrypt(reader, outputStream, null,
					null, PdfWriter.AllowAssembly | PdfWriter.AllowCopy
					| PdfWriter.AllowDegradedPrinting | PdfWriter.AllowFillIn
					| PdfWriter.AllowModifyAnnotations | PdfWriter.AllowModifyContents
					| PdfWriter.AllowPrinting | PdfWriter.AllowScreenReaders, false);
		} catch (final IOException ex) {
			Logger.getLogger(DeprotectMain.class.getName()).log(
					Level.SEVERE,
					"Unexpected IOException : the ouput file could not be created",
					ex);
			throw new RuntimeException(ex);
		} catch (DocumentException ex) {
			Logger.getLogger(DeprotectMain.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
