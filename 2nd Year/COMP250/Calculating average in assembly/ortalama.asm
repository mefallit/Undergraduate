   @i      // i refers to some mem. location
   M=1
   @sum
   M=0     // sum=0
   (LOOP)
   @i
   D=M         // D=i
   @100
   D=D-A     // D=i-100
   @ortalama
   D;JGT    // If (i-100)>0 goto ortalama
   @i
   D=M       // D=i
   @sum
   M=D+M // sum=sum+i
   @i
   M=M+1     // i=i+1
   @LOOP
   0;JMP     // Goto LOOP
   (ortalama)   // average starts here
   @j  // j refers to mem. location 
   M=0  
   (LOOPAGAIN)
   @100
   D=A
   @sum
   M=M-D  //substraction 100 from sum result
   D=M
   @end // 
   D;JLT // if (sum-100)<0 go to end.
   @j
   M=M+1
   @LOOPAGAIN
   0;JMP //goto LOOPAGAIN
   (end)
   @end   
   0;JMP     // Infinite loop
   
   
