package com.mirak.leetcode.individual.medium;

public class ValidateIPAddress {

  private static final String NEITHER = "Neither";
  private static final String IPV4 = "IPv4";
  private static final String IPV6 = "IPv6";

  public String validIPAddress(String IP) {
    if (IP.indexOf('.') != -1) {
      return validateIPv4Address(IP);
    }
    return validateIPv6Address(IP.toUpperCase());
  }

  private String validateIPv4Address(String ip) {
    if (ip.endsWith(".")) {
      return NEITHER;
    }

    String[] strs = ip.split("\\.");
    if (strs.length != 4) {
      return NEITHER;
    }
    for (String numStr : strs) {
      if (!validIPv4Number(numStr)) {
        return NEITHER;
      }
    }
    return IPV4;
  }

  private boolean validIPv4Number(String numStr) {
    if (numStr.length() > 3 || numStr.length() == 0) {
      return false;
    }
    for (char a : numStr.toCharArray()) {
      if (!Character.isDigit(a)) {
        return false;
      }
    }
    int value = Integer.parseInt(numStr);
    if (value > 255 || ("" + value).length() != numStr.length()) {
      return false;
    }
    return true;
  }

  private String validateIPv6Address(String ip) {
    if (ip.endsWith(":")) {
      return NEITHER;
    }
    String[] strs = ip.split(":");
    if (strs.length != 8) {
      return NEITHER;
    }
    for (String numStr : strs) {
      if (!validIPv6Number(numStr)) {
        return NEITHER;
      }
    }
    return IPV6;
  }

  private boolean validIPv6Number(String numStr) {
    if (numStr.length() > 4 || numStr.length() == 0) {
      return false;
    }

    for (char a : numStr.toCharArray()) {
      if (Character.isDigit(a)) {
        continue;
      }
      if (a >= 'A' && a <= 'F') {
        continue;
      }
      return false;
    }
    return true;
  }
}
