package com.locademiaz.drools.shell;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import com.locademiaz.drools.Person;
import com.locademiaz.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * creation date: 3/1/11
 */
public class ListFactsTest extends CliCommandTest {

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();

        final String noResultsOutput = "0 results found";

        String countOutput = shell.run(ksession, "lsfacts");
        Assert.assertTrue(noResultsOutput.equals(countOutput));

        ksession.insert(new Person("robert", 23));
        countOutput = shell.run(ksession, "lsfacts");
        Assert.assertTrue(!noResultsOutput.equals(countOutput));
    }
}
