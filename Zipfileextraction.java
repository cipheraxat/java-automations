import java.io.*;
import java.util.zip.*;

public class ZipFileExample {
    public static void main(String[] args) throws IOException {
        // Open the original zip file
        String zipFilePath = "original.zip";
        ZipFile zipFile = new ZipFile(zipFilePath);

        // Create a new zip file
        String newZipFilePath = "new.zip";
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(newZipFilePath));

        // Loop through all the entries in the original zip file
        for (ZipEntry entry : zipFile.entries()) {
            // Check if the current entry is the file that you want to remove
            if (entry.getName().equals("textfile.txt")) {
                continue;
            }

            // Copy the current entry to the new zip file
            zipOutputStream.putNextEntry(new ZipEntry(entry.getName()));

            InputStream inputStream = zipFile.getInputStream(entry);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                zipOutputStream.write(buffer, 0, len);
            }

            inputStream.close();
            zipOutputStream.closeEntry();
        }

        // Close the input and output streams associated with the original and new zip files
        zipFile.close();
        zipOutputStream.close();
    }
}
