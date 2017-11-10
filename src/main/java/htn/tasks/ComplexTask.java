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

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3, Method<T> method4,
                       Method<T> method5){
        this(name, method1, method2, method3, method4);
        this.methods.add(method5);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3, Method<T> method4,
                       Method<T> method5, Method<T> method6){
        this(name, method1, method2, method3, method4, method5);
        this.methods.add(method6);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3, Method<T> method4,
                       Method<T> method5, Method<T> method6, Method<T> method7){
        this(name, method1, method2, method3, method4, method5, method6);
        this.methods.add(method7);
    }

    public ComplexTask(String name, Method<T> method1, Method<T> method2, Method<T> method3, Method<T> method4,
                       Method<T> method5, Method<T> method6, Method<T> method7, Method<T> method8){
        this(name, method1, method2, method3, method4, method5, method6, method7);
        this.methods.add(method8);
    }
}
