package model;

import javax.xml.bind.annotation.*;
import java.util.*;
@XmlRootElement(name = "Model")
public class Model {
    //The LinkedList is used because the method "remove" is faster here.
    @XmlAnyElement(lax = true)
    @XmlElementWrapper(name = "Rules")
    private LinkedList<Rule> rules;
    @XmlElement(name = "Fact")
    @XmlElementWrapper(name = "KnownFacts")
    private Set<String> facts;

    public Model(LinkedList<Rule> rules, Set<String> facts) {
        this.rules = rules;
        this.facts = facts;
    }

    //This constructor is required for JAXB to work correctly.
    public Model(){}

    public void evaluate() {
        Rule rule;
        boolean rulesRemoved;
        do {
            rulesRemoved = false;
            Iterator<Rule> itr = rules.iterator();
            while (itr.hasNext()) {
                rule = itr.next();
                String fact = rule.evaluate(facts);
                if (fact != null) {
                    facts.add(fact);
                    rulesRemoved = true;
                    itr.remove();
                }
            }
        } while (rulesRemoved);

    }

    public Set<String> getFacts() {
        return facts;
    }

    public LinkedList<Rule> getRules() {
        return rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (rules != null ? !rules.equals(model.rules) : model.rules != null) return false;
        return facts != null ? facts.equals(model.facts) : model.facts == null;
    }

    @Override
    public int hashCode() {
        int result = rules != null ? rules.hashCode() : 0;
        result = 31 * result + (facts != null ? facts.hashCode() : 0);
        return result;
    }
}

