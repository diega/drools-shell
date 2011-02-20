package org.plugtree.drools.mvel;

import org.junit.Assert;
import org.junit.Test;
import org.mvel2.MVEL;

/**
 * creation date: 2/20/11
 */
public class SimpleMvelTest {

    @Test
    public void mvelEval(){
        Assert.assertNotNull(MVEL.eval("with(new org.plugtree.drools.Person()){name='robert', age =18}"));
    }
}
