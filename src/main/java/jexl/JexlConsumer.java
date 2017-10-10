package jexl;

import org.apache.commons.jexl3.*;

import java.util.function.Consumer;

/**
 * Created by Doug on 10/8/2017.
 */
public class JexlConsumer<T> implements Consumer<T> {
    private JexlExpression e;

    public JexlConsumer(String expression) {
        JexlEngine je = new JexlBuilder().create();

        try {
            this.e = je.createExpression(expression);
        } catch (Exception e) {
            throw new RuntimeException("Error creating JEXL expression", e);
        }
    }

    @Override
    public void accept(T t) {
        JexlContext jc = new ObjectContext<>(JexlEngineManager.Engine, t);
        try {
            e.evaluate(jc);
        } catch (Exception e) {
            throw new RuntimeException("Error evaluating JEXL expression", e);
        }
    }
}
