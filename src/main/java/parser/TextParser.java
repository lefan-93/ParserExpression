package parser;

import model.*;
import exception.*;

import java.io.*;
import java.util.*;
import java.util.List;

public class TextParser {
    private static final String FACT_PATTERN = "^[_]*[a-zA-Z]+[a-zA-Z0-9_]*$";
    private static final String SEPARATOR = "----------------------------------------------------------------";
    private int charIndex, bracketCount, lineCount;


    private enum State {
        RULE,
        FACT,
        FINISH
    }

    private enum ExpressionState {
        AWAITING_FACT,
        BRACKET_EXPRESSION,
        FACT_WITHOUT_LETTER,
        FACT_LETTER,
        AWAITING_OPERATOR,
        OR_OPERATOR,
        AND_OPERATOR
    }

    private enum ExpressionType {
        AND,
        OR
    }

    private String parseFact(String fact) throws ParserException {
        if (!fact.matches(FACT_PATTERN))
            throw new ParserException("Wrong format of fact", lineCount);
        return fact;
    }

    private Rule parseRule(String line) throws ParserException {
        charIndex = 0;
        bracketCount = 0;
        String stringRule[] = line.split("->");
        if (stringRule.length != 2) {
            throw new ParserException("Wrong format of rule", lineCount);
        }
        return new Rule(parseExpression(stringRule[0].trim()), parseFact(stringRule[1].trim()));

    }

    private IExpression AssembleExpression(List<IExpression> orExpressions, List<IExpression> andExpressions, IExpression interimExp, ExpressionType currentExpression) {
        if (currentExpression == ExpressionType.OR) {
            orExpressions.add(interimExp);
        } else {
            andExpressions.add(interimExp);
            if (orExpressions.size() != 0) {
                orExpressions.add(new AndExpression(andExpressions));
            } else {
                return new AndExpression(andExpressions);
            }
        }
        return new OrExpression(orExpressions);
    }

