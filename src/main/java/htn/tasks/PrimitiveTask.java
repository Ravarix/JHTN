package htn.tasks;


import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Doug on 10/7/2017.
 */
public class PrimitiveTask<T> extends Task<T>
{
    private Predicate<T> condition;
    private Consumer<T> effect;
    private Runnable action;

    public PrimitiveTask(String name, Consumer<T> effect) {
        this(name, null, effect);
    }

    public PrimitiveTask(String name, Predicate<T> condition, Consumer<T> effect){
        super(name);
        this.condition = condition;
        this.effect = effect;
    }

    public PrimitiveTask(String name, Predicate<T> condition, Consumer<T> effect, Runnable action){
        this(name, condition, effect);
        this.action = action;
    }

    public boolean evaluate(T t){
        return condition == null || condition.test(t);
    }

    public void apply(T t){
        effect.accept(t);
    }

    public void executeAction(){
        if(action != null)
            action.run();
    }
}
