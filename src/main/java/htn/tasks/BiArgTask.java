package htn.tasks;

import utility.functionalInterfaces.TriConsumer;
import utility.functionalInterfaces.TriPredicate;
import java.util.function.Function;

/**
 * Created by Doug on 10/8/2017.
 */
public class BiArgTask<T,TArg1, TArg2> extends PrimitiveTask<T>{

    private Function<T,TArg1> arg1;
    private Function<T,TArg2> arg2;
    private TArg1 cachedArg1;
    private TArg2 cachedArg2;
    private TriConsumer<T, TArg1, TArg2> effect;
    private TriPredicate<T, TArg1, TArg2> condition;

    public BiArgTask(String name, TriConsumer<T, TArg1, TArg2> effect) {
        this(name, null, effect);
    }

    public BiArgTask(String name, TriPredicate<T, TArg1, TArg2> condition,TriConsumer<T, TArg1, TArg2> effect){
        super(name, null, null);
        this.condition = condition;
        this.effect = effect;
    }

    public PrimitiveTask<T> with(Function<T, TArg1> arg1, Function<T, TArg2> arg2){
        BiArgTask<T,TArg1, TArg2> newTask = new BiArgTask<>(name, condition, effect);
        newTask.arg1 = arg1;
        newTask.arg2 = arg2;
        return newTask;
    }

    public PrimitiveTask<T> with(TArg1 arg1, TArg2 arg2){
        BiArgTask<T,TArg1, TArg2> newTask = new BiArgTask<>(name, condition, effect);
        newTask.arg1 = t -> arg1;
        newTask.arg2 = t -> arg2;
        return newTask;
    }

    public PrimitiveTask<T> with(Function<T, TArg1> arg1, TArg2 arg2){
        BiArgTask<T,TArg1, TArg2> newTask = new BiArgTask<>(name, condition, effect);
        newTask.arg1 = arg1;
        newTask.arg2 = t -> arg2;
        return newTask;
    }

    public PrimitiveTask<T> with(TArg1 arg1, Function<T, TArg2> arg2){
        BiArgTask<T,TArg1, TArg2> newTask = new BiArgTask<>(name, condition, effect);
        newTask.arg1 = t -> arg1;
        newTask.arg2 = arg2;
        return newTask;
    }

    @Override
    public boolean evaluate(T t) {
        cachedArg1 = arg1.apply(t);
        cachedArg2 = arg2.apply(t);
        return condition == null || condition.test(t, cachedArg1, cachedArg2);
    }

    @Override
    public void apply(T t) {
        if(effect == null)
            return;
        effect.accept(t, cachedArg1, cachedArg2);
    }

    @Override
    public String toString() {
        return String.format("%s(%s, %s)", name, cachedArg1, cachedArg2);
    }
}
