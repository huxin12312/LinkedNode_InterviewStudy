package LinkedNode;

import java.util.HashMap;
import java.util.Stack;

public class Main {

	private static class Node {
		int val;
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	private static void printNode(Node head) {
		while (head != null) {
			System.out.print(head.val + " ");
			head = head.next;
		}
		System.out.println();
	}

	private static int counterNodeLength(Node head) {
		int counter = 0;
		while (head != null) {
			counter++;
			head = head.next;
		}
		return counter;
	}

	/*
	 * create a current pointer create another temp to store the current node
	 * move the current to next of input node make temp's next equal to results
	 * that we need make result first node to current point from input head
	 * 
	 * 1 -> 2 -> 3 ->4 ->5 current -> 1 -> .... temp = current ->1 -> 2 .....
	 * current = 2->3..... temp ->1 ->null result ->1 -> null iteratively it
	 * again
	 */
	private static Node reverseNode_iteratively(Node head) {
		Node result = null;
		Node current = head;
		if (head == null || head.next == null)
			return result;
		while (head != null) {
			Node temp = current;
			current = current.next;
			temp.next = result;
			result = temp;
		}

		return result;
	}

	private static Node reverseNode_Recursive(Node head) {
		Node result = null;
		if (head == null || head.next == null)
			return result;
		result = reverseNode_Recursive(head.next);
		head.next.next = head;
		head.next = null;

		return result;
	}

	private static Node GetKthNode(Node head, int k) {
		Node result = head;
		if (head == null || k < 1)
			return result;
		Node counter = head;

		while (k > 1 || counter != null) {
			k++;
			counter = counter.next;
		}
		if (k > 1 || counter == null)
			return null;

		while (counter.next != null) {
			counter = counter.next;
			result = result.next;
		}
		return result;
	}

	private static int nodek = 0;

	private static Node GetKthNode_recursive(Node head, int k) {
		Node result = head;
		if (head == null || k < 1)
			return result;

		result = GetKthNode_recursive(head.next, k);
		if (nodek == k) {
			result = head;
			return result;
		}
		return head;
	}

	private static Node GetMiddleNode(Node head) {
		Node result = head;
		Node temp = head;

		while (temp.next != null) {
			result = result.next;
			temp = temp.next;
			if (temp.next != null)
				temp = temp.next;
		}
		return result;
	}

	private static void reverPrintNode(Node head) {
		Stack<Node> stack = new Stack<Node>();
		Node temp = head;
		while (temp != null) {
			stack.add(head);
			temp = temp.next;
		}
		while (!stack.isEmpty()) {
			System.out.println(stack.pop().val);
		}
	}

	private static void reverPrintNode_Recursive(Node head) {
		Node temp = head;
		if (head == null)
			return;
		reverPrintNode_Recursive(head.next);
		System.out.println(head.val);
	}

	private static Node mergeSortNode(Node first, Node Second) {
		if (first == null)
			return Second;
		if (Second == null)
			return first;
		Node temp = null;
		Node result = null;
		if (first.val < Second.val) {
			result = first;
			result.next = null;
		} else {
			result = Second;
			result.next = null;
		}
		temp = result;
		while (first != null && Second != null) {
			if (first.val <= Second.val) {
				temp.next = first;
				first = first.next;
				temp = temp.next;
				temp.next = null;

			} else {
				temp.next = Second;
				Second = Second.next;
				temp = temp.next;
				temp.next = null;
			}

			if (first == null) {
				temp.next = Second;
				break;
			}
			if (Second == null) {
				temp.next = first;
				break;
			}

		}
		return result;
	}

	private static Node mergeSortNode_Recursive(Node first, Node Second) {
		if (first == null)
			return Second;
		if (Second == null)
			return first;
		Node result = null;
		if (first.val < Second.val) {
			result = first;
			first = first.next;
			result.next = mergeSortNode_Recursive(first, Second);
		}
		if (Second.val < first.val) {
			result = Second;
			Second = Second.next;
			result.next = mergeSortNode_Recursive(first, Second);
		}
		return result;
	}

	private static boolean isCycle(Node head) {
		if (head == null || head.next == null)
			return false;
		Node first = head;
		Node Second = head;

		while (first.next != null || first.next.next != null) {
			first = first.next.next;
			Second = Second.next;

			if (first == Second)
				return true;
		}
		return false;
	}

	private static boolean isIntersect(Node first, Node Second) {
		if (first == null || Second == null)
			return false;
		while (first != null) {
			first = first.next;
		}
		while (Second != null) {
			Second = Second.next;
		}
		if (first == Second)
			return true;
		return false;
	}

	private static Node getFirstCollectedPoint(Node first, Node Second) {
		Node result = null;
		if (first == null || Second == null)
			return result;
		int first_Length = counterNodeLength(first);
		int Second_Length = counterNodeLength(Second);
		for (int i = 0; i <= (first_Length + Second_Length); i++) {
			if (first.next != null)
				first = first.next;
			else
				first = Second;
			if (Second.next != null)
				Second = Second.next;
			else
				Second = first;
		}
		return Second == first ? first : result;
	}

	private static Node getFirstNode_Cycle(Node head) {
		Node first = head;
		Node Second = head;
		if (head == null)
			return first;
		while (first.next != null || first.next.next != null) {
			first = first.next.next;
			Second = Second.next;
			if (first == Second)
				break;
		}
		Second = head;
		while (first != Second) {
			first = first.next;
			Second = Second.next;
		}
		return first;
	}

	private static Node getFirstNode_Cycle_Hashmap(Node head) {
		if (head == null)
			return head;
		HashMap<Node, Boolean> map = new HashMap<Node, Boolean>();
		while (head != null) {
			if (map.get(head) == true) {
				return head;
			} else {
				map.put(head, true);
				head = head.next;
			}
		}
		return head;
	}

	private static void DeleteNode(Node head, Node DeleteNode) {
		if (head == null || DeleteNode == null)
			return;
		Node temp = head;
		if (DeleteNode.next != null) {
			DeleteNode.val = DeleteNode.next.val;
			DeleteNode = DeleteNode.next;
		} else {
			if (head == DeleteNode)
				DeleteNode = null;
			else {
				while (temp.next != DeleteNode)
					temp = temp.next;
				temp.next = null;
			}
		}
	}

}