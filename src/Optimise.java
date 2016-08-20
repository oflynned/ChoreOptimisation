import java.util.ArrayList;

/**
 * Created by ed on 20/08/16.
 */
class Optimise {

    private int startingLevel = 1;

    enum Transitions  {
        Lowest, Highest
    }

    private ArrayList<Task> tasks;

    Optimise() {
        tasks = new ArrayList<>();

        Task cleanUp = new Task(15, 3, "Change bedsheets");
        Task washSheets = new Task(5, 1, "Wash bedsheets", cleanUp);
        Task washDishes = new Task(10, 1, "Wash Dishes");
        Task poop = new Task(15, 2, "Clean bathroom");
        Task laundry = new Task(5, 1, "Laundry");
        Task shower = new Task(15, 1, "Shower");

        tasks.add(washDishes);
        tasks.add(poop);
        tasks.add(laundry);
        tasks.add(shower);
        tasks.add(cleanUp);
        tasks.add(washSheets);
    }

    public static ArrayList<ArrayList<Task>> permutate(ArrayList<Task> tasks, int index){
        ArrayList<ArrayList<Task>> result = new ArrayList<>();
        if(index == tasks.size() - 1){
            ArrayList<Task> list = new ArrayList<>();
            list.add(tasks.get(index));
            result.add(list);
            return result;
        } else {
            ArrayList<ArrayList<Task>> partial = permutate(tasks, index + 1);
            for(ArrayList<Task> list : partial){
                for(int i = 0; i <= list.size(); i++){
                    ArrayList<Task> tmp = new ArrayList<>(list);
                    tmp.add(i, tasks.get(index));
                    result.add(tmp);
                }
            }
            return result;
        }
    }

    static int aggregateCost(ArrayList<Task> tasks) {
        int cost = 0, currentLevel = 1;
        for(int i=0; i<tasks.size(); i++) {
            if(i == 0) {
                if(tasks.get(i).getLevel() != currentLevel) {
                    cost += tasks.get(i).getLevel();
                }
            } else if(tasks.get(i).getLevel() != currentLevel) {
                cost += getTransitionCost(tasks.get(i), tasks.get(i-1));
            }
            cost += tasks.get(i).getDuration();
        }
        return cost;
    }

    static int getTransitionCost(Task task1, Task task2) {
        return Math.abs(task1.getLevel() - task2.getLevel());
    }

    static int getMaxTransitionCosts(ArrayList<Task> tasks, Transitions transition) {
        int lowest = 1, highest = 1;
        for(Task task : tasks) {
            if (task.getLevel() > highest)
                highest = task.getLevel();
            if (task.getLevel() < lowest)
                lowest = task.getLevel();
        }

        return transition.equals(Transitions.Highest) ? highest - 1 : lowest - 1;
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getStartingLevel() {
        return startingLevel;
    }
}
