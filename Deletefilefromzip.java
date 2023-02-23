import java.io.*;
import java.util.zip.*;

public class DeleteFileFromZip {
    public static void main(String[] args) throws IOException {
        String zipFilePath = "path/to/zip/file.zip";
        String textFileName = "file.txt";
        
        // Open the zip file
        try (FileSystem zipFileSystem = FileSystems.newFileSystem(Paths.get(zipFilePath), null)) {
            
            // Get the path of the text file within the zip file
            Path pathInZipfile = zipFileSystem.getPath(textFileName);
            
            // Delete the text file from the zip file
            Files.deleteIfExists(pathInZipfile);
            
            // Close the zip file system
            zipFileSystem.close();
            
            System.out.println("Text file " + textFileName + " deleted from zip file " + zipFilePath);
        } catch (IOException e) {
            System.err.println("Error deleting text file from zip file: " + e.getMessage());
        }
    }
}
