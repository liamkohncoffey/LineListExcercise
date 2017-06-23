/* Assignment Two Liam Coffey 1206617*/
/*FileSort.java*/
import java.io.File;
class FileSort{

    public static void main(String[] args){
	//Checks to See if the user has entered a value
	if(args.length == 0){
            System.out.println("Please enter a Value");
            System.exit(0);
        }
	//If the entered value = I scans the file passed, breaks it into lines, sorts it alphebitically the prints it with the count
	if(args[0].equals("I") || args[0].equals("i")){
	    LineList L = new LineList(args[0], args[1]);
	    L.Scan();
	    L.iSort();
	    L.printList();
	    L.getCount();
	}
	//If the entered value = Q scans the file passed, breaks it into lines, Breaks it into groups larger or smaller than the first value in the list, sorts the two seperate lists alphabitically then combines the two lists then prints it with the count
	else if(args[0].equals("Q") || args[0].equals("q")){
	    LineList L = new LineList(args[0], args[1]);
	    L.Scan();
	    L.qSort();
	    L.printList();
	    L.getCount();
	    
	}
	// if the entered comand was not valid
	else{
	    System.out.println(args[0]+": is not a recognised comand");
	    System.out.println("Please enter eather Q for quicksort or I for InsertionSort");
	}	  
      }
}
