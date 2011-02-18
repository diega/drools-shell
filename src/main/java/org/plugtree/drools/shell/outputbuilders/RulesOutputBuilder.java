package org.plugtree.drools.shell.outputbuilders;

import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;

import java.util.Collection;
import java.util.Map;

/**
 * creation date: 2/17/11
 */
public class RulesOutputBuilder implements OutputBuilder<Map<KnowledgePackage,Collection<Rule>>>{
    @Override
    public String getOutput(Map<KnowledgePackage, Collection<Rule>> input) {
        StringBuilder output = new StringBuilder();
        for(Map.Entry<KnowledgePackage, Collection<Rule>> entry : input.entrySet()){
            output.append("* ");
            output.append(entry.getKey().getName());
            if (entry.getValue().isEmpty()){
                output.append(" (empty)");
                output.append(CR);
            } else {
                output.append(CR);
                for(Rule rule : entry.getValue()){
                    output.append("\t * ");
                    output.append(rule.getName());
                    output.append(CR);
                }
            }
        }
        return output.toString();
    }
}
