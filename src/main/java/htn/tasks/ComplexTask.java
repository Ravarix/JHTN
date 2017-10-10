package htn.tasks;

import htn.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Doug on 10/7/2017.
 */
public class ComplexTask<T> extends Task<T> {

    public List<Method<T>> methods = new ArrayList<>();

    public ComplexTask(String name, Method<T> method1) {
        super(name);
        this.methods.add(method1);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2){
        this(name, method1);
        this.methods.add(method2);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3){
        this(name, method1, method2);
        this.methods.add(method3);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3, Method<T> method4){
        this(name, method1, method2, method3);
        this.methods.add(method4);
    }
}
