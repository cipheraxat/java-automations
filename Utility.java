import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ZipFileRenamer {
    public static void main(String[] args) {
        // Specify the directory containing the zip file and text files
        File directory = new File("/path/to/directory");
        
        // Define the regex pattern to match the zip and text file names
        Pattern pattern = Pattern.compile("^control_([0-9]{8})_([0-9]{6}).zip$|^([a-c])_([0-9]{8})_([0-9]{6}).txt$");
        
        // Get the list of files in the directory
        File[] files = directory.listFiles();
        
        // Loop through the files and rename them if they match the pattern
        for (File file : files) {
            Matcher matcher = pattern.matcher(file.getName());
            if (matcher.matches()) {
                // If it's the zip file, rename it
                if (matcher.group(1) != null) {
                    String date = matcher.group(1);
                    String time = matcher.group(2);
                    String newName = "control_" + date + "_" + time + ".zip";
                    file.renameTo(new File(directory, newName));
                }
                // If it's a text file, rename it
                else {
                    String letter = matcher.group(3);
                    String date = matcher.group(4);
                    String time = matcher.group(5);
                    String newName = letter + "_" + date + "_" + time + ".txt";
                    file.renameTo(new File(directory, newName));
                }
            }
        }
    }
}
