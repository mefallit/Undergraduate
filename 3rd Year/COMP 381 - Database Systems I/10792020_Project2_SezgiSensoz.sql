1-a) Select Employee.name
   From Employee,Employee_Manages_Department,Department_Controls_Project,Project
   Where Employee.id=Employee_Manages_Department.e_id and
         Employee_Manages_Department.d_id =Department_Controls_Project.d_id and
	     Department_Controls_Project.p_id = Project.id and
	     Project.location="Seoul";


1-b)  Select Department.id, Department.name
    from Department, Employee_Works_For_Department, Employee, Employee_Has, Supervisor
	Where Department.id = Employee_Works_For_Department.d-id and
	      Employee_Works_For_Department.e_ssn = Employee.ssn  and
		  Employee.id = Employee_Has.e_id and
		  Employee_Has.s_id = Supervisor.id ad
		  Supervisor.name = "Choi Ji Woo";
		  
1-c)	Select Employee.name
    from Employee
	where Employee.type ="Full" and
	      Employee.name IN (Select Employee.name
		                    From Employee, Employee_Works_On_Project, Project
							Where Employee.id = Employee_Works_On_Project.e_id and
							      Employee_Works_On_Project.p_id = Project.id  and
								  Project.name = "ProjectX";)
	   OR Employee.name IN  (Select Employee.name
	                        From Employee, Employee_Dependents_Of, Dependent
							Where Employee.id = Employee_Dependents_Of.e_id and
							      Employee_Dependents_Of.dt_id = Dependent.id and
								  Dependent.relationship = "cousin" and
								  Dependent.name = "Hyun Bin");

								  
1-d)  Select Dependent.name
    from Dependent
    Where Dependent.birth_date > 1970.01.01 and
	      Dependent.name IN (Select Dependent.name 
		                    from Dependent, Employee_Dependents_Of, Employee, Employee_Works_For_Department, Department
						    Where Dependent.id = Employee_Dependents_Of.dt.id and
						         Employee_Dependents_Of.e_id = Employee.id and
								 Employee.ssn= Employee_Works_For_Department.e_ssn and
								 Employee_Works_For_Department.d-id = Department.id and
								 Department.name = "Finance";)
		OR Dependent.name IN (Select Dependent.name 
		                      from Dependent, Employee_Dependents_Of, Employee, Employee_Manages_Department, Department
						      Where Dependent.id = Employee_Dependents_Of.dt.id and
						         Employee_Dependents_Of.e_id = Employee.id and
								 Employee.id= Employee_Manages_Department.e_id and
								 Employee_Manages_Department.d_id = Department.id and
								 Department.name = "Marketind";);
	
1-e)  Select Supervisor.name 
      From Supervisor, Employee_Has, Employee
	  Where Supervisor.id = Employee_Has.s_id and
	        Employee_Has.e_id = Employee.id and 
			Employee.id in (Select Employee.id, Count(*)
	                                  from Employee, Employee_Works_On_Project, Project
									  Where Employee.id = Employee_Works_On_Project.e_id and 
									        Employee_Works_On_Project.p_id = Project.id 
                                      Group By Employee.id
									  Having Project.hours > 2 or 
									         Project.location= "Taejeon";);
1-f) Select Dependent.name
     from Dependent, Employee_Dependents_Of, Employee
     Where Dependent.relationship = "nephew" and
	       Dependent.id = Employee_Dependents_Of.dt_id and
		   Employee_Dependents_Of.e_id = Employee.id and
		   Employee.id in (Select Employee.id 
		                   from Employee, Employee_Works_For_Department, Department
						   Where Employee.ssn = Employee_Works_For_Department.e_ssn and
						         Employee_Works_For_Department.d_id = Department.id and
								 Department.name = "Finance" or Department.name="Marketing");
								 
1-g)Select Employee.name
    from Employee, Employee_Has, Supervisor, Employee_Works_On_Project, Project
	Where Project.id = Employee_Works_On_Project.p_id and
	      Employee_Works_On_Project.e_ssn = Employee.ssn and
		  Employee.id = Employee_Has.e_id and
		  Employee_Has.s_id = Supervisor.id and
		  Supervisor.name = "Jang Hyuk" and
		  Project.name = "ProjectY" and
		  Employee_Works_On_Project.hours > 3;
		  
		  
1-h) Select Dependent.name
     from Dependent, Employee_Dependents_Of, Employee 
     Where Dependent.id = Employee_Dependents_Of.dt_id and
           Employee_Dependents_Of.e_id = Employee.id and
		   Employee.e-type = "half" and
		   Dependent.relationship = "Mother" and
           Employee.id IN (Select Employee.id 
                           from Employee, Employee_Works_For_Department, Department
                           Where Employee.ssn = Employee_Works_For_Department.e_ssn and
                                 Employee_Works_For_Department.d_id = Department.id and
								 Department.name = "Finance")
		   or Employee.id Not IN (Select Employee.id 
		                          from Employee, Employee_Works_On_Project, Project
								  Where Employee.id = Employee_Works_On_Project.e_id and
								  Employee_Works_On_Project.p_id = Project.id and

									  
									  
1-i)Select Supervisor.title
    from Supervisor,Employee_Has,Employee
	where Supervisor.id = Employee_Has.s_id AND 
		  Employee_Has.e_id = Employee.id AND
		  Employee.id IN ( Select Employee.id
						   From Employee, Employee_Works_On_Project, Project
						   Where Employee.id = Employee_Works_On_Project.e_id and
								 Employee_Works_On_Project.p_id = Project.id and
								 Project.name = "ProjectX";)
	 AND Employee.id not IN (Select Employee.name
						From Employee, Employee_Dependents_Of, Dependent
						Where Employee.id = Employee_Dependents_Of.e_id and
							  Employee_Dependents_Of.dt_id = Dependent.id and
							  Dependent.relationship = "cousin");

1-j)Select Employee.name
  From Employee,Employee_Works_On_Project,Project,Employee_Dependents_Of,Dependent
  Where Employee.salary > 1000 AND
       Employee.id = Employee_Works_On_Project.e_id AND
       Employee_Works_On_Project.p_id=Project.id AND
       Project.name = "Project X" AND
       Employee.id = Employee_Dependents_Of.e_id AND
       Employee_Dependents_Of.dt_id = Dependent.id AND
       Dependent.name != "Ji Sung";							  

2) Create TRIGGER trg_dependent_updated
   AFTER INSERT ON Dependent
   FOR EACH ROW
   BEGIN
      DECLARE @DependentID INT
      IF NEW.relationship = null THEN
        SET @DependentID=New.ID
        UPDATE Dependent SET Dependent.relationship = "cousin" WHERE Dependent.id=@DependentID;
      END IF;
    END;

  
3) Create TRIGGER trg_employe_insert
AFTER INSERT ON Employee
FOR EACH ROW
BEGIN
select SUM(salary) from Employee;
END;































								  