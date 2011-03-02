package org.plugtree.drools.shell;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import org.plugtree.drools.Person;
import org.plugtree.drools.ext.ObjectStore;
import org.plugtree.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * creation date: 3/1/11
 */
public class InsertFactTest extends CliCommandTest {

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();
        final String output = shell.run(ksession, "insert", "-c", Person.class.getName(), "-f", "name='robert',age=23");
        Assert.assertNotNull(output);
        Assert.assertEquals(1, ksession.getObjects().size());
        System.out.println(output);
    }

}