    private IExpression parseExpression(String expression) throws ParserException {
        List<IExpression> orExpressions = new ArrayList<>();
        List<IExpression> andExpressions = new ArrayList<>();
        IExpression interimExp = null;
        int charStartIndex = 0;
        ExpressionType currentExpression = ExpressionType.OR;
        ExpressionState expressionState = ExpressionState.AWAITING_FACT;
        for (; charIndex < expression.length(); charIndex++) {
            switch (expressionState) {
                case AWAITING_FACT:
                    if (Character.isSpaceChar(expression.charAt(charIndex)))
                        break;
                    if (expression.charAt(charIndex) == '(') {
                        expressionState = ExpressionState.BRACKET_EXPRESSION;
                        break;
                    }
                    if (expression.charAt(charIndex) == '_') {
                        charStartIndex = charIndex;
                        expressionState = ExpressionState.FACT_WITHOUT_LETTER;
                        break;
                    }
                    if (Character.isLetter(expression.charAt(charIndex))) {
                        charStartIndex = charIndex;
                        expressionState = ExpressionState.FACT_LETTER;
                        break;
                    }
                    throw new ParserException("Wrong format of the beginning of the fact", lineCount, charIndex);
                case BRACKET_EXPRESSION:
                    bracketCount++;
                    interimExp = parseExpression(expression);
                    expressionState = ExpressionState.AWAITING_OPERATOR;
                    break;
                case FACT_WITHOUT_LETTER:
                    if (expression.charAt(charIndex) == '_') {
                        break;
                    }
                    if (Character.isLetter(expression.charAt(charIndex))) {
                        expressionState = ExpressionState.FACT_LETTER;
                        break;
                    }
                    throw new ParserException("Wrong format of fact", lineCount, charIndex);
                case FACT_LETTER:
                    if (Character.isSpaceChar(expression.charAt(charIndex))) {
                        interimExp = new FactExpression(expression.substring(charStartIndex, charIndex));
                        expressionState = ExpressionState.AWAITING_OPERATOR;
                        break;
                    }
                    if (expression.charAt(charIndex) == '&') {
                        interimExp = new FactExpression(expression.substring(charStartIndex, charIndex));
                        expressionState = ExpressionState.AND_OPERATOR;
                        break;
                    }
                    if (expression.charAt(charIndex) == '|') {
                        interimExp = new FactExpression(expression.substring(charStartIndex, charIndex));
                        expressionState = ExpressionState.OR_OPERATOR;
                        break;
                    }
                    if (expression.charAt(charIndex) == ')') {
                        bracketCount--;
                        if (bracketCount < 0)
                            throw new ParserException("The closing and opening brackets do not match", lineCount);
                        interimExp = new FactExpression(expression.substring(charStartIndex, charIndex));
                        return AssembleExpression(orExpressions, andExpressions, interimExp, currentExpression);
                    }
                    if (Character.isLetterOrDigit(expression.charAt(charIndex)) || expression.charAt(charIndex) == '_') {
                        break;
                    }
                    throw new ParserException("Wrong format of fact", lineCount, charIndex);
                case AWAITING_OPERATOR:
                    if (Character.isSpaceChar(expression.charAt(charIndex)))
                        break;
                    if (expression.charAt(charIndex) == '&') {
                        expressionState = ExpressionState.AND_OPERATOR;
                        break;
                    }
                    if (expression.charAt(charIndex) == '|') {
                        expressionState = ExpressionState.OR_OPERATOR;
                        break;
                    }
                    if (expression.charAt(charIndex) == ')') {
                        bracketCount--;
                        if (bracketCount < 0)
                            throw new ParserException("The closing and opening brackets do not match", lineCount);
                        return AssembleExpression(orExpressions, andExpressions, interimExp, currentExpression);
                    }
                    throw new ParserException("Wrong format of fact", lineCount, charIndex);
                case AND_OPERATOR:
                    if (expression.charAt(charIndex) != '&')
                        throw new ParserException("Wrong format of operator", lineCount, charIndex);
                    andExpressions.add(interimExp);
                    currentExpression = ExpressionType.AND;
                    expressionState = ExpressionState.AWAITING_FACT;
                    break;
                case OR_OPERATOR:
                    if (expression.charAt(charIndex) != '|')
                        throw new ParserException("Wrong format of operator", lineCount, charIndex);

                    if (currentExpression == ExpressionType.OR) {
                        orExpressions.add(interimExp);
                    } else {
                        andExpressions.add(interimExp);
                        orExpressions.add(new AndExpression(andExpressions));
                        andExpressions = new ArrayList<>();
                        currentExpression = ExpressionType.OR;
                    }
                    expressionState = ExpressionState.AWAITING_FACT;
                    break;
            }
        }
        if (bracketCount > 0)
            throw new ParserException("The closing and opening brackets do not match", lineCount);
        if (expressionState != ExpressionState.FACT_LETTER && expressionState != ExpressionState.AWAITING_OPERATOR)
            throw new ParserException("Unexpected ending of expression", lineCount);
        if (expressionState == ExpressionState.FACT_LETTER)
            interimExp = new FactExpression(expression.substring(charStartIndex));
        return AssembleExpression(orExpressions, andExpressions, interimExp, currentExpression);
    }

    public Model parseFile(String filePath) throws ParserException, IOException {
        LinkedList<Rule> rules = new LinkedList<>();
        Set<String> facts = new HashSet<>();
        lineCount = 0;
        State state = State.RULE;
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            do {
                line = br.readLine();
                lineCount++;
                if (line != null) {
                    line = line.trim();
                }
                if (line == null && state != State.FINISH) {
                    throw new ParserException("Wrong format of file");
                }
                switch (state) {
                    case RULE:
                        if (line.equals(SEPARATOR) && rules.size() > 0) {
                            state = State.FACT;
                            break;
                        }
                        if (line.equals(""))
                            break;
                        rules.add(parseRule(line));
                        break;
                    case FACT:
                        if (line.equals(""))
                            break;
                        if (line.charAt((line.length()) - 1) == ',')
                            throw new ParserException("Wrong format record of facts");
                        String[] str = line.split(",");
                        for (String fact : str) {
                            facts.add(parseFact(fact.trim()));
                        }
                        state = State.FINISH;
                        break;
                    case FINISH:
                        if (line != null && !line.equals("")) {
                            throw new ParserException("Wrong format of file");
                        }
                        break;
                }
            } while (line != null);

        }
        return new Model(rules, facts);
    }

}
