package book_exercise.corporation_8;

/**
 * @author ASUS
 */

public class Employees {
    private double basicSalary;
    private double workTime;
    private String name;

    static final int MINSALARY=8;
    static final int WORKTIME=40;
    static final int MAXWORKTIME=60;
    static final double OVERTIME=1.5;

    public Employees(String name, double basicSalary, double workTime) {
        this.basicSalary = basicSalary;
        this.workTime = workTime;
        this.name = name;
    }

    public String totalSalary(double workTime){
        if(basicSalary <MINSALARY){
            return "基本工资少于8元/h";
        }
        if(workTime<=WORKTIME){
            return String.valueOf((workTime* basicSalary));
        }
        else if(WORKTIME<workTime&&workTime<MAXWORKTIME){
            return String.valueOf((workTime* basicSalary + basicSalary *OVERTIME));
        }
        else{
            return "工作时间超过60h";
        }
    }

    @Override
    public String toString() {
        return this.name+" basic_salary=" + basicSalary +
                ", work_time=" + workTime +
                ",Total salary="+totalSalary(this.workTime);
    }

}
