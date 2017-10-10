package htn.tasks;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * Created by Doug on 10/8/2017.
 */
public class ArgTask<T,TArg> extends PrimitiveTask<T>{

    private Function<T,TArg> arg1;
    private TArg cachedArg1;
    private BiConsumer<T, TArg> effect;
    private BiPredicate<T, TArg> condition;

    public ArgTask(String name, BiConsumer<T, TArg> effect) {
        this(name, null, effect);
    }

    public ArgTask(String name, BiPredicate<T, TArg> condition, BiConsumer<T, TArg> effect){
        super(name, null, null);
        this.condition = condition;
        this.effect = effect;
    }

    public PrimitiveTask<T> with(Function<T, TArg> arg1){
        ArgTask<T, TArg> newTask = new ArgTask<>(name, condition, effect);
        newTask.arg1 = arg1;
        return newTask;
    }

    public PrimitiveTask<T> with(TArg arg1){
        ArgTask<T, TArg> newTask = new ArgTask<>(name, condition, effect);
        newTask.arg1 = t -> arg1;
        return newTask;
    }

    @Override
    public boolean evaluate(T t) {
        cachedArg1 = arg1.apply(t);
        return condition == null || condition.test(t, cachedArg1);
    }

    @Override
    public void apply(T t) {
        if(effect == null)
            return;
        effect.accept(t, cachedArg1);
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", name, cachedArg1);
    }
}
