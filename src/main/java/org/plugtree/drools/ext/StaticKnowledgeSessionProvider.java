package org.plugtree.drools.ext;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.io.InputStream;
import java.util.List;

/**
 * creation date: 3/1/11
 */
public class StaticKnowledgeSessionProvider implements KnowledgeSessionProvider{

    private KnowledgeBase kbase;

    public StaticKnowledgeSessionProvider(List<InputStream> rules) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        for(InputStream rule : rules ){
            kbuilder.add(ResourceFactory.newInputStreamResource(rule), ResourceType.DRL);
        }
        if(kbuilder.hasErrors()){
            throw new IllegalArgumentException(kbuilder.getErrors().toString());
        }

        kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
    }

    @Override
    public StatefulKnowledgeSession getSession() {
        return kbase.newStatefulKnowledgeSession();
    }
}
