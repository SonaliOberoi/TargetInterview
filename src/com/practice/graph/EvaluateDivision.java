package com.practice.graph;

import java.util.*;

//https://leetcode.com/problems/evaluate-division/
//https://leetcode.com/problems/evaluate-division/discuss/465551/Java-Dfs-solution
public class EvaluateDivision {
    double ans = -1.0;
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] result = new double[queries.size()];
        Map<String, Map<String, Double>> graph = new HashMap();
        for (int i = 0; i< values.length; i++) {
            List<String> currentEq = equations.get(i);
            String numerator = currentEq.get(0);
            String denominator = currentEq.get(1);
            //numerator
            if (graph.containsKey(numerator)) {
                graph.get(numerator).put(denominator, values[i]);
            } else {
                Map<String, Double> current = new HashMap();
                current.put(denominator, values[i]);
                graph.put(numerator, current);
            }
            //denominator
            if (graph.containsKey(denominator)) {
                graph.get(denominator).put(numerator, 1/values[i]);
            } else {
                Map<String, Double> current = new HashMap();
                current.put(numerator, 1/values[i]);
                graph.put(denominator, current);
            }
        }
        for (int i = 0; i< queries.size() ;i++) {
            List<String> currentQuery = queries.get(i);
            if (!graph.containsKey(currentQuery.get(0)) || !graph.containsKey(currentQuery.get(1)) ) {
                result[i] = -1.0;
                continue;
            } else if(currentQuery.get(0).equals(currentQuery.get(1))) {
                result[i] = 1.0;
                continue;
            }
            dfsUtil(new HashSet<>(),currentQuery, graph, currentQuery.get(0), 1.0);
            result[i] = ans;
        }
        return result;

    }

    private void dfsUtil(Set<String> visited, List<String> currentQuery,
                           Map<String, Map<String, Double>> graph, String start, double result) {

        visited.add(start);
        Map neighbours = graph.get(start);
        Iterator it = neighbours.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(!visited.contains(pair.getKey().toString())) {
                dfsUtil(visited, currentQuery, graph, pair.getKey().toString(),
                        result * Double.valueOf(pair.getValue().toString()));
                if (pair.getKey().equals(currentQuery.get(1))) {
                    ans = result * Double.valueOf(pair.getValue().toString());
                    return;
                }
            }
        }
    }

    public static void main(String args[]) {
        EvaluateDivision evaluateDivision = new EvaluateDivision();
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
