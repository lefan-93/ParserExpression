package model;

import java.util.BitSet;
import java.util.List;
import java.util.Set;

public interface IExpression {

    boolean evaluate(Set<String> facts);
}
