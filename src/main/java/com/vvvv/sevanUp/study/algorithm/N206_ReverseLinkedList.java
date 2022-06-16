package com.vvvv.sevanUp.study.algorithm;


import com.vvvv.sevanUp.study.algorithm.assist.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class N206_ReverseLinkedList {
    static ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        while (head != null && head.next !=null) {
            ListNode dnext = dummy.next;
            ListNode hnext = head.next;
            dummy.next = hnext;
            head.next = hnext.next;
            hnext.next = dnext;
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
        System.out.println("reverseList(listNode) = " + reverseList(listNode));
    }
}
