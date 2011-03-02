package org.plugtree.drools.shell;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import org.plugtree.drools.Person;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * creation date: 3/2/11
 */
public class FireAllRulesTest extends CliCommandTest{

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();

        final String output = shell.run(ksession, "fire");
        Assert.assertEquals(1, ksession.getObjects().size());
        System.out.println(output);
    }
}
