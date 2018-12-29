package engine.util;

import java.io.*;
import java.net.URISyntaxException;

/**
 * The Engine resource manager class provides many methods to load anything inside the engine.
 * Also helps to manager files.
 *
 * Created by KitK4t on 2018-12-16.
 */
public class ResourceManager {

    /**
     * This method is static and can be access from everywhere in the code (fully independent).
     * However, this method have to be called from a runnable jar compiled. Errors or mistakes could occur.
     *
     * @return File of the runnable jar application.
     * If the a problem is encountered, it returns null.
     */
    public static File getApplicationFile() {
        try {
            return new File(ResourceManager.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**Loads the content of a file as a text. It can be used to load the content of a resource.
     * @param file Path of the file to load the content from.
     * @return Content of the resource file.*/
    public static String getFileSource(String file){
        StringBuilder source = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine())!=null)
                source.append(line).append("\n");//TODO might need // before \n
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return source.toString();
    }

    /**
     * This method is static and can be access from everywhere in the code (fully independent).
     *
     * @return Path of the runnable application`s folder.
     * This method simply returns the parent of the jar runnable file.
     */
    public static String getApplicationFolderPath() {
        return getApplicationFile().getParent();
    }
}
