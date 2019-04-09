package com.mirak.leetcode.individual.medium;

import java.util.*;

public class ReconstructItinerary {

  public List<String> findItinerary(String[][] tickets) {
    if (tickets.length == 0) {
      return new ArrayList<>();
    }
    Map<String, ArrayList<String>> adjList = getAdjListFromTickets(tickets);
    Map<String, Integer> consumedEdges = new HashMap<>();
    Stack<String> stack = new Stack<>();

    LinkedList<String> list = new LinkedList<>();
    stack.push("JFK");
    while (!stack.isEmpty()) {
      String current = stack.peek();
      if (nodeStuck(current, adjList, consumedEdges)) {
        list.addFirst(stack.pop());
        continue;
      }
      int skippedEdges = consumedEdges.getOrDefault(current, 0);
      stack.push(adjList.get(current).get(skippedEdges));
      consumedEdges.put(current, skippedEdges + 1);
    }
    return list;
  }

  private Map<String, ArrayList<String>> getAdjListFromTickets(String[][] tickets) {
    Map<String, ArrayList<String>> adjList = new HashMap<>();
    for (String[] ticket : tickets) {
      adjList.putIfAbsent(ticket[0], new ArrayList<>());
      adjList.get(ticket[0]).add(ticket[1]);
    }
    for (String key : adjList.keySet()) {
      Collections.sort(adjList.get(key));
    }
    return adjList;
  }


  private boolean nodeStuck(String node, Map<String, ArrayList<String>> adjList,
      Map<String, Integer> consumedEdges) {
    return !adjList.containsKey(node) || consumedEdges.getOrDefault(node, 0) == adjList
        .get(node).size();
  }

}
