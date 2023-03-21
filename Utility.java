import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.Enumeration;
import java.io.IOException;

public class ZipFileRenamer {
    public static void main(String[] args) {
        // Specify the directory containing the zip file
        File directory = new File("/path/to/directory");

        // Define the regex pattern to match the zip and text file names
        Pattern zipPattern = Pattern.compile("^zip_([0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}).zip$");
        Pattern textPattern = Pattern.compile("^([a-d])_([0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}).txt$");

        // Get the list of files in the directory
        File[] files = directory.listFiles();

        // Loop through the files and rename them if they match the pattern
        for (File file : files) {
            Matcher zipMatcher = zipPattern.matcher(file.getName());
            Matcher textMatcher = textPattern.matcher(file.getName());
            if (zipMatcher.matches()) {
                // If it's the zip file, rename it
                String oldName = file.getName();
                String date = "_" + zipMatcher.group(1);
                String newName = oldName.replaceFirst("^zip_", "zip" + date + "_");
                file.renameTo(new File(directory, newName));
                
                // Rename the text files inside the zip file
                try (ZipFile zipFile = new ZipFile(file)) {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();
                        String entryName = entry.getName();
                        Matcher textFileMatcher = textPattern.matcher(entryName);
                        if (textFileMatcher.matches()) {
                            String textDate = "_" + zipMatcher.group(1);
                            String newTextName = textFileMatcher.group(1) + textDate + "_" + textFileMatcher.group(2) + ".txt";
                            entry.setName(newTextName);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (textMatcher.matches()) {
                // If it's a text file, do nothing
            } else {
                // If it's not a zip or text file, do nothing
            }
        }
    }
}
