import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.FileNotFoundException;




/*
  This program should be executed by typing...

  java UTF16BE_to_UTF8 file1.txt file2.txt

  It is supposed to convert a UTF-16BE encoded
  file to a UTF-8 encoded file.
*/
public class UTF16BE_to_UTF8 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Make sure to specify the file to convert ");
            System.out.println("and the filename to use when writing.");
            return;
        }
        String input_filename = args[0];
        String output_filename = args[1];





        File file = new File(input_filename);

        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out;
            try {
                out = new FileOutputStream(output_filename);
            }
            catch (FileNotFoundException e) {
                System.out.println(output_filename + " could not be opened for writing.");
                System.out.println("Make sure that you did not specify the name of a directory.");
                return;
            }
        


        byte[] bytes = new byte[(int) file.length()];



        try {
            in.read(bytes);
        }
        catch (IOException e) {
            System.out.println("An IOException was thrown when attempting to read the bytes from the file.");
            System.out.println("Here's the detail message...");
            System.out.println(e.getMessage());
            return;
        }
        finally {
            try {
                in.close();
            }
            catch (IOException e) {
                System.out.println("An IOException was thrown when attempting to close the FileInputStream.");
            }
        }






        bytes = Help.UTF16BE_to_UTF8(bytes);


        try {
            out.write(bytes);
        }
        catch (IOException e) {
            System.out.println("An IOException was thrown when attempting to write the bytes to the file.");
            System.out.println("Here's the detail message...");
            System.out.println(e.getMessage());
            return;
        }
        finally {
            try {
                out.close();
            }
            catch (IOException e) {
                System.out.println("An IOException was thrown when attempting to close the FileOutputStream.");
            }
        }

        }
        catch (FileNotFoundException e) {
            System.out.println(input_filename + " was not found, or it was found and is a directory.");
            System.out.println("Are you sure you typed the filename correctly?");
            return;
        }

    }
}