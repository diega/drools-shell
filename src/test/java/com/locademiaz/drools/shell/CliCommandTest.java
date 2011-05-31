package com.locademiaz.drools.shell;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * creation date: 3/1/11
 */
public abstract class CliCommandTest {

    protected StatefulKnowledgeSession getStatefulKnowledgeSession(List<InputStream> rules) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for(InputStream rule : rules ){
            kbuilder.add(ResourceFactory.newInputStreamResource(rule), ResourceType.DRL);
        }
        if(kbuilder.hasErrors()){
            throw new IllegalArgumentException(kbuilder.getErrors().toString());
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase.newStatefulKnowledgeSession();
    }

    protected StatefulKnowledgeSession getStatefulKnowledgeSession() {
        return getStatefulKnowledgeSession(Arrays.asList(getClass().getResourceAsStream("/test1.drl"),
                getClass().getResourceAsStream("/test2.drl")
        ));
    }
}
