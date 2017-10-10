package htn;

import htn.tasks.ComplexTask;

/**
 * Created by Doug on 10/8/2017.
 */
public class Domain<T> {
    public ComplexTask<T> rootTask;

    public Domain(ComplexTask<T> rootTask){
        this.rootTask = rootTask;
    }
}
