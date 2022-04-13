package com.vvvv.sevanUp.study.easy;

import java.util.Stack;

/**
 * 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "([)]"
 * 输出：false
 *
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class N20_ValidParentheses {

    public static boolean valid(String s) {
        while (s.contains("()") || s.contains("{}") || s.contains("[]")) {
            if (s.contains("()")) {
                s = s.replace("()", "");
            }
            if (s.contains("[]")) {
                s = s.replace("[]", "");
            }
            if (s.contains("{}")) {
                s = s.replace("{}", "");
            }
        }
        return s.length() == 0;
    }

    public static boolean valid1(String s) {
        Stack<String> parentheses = new Stack<>();
        parentheses.add(s);
        while (!parentheses.isEmpty()) {

        }

        return s.length() == 0;
    }


    public static void main(String[] args) {
        System.out.println(valid("[]{}{}()"));
    }
}
