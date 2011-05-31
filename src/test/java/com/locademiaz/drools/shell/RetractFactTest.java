package com.locademiaz.drools.shell;

import org.drools.runtime.StatefulKnowledgeSession;
import org.junit.Assert;
import org.junit.Test;
import com.locademiaz.drools.Person;
import com.locademiaz.drools.ext.ObjectStore;
import com.locademiaz.drools.shell.exceptions.CommandNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * creation date: 3/2/11
 */
public class RetractFactTest extends CliCommandTest{

    @Test
    public void runCommand() throws CommandNotFoundException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/context.xml");
        final DroolsShell shell = applicationContext.getBean("shell", DroolsShell.class);
        final StatefulKnowledgeSession ksession = getStatefulKnowledgeSession();

        final ObjectStore objectStore = applicationContext.getBean("objectStore", ObjectStore.class);
        Person person = new Person("manny", 23);
        ksession.insert(person);
        final int personId = objectStore.put(person);

        final String output = shell.run(ksession, "retract", "-i", String.valueOf(personId));

        Assert.assertTrue(ksession.getObjects().size() == 0);
        Assert.assertTrue(objectStore.isEmpty());
        System.out.println(output);
    }
}
