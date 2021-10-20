package seedu.duke.command;

import seedu.duke.Menu;
import seedu.duke.Ui;
import seedu.duke.exceptions.LotsException;

import java.util.ArrayList;
import java.util.Locale;

public class MenuCommand extends Command {

    public static final String COMMAND_WORD = "menu";

    private String getCommandWord;
    private boolean chosenMenu = false;
    private ArrayList<Double> priceList;
    private ArrayList<String> foodList;

    /**
     * MenuCommand calls access to file to retrieve stored menu.
     */
    public MenuCommand(String input) throws LotsException {
        if(checkUserCommand(input)) {
            priceList = Menu.PRICELIST;
            foodList = Menu.FOODLIST;
            getCommandWord = menuCommandParser(input);
        } else {
            throw new LotsException("Invalid Command");
        }
    }

    /**
     * Prints menu in the format of index, foodname and price with column headers.
     */
    @Override
    public void execute() throws LotsException {
        try {
            if(getCommandWord.contains("list")) {
                Ui.printMenuHeader();
                for (int i = 0; i < foodList.size(); i++) {
                    Ui.printMenu(i + 1, foodList.get(i), priceList.get(i));
                }
                Ui.printWithBorder("");
            } else if (Integer.parseInt(getCommandWord) > 0
                    && Integer.parseInt(getCommandWord) < 11
                    && chosenMenu == false) {
                Menu.readMenuFromFile("menu"+getCommandWord);
                Ui.printMenuSelection(getCommandWord);
                chosenMenu = true;
            } else {
                throw new LotsException("Please enter the right input");
            }
        } catch (NumberFormatException e) {
            throw new LotsException("Invalid Input!");
        }
    }

    /**
     * Parse the user input and returns the 2nd part of the command.
     * It is a number from 1 to 10 or list.
     * @param input is raw user input.
     * @return the parsed String.
     */
    protected String menuCommandParser(String input) {
        String[] inputSplitted = input.split(" ");
        String command = inputSplitted[1];
        if(command.contains("list")){
            return command.toLowerCase().trim();
        }
        return command.trim();
    }

    /**
     * Checks if the User input is valid. It must be a number from 1 to 10
     * or list.
     * @param input
     * @return boolean true if the input is valid.
     */
    protected boolean checkUserCommand(String input) {
        String command = menuCommandParser(input);
        int menuNumber = Integer.parseInt(command);
        if(command.contains("list")) {
            return true;
        }
        if(menuNumber > 0 && menuNumber < 11) {
            return true;
        }
        return false;
    }
}
