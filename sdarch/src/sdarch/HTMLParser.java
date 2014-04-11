package sdarch;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParser {
	private Document doc;
	private HashSet<String> imageURLSet = new HashSet<String>();
		
	public String getHTMLBody(String absFilePath) throws IOException {
		File html = new File(absFilePath);
		doc = Jsoup.parse(html,"UTF-8");
		return doc.body().toString();
	}
	
	public void getImageURL() throws IOException {
		Elements media = doc.select("[src]");
		for (Element src : media) {
			if (src.attr("abs:src") != "") 
				imageURLSet.add(src.attr("abs:src"));
		}
	}
	
	public HashSet<String> getImageURLList() {
		return imageURLSet;
	}

}
