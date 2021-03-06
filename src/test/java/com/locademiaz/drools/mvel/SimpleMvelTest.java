package com.locademiaz.drools.mvel;

import org.junit.Assert;
import org.junit.Test;
import org.mvel2.MVEL;
import com.locademiaz.drools.Person;

/**
 * creation date: 2/20/11
 */
public class SimpleMvelTest {

    @Test
    public void mvelEval(){
        String personName = "robert";
        int personAge = 18;

        StringBuilder mvnExpression = new StringBuilder("with(new com.locademiaz.drools.Person()){name='");
        mvnExpression.append(personName).append("', age=").append(personAge).append("}");

        final Object eval = MVEL.eval(mvnExpression.toString());

        Assert.assertNotNull(eval);
        Assert.assertEquals(new Person(personName, personAge), eval);
    }
}
