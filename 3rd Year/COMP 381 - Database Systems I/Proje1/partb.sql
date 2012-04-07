1)Select  ACCOUNT.AccountID
from customer as c 
Left JOIN ACCOUNT ON ACCOUNT.` AccCustomerID` = c.CustomerID
Where c.CName = "Sezgi" and c.CLname = "Sensoz"

2)Select  *
from customer as c
Where c.Statu = "GOLD"

3) Select c.Cname, c.LName, i.ItemType, i.Brand, i.Model
from customer as c, ACCOUNT as a, BILLING as b, ORDERED as o, items as i
where c.CustomerID = a.AccCustomerID AND
      a.AccountId = b.Account AND
	  b.OrderID = o.OrderID AND
	  o.ItemID = i.ItemID AND
	  c.Cname = "Bill" 
	  
4)Select CName, CLName, COUNT(Account.AccountID) 
from customer as c
Left join Account on  ACCOUNT.` AccCustomerID` = c.CustomerID


5) Select CName,CLname,SUM(o.Amount),SUM(o.Tax)
from  customer as c, ORDERED as o, BILLING as b, account as a
  where c.CustomerID = a.` AccCustomerID` AND
  a.AccountID = b.Account AND
  b.OrderID = o.OrderID
  
  
6)Update customer
SET Statu = "Platin"
where CustomerID in 
(Select AccCustomerID from Account where accountID in 
(select account from BILLING where orderID in 
(select orderID from ORDERED where SUM(amount) > 1000)))



8)Create view RECENT_SHOPPING
Select CName, CLname, ItemType, Brand, Model
from customer, ACCOUNT, BILLING, ORDERED, items
Where CustomerID = AccCustomerID AND
      AccountID = Acoount AND
	  BILLING.OrderID = ORDERED.OrderID AND
	  Item = ItemID AND
	  BillDate = 1.1. 2005
	  
 