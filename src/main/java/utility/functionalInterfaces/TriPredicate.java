package utility.functionalInterfaces;

/**
 * Created by Doug on 10/9/2017.
 */
@FunctionalInterface
public interface TriPredicate<T1, T2, T3>{
    boolean test(T1 t1, T2 t2, T3 t3);
}