interface QueueInterface {
	void push(int n);
	int pop() throws InterruptedException;
}
public class Queue implements QueueInterface{
	Item head;
	Item tail;
	int length=0;
	public Queue(){
		head=null;
		tail=null;
	}
	public synchronized void push(int n) {
		Item i = new Item(n,null);
		Item bak = head;
		if(head==null && tail==null){
			head=i;
			tail=i;
			notifyAll();
			length++;
		}
		else{
			if(bak.next==null){
			i.next=bak.next;
			bak.next=i;
			length++;
			}
			else{
				if(bak.next.value>i.value){
					i.next=bak.next;
					bak.next=i;
					length++;
				}
				else{
					bak.next=i;
				}
			}
		}
	}
	public synchronized int pop() throws InterruptedException {
		int firstElementOfQueue=0;
		if(head==null){
			wait();
		}
		else{
			firstElementOfQueue=head.value;
			head=head.next;
			length--;
		}
		
		return firstElementOfQueue;
	}
	public void printQueue() {
		String queue = "";
		Item element = head;
		for(int i=0; i<length; i++){
			queue=queue + element.value + " ";
			element=element.next;
		}
		System.out.println(queue);
	}
	public static void main(String[] args){
		Queue q = new Queue();
		q.push(5);
     	q.push(15);
     	q.printQueue();
		try {
			System.out.println(q.pop());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		q.printQueue();
		q.push(3);
		q.printQueue();
	}
}
class Item {
	int value;
	Item next;
	public Item(int value, Item i){
		this.value=value;
		this.next=i;
	}
}
