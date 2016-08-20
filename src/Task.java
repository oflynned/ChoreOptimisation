/**
 * Created by ed on 20/08/16.
 */
class Task {
    private int duration, level;
    private Task dependency;
    private String name;

    Task(int duration, int level, String name) {
        this(duration, level, name, null);
    }

    Task(int duration, int level, String name, Task dependency) {
        this.duration = duration;
        this.level = level;
        this.name = name;
        this.dependency = dependency;
    }

    int getDuration() {
        return duration;
    }

    int getLevel() {
        return level;
    }

    String getName() {
        return name;
    }

    Task getDependency() {
        return dependency;
    }
}
