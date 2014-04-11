package sdarch;

import sdarch.*;

import java.text.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.*;

public class ArchMain {
	static Logger logger = Logger.getLogger(ArchMain.class.getName());
	static String rootPath;
	
	public static void main(String[] args) throws FileNotFoundException {
		/*if (args.length > 0){
			rootPath = args[0];
        } else {
        	System.out.println("Root folder not specified. Please provide a root path.");
        	return;
        }*/
		rootPath = "D:\\sdarchjava\\sda-archives\\tftd\\imagetftd";
		
		logger.setLevel(Level.FINE);
		String monthValue, fileName, absFilePath, dateString, bodyHTML;
		String fileNamePattern = "^tftd_\\d{6}.html$"; //searches for string like tftd_040914.html
		HashSet<String> imageURLList;
		Pattern p = Pattern.compile(fileNamePattern);
		Matcher m;
		
		//Class object initialization.
		GetDates dt1 = new GetDates(); 
		HTMLParser p1 = new HTMLParser();
		HTMLBuilder b1 = new HTMLBuilder();
		FolderParser f1 = new FolderParser();
		ImageDownloader i1 = new ImageDownloader();
		
		try {
			//FileHandler file name with max size and number of log files limit
            Handler fileHandler = new FileHandler("/sdarchjava/sdarchErr.log", 2000, 5);
            fileHandler.setFormatter(new LogFormatter());
            logger.addHandler(fileHandler);
            
            //Parse the root directory recursively and get all html files. Uses apache.commons.FileUtils package
            Collection<File> files = f1.getFileNames(rootPath);
                        
            //Iterate over all list of html files returned and format them
            for (Iterator<File> iterator = files.iterator(); iterator.hasNext();) {
    			File file = (File) iterator.next();
    			m = p.matcher(file.getName());
    		    if (m.matches()) {
    		    	fileName = m.group();
    			    dateString = fileName.split("_")[1].split("\\.")[0];
    			    absFilePath = file.getAbsolutePath();
    			    monthValue = dt1.getMonth(dateString);
    			    bodyHTML = p1.getHTMLBody(absFilePath);
    			    p1.getImageURL();
    			    b1.buildTFTD(bodyHTML, monthValue);
    			    //b1.storeTFTD(absFilePath);
    		    }
            }
            imageURLList = p1.getImageURLList();
            Iterator<String> iterator = imageURLList.iterator();
            i1.storeImages(iterator, rootPath);
        		
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.CONFIG, e.getMessage());
		} /*catch (ParseException e) {
			logger.log(Level.CONFIG, e.getMessage());
		} catch (IOException e) {
			logger.log(Level.CONFIG, e.getMessage());
		} */
	}
}
