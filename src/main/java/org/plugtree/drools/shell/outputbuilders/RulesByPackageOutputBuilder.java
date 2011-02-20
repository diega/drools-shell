package org.plugtree.drools.shell.outputbuilders;

import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;

import java.util.Collection;
import java.util.Map;

/**
 * creation date: 2/17/11
 */
public class RulesByPackageOutputBuilder implements OutputBuilder<Map<KnowledgePackage,Collection<Rule>>>{
    @Override
    public String getOutput(Map<KnowledgePackage, Collection<Rule>> input) {
        if(input.isEmpty())
            return "There are no rules in the knowledge base";
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<KnowledgePackage, Collection<Rule>> entry : input.entrySet()){
            builder.append("-");
            builder.append(entry.getKey().getName());
            if (entry.getValue().isEmpty()){
                builder.append(" -> empty");
                builder.append(CR);
            } else {
                builder.append(":");
                builder.append(CR);
                for(Rule rule : entry.getValue()){
                    builder.append("    -");
                    builder.append(rule.getName());
                    builder.append(CR);
                }
            }
        }
        builder.deleteCharAt(builder.lastIndexOf(CR));
        return builder.toString();
    }
}
