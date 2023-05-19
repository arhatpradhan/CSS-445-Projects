#### CSS-445-Projects
A collection of projects and labs done in the class Data Structures and Algorithms 1. This repo mainly highlights the projects done since the labs were were shorter coding assingments based off of the projects.

## Project 1

You tell the audience your have a deck on N cards labeled 0 .. N-1. You ask the audience to choose any number between 1 and N-1. Whatever this number is, you will shuffle the deck until the top card is at that dept into the deck. Assuming you are capable of doing the inShuffle and the outShuffle, this trick wil be easy for you because you can place the top card of the deck into any position by performing a sequence of in and out shuffles. The exact sequence of in/out shuffles is determined as follows. Suppose you have a deck of 20 card and the audience wants you to shuffle the top card to the 13th position in the deck. This means that your [0]th card (top) will move to [13]th position ([13] array index) in the deck. Take the number 13 and convert it to binary: i.e. 13 ==> 1101. Better yet, store it as String: "1101". Now going from left to right across that bitString and if you land on a 1 do an inShuffle. If you land on a 0 do a outShuffle. When you are done, the top card will be at index [13] in the deck (array).

## Project 2

A Bag is an Abstract Data Type or ADT. It is a container similar to a Set concept in that it implements operations such as add, remove, contains and so on, but it has no restriction about duplicates. In this project we will write a class that represents a bag. Our implementation will however add the restriction of duplicates. No duplicates wll be allowed into the container we are writing. Thus our arrBag is a set. A second important property of our implementation will be that it uses Java's Generics (sometimes mis-referred to as Templating by those who have programmed in C++). Generics is a powerful technique that allows the programmer to write the definition of the class in such a way that the container is capable of storing many different types of objects. The programmer only writes one version of the code for the class. Many clients can use this definition to store whatever type they wish as long as the type being stored supports all the operations that the code exercises on the data.

Since our version of the Bag ADT will implement some set functionality it will be somewhat of a hybrid between Bag and Set. These set specific operations provide excellent examples to work out the syntax for passing Bags into the methods of other Bags and operating on the internals of one another.

## Project 3

Implement the following methods in your LinkedList.java file
Write them as conscisely and simply as you can

public int size() Write simplest loop you can to count the Nodes
public boolean empty() Must call size() NOT examine head and be written as 1 line return statement
public boolean contains( T key ) Must call search and be written as 1 line return statement
public Node<T> search( T key ) 1 simple loop return ref to 1st Node that contains key value
public void insertAtTail(T data) Walk list to caboose (last Node). new Node become the new caboose
public void insertInOrder(T data) Insert Node into proper alphabetic/lexical position in list
*** insertInOrder DO NOT cast to Comparable becuase we do .
public boolean remove(T key) Walk list until you find 1st Node with this key. Remove Node w/out breaking list
public boolean removeAtTail() Remove the caboose Node
public boolean removeAtFront() Remove the first Node of list w/out breaking list
public LinkedList<T> union( LinkedList<T> other )
public LinkedList<T> inter( LinkedList<T> other )
public LinkedList<T> diff( LinkedList<T> other )
public LinkedList<T> xor( LinkedList<T> other 
  
##Project 4
  
Write a Java program that takes 1 command line arg which is the input file name and finds all the subsets of the original set that sum to the specified target number. You program must print a report to stdout that is formatted like this:

27 98 87 76 32 46 35 24 13                           <-- this is a subset of the original set. It sums to 438
27 98 87 76 80 57 13                                 <-- these subset lines do not have to be in any particular order
27 98 87 76 80 46 24                                 <-- each one must however sum to 438
27 98 87 65 43 46 35 24 13                           <-- and the number of subset lines must match my solution's number exactly
... etc. for each subset found that sums to  438
## Project 5
 
## Project 6
  
Project #6 will simulate the Josephus permutation, a mathematical problem modeled after the famous story about a small band of zealots in the first century that withstood the Roman army for a long period of time but eventually realized they were going to lose. They made a suicide pact to die rather than surrender. One of the zealots was Josephus Flavius. Josephus himself did not actually prefer death to surrender so one of the zealots devised a suicide ritual that gave himself and his best friend a way to survive. Josephus suggested they all stand in circle and go around the circle killing every third (or n'th) man until everyone was gone. According to legend Josephus was a mathematician and made sure that himself and his friend stood in the two spots that would be the last to kill themselves. Then after everyone else had died, he and his friend surrendered to the Romans. Josephus went on to become a famous historian of that century writing "the Antiquities" and other famous works that have endured till today. The Josephus problem is the prediction of who will be the last Node remainimg given a circle of N nodes, starting at a given node, and deleting every k'th node in some direction CLOCKWISE or COUNTER_CLOCKWISE. Our program will not attempt an analytical solution. We will simulate the execution of the algorithm until only one node remains.
  
## Project 7
 
This problem is a more complex version of the Simple Swamp of Lab #7. Now you must find and print ALL paths from the origin to the edge of the swamp. You will have multiple input files of incresing complexity.
  
## Project 8
  
The classic Towers of Hanoi problem
Read the files in the directory beside this html file.
Do your own stack trace using the stack.txt and towers.txt files in this directory.
Fill in the moveTower(....) method in the Hanio.java tester file, based on the pseudo code given.
  
## Project 9
  
Solving the game of Boggle can be done elegantly with recursion and backtracking. Backtracking is a technique whereby an algorithm recognizes it is impossible or unnecessary to search any deeper into the search space from a given point. An example of this is finding your way through a maze. When you hit a dead end you retreat from that point and never take the same path to that point again. A fast and efficient solution to Boggle requires a heuristic that helps you recognize a dead end and save vast amounts of wasted words formed and wasted searches (and thus vast amounts of time)
