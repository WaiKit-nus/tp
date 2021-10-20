package seedu.duke;

import seedu.duke.exceptions.LotsException;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Menu {

    private static String[] FOODS = {"Ban Mian", "Chicken Rice", "Fried Prawn Noodles (Hokkien Mee)",
                                     "Minced MeatNoodles(Ba Chor Mee)", "Fried Carrot Cake",
                                     "Fried Kway Teow (Char Kway Teow)",
                                     "Pork Rib Soup (Bak Kut Teh)", "Nasi Lemak"};
    private static final Double[] PRICES = {3.0, 3.0, 3.5, 3.0, 3.0, 3.0, 5.0, 3.6};

    public static final int TOTAL_MENU_ITEMS = PRICES.length;
    public static final ArrayList<String> FOODLIST = new ArrayList<>(Arrays.asList(FOODS));
    public static final ArrayList<Double> PRICELIST = new ArrayList<>(Arrays.asList(PRICES));

    /**
     * Finds all the menus in the menu folder.
     * @param menuFolder is the name of the targeted folder.
     * @return an array of txt file names.
     */
    public String[] findAllMenuInFile(String menuFolder)
            throws LotsException {
        try {
            String[] fileNames = new String[10];
            Path filePath = Paths.get(menuFolder);
            File folder = new File(filePath.toString());
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                File file = listOfFiles[i];
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    fileNames[i] = file.getName();
                }
            }
            return fileNames;
        } catch (InvalidPathException e) {
            throw new LotsException("Such a folder does not exist!");
        }
    }

    /**
     * Prints the names of all the menus.
     * @param menu is the array of txt file names.
     */
    public void printListOfMenu(String[] menu) {
        for(int i = 0; i < menu.length; i++) {
            Ui.printListOfMenu(i+1, menu[i]);
        }
    }

    /**
     * Read the txt file and input values
     * into PRICES and FOOD arrays.
     * @param menuName selected menu of the user.
     */
    public static void readMenuFromFile(String menuName) {
        try {
            String outputFileLine = "";
            Path filePath = Paths.get(menuName);
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader(filePath.toAbsolutePath().toString()));
            while((outputFileLine = bufferedReader.readLine()) != null) {
                int menuIndex = 0;
                splitFoodNameAndFoodPrice(outputFileLine, menuIndex);
                menuIndex++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file");

        } catch (IOException e) {
            System.out.println("There is no text in the file");
        }
    }

    public static void splitFoodNameAndFoodPrice(String menuInput, int menuIndex) {
        try {
            String[] temp = menuInput.split("$");
            String foodName = temp[0];
            double foodPrice = Double.parseDouble(temp[1]);
            FOODS[menuIndex] = foodName;
            PRICES[menuIndex] = foodPrice;
        } catch (PatternSyntaxException e) {

        }
    }

}
