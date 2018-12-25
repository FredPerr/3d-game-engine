package engine.util;

import java.io.File;
import java.util.ArrayList;

/**
 * The resource class provide a hierarchical way to load files
 * from the computer of the user and to the web.
 *
 * TODO url safety for null files.
 */
public class Resource {

    private static ArrayList<Resource> resources = new ArrayList<>();

    private File externalFile;

    /**
     * Create a resource.
     * @param externalPath Path from the computer root to the external file(C:/...).
     */
    private Resource(String externalPath) {
        this.externalFile = new File(externalPath);
        if(this.externalFile == null) {
            System.err.println("Could not load the file: \n" + externalFile.getPath());
            resources.remove(this);
        }
    }

    /**@return File of the resource (external).*/
    public File getFile(){
        return getExternalFile();
    }

    /**
     * @return External file. May be null.
     */
    public File getExternalFile() {
        return this.externalFile;
    }

    /**
     * Set the path of the external file.
     *
     * @param externalFile External file.
     */
    public void setExternalFile(File externalFile) {
        this.externalFile = externalFile;
    }

    /**
     * Create a resource.
     * @param externalPath Path from the computer root to the external file(C:/...).
     */
    public static Resource createResource(String externalPath) {
        Resource r = new Resource(externalPath);
        resources.add(r);
        return r;
    }

    /**
     * @return Resources created.
     */
    public static ArrayList<Resource> getResources() {
        return resources;
    }
}
