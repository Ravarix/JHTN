package htn.tasks;

/**
 * Created by Doug on 10/8/2017.
 */
public abstract class Task<T> {
    public String name;

    protected Task(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
