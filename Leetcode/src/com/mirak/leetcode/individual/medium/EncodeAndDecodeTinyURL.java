package com.mirak.leetcode.individual.medium;

import java.util.*;

public class EncodeAndDecodeTinyURL {

  class Codec {

    private String alphabet = "0123456789abcdefjhijklmnopqrstuvwxyzABCDEFJHIJKLMNOPQRSTUVWXYZ";
    private Random random;
    private final int MAXN = 10;
    private Map<String, String> encodedToDecoded;

    Codec() {
      random = new Random();
      encodedToDecoded = new HashMap<>();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
      String encoded = getRandomString();
      encodedToDecoded.put(encoded, longUrl);
      return encoded;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
      return encodedToDecoded.get(shortUrl);
    }

    private String getRandomString() {
      StringBuilder builder = new StringBuilder();
      for (int i = 0; i < MAXN; i++) {
        builder.append(alphabet.charAt(random.nextInt(alphabet.length())));
      }
      return builder.toString();
    }
  }
}



