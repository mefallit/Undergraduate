//  @author Sezgi Sensoz
import java.util.ArrayList;
public class priorityQueue {
	
	ArrayList<nesne> liste = new ArrayList<nesne>();//the array list that we will add nesne into it
	
	/*
	 * yerlestir: it is for to locate "nesne" in to
	 * the array list. It will shift some items in the array list and
	 * put the given "nesne" into the given place which is "i".
	 */
	private void yerlestir(nesne n, ArrayList<nesne> a, int i){   
		for(int b=a.size(); i>b; b--) {//to reach every element in the array list from end to i'th place
			a.add(b+1, a.get(b));//to shift them
		}
		a.add(i,n);//add nesne to the given place
	}
	
	/*
	 * sirayaKoy= it consumes nesne, arraylist and an integer. Integer is a 
	 * point of where will we start to look from or we can say that from which location
	 * we will be compare the items in the array list with given nesne. 
	 */
	private void sirayaKoy(nesne n, ArrayList<nesne> a, int i) {
		if(i != a.size()){//if i is not equal to the size of the array list 
			if(n.id <= a.get(i).id && n.priority == a.get(i).priority) {
				/*
				 * if the given nesne's id is equal or smaller than
				 * the i'th item's id and if their priorities are equal 
				 */
				yerlestir(n,a,i);//put the item according to given i
			}
			else {
				if(n.id > a.get(i).id && n.priority == a.get(i).priority) {
					/*
					 * if the given nesne's id is bigger than
					 * the i'th item's id and if their priorities are equal 
					 */
					sirayaKoy(n, a, i+1);//make recursive to find bigger or equal i'th id 
				}
				else {
					if(n.priority > a.get(i).priority) {
						/*
						 * if nesne's priority is bigger than i'th element
						 * it means that our priority is biggest one. So we must
						 * add it according to i.
						 */
						yerlestir(n,a,i);
					}
					else {
						if(n.priority < a.get(i).priority) {
							/*
							 * if nesne's priority is smaller than i'th element's 
							 * priority we must continue for finding equal priority
							 */
							sirayaKoy(n, a, i+1);
						}
					}
				}
			}
		}
		else {
			/*
			 * if we looked all of the items in the array list and we can not 
			 * find place to add our nesne, it means that we must add it to the
			 * end of array list.
			 */
			a.add(n);
		}
	}
	public void ekle(nesne n) {//for adding nesne into array list it will use sirayaKoy
		sirayaKoy(n, liste, 0);
	}
	
	/*
	 * cikar=it will remove the first element of the array list and it will print the
	 * first element of the array list.
	 */
	public void cikar(){
		if(liste.size()!=0){//if there is an element in our list
			liste.remove(0);//to remove the first element of the list
			System.out.println("["+liste.get(0).id+", "+liste.get(0).priority+"]");
			//to print new first element of the list's id and priority
		}
	}
	
	public void bas() {//for printing the array list
		String empty="";//empty string
		for(int i=0; i<liste.size();i++) {//for reaching all elements in list
			//to add the id and priority to string 
			empty=empty+"["+liste.get(i).id+", "+liste.get(i).priority+"]"+" ";
		}
		System.out.println(empty);
	}
	
	public void findAndReplace(int i, int newPriority) {//for changing the priority of a nesne
		for(int a=0; a<liste.size(); a++) {//to reach the elements in the list
			if (liste.get(a).id==i) {//if the i'th id is equal to id that we are searching
				nesne b;//create a new nesne
				b=liste.get(a);//it is i'th element of the list
				b.priority=newPriority;//its priority is the given newPriority
				liste.remove(a);//remove the i'th element
				ekle(b);//and add the new nesne which has same id different priority called b				
			}
		}
	}
	
	public void size() {//to find the size of the array list
		System.out.println(liste.size());
	}
	public static void main(String[] args) {
		nesne a = new nesne (100,1);//for creating nesne 
		nesne b = new nesne (102,1);//for creating nesne
		nesne c = new nesne (103,1);//for creating nesne
		nesne d = new nesne (101,0);//for creating nesne
		nesne e = new nesne (110,2);//for creating nesne
		nesne f = new nesne (11,3);//for creating nesne
		nesne g = new nesne (13,3);//for creating nesne
		nesne h = new nesne (12,2);//for creating nesne
		nesne i = new nesne (111,1);//for creating nesne
		nesne t = new nesne (14,3);//for creating nesne
		nesne r = new nesne (10,3);//for creating nesne

		priorityQueue p = new priorityQueue();//for creating priorityQueue

		p.ekle(a);//add a to the queue
		p.ekle(b);//add b to the queue
		p.ekle(c);//add c to the queue
		p.ekle(d);//add d to the queue
		p.ekle(e);//add e to the queue
		p.ekle(f);//add f to the queue
		p.ekle(g);//add g to the queue
		p.ekle(h);//add h to the queue
		p.ekle(i);//add i to the queue
		p.ekle(t);//add t to the queue
		p.ekle(r);//add r to the queue
		
		System.out.println("Queue is: ");
		p.bas();
		System.out.println("");
		
		System.out.println("If we want to change the priority of a" +
				" nesne which's id is 11 priority 1 to 3");
		p.findAndReplace(11, 1);
		System.out.println("Our new queue is: ");
		p.bas();
		System.out.println("");
		
		System.out.println("If we want to remove the first element in the queue" +
				"and to learn who is the new first element in the list: ");
		p.cikar();
		System.out.println("");
		
		System.out.println("Our new queue is: ");
		p.bas();
		System.out.println("");
		
		System.out.println("Size of our queue is: ");
		p.size();
	}
}

class nesne extends priorityQueue {
	int id;//id of nesne
	int priority;//priority of nesne
	public nesne(int id, int priority) {//constructor
		this.id=id;//to define id to main id 
		this.priority=priority;//to define priority to main priority
	}
}
