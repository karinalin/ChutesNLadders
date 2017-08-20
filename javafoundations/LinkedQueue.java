//********************************************************************
//  LinkedQueue.java       Java Foundations
//
//  Represents a linked implementation of a queue.
//********************************************************************

package javafoundations;

import javafoundations.exceptions.*;

public class LinkedQueue<T> implements Queue<T> {
 private int count;
 private LinearNode<T> front, rear;

 //-----------------------------------------------------------------
 //  Creates an empty queue.
 //-----------------------------------------------------------------
 public LinkedQueue() {
  count = 0;
  front = rear = null;
 }

 //-----------------------------------------------------------------
 //  Adds the specified element to the rear of this queue.
 //-----------------------------------------------------------------
 public void enqueue (T el) {
   //create a LinearNote out of the input element
  LinearNode<T> node = new LinearNode<T>(el);

  if (count == 0)
   front = node;
  else
   rear.setNext(node);

  rear = node;
  count++;
 }

 public T dequeue () throws EmptyCollectionException {
   if (count == 0) {
     throw new EmptyCollectionException("Dequeue failed. Empty :(");
   }
// an implicit else: will never happen if the exception is thrown!
     LinearNode<T> out = front;
     front = front.getNext(); // will this work if there is only one element? yes, it points to "null"
     count--;
     return out.getElement();
 
 }

 public T first () throws EmptyCollectionException {
   if (count == 0) {
     throw new EmptyCollectionException("Dequeue failed. Empty :(");
   }

     return front.getElement();
 }
  public LinearNode<T> firstNode () throws EmptyCollectionException {
   if (count == 0) {
     throw new EmptyCollectionException("Dequeue failed. Empty :(");
   }

     return front;
 }
 
 
 public boolean isEmpty() {
   return (count == 0);
 }
 
 public int size() {
  return count;
 }
 
 public String toString() {
String s = "";
LinearNode current = front;
int counter = 0;

while (counter < size()-1) {
  
    s += current.getElement() + " + ";
    current = current.getNext();
    counter ++;
}
if (counter == size()-1) {
  s += current.getElement() ;
}


return s;
 }
 
 public static void main(String[] args) {
  LinkedQueue<Integer> test = new LinkedQueue<Integer>();
  test.enqueue(4);
  test.enqueue(5);
  test.enqueue(6);
  test.enqueue(7);
  System.out.println("the queue: \n" + test);
  System.out.println("removing: " + test.dequeue());
  System.out.println("removing: " + test.dequeue());
  System.out.println("removing: " + test.dequeue());
  System.out.println("removing: " + test.dequeue());
 }
 
}
