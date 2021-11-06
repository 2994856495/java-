package book_exercise.corporation_8;

/**
 * @author ASUS
 */
public class CorporationTest {
    public static void main(String[] args) {
        Employees[] employees=new Employees[3];
        employees[0]=new Employees("员工1",7.50,35);
        employees[1]=new Employees("员工2",8.2,47);
        employees[2]=new Employees("员工3",10,73);
        for(Employees e:employees){
            System.out.println(e.toString());
        }
    }
}
