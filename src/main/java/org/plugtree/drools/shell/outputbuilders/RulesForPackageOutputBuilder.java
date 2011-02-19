package org.plugtree.drools.shell.outputbuilders;

import org.drools.definition.rule.Rule;

import java.util.Collection;

/**
 * creation date: 2/19/11
 */
public class RulesForPackageOutputBuilder implements OutputBuilder<Collection<Rule>> {
    @Override
    public String getOutput(Collection<Rule> input) {
        StringBuilder builder = new StringBuilder();
        for(Rule rule : input){
            builder.append("-");
            builder.append(rule.getName());
            builder.append(CR);
        }
        builder.deleteCharAt(builder.lastIndexOf(CR));
        return builder.toString();
    }
}
