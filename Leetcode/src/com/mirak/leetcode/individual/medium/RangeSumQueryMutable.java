package com.mirak.leetcode.individual.medium;

public class RangeSumQueryMutable {

  class NumArray {

    private int[] tree;
    private int[] nums;

    public NumArray(int[] nums) {
      this.nums = nums.clone();
      tree = new int[4 * nums.length];
      build(1, 0, nums.length - 1);
    }

    public void update(int i, int val) {
      update(1, 0, nums.length - 1, i, val);
    }

    public int sumRange(int i, int j) {
      return query(1, 0, nums.length - 1, i, j);
    }

    private void build(int vertex, int l, int r) {
      if (l > r) {
        return;
      }
      if (l == r) {
        tree[vertex] = nums[l];
        return;
      }
      int mid = (l + r) / 2;
      build(2 * vertex, l, mid);
      build(2 * vertex + 1, mid + 1, r);
      tree[vertex] = tree[2 * vertex] + tree[2 * vertex + 1];
    }

    private int query(int vertex, int l, int r, int i, int j) {
      if (l >= i && r <= j) {
        return tree[vertex];
      }
      int mid = (l + r) / 2;
      if (j <= mid) {
        return query(2 * vertex, l, mid, i, j);
      }
      if (i > mid) {
        return query(2 * vertex + 1, mid + 1, r, i, j);
      }
      return query(2 * vertex, l, mid, i, j) + query(2 * vertex + 1, mid + 1, r, i, j);
    }

    private void update(int vertex, int l, int r, int idx, int val) {
      if (l == r) {
        tree[vertex] = val;
        return;
      }
      int mid = (l + r) / 2;
      if (idx <= mid) {
        update(2 * vertex, l, mid, idx, val);
      } else {
        update(2 * vertex + 1, mid + 1, r, idx, val);
      }
      tree[vertex] = tree[2 * vertex] + tree[2 * vertex + 1];
    }
  }

}
