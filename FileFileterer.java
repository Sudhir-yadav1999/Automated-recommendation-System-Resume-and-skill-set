package t1;

import java.io.File;
import java.io.FileFilter;

public class FileFilterer implements FileFilter
{
    public final String[] AcceptedFileExtensions = new String[] {"pdf"};
	public boolean accept(File file)
	  {
	    for (String extension : AcceptedFileExtensions)
	    {
	      if (file.getName().toLowerCase().endsWith(extension))
	      {
	        return true;
	      }
	    }
	    return false;
	  }
}
