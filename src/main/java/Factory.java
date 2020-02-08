import java.time.DateTimeException;
import java.util.Scanner;

/** Creates task objects. */
public class Factory {

    public Task buildTask(String taskName, String input) {
        Task task = null;
        if (taskName.equals("todo")) {
            task = createTodo(input);
        } else if (taskName.equals("deadline")) {
            task = createDeadline(input);
        } else {
            task = createEvent(input);
        }
        return task;
    }

    /**
     * Create Todo object.
     *
     * @param desc Todo description
     * @return Todo object
     */
    public Task buildTodoFromCloud(String desc) {
        return createTodo(desc);
    }

    /**
     * Create Deadline object.
     *
     * @param desc Deadline description.
     * @param dateTimeStr Deadline due datetime.
     * @return Deadline object.
     */
    public Task buildDeadlineFromCloud(String desc, String dateTimeStr) {
        TaskDate td = new TaskDate(dateTimeStr);
        if (td.getLocalDate() != null) {
            return new Deadline(desc, td);
        } else {
            return null;
        }

    }

    /**
     * Create Event object.
     *
     * @param desc Event description.
     * @param dateTimeStr1 Event start datetime.
     * @param dateTimeStr2 Event end datetime
     * @return Event object.
     */
    public Task buildEventFromCloud(String desc, String dateTimeStr1, String dateTimeStr2) {
        TaskDate td = new TaskDate(dateTimeStr1);
        TaskDate td2 = new TaskDate(dateTimeStr2);
        if (td.getLocalDate() != null && td2.getLocalDate() != null) {
            return new Event(desc, td, td2);
        } else {
            return null;
        }
    }

    /**
     * Creates a Todo object.
     *
     * @param input user input string.
     * @return Todo object.
     */
    private Todo createTodo(String input) {
        return new Todo(input);
    }

    /**
     * Creates a Deadline object.
     *
     * @param input user input string.
     * @return Deadline object.
     */
    private Deadline createDeadline(String input) {
        int indexCut = input.indexOf("/by");
        String desc = input.substring(0, indexCut - 1);
        String by = input.substring(indexCut + 4);
        TaskDate td = new TaskDate(by);
        return new Deadline(desc, td);
    }

    /**
     * Creates an Event object.
     *
     * @param input user input string.
     * @return Event object.
     */
    private Event createEvent(String input) {
        Event event = null;
        Scanner sc2 = new Scanner(System.in);
        TaskDate tdEnd = null;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Event end date and time: ");
                String endDate = sc2.nextLine();
                tdEnd = new TaskDate(endDate);
                isValid = true;

                int indexCut = input.indexOf("/at");
                String desc = input.substring(0, indexCut - 1);
                String at = input.substring(indexCut + 4);
                TaskDate tdStart = new TaskDate(at);
                event = new Event(desc, tdStart, tdEnd);

            } catch(ArrayIndexOutOfBoundsException e) {
                System.err.println("Invalid input, please follow the format {dd/mm/yyyy hhmm}");
            } catch(DateTimeException e) {
                System.err.println("Invalid date!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return event;
    }

}
