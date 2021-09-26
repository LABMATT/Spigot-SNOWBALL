package Mangers;

import labmatt.space.Snowball;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.NotDirectoryException;

public class XMLManger {

    //private Snowball plugin;
    private File file;

    //public XMLManger(Snowball plugin) {this.plugin = plugin;}

    // Gets the file that the we want to read or write to.
    public XMLManger getXML(String filename) throws FileNotFoundException {

        if(filename.length() > 0){
            File file = new File(filename);

            if(file.exists())
            {

                this.file = file;
            } else {
                throw new FileNotFoundException("404 File not found.");
            }
        } else {
            throw new FileNotFoundException("404 File not found. You need to enter a file name.");
        }

        return this;
    }

    // Create a new file under that name and location.
    public XMLManger newXML(String name, String location) throws Exception {
        if (name.length() != 0 && location.length() != 0) {
            File dir = new File(location);
            if (dir.isDirectory()) {
                int llen = location.length() - 1;
                name = name.replace(".xml", "");

                // If we included a last backslash or forwards slash remove it and replace it with the oprating system appropate one.
                if (location.charAt(llen) == '/' || location.charAt(llen) == '\\')
                {
                    location = location.substring(0, llen);
                    this.file = new File(location + File.separator + name + ".xml");
                } else {
                    this.file = new File(location + File.separator + name + ".xml");
                }

            } else {
                throw new NotDirectoryException("No Directory called <" + location + "> was found.");
            }
        } else {
            throw new Exception("Must provide a file name and location.");
        }

        return this;
    }


    public void readXML()
    {

    }
}
