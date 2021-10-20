package seedu.duke;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.exceptions.LotsException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static Logger log = Logger.getLogger("LOTS");

    public static void main(String[] args) throws LotsException {
        Ui.printWelcome();
        runDuke();
        Ui.printGoodbye();
    }

    /**
     * The main function that duke runs on.
     */
    private static void runDuke() throws LotsException {
        Menu menu = new Menu();
        menu.printListOfMenu(menu.findAllMenuInFile("Menu"));
        Command currentCommand;
        PeopleManager manager = new PeopleManager();
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String userInput = Ui.readInput();
                currentCommand = Parser.getCommand(userInput);
                assert currentCommand != null : "Command returned cannot be null!";
                currentCommand.setData(manager);
                currentCommand.execute();
                if (currentCommand instanceof ByeCommand) {
                    isEnd = true;
                }
            } catch (LotsException e) {
                Ui.printWithBorder(e.getMessage());
            } catch (Exception x) {
                Ui.printWithBorder("Oops! Unknown error. Please try again.");
                log.log(Level.SEVERE, x.getMessage());
                //x.printStackTrace();
            }
        }
    }
}