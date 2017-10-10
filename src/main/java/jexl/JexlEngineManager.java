package jexl;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;

/**
 * Created by Doug on 10/8/2017.
 */
public class JexlEngineManager{

    public static JexlEngine Engine;

    static {
        JexlBuilder builder = new JexlBuilder().strict(true);
//        builder.namespaces(nsMap);
        Engine = builder.create();
    }

    private JexlEngineManager()
    {}

}
