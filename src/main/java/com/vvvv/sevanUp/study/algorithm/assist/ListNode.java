package com.vvvv.sevanUp.study.algorithm.assist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public String out(ListNode v) {
        String o = "";
        if (v.next != null) {
            o += v.val + "," + out(v.next);
        } else {
            o += v.val;
        }
        return o;
    }

    @Override
    public String toString() {
        return "[" + out(this) + "]";
    }
}
