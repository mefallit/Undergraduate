import junit.framework.TestCase;


public class TestJUnitVendingMachine extends TestCase {

	public VendingMachine Vendor1;
	
	public static final double PEPSI_PRICE = 2.0;
	public static final double FANTA_PRICE = 1.5;
	public static final double KRISTALCOLA_PRICE = 0.5;
	
	
	protected void setUp() throws Exception {
		Vendor1 = new VendingMachine();
		Vendor1.items[1].itemno=1;
		Vendor1.items[1].price=PEPSI_PRICE;
		Vendor1.items[2].itemno=2;
		Vendor1.items[2].price=FANTA_PRICE;
		Vendor1.items[3].itemno=3;
		Vendor1.items[3].price=KRISTALCOLA_PRICE;
		
	}

	protected void tearDown() throws Exception {
		Vendor1 = null;
	}
	
	private void testConstructor() {
		assertNotNull("Vendor1 exists", 
				Vendor1);
		
		assertTrue("Vendor1 is a vending machine", 
				Vendor1 instanceof VendingMachine);
	}
	
	public void testItemPrice(){
		
	    assertEquals("PEPSI costs " + PEPSI_PRICE, 
	        PEPSI_PRICE, 
	        Vendor1.items[1].price);
	    assertEquals("FANTA costs " + FANTA_PRICE, 
		        FANTA_PRICE, 
		        Vendor1.items[2].price);
	    assertEquals("KRISTALCOLA costs " + KRISTALCOLA_PRICE, 
	    		KRISTALCOLA_PRICE, 
		        Vendor1.items[3].price);
	}
	
	public void testGetBalanceStart() {
		
	    assertEquals("Balance of Vendor1 is initially zero", 
	        0,
	        Vendor1.getBalance());
	}
	
	public void testGetBalance() {
		//insert 1 TL coin
	    int coins = 1;
	    Vendor1.insertCoin(coins);
	    assertEquals("Balance of Vendor1 is equal to " + coins, coins, Vendor1.getBalance());
	    //BREAK
	    //insert 2x 1 TL coins
	    coins = coins + 2;
	    Vendor1.insertCoin(coins);
	    assertEquals("Balance of Vendor1 is equal to " + coins, coins, Vendor1.getBalance());
	}




}
