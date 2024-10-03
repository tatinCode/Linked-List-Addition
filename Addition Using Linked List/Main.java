/*
*
*	This Java program adds two large integers represented as strings by converting them into doubly linked lists and then performing digit-by-digit 
*	addition, similar to how you would manually add numbers on paper. The result is stored and printed as another linked list.
*
*/
import java.util.Scanner;

//Node Class
class Node {
	int data;
	Node next;
	Node prev;

	// Constructor
	public Node(int data) {
		this.data = data;
		next = null;
		prev = null;
	}
}

// main function
public class Main {

	public static void main(String[] args) {
		SDList x, y, z;
		String a, b;
		Scanner input = new Scanner(System.in); // Create a Scanner object
		System.out.println("\n" + //
				"\n" + //
				"Enter 2 ints that you'd like to add");
		System.out.print("input A: ");
		a = input.nextLine();
		x = makeSDList(a); // convert first string to a linkaed list
		x.displayList(); // call function that displays list x
		System.out.print("input B: ");
		b = input.nextLine();
		y = makeSDList(b); // convert second string to a linked list
		y.displayList(); // call function that displays list z
		z = x.addLists(y); // add lists x & y and store result in list y
		System.out.print("result of A+B: ");
		z.displayList(); // call function that displays list z

		input.close();
	}

	public static SDList makeSDList(String s) {
		// Create a new SDList object
		SDList curr = new SDList();

		// Loop through the string 's' in reverse order
		for (int i = s.length() - 1; i >= 0; i--) {
			// Add each character to the front of the SDList
			curr.addFirst(s.charAt(i));
		}

		// Return the populated SDList
		return curr;
	}
}

class SDList {
	Node head = null;

	// adds the two string of numbers
	public SDList addLists(SDList c) {
		SDList temp = new SDList();
		int carry = 0;
		Node curr1 = head;
		Node curr2 = c.head;
		char digit;

		while (curr1.next != null) {
			curr1 = curr1.next;
		} // while the next node is not going to
			// be equal to null, it keep on looping
		while (curr2.next != null)
			curr2 = curr2.next;

		while (curr1 != null || curr2 != null) {
			int sum = 0;

			if (curr1 != null) {
				sum += curr1.data;
			}
			// adds the data of the nodes
			// to sum then adding the carry if there's any
			if (curr2 != null) {
				sum += curr2.data;
			}
			sum += carry;

			// converts sum % 10 into a char and adding the 0 as a char
			// to it to increase it's ASCII value to match its actual value
			digit = (char) ((sum % 10) + '0');

			carry = sum / 10;
			sum = sum % 10;
			temp.addFirst(digit);// adds digit to the front of the list for easy print

			if (curr1 != null) {
				curr1 = curr1.prev;
			} // if the list hasn't hit null yet
			if (curr2 != null) { // they go back a data
				curr2 = curr2.prev;
			}
		}

		// if carry is not equal to 0 at the end of the loop
		// it makes a node and insert it to the front
		if (carry != 0) {
			temp.addFirst((char) (carry + '0'));
		}

		return temp;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	public void displayList() {
		Node curr = head;
		while (curr != null) {
			System.out.print(curr.data); // prints the node being called
			curr = curr.next;
		}
		System.out.println();
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// ******************************************************************************************//
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void addFirst(char digits) {
		// if head is a special case where it's equal to null
		// it creates a new node
		if (head == null) {
			head = new Node(Character.getNumericValue(digits));
			return;
		}

		// if head doesn't have a special case then we create a temporary node called
		// newnode that
		// creates a new node that would hold the data that was asked to be inserted.
		// Then newnode
		// will hold the current data of head before we lose it then set the previous to
		// the newnode
		// which would be behind head.next after we point head to the temporary node
		// newnode
		Node newnode = new Node(Character.getNumericValue(digits));
		newnode.next = head;
		head.prev = newnode;
		head = newnode;

	}
}
