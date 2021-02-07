package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/evaluate-division/
public class CurrencyConvert {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if(equations.isEmpty() ||  equations.size() != values.length) {
            return null;
        }
        Map<String, List<Pair>> graph = new HashMap<>();
        constructGraph(equations, values, graph);
        double[] answerArray = new double[queries.size()];

        int i = 0;
        for(List<String> query: queries) {
            String numerator = query.get(0);
            String denominator = query.get(1);
            if(!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                answerArray[i] = - 1.0;
                i++;
                continue;
            }
            if(numerator.equalsIgnoreCase(denominator)) {
                answerArray[i] = 1.0;
                i++;
                continue;
            }
            double answer = 1.0;
            dfsUtil(graph, new HashSet<>(), numerator, denominator, answer);
            answerArray[i] = global;
            if (global != - 1.0) {
                global = - 1.0;
            }
            i++;
        }

        return answerArray;

    }
    double global = - 1.0;
    private void dfsUtil(Map<String, List<Pair>> graph, Set<String> visited, String current, String end, double factor) {
        if(visited.contains(current) || !graph.containsKey(current)) {
            return;
        }
        visited.add(current);
        List<Pair> pairList = graph.get(current);
        for(Pair pair: pairList) {
            if(visited.contains(pair.str)) {
                continue;
            }
            //factor = factor * pair.value;
            if(pair.str.equalsIgnoreCase(end)) {
                global = factor *  pair.value;
                return;
            }
            dfsUtil(graph, visited, pair.str, end, factor * pair.value);
        }
    }

    private void constructGraph(List<List<String>> equations, double[] values,  Map<String, List<Pair>> graph) {
        int i = 0;
        for(List<String> equation: equations) {
            String numerator = equation.get(0);
            String denominator = equation.get(1);
            double value = values[i];
            List<Pair> list;
            if(graph.containsKey(numerator)) {
                list = graph.get(numerator);
            } else {
                list = new ArrayList<>();
            }
            list.add(new Pair(denominator, value));
            graph.put(numerator, list);

            List<Pair> denomList;
            if(graph.containsKey(denominator)) {
                denomList = graph.get(denominator);
            } else {
                denomList = new ArrayList<>();
            }
            denomList.add(new Pair(numerator, 1/value));
            graph.put(denominator, denomList);

            i++;
        }
    }
    class Pair {
        String str;
        double value;
        Pair(String str, double value) {
            this.str = str;
            this.value = value;
        }
    }

    public static void main(String args[]) {
        CurrencyConvert evaluateDivision = new CurrencyConvert();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        List<String> list4 = new ArrayList<>();
        List<String> list5 = new ArrayList<>();

        list1.add("a"); list1.add("e");
        list2.add("e"); list2.add("b");
        list3.add("b"); list3.add("d");
        list4.add("b"); list4.add("b");
        list5.add("b"); list5.add("z");

        double[] values = {3.0, 4.0, 5.0, 6.0};

        List<String> eq1= new ArrayList<>();
        List<String> eq2= new ArrayList<>();
        List<String> eq3= new ArrayList<>();
        List<String> eq4= new ArrayList<>();
        eq1.add("a"); eq1.add("b");
        eq2.add("b"); eq2.add("c");
        eq3.add("c"); eq3.add("d");
        eq4.add("d"); eq4.add("e");
        System.out.print(Arrays.toString(evaluateDivision.calcEquation(List.of(eq1, eq2, eq3, eq4),
                values,List.of(list1, list2, list3, list4, list5))));
    }
}


