package deprotect;

import com.google.common.io.Files;
import com.itextpdf.text.pdf.PdfEncryptor;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeprotectMain {


	
	public static void main(final String[] args) {
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
		final String filenameWithoutExtension = Files.getNameWithoutExtension(inputFile.getPath());
		final File outputFile = new File(filenameWithoutExtension);
		
		
	}
	
}
