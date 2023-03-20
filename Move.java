import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileMover {
    public static void main(String[] args) {
        String sDir = "C:\\Source\\Directory\\Path"; // replace with source directory path
        String dDir = "C:\\Destination\\Directory\\Path"; // replace with destination directory path
        String fileName = "example.txt"; // replace with the name of the file you want to move
        
        File[] files = new File(sDir).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    Path source = file.toPath();
                    Path destination = new File(dDir + "\\" + fileName).toPath();
                    try {
                        Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("File moved successfully.");
                    } catch (IOException e) {
                        System.out.println("Error moving file: " + e.getMessage());
                    }
                    break;
                }
            }
        }
    }
}
