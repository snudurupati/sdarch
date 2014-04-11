package sdarch;


import java.io.File;
import java.io.IOException;
import java.util.Collection;
import org.apache.commons.io.FileUtils;

public class FolderParser {

	public Collection<File> getFileNames(String rootPath) throws IOException {
		File root = new File(rootPath);
		//String[] extensions = {"html", "jpg", "xhtml"};
		String[] extensions = {"html"};
		boolean recursive = true;
		Collection<File> files = FileUtils.listFiles(root, extensions, recursive);
		return files;
	}
}

