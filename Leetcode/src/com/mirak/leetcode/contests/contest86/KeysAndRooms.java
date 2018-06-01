package com.mirak.leetcode.contests.contest86;

import java.util.List;

public class KeysAndRooms {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] vis = new boolean[rooms.size()];
    dfs(0, rooms, vis);
    boolean ret = true;
    for(int i = 0; i < rooms.size(); i++) {
      ret &= vis[i];
    }
    return ret;
  }

  private void dfs(int node, List<List<Integer>> rooms, boolean[] vis) {

    vis[node] =true;

    for(int v : rooms.get(node)) {
      if(!vis[v]) {
        dfs(v, rooms, vis);
      }
    }
  }
}