package com.mirak.leetcode.individual.easy;

import java.util.*;

public class UniqueEmailAddresses {

  public int numUniqueEmails(String[] emails) {
    // localname after cleaning + @domain.com
    Set<String> set = new HashSet<>();
    for (String email : emails) {
      int domainIndex = email.indexOf('@');
      String localname = email.substring(0, domainIndex);
      set.add(getRefinedLocalname(localname) + email.substring(domainIndex));
    }
    return set.size();
  }

  private String getRefinedLocalname(String name) {
    int plusIndex = name.indexOf('+');
    if (plusIndex != -1) {
      name = name.substring(0, plusIndex);
    }
    return name.replaceAll("\\.", "");
  }

}
