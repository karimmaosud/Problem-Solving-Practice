package com.mirak.leetcode.contests.contest78;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SubdomainVisitCount {
  public List<String> subdomainVisits(String[] cpdomains) {
    Map<String, Integer> domainCountMap = new HashMap<>();
    for(String cpdomain: cpdomains) {
      String[] parsed = cpdomain.split(" ");
      int count = Integer.parseInt(parsed[0]);
      String domain = parsed[1];
      String[] subdomains = domain.split("\\.");
      StringBuilder builder = new StringBuilder(subdomains[subdomains.length - 1]);
      domainCountMap.put(subdomains[subdomains.length - 1], domainCountMap.getOrDefault(subdomains[subdomains.length - 1], 0) + count);
      for(int i = subdomains.length - 2; i >= 0; i--){
        builder = new StringBuilder(subdomains[i]).append(".").append(builder);
        String currentSubdomain = builder.toString();
        domainCountMap.put(currentSubdomain, domainCountMap.getOrDefault(currentSubdomain, 0) + count);
      }
    }
    List<String> res = new LinkedList<>();
    for(Entry<String, Integer> entry: domainCountMap.entrySet()) {
      StringBuilder builder = new StringBuilder();
      builder.append(entry.getValue()).append(" ").append(entry.getKey());
      res.add(builder.toString());
    }
    return res;
  }
}
