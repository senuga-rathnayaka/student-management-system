import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class studentManagementSystem {
    private static final int MAX_STUDENTS = 100;
    private static Student[] studentsArr = new Student[MAX_STUDENTS];
    private static int studentCount = 0;

    public static void checkAvailableSeats(){
        System.out.println("Available Seats: "+(MAX_STUDENTS-studentCount));
    }


    public static void registerStudent(Scanner scanner){
        String id="";
        String moduleName="";
        double moduleMark=0;
        boolean flag=false;
        while(true){
            flag=false;
            System.out.print("Enter student id: ");
            id=scanner.next();
            if(id.length()==8){
                for(int i=0;i<studentsArr.length;i++){
                    if(studentsArr[i]!=null){
                        Student student=studentsArr[i];
                        if(student.getStudentId().equals(id)){
                            System.out.println("That student is already exists.");
                            flag=true;
                            break;
                        }
                    }
                }
                if(!flag){
                    break;
                }
            }else{
                System.out.println("Enter a id with length of 8.");
            }
        }
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name=scanner.nextLine();
        String listOfNames[]=name.split(" ");
        for(int i=0;i<listOfNames.length;i++){ // Capitalixe the first letter
            listOfNames[i]=listOfNames[i].substring(0,1).toUpperCase()+listOfNames[i].substring(1);
        }
        String first=listOfNames.length>0?listOfNames[0].trim():"";
        String second=listOfNames.length>1?listOfNames[1].trim():"";

        name=((listOfNames.length>1)?first+" ":first+"")+second;

        Module modulesArr[]=new Module[3];
        for(int i=0;i<modulesArr.length;i++){
            //module name="" and module mark=0.0
            Module module=new Module(moduleName,moduleMark);
            modulesArr[i]=module;
        }
        Student student=new Student(name,id,modulesArr);
        studentsArr[studentCount++]=student;
        System.out.println("Student registered Successfully.");
    }

    public static void deleteStudent(Scanner scanner){
        int count=0;
        Student tempArr[]=new Student[MAX_STUDENTS];
        boolean flag=false;
        if(studentsArr[0]==null){
            System.out.println("No students registered yet.");
        }
        while(true){
            System.out.print("Enter student id: ");
            String id=scanner.next();
            if(id.length()==8){
                for(int i=0;i<studentsArr.length;i++){
                    if(studentsArr[i]!=null){
                        Student student=studentsArr[i];
                        if(student.getStudentId().equals(id)){
                            flag=true;
                            count=i;
                            break;
                        }
                    }
                }
            }else{
                System.out.println("Enter a id with length of 8.");
            }
            break;//Breaking the while loop
        }
        for(int i=0;i<count;i++){ //Copying the elements before the equals student id in to a temporary array
            tempArr[i]=studentsArr[i];
        }
        for(int j=count+1;j<tempArr.length;j++){ //Copying the elements after the equals student id in to a temporary array
            tempArr[j]=studentsArr[j];
        }
        studentsArr=tempArr;
        if(!flag){
            System.out.println("No such student");
            return;
        }
        System.out.println("Student deleted successfully.");
    }

    public static void storeDetailsToFile(){
        try {
            FileWriter textFile=new FileWriter("text.txt");
            for(int i=0;i<studentsArr.length;i++){
                if(studentsArr[i]!=null){ //checking student array i place is an empty
                    Student student=studentsArr[i];
                    Module[]modules=student.getModules();
                    textFile.write(student.getStudentId()+","+student.getStudentName());
                    for(int obj=0;obj<3;obj++){
                        if(modules[obj]!=null){
                            textFile.write(","+modules[obj].getModuleName()+","+modules[obj].getModuleMark());
                        }
                    }
                    textFile.write("\n");
                }
            }
            textFile.close();
            System.out.println("Student details successfully stored.");
        } catch (IOException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
    public static void loadStudentDetailsFromFile(){
        int count=0;
        String name="";
        try {
            File file =new File("text.txt");
            Scanner file_reader=new Scanner(file);
            while(file_reader.hasNextLine()){ 
                String text=file_reader.nextLine(); 
                String[] listOfDetails=text.split(","); //remove commas and seperate each word
                String id=listOfDetails[0].trim();
                name=listOfDetails[1];
                Module modules[]=new Module[3];
                for(int num=0;num<modules.length;num++){
                    String moduleName=listOfDetails[2+num*2].trim();//removing white spaces
                    double moduleMark=Double.parseDouble(listOfDetails[3+num*2].trim());
                    modules[num]=new Module(moduleName,moduleMark);

                }
                Student student=new Student(name,id,modules);
                studentsArr[count++]=student;
            }
            studentCount=count;
            file_reader.close();
            System.out.println("Successfully lorded details");
            
        }catch (Exception e) {
            System.out.println("File not found");
        }
    }


        public static void findStudent(Scanner scanner){
            String id;
            boolean flag=false;
            if(studentsArr[0]==null){
                System.out.println("No students registered.");
                return;
            }
            while(true){ //while student id length equals to 8
                System.out.print("Enter your ID: ");
                id=scanner.next();
                if(id.length()==8){
                    for(int i=0;i<studentsArr.length;i++){
                        if(studentsArr[i]!=null){
                            Student student=studentsArr[i];
                            if(student.getStudentId().equals(id)){// if entered it equals to student id
                                flag=true;
                                System.out.println("Student Name: "+student.getStudentName());
                                Module modules[]=student.getModules();
                                for(int y=0;y<modules.length;y++){
                                    System.out.println("Module Name: "+modules[y].getModuleName());
                                    System.out.println(modules[y].getModuleName()+" Mark: "+modules[y].getModuleMark());
                                }
                            }
                        }
                    }
                    if(!flag){
                        System.out.println("No Such Student.");
                        return;
                    }else{
                        break;
                    }
                }else{
                    System.out.println("Enter a id with length of 8.");
                }   
            }
        }

        public static void viewTheStudentWithNames(){
            String listOfNames[]=new String[studentCount];
            for(int i=0;i<studentsArr.length;i++){
                if(studentsArr[i]!=null){
                    Student student=studentsArr[i];
                    listOfNames[i]=student.getStudentName();
                }
            }
            //Sorting Process
            for(int i=0;i<listOfNames.length;i++){
                for(int y=i;y<listOfNames.length;y++){
                    if(listOfNames[i].compareTo(listOfNames[y])>0){
                        String temp=listOfNames[i];
                        listOfNames[i]=listOfNames[y];
                        listOfNames[y]=temp;
    
                    }    
                }
            }
            System.out.print("Names in alphabetic order: ");
            for(int i=0;i<listOfNames.length;i++){
                System.out.print(listOfNames[i]+", ");
            }
            System.out.println("\b\b ");
        }        

        public static void addStudentWithMarks(Scanner scanner){
            boolean flag=false;
            if(studentsArr[0]==null){
                System.out.println("No students are added yet.");
                return;
            }
            scanner.nextLine();
            System.out.print("Enter student name: ");
            String name=scanner.nextLine();
            String listOfNames[]=name.split(" ");// removes " " and sperate
            for(int i=0;i<listOfNames.length;i++){
                listOfNames[i]=listOfNames[i].substring(0,1).toUpperCase()+listOfNames[i].substring(1);//capitalize every letter in each word of the name
            }
            String first=listOfNames.length>0?listOfNames[0]:"";
            String second=listOfNames.length>1?listOfNames[1]:"";
            name=listOfNames.length<2?first+"":first+" "  +(second.isEmpty()?"":second.trim());
            for(int i=0;i<studentsArr.length;i++){//if the length of listof names greater than 1 its first and second name else name had first only
                if(studentsArr[i]!=null){
                    Student student=studentsArr[i];
                    if(student.getStudentName().equals(name)){ //when entered name and student name is equals
                        flag=true;
                        Module moduleArr[]=student.getModules();
                        for(int s=0;s<moduleArr.length;s++){
                            System.out.print("Enter module name: ");
                            String moduleName=scanner.next();
                            System.out.print("Enter module mark: ");
                            double moduleMark=scanner.nextDouble();
                            Module module=new Module(moduleName,moduleMark);
                            moduleArr[s]=module;
                        }
                        System.out.println("Student updated with marks successfully.");
                        break;

                    }
                }
            }
            if(!flag){
                System.out.println("No Such Student.");
            }

        }

    
    public static void summary(){
        boolean flag=false;
        int counter=0;
        System.out.println("Total Students Registered: "+studentCount);

        for(int i=0;i<studentsArr.length;i++){
            if(studentsArr[i]!=null){
                Student student=studentsArr[i];
                Module moduleArr[]=student.getModules();
                for(int u=0;u<moduleArr.length;u++){
                    if(moduleArr[i].getModuleMark()>40){ 
                        flag=true;// flag for handle the all the module marks greater than 40
                    }else{
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    counter++;
                }
            }
        }
        System.out.println("Total students get above 40 marks for 3 modules: "+counter);
    }
    

    public static void report(){
        double total=0;
        double average=0;
        String grade="";
        for(int i=0;i<studentsArr.length;i++){
                total=0;
            if(studentsArr[i]!=null){ //checking the array element is null or not
                Student student=studentsArr[i]; //Taking object to the variable
                Module moduleArr[]=student.getModules();
                System.out.println("Student ID: "+student.getStudentId());
                System.out.println("Student Name: "+student.getStudentName());
                for(Module module:moduleArr){
                    System.out.println("Module name: "+module.getModuleName());
                    System.out.println("Module Mark: "+module.getModuleMark());
                    total+=module.getModuleMark();
                }
                average=total/3;
                System.out.println("Total: "+total+"\t");
                System.out.println("Average: "+average+"\t");
                if(average>=80){
                    grade="Distinction";
                }else if(average>=70){
                    grade="Merit";
                }else if(average>=40){
                    grade="Pass";
                }else{
                    grade="Fail";
                }
                System.out.println("Grade: "+grade);
                System.out.println();
            }
        }
    }

    public static void main(String args[]){
        int choice=0;
        while(true){
            displayMenu();
            boolean flag=false;
            Scanner scanner=new Scanner(System.in); //Take the Scanner class as the scanner
            while(true){
                try{
                    System.out.print("Enter your choice: ");
                    choice=scanner.nextInt();
                    break;
                }catch(Exception e){
                    System.out.println("Invalid number");
                }
            }
            switch(choice){
                case 1:checkAvailableSeats();break;
                case 2:registerStudent(scanner); break;
                case 3:deleteStudent(scanner); break;
                case 4:findStudent(scanner); break;
                case 5:storeDetailsToFile(); break;
                case 6:loadStudentDetailsFromFile(); break;
                case 7:viewTheStudentWithNames(); break;
                case 8:addStudentWithMarks(scanner); break;
                case 9:summary(); break;
                case 10:report(); break;
                case 11:flag=true; break;
                default:System.out.println("Invalid input.");
            }
            if(flag){
                System.out.println("Exiting...");
                break;
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nMenu :");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student");
        System.out.println("3. Delete student");
        System.out.println("4. Find student");
        System.out.println("5. Store student details into a file"); // Serialization is not supported
        System.out.println("6. Load student details from the file to the system"); // Deserialization is not supported
        System.out.println("7. View the list of students based on their names");
        System.out.println("8. Add student with marks");
        System.out.println("9. Summary");
        System.out.println("10. Report");
        System.out.println("11. Exit");
    }


}