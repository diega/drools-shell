package org.plugtree.drools.shell.outputbuilders;

import org.drools.definition.KnowledgePackage;

import java.util.Collection;

/**
 * creation date: 2/17/11
 */
public class KnowledgePackageCollectionOutputBuilder implements OutputBuilder<Collection<KnowledgePackage>> {

    @Override
    public String getOutput(Collection<KnowledgePackage> input) {
        StringBuilder output = new StringBuilder();
        for(KnowledgePackage kpackage : input){
            output.append("* ");
            output.append(kpackage.getName());
            output.append(CR);
        }
        if (output.length() > 0) {
            output.delete(output.length() - 2, output.length());
            return output.toString();
        }
        return "no packages";
    }
}
