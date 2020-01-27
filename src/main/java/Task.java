import java.io.Serializable;

/**
 * Represents a simple Task with a description and status.
 */
public class Task implements Serializable {
    protected String desc;
    protected boolean isDone;

    /**
     * Constructor for Task class
     * @param desc
     */
    public Task(String desc) {
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Sets task status to be done or not
     * @param b status of task
     */
    public void setDone(boolean b) {
        this.isDone = b;
    }

    /**
     * Returns tick or cross based on Task status
     * @return tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + this.desc;
    }
}