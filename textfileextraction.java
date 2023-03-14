import java.io.*;
import java.util.zip.*;

public class ZipFileExtractor {
    public static void extractTextFile(String zipFileName, String textFileName) throws IOException {
        File zipFile = new File(zipFileName);
        File textFile = new File(textFileName);

        // Open the zip file for reading
        ZipFile zip = new ZipFile(zipFile);

        try {
            // Get the entry for the text file
            ZipEntry entry = zip.getEntry(textFileName);

            if (entry == null) {
                throw new FileNotFoundException("File not found in zip: " + textFileName);
            }

            // Extract the text file
            InputStream in = zip.getInputStream(entry);
            OutputStream out = new FileOutputStream(textFile);
            byte[] buffer = new byte[1024];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }

            // Close streams
            in.close();
            out.close();
        } finally {
            // Close the zip file
            zip.close();
        }

        // Delete the original zip file
        zipFile.delete();
    }
}
