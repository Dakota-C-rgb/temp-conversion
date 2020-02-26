package Conversion;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ConversionApp {
    final static String conversionFilePath = "Conversion.ser";

    public static void main(String[] args) {
        ArrayList<Conversion> conversions = loadConversions();
        Conversion con = startConversion();
        conversions.add(con);
        saveConversion(conversions);
    }

    public static void saveConversion(ArrayList<Conversion> con) {
        try {
            FileOutputStream fileStream = new FileOutputStream("Conversion.ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(con);
            os.close();
        } catch (IOException ex) {
            System.out.println("Could not save :( ");
        }
    }

    public static Conversion startConversion() {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to convert ");
        System.out.println("1. Fahrenheit to celsius");
        System.out.println("2. celsius to fahrenheit");
        String fahOrCel = input.nextLine();
        if (fahOrCel.equals("1")) {
            System.out.println("What temperature in fahrenheit would you like to convert: ");
            Integer fah = input.nextInt();
            Integer answerFah = (fah - 32) * 5 / 9;
            System.out.println("That is " + answerFah + " in celsius.");
            return new Conversion(fahOrCel, fah, answerFah);
        } else if (fahOrCel.equals("2")) {
            System.out.println("What temperature in celsius would you like to convert: ");
            Integer cel = input.nextInt();
            Integer answerCel = (cel * 9 / 5) + 32;
            System.out.println("That is " + answerCel + " in fahrenheit.");
            return new Conversion(fahOrCel, cel, answerCel);
        }
        return null;
    }

    public static ArrayList<Conversion> loadConversions() {
        try {
            FileInputStream fileStream = new FileInputStream("Conversion.ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            ArrayList<Conversion> conversions = (ArrayList<Conversion>) os.readObject();
            os.close();
            return conversions;
        } catch (IOException | ClassNotFoundException ex) {
            return new ArrayList<Conversion>();
        }
    }
}