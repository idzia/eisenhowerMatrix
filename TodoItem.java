import java.time.LocalDate;

public class TodoItem {
    public String title;
    public LocalDate deadline;
    public boolean isDone;

    public TodoItem (String title, LocalDate deadline){
        this.title = title;
        this.deadline = deadline;
        this.isDone = false;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void mark(){
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }

    public String toString() {
        char marker;
        if (this.isDone) {
            marker = 'x';
        } else {
            marker = ' ';
        }
        return ("[" + marker + "] " + this.deadline.getDayOfMonth() + "-" + this.deadline.getMonthValue() + " "
                + this.title);
    }

    public String toCsv(boolean isImportant) {
        String status;
        if (isImportant) {
            status = "important";
        } else {
            status = "";
        }
        return (this.title + "|" + this.deadline.getDayOfMonth() + "-" + this.deadline.getMonthValue() + "|" + status + "\n");
    }
}
    

