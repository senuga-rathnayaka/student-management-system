public class Module {
    private String moduleName;
    private double moduleMark;
    public Module(String moduleName,double moduleMark){
        this.moduleName=moduleName;
        this.moduleMark=moduleMark;
    }
    public String getModuleName(){
        return moduleName;
    }
    public double getModuleMark(){
        return moduleMark;
    }
}
