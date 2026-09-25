import java.util.*;

class Solution {
    private int idx;
    private String expr;

    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.idx = 0;

        Set<String> result = parseExpr();
        return new ArrayList<>(result);
    }

    // Handles union: expr1,expr2,...
    private Set<String> parseExpr() {
        Set<String> result = parseTerm();

        while (idx < expr.length() && expr.charAt(idx) == ',') {
            idx++; // skip ','
            result.addAll(parseTerm());
        }

        return result;
    }

    // Handles concatenation
    private Set<String> parseTerm() {
        Set<String> result = new TreeSet<>();
        result.add("");

        while (idx < expr.length()
                && expr.charAt(idx) != '}'
                && expr.charAt(idx) != ',') {

            Set<String> next;

            char ch = expr.charAt(idx);

            if (ch == '{') {
                idx++; // skip '{'
                next = parseExpr();
                idx++; // skip '}'
            } else {
                next = new TreeSet<>();
                next.add(String.valueOf(ch));
                idx++;
            }

            result = product(result, next);
        }

        return result;
    }

    // Cartesian product for concatenation
    private Set<String> product(Set<String> a, Set<String> b) {
        Set<String> res = new TreeSet<>();

        for (String s1 : a) {
            for (String s2 : b) {
                res.add(s1 + s2);
            }
        }

        return res;
    }
}