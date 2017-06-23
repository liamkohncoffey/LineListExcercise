/* Assignment Two Liam Coffey 1206617*/
/*LineList.java*/
//Imports the needed Libraries
import java.io.File;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
class LineList{
    //internal node class
    private class node{
	public String data;
	public node next;
	public node(String x){
	    data = x;
	    next = null;
        }
    }
    //declearing the needed values
    private File FileS;
    private String Sm;
    private node head;
    private node big;
    private node small;
    private int count;
    // constructor method for the LineList
    public LineList(String SortMethod, String File){
        FileS = new File(File);
	Sm = SortMethod;
    }
    // this method scans the files and breaks it into lines and adds it to the unsorted linked list
    public void Scan(){
	if(FileS.exists()){
		try{
	    Scanner sc = new Scanner(FileS);
	    String S;
	    while(sc.hasNext()){
		S = sc.nextLine();
		add(S);
	    }
	}catch(Exception e){};}
	else{
	    System.out.println("File Not Found");
	}
    }
    //Setter method for the Count
    public void SetCount(){
	count++;
    }
    //Getter method for the Count
    public void getCount(){
	System.err.println("COUNT:"+count);
    }
    //Add method for the First unsorted list
    public void add(String X){
	node temp = new node(X);
	if(head == null){
	    head = temp;
	}
	else{
	    node curr = head;
	    while(curr.next != null){
		curr = curr.next;
	    }
	    curr.next = temp;
	    }
	}
    //Add method for the Big List for the Q sort
    public void addBig(String X){
	node temp = new node(X);
	if(big == null){
	    big = temp;
	}
	else{
	    node curr = big;
	    while(curr.next != null){
		curr = curr.next;
	    }
	    curr.next = temp;
	    //System.out.println(temp.data + " was added");
	}
    }
    //Add method for the Small list for the Q sort
     public void addSmall(String X){
	node temp = new node(X);
	if(small == null){
	    small = temp;
	}
	else{
	    node curr = small;
	    while(curr.next != null){
		curr = curr.next;
	    }
	    curr.next = temp;
	    // System.out.println(temp.data + " was added");
	}
    }
    //Print List method for the Sorted or unsorted list
    public void printList(){
	node curr = head;
	System.out.println(curr.data);
	    while(curr.next != null){
		System.out.println(curr.next.data);
		curr = curr.next;
	    }
    }
    //Checks a list to see if the value = head
    public boolean isHead(String Key, node X){
	if(X.data.equals(Key)){
	    return true;
	}
	else{
	    return false;
	}
    }
    //Finds the previous node of the value passed in any List 
    public node findPrevious(String Key, node X){
	node curr = X;
	while(curr != null){
	    if(curr.next.data.equals(Key)){
		return curr;
	    }else{
		curr = curr.next;
	    }
	}
	    return null;	
    }
    //Finds the current node of the value passed in any List
    public boolean findCurrent(String Key, node X){
	node curr = X;
	while(curr != null){
	    if(curr.data.equals(Key)){
		return true;
	    }else{
		curr = curr.next;
	    }
	}
	    return false;	
    }
    //Finds the next node of the value passed in any List
    public node findNext(String Key, node X){
	node curr = X;
	while(curr != null){
	    if(curr.data.equals(Key)){
		return curr.next;
	    }else{
		curr = curr.next;
	    }
	}
      return null;	
    }
    //The Sort method for both Q and I Lists 
     public node sort(node X){
      node tempList = null;
      //Loops through the passed List
      while(X != null){
	  //Checks to see if the internally created list has a head
	  if(tempList == null){
	      node temp = new node(X.data);
	      tempList = temp;
	  }
	    else{
		//Checks to see if the internally created list has a head.next
	     if(tempList.next == null){
	      node temp = new node(X.data);
	      tempList.next = temp;
	  }
	    else{
		//loops through the internally created list
          node curr = tempList;
	  node temp = new node(X.data);
	  while(curr.next != null){
	      //if its a double up do not add it
	       if(curr.data.compareTo(X.data) == 0){
		  SetCount();
		  break;
	       }else{
		  // if its not already in the list
		   if(findCurrent(X.data, tempList) == false){
		  //if its greater than the current value and its the head add it to the front
		  if(curr.data.compareTo(X.data) > 0 && isHead(curr.data, tempList)){
		  temp.next = tempList;
		  tempList = temp;
		  SetCount();
		  break;
	      }else{
	       //if its greater than the current and less than the next add it inbetween
	       if(curr.data.compareTo(X.data) < 0){
		   if(curr.next.data.compareTo(X.data) > 0){
		   node N = findNext(curr.data, tempList);
		   temp.next = N;
	           curr.next = temp;
		   SetCount();
		   break;
		   }
	       }
	      }
	       }
	       }
	       //loop to the next value in the internally created list
	       curr = curr.next;
	  
	  }
	  //if it has gone through the entire list and not being added add it to the end
	  if(findCurrent(X.data, tempList) == false){
	      curr.next = temp;
	  }
	    }
	    }
	  //loop to the next item in the unsorted list
	  X = X.next;
      }
      System.out.println("sort successful");
      return tempList;
     }
    // ISort method
    public void iSort(){
	//Creates a new node and adds it sorted
	node iList = sort(head);
	//makes head = the new sorted list
	head = iList;
    }
    public void qSort(){
	//sets the first value in head to be the pivot
	String pivot = head.data;
	//removes an item from the front of the list
	head = head.next;
	//loops through the unsorted list
	while(head != null){
	    //if its larger then the pivot add it to Big
	if(pivot.compareTo(head.data)>0){
	    addBig(head.data);
	}else{
	    //if its smaller than the pivot add it to small
	    addSmall(head.data);
	}
	head = head.next;
	}
	//sorts the big list
	node bigList = sort(big);
	//sorts the small list
	node smallList = sort(small);
	//adds pivot back on to Big
	addBig(pivot);
	node Combine = bigList;
	while(Combine.next != null){
	    Combine = Combine.next;
	}
	//adds the small list back on to the end of big
	Combine.next = smallList;
	//sets the new list to head
	head = Combine;
    }
}

