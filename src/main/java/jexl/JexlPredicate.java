package jexl;

import org.apache.commons.jexl3.*;

import java.util.function.Predicate;

public class JexlPredicate<T> implements Predicate<T>
{
    private JexlExpression e;

    public JexlPredicate(String expression) {
        JexlEngine je = new JexlBuilder().create();

        try {
            this.e = je.createExpression(expression);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JEXL expression", e);
        }
    }

    @Override
    public boolean test(T t) {
        JexlContext jc = new ObjectContext<>(JexlEngineManager.Engine, t);
        Boolean b;
        try {
            b = (Boolean) e.evaluate(jc);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating JEXL expression", e);
        }
        return b;
    }
}