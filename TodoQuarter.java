import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.*;

public class TodoQuarter {
    public List<TodoItem> todoItems;

    public TodoQuarter() {
        this.todoItems = new ArrayList<TodoItem>();
    }

    public TodoItem getItem(int index){
        return this.todoItems.get(index);
    }

    public void addItem(String title, LocalDate deadline) {
        TodoItem todos = new TodoItem (title, deadline);
        this.todoItems.add(todos);
        this.sortTodos();
    }

    public void removeItem(int position) {
        this.todoItems.remove(position - 1);
    }

    public ArrayList<TodoItem> getDoneList() {
        ArrayList<TodoItem> doneList = new ArrayList<TodoItem>();
        for (TodoItem todos : this.todoItems) {
            if (todos.getIsDone() == true) {
                doneList.add(todos);
            }
        }
        return doneList;
    }

    public void sortTodos() {
        Collections.sort(this.todoItems, new Comparator<TodoItem>() {
            @Override
            public int compare(TodoItem todos1, TodoItem todos2 ) {
                return todos1.getDeadline().compareTo(todos2.getDeadline());
            }
        });
        
    }

    public String toString() {
        String todoString = "";
        int num = 1;
        this.sortTodos();
        for (TodoItem todos : this.todoItems) {
            todoString += num + ". " + todos.toString() + "\n";
            num++;
        }
        return todoString;
    }

    public String toCsv(boolean isImportant) {
        String todoString = "";
        this.sortTodos();
        for (TodoItem todos : this.todoItems) {
            todoString += todos.toCsv(isImportant);
        }
        return todoString;
    }

    public void archiveItems() {
        ArrayList<TodoItem> doneList = getDoneList();
        this.todoItems.removeAll(doneList);
         
        // for (int i = list.size() - 1; i >= 0 ; i--) {
        //     if (list.get(i).getIsDone() == true) {
        //         this.list.remove(i);
        //     }
        // }

        // int position = 0;
        // for (TodoItem todos : this.list) {
        //     if (todos.getIsDone() == true) {
        //         this.list.remove(position)
        //     }
        //     position ++;
        // }
    }



}