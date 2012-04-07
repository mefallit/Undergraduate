/* Sezgi Şensöz 
   10792020 */

// Decimal to Binary Assignment 

/* Pseudocode
result = ""
repeat {
   if number is even [if number is divisible by 2]
      then prepend "0" on result 
      else prepend "1" on result
   divide number by 2 (discard any remainder)
} until number is zero */

String result = "" ;

String DecimalToBinary (int n) {
  /* DecimalToBinary will convert number which is decimal mode to binary mode
  2 and 1 is our trivial case */
  if ((n/2) == 1) {
    result = "1" + (n%2) + result ;
  }
  if ((n/2) == (1/2)) {
    result = (n%2) + result;
  }
  else {
    result = (n%2) + result;
    n= n/2;
    DecimalToBinary(n);
  }
  return result;
}

void setup() {
  println("Decimal 1 is equal to in binary" + " " + DecimalToBinary(1));
  println("Decimal 2 is equal to in binary" + " " + DecimalToBinary(2));
  println("Decimal 5 is equal to in binary" + " " + DecimalToBinary(5));
  println("Decimal 20 is equal to in binary" + " " + DecimalToBinary(20));
  println("Decimal 100 is equal to in binary" + " " + DecimalToBinary(100));
}
   
  




