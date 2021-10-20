package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.MenuCommand;
import seedu.duke.command.OrdersCommand;
import seedu.duke.command.UnknownCommand;
import seedu.duke.command.EditCommand;
import seedu.duke.exceptions.LotsException;

public class Parser {
    /**
     * Returns a Command object based on the user's input
     * string, else unknown command Type.
     *
     * @param input The user's input in String.
     * @return The respective Command type.
     */
    public static Command getCommand(String input) throws LotsException {
        assert input != null : "Input to getCommand Cannot be NULL!";

        if (input.isBlank() || input.isEmpty()) {
            return new UnknownCommand();
        }
        String[] listOfInputs = input.split(" ");
        String commandInString = listOfInputs[0].toLowerCase();
        
        switch (commandInString) {
        case (AddCommand.COMMAND_WORD):
            return new AddCommand(input);
        case (DeleteCommand.COMMAND_WORD):
            return new DeleteCommand(input);
        case (EditCommand.COMMAND_WORD):
            return new EditCommand(input);
        case (OrdersCommand.COMMAND_WORD):
            return new OrdersCommand(input);
        case (MenuCommand.COMMAND_WORD):
            return new MenuCommand(input);
        case (FindCommand.COMMAND_WORD):
            return new FindCommand(input);
        case (ByeCommand.COMMAND_WORD):
            return new ByeCommand();
        default:
            return new UnknownCommand();
        }
    }
}
