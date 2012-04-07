public class VendingMachine {

	class Item{
		int itemno;
		double price;
	}
	int n = Integer.MAX_VALUE;
	Item items[] = new Item[n];
	double currentAmount = 0.0;

	void addItem(int itemno, double price){
		items[itemno].price = price;
		items[itemno].itemno =itemno;
	}
	void removeItem(int no){
		items[no] = null;

	}
	void insertCoin(double amount){
		this.currentAmount = this.currentAmount + amount;
		System.out.println(getBalance());

	}
	double getBalance(){
		return this.currentAmount;


	}
	boolean makePurchase(int itemno){

		if(items[itemno].price <= this.currentAmount && !(items[itemno] == null)){
			this.currentAmount = this.currentAmount - items[itemno].price;
			getBalance();
			return true;
		}
		else{

			return false;
		}
	}
	double returnChange(){
		double change = 0;
		if(this.currentAmount > 0){
			change = this.currentAmount;
		}
		return change;
	}


	public static void main(String[] args) {

	}
}
