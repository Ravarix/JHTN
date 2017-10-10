package utility.functionalInterfaces;

/**
 * Created by Doug on 10/9/2017.
 */
@FunctionalInterface
public interface TriFunction<T1, T2, T3, TReturn>{
    TReturn apply(T1 t1, T2 t2, T3 t3);
}