import java.util.ArrayList;

/**
 * Created by ed on 20/08/16.
 */
public class Main {
    public static void main(String[] args) {
        Optimise optimise = new Optimise();

        ArrayList<Task> tasks = optimise.getTasks();
        System.out.println("Highest: " + Optimise.getMaxTransitionCosts(tasks, Optimise.Transitions.Highest) + " min transition time");
        System.out.println("Lowest: " + Optimise.getMaxTransitionCosts(tasks, Optimise.Transitions.Lowest) + " min transition time\n");

        for(Task task : tasks) {
            System.out.println(task.getName() + " " + task.getLevel() + " " + task.getDependency() + " " + task.getDuration());
            if(task.getDependency() != null)
                System.out.println("Dependency: " + task.getDependency().getName());
            System.out.println("-----------------------");
        }

        System.out.println("Permutations:");
        ArrayList<ArrayList<Task>> data = Optimise.permutate(tasks, 0);
        for(int i=0; i<data.size(); i++){

            System.out.println("\n");
            for(int j=0; j<data.get(i).size(); j++) {
                System.out.print(data.get(i).get(j).getName() + ", ");
                if(j==data.get(i).size() - 1)
                    System.out.println(Optimise.aggregateCost(tasks));
            }
        }
    }
}
