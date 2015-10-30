import java.util.*;

public class GenLinkedList<T>
{
	private Node<T> head;
	private Node<T> tail;
	int size;

	public static class Node<T>
	{
		T data;
		Node<T> next, prev;

		Node(T d, Node<T> n, Node<T> p)
		{
			data = d;
			next = n;
			prev = p;
		}
	}

	public void addFront(T x)
	{
		if (size == 0)
		{
			head = new Node<T>(x, null, null);
			tail = head;
			size++;
		} else
		{
			head = new Node<T>(x, head, null);
			size++;
		}
	}

	public void addEnd(T x)
	{
		if (size == 0)
		{
			head = new Node<T>(x, null, null);
			tail = head;
			size++;
		} else
		{
			tail.next = new Node<T>(x, null, tail);
			tail = tail.next;
			size++;
		}
	}

	public void removeFront()
	{
		if (size > 0)
		{
			head = head.next;
			head.prev = null;
			size--;
		}
	}

	public void removeEnd()
	{
		if (size > 0)
		{
			tail = tail.prev;
			tail.next = null;
			size--;
		}
	}

	public void set(int pos, T newData)
	{
		Node<T> temp = head;

		if (pos < size)
		{
			for (int i = 0; i < pos; i++)
				temp = temp.next;
			temp.data = newData;
		} else
			throw new IndexOutOfBoundsException();
	}

	public T get(int pos)
	{
		Node<T> temp = head;

		if (pos < size)
		{
			for (int i = 0; i < pos; i++)
				temp = temp.next;
		}

		return temp.data;
	}

	public int find(T d)
	{
		Node<T> temp = head;

		for (int pos = 0; pos < size; pos++)
		{
			if (temp.data == d)
				return pos;

			temp = temp.next;
		}

		return -1;
	}

	public void swap(int p1, int p2)
	{
		Node<T> temp1, temp2;

		T tempD = null;
		temp1 = head;
		temp2 = head;

		if (p1 < 0 || p1 > size - 1 && p2 < 0 || p2 > size - 1)
			throw new IndexOutOfBoundsException();
		else
		{
			for (int i = 0; i < p1; i++)
				temp1 = temp1.next;

			for (int i = 0; i < p2; i++)
				temp2 = temp2.next;

			tempD = temp1.data;
			temp1.data = temp2.data;
			temp2.data = tempD;
		}
	}

	// Second swap function done with get and set
	public void swap2(int p1, int p2)
	{
		if (p1 < 0 || p1 > size - 1 && p2 < 0 || p2 > size - 1)
			throw new IndexOutOfBoundsException();
		else
		{
			T tempD;

			tempD = get(p1);
			set(p1, get(p2));
			set(p2, tempD);
		}
	}

	public void reverse()
	{
		if (size < 2)
			return;

		Node<T> p1 = null;
		Node<T> p2 = head;
		Node<T> p3 = head.next;

		while (p3 != null)
		{
			p2.next = p1;
			p2.prev = p3;
			p1 = p2;
			p2 = p3;
			p3 = p3.next;
		}

		p2.next = p1; // for last node

		Node<T> temp = head;
		head = tail;
		tail = temp;
	}

	public void erase(int pos, int num)
	{
		if (pos > size || (pos + num) > size)
			throw new IndexOutOfBoundsException();
		else
		{
			Node<T> temp1 = head, temp2;

			if (pos == 0)
			{
				for (int i = 0; i < pos; i++)
					temp1 = temp1.next;
				temp2 = temp1;
				for (int j = 0; j < num - 1; j++)
					temp2 = temp2.next;

				temp1.next = temp2;
				temp2.prev = temp1;
				removeFront();
			}

			if ((pos + num) == size)
			{
				for (int i = 0; i < pos; i++)
					temp1 = temp1.next;

				temp2 = temp1;
				for (int j = 0; j < num; j++)
					temp2 = temp2.next;

				temp1.prev.next = temp2;
				// temp2.prev = temp1;
				// removeEnd();
			}

			else
			{
				for (int i = 0; i < pos; i++)
					temp1 = temp1.next;

				temp2 = temp1;
				for (int j = 0; j < num; j++)
					temp2 = temp2.next;

				temp1.prev.next = temp2;
				temp2.prev = temp1;
			}

		}
	}

	@SuppressWarnings("unchecked")
	public void insertList(List<?> jList, int pos)
	{
		Node<T> temp = head;
		Node <T> temp2 = temp.next;
		GenLinkedList<T> list2 = new GenLinkedList<>();

		for (int i = 0; i < pos; i++)
			temp = temp.next;

		//Add elements from new GenLinkedList into main list
		if (jList.size() > size || pos > size || pos + jList.size() > size)
			throw new IndexOutOfBoundsException();
		else if (jList.isEmpty())
			throw new IllegalArgumentException("Empty List");
		else
		{
			for (int j = 0; j < jList.size(); j++)
			{
				list2.addEnd((T) jList.get(j));
			}
			// linking the temp node to list2
			temp.next = list2.head;
			list2.head.prev = temp;

			// linking the temp2 node to list2
			temp2.prev = list2.tail;
			list2.tail.next = temp2;

			size += jList.size();
		}

	}

	public String toString()
	{
		StringBuilder sb = new StringBuilder("[ ");

		Node<T> p = head;
		while (p != null)
		{
			sb.append(p.data + " ");
			p = p.next;
		}

		sb.append("]");
		return new String(sb);
	}

	public static void main(String[] args)
	{
		GenLinkedList<Integer> list = new GenLinkedList<Integer>();

		for (int i = 10; i > 0; i--)
			list.addFront(i);
		System.out.println("addFront");
		System.out.println(list);

		list.addEnd(11);
		System.out.println("addEnd");
		System.out.println(list);

		list.removeFront();
		System.out.println("removeFront");
		System.out.println(list);

		list.removeEnd();
		System.out.println("removeEnd");
		System.out.println(list);

		System.out.println("Set second number to 88");
		try
		{
			list.set(1, 88);
			System.out.println(list);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// list.get(1);
		System.out.println("Get second number");
		System.out.println(list.get(1));

		list.swap(0, 1);
		System.out.println("Swap");
		System.out.println(list);

		list.reverse();
		System.out.println("Reverse");
		System.out.println(list);

		list.erase(3, 3);
		System.out.println("Erase positions 3-4");
		System.out.println(list);
		
		System.out.println("Make new java list from 0 to 4");
		List<Integer> newList = new LinkedList<>();
		for (int i = 0; i<5; i++)
			newList.add(i);
		System.out.println(newList);
		
		System.out.println("Add java list into GenLinkedList at position 0");
		list.insertList(newList, 1);
		System.out.println(list);

	}

}
