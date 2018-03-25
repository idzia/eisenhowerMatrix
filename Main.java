import java.time.LocalDate;
import java.time.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        //int month = 04;
        //nt day = 12;
        LocalDate deadline = LocalDate.of(2018, 04, 12);
        TodoItem pierwszy = new TodoItem("Zadanie domowe z javy", LocalDate.of(2018, 05, 12));
        TodoItem drugi = new TodoItem("Odebrac Tosie", LocalDate.of(2018, 04, 10));
        TodoItem trzeci = new TodoItem("Ugotować obiad", LocalDate.of(2018, 04, 21));
        TodoItem czwarty = new TodoItem("pójść na zebranie", deadline);
        // pierwszy.mark();
        System.out.println(pierwszy.toString());
        System.out.println(drugi.toString());
        System.out.println(trzeci.toString());
        System.out.println(czwarty.toString());

        TodoQuarter myItems = new TodoQuarter();
        myItems.addItem("Zadanie domowe z javy", LocalDate.of(2018, 05, 12));
        myItems.addItem("Odebrac Tosie", LocalDate.of(2018, 04, 10));
        myItems.addItem("Ugotować obiad", LocalDate.of(2018, 04, 21));
        myItems.addItem("pójść na zebranie", deadline);
        System.out.println("");

        myItems.getItem(0).mark();

        myItems.getItem(2).mark();
        
        System.out.println(myItems.getItem(0));
        System.out.println(myItems.getItem(1));
        System.out.println(myItems.getItem(2));
        System.out.println(myItems.getItem(3));
    
        myItems.archiveItems();
        System.out.println("");
        System.out.println(myItems.getItem(0));
        System.out.println(myItems.getItem(1));
        System.out.println("");
        System.out.println("");
        
        
        System.out.println(myItems.toString());

        TodoMatrix todoMatrix = new TodoMatrix();
        todoMatrix.addItem("Ugotować obiad", LocalDate.of(2018, 04, 21));
        todoMatrix.addItemsFromFile("todo_items_read_test.csv");
        System.out.println(todoMatrix.toString());
        todoMatrix.saveItemsToFile("my_todo.csv");




    }





}