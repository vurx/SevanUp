package com.vvvv.sevanUp.study.easy;

import com.vvvv.studying.algorithm.easy.assist.ListNode;

public class N203_RemoveLinkedListElement {
    static ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0, head),prev = dummy;
        while (head != null) {
            if (head.val == val) {
                prev.next = head.next;
            } else {
                prev = head;
            }
            head = head.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode listNode =
                new ListNode(1,
                        new ListNode(2,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(5,
                                                        new ListNode(6)
                                        )
                                )
                        )
                )
        );
        System.out.println("removeElements(listNode, 3) = " + removeElements(listNode, 3));
    }
}
