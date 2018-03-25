import java.util.Map;
import java.util.HashMap;
import java.time.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TodoMatrix {
    public Map<String, TodoQuarter> todoQuarters;

    public TodoMatrix() {
        this.todoQuarters = new HashMap<String, TodoQuarter>();
        TodoQuarter quarter1 = new TodoQuarter();
        TodoQuarter quarter2 = new TodoQuarter();
        TodoQuarter quarter3 = new TodoQuarter();
        TodoQuarter quarter4 = new TodoQuarter();
        this.todoQuarters.put("IU", quarter1);
        this.todoQuarters.put("IN", quarter2);
        this.todoQuarters.put("NU", quarter3);
        this.todoQuarters.put("NN", quarter4);
    }

    public TodoQuarter getQuarter(String status) {
        return this.todoQuarters.get(status);
    }

    public void addItem(String title, LocalDate deadline, boolean isImportant) {
        String status = this.resolveStatus(deadline, isImportant);
        this.getQuarter(status).addItem(title, deadline);
    }

    public void addItem(String title, LocalDate deadline) {
        this.addItem(title, deadline, false);
    }

    private String resolveStatus(LocalDate deadline, boolean isImportant) {
        String status = "";
        if (isImportant) {
            status += "I";
        } else {
            status += "N";
        }

        LocalDateTime todayTime = LocalDate.now().atStartOfDay();
        LocalDateTime deadlineTime = deadline.atStartOfDay();
        long duration = Duration.between(todayTime, deadlineTime).toHours();

        if (duration < 72) {
            status += "U";
        } else {
            status += "N";
        }

        return status;
    }

    public void addItemsFromFile (String path) {
        File fileName = new File(path);
        try {
            BufferedReader f = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line=f.readLine()) != null) {
                String[] row = line.trim().split("\\|");
                String[] date = row[1].split("\\-");
                boolean isImportant;
                if (row.length==2) {
                    isImportant = false;
                } else {
                    isImportant = true;
                }
                this.addItem(row[0], LocalDate.of(2018, Integer.parseInt(date[1]), Integer.parseInt(date[0])), isImportant);
            }  
            f.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        catch (IOException e){
            System.out.println("Some error");
        }
    }

    public void saveItemsToFile(String path) {
        File fileName = new File(path);
        try {
            BufferedWriter f = new BufferedWriter(new FileWriter(fileName));
            f.write(this.toCsv());

            f.close();
        } catch (IOException e) {
            System.out.println("Some error");
        }

    }

    public String toString() {
        return ("IU:\n" + this.getQuarter("IU").toString() + "\nIN:\n" + this.getQuarter("IN").toString() + "\nNU:\n"
                + this.getQuarter("NU").toString() + "\nNN:\n" + this.getQuarter("NN").toString());
    }

    public String toCsv() {
        return(this.getQuarter("IU").toCsv(true) + this.getQuarter("IN").toCsv(true) 
        + this.getQuarter("NU").toCsv(false) + this.getQuarter("NN").toCsv(false));
    }

    public void archiveItems() {
        this.todoQuarters.get("IU").archiveItems();
        this.todoQuarters.get("IN").archiveItems();
        this.todoQuarters.get("NU").archiveItems();
        this.todoQuarters.get("NN").archiveItems();
    }
}