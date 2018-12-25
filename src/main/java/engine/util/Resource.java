package engine.util;

import java.io.File;
import java.util.ArrayList;

/**
 * The resource class provide a hierarchical way to load files
 * from the computer of the user to the web in case of need.
 *
 * TODO Get the missing files from URLs and download them. An update system with the version of the file could be added.
 */
public class Resource {

    private static ArrayList<Resource> resources = new ArrayList<>();

    private File assetFile;

    /**
     * Create a resource and add it to the list of the resources if it exits.
     * Otherwise, the resource is not taken in account.
     * @param assetFilePath Path from the computer root to the asset file including it and its extension (C:/../../../test.png).
     */
    public Resource(String assetFilePath) {
        this.assetFile = new File(assetFilePath);
        resources.add(this);
        if(this.assetFile == null || !this.assetFile.exists()) {
            System.err.println("Could not load the file: \n" + assetFile.getPath());
            resources.remove(this);
        }
    }

    /**@return File of the resource (from the computer).
     * This file need to be valid (isValid() method for more details).
     * If not, the return value will be null.*/
    public File getFile(){
        return this.assetFile;
    }

    /**
     * Set the file of the asset file. The file must be created.
     * Otherwise, it will not change it and returns a error message.
     * If the resource was not into the resource list, it will add it.
     * @param assetFile Asset file to set. The file must exist.
     */
    public void setAssetFile(File assetFile) {
        if(assetFile == null || !assetFile.exists()) {
            System.err.println("Could not change the file : \n" + this.assetFile.getPath() + "\nto the file\n"+assetFile.getPath());
            return;
        }
        this.assetFile = assetFile;
        if(!resources.contains(this))
            resources.add(this);
    }

    /**@return True if and only if the file is not null,
     *  the file exists in the asset folder from the path of the computer root (C:/../../..),
     *  the resource is into the resources list.*/
    private boolean isValid(){
        return assetFile != null && assetFile.exists() && resources.contains(this);
    }

    /**
     * @return Resources created that are valid which includes the existing files.
     * You should not remove or add resource with that method by getting the list
     * because everything is automatic and manual changes could create errors.
     */
    public static ArrayList<Resource> getResources() {
        return resources;
    }
}
