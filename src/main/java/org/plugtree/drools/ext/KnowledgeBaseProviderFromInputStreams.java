package org.plugtree.drools.ext;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

import java.io.InputStream;
import java.util.List;

/**
 * creation date: 2/17/11
 */

public class KnowledgeBaseProviderFromInputStreams implements KnowledgeBaseProvider {

    private KnowledgeBase kbase;

    public KnowledgeBaseProviderFromInputStreams(List<InputStream> rules) {
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
    public KnowledgeBase getKnowledgeBase() {
        return kbase;
    }
}
