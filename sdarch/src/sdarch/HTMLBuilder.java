package sdarch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class HTMLBuilder {
	private String tftdHTML;
	public void buildTFTD(String bodyHTML, String monthValue) {
		String headerHTML, footerHTML;
		headerHTML  = "<html xmlns='http://www.w3.org/1999/xhtml'><body><div align='center'>  <p class='ct'><a href='../../../index.html'>Back to Home Page</a></p><p class='ct'><a href='index.html'>Back to "
						+ monthValue + " </a><a href='index.html'> Index</a></p><br /></div>";
		footerHTML = "<p align=center><a href='../../../index.html'>Back to Home Page</a></p><p align=center><a href='index.html'>Back to "+ monthValue + " Index</a></p></body></html>";
		tftdHTML = headerHTML + bodyHTML + footerHTML;
	}
	
	public void storeTFTD(String absFilePath) throws IOException {
		File file = new File(absFilePath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(tftdHTML);
		bw.close();
	}

}
