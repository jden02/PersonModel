import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {

    public static void main(String[] args) {
        boolean repeat;
        Scanner sc = new Scanner(System.in);
        ArrayList<String> persons = new ArrayList<String>();

        do{
            String ID = SafeInput.getNonZeroLenString(sc,"Enter persons ID");
            String firstName = SafeInput.getNonZeroLenString(sc, "Enter persons First name");
            String lastName = SafeInput.getNonZeroLenString(sc,"Enter persons Last name");
            String title = SafeInput.getNonZeroLenString(sc, "Enter persons Title");
            int birthYear = SafeInput.getRangedInt(sc,"Enter persons birth year",0, 2023);
            String formatedInfo = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + birthYear;
            persons.add(formatedInfo);

            repeat = SafeInput.getYNConfirm(sc, "Do you want to add another person?");
        }while(repeat);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + SafeInput.getNonZeroLenString(sc, "Enter file name") + ".txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
            for (String item : persons) {
                writer.write(item, 0, item.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("List saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }
}