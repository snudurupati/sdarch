package sdarch;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import org.apache.commons.io.FilenameUtils;

public class ImageDownloader
{      
    public void storeImages(Iterator<String> iterator, String rootPath) throws IOException    {
        BufferedImage image =null;
        String[] imageURL;
        String baseName, extension;
        URL url;
       	while(iterator.hasNext()) {
       		imageURL = iterator.next().split("#");
       		if(imageURL.length > 1) {
       			url =new URL(imageURL[1]);
       		}
       		else {
       			url =new URL(imageURL[0]);
       		}
       		baseName = FilenameUtils.getBaseName(url.toString());
            extension = FilenameUtils.getExtension(url.toString());
            System.out.println(rootPath+"\\picture_library\\"+baseName+"."+extension);
       	    image = ImageIO.read(url);
       	    ImageIO.write(image, extension,new File(rootPath+"\\picture_library\\"+baseName+"."+extension));
       	    
       }
    }
}
