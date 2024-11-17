public class Student {
    private String studentId;
    private String studentName;
    private Module modules[];
    public Student(String studentName,String studentId,Module modules[]){
        this.studentId=studentId;
        this.studentName=studentName;
        this.modules=modules;
    }
    public String getStudentName(){
        return studentName;
    }
    public String getStudentId(){
        return studentId;
    }
    public Module[] getModules(){
        return modules;
    }
}
