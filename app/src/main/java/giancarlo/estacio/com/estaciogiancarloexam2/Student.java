package giancarlo.estacio.com.estaciogiancarloexam2;

public class Student {
    //Long id;
    String fname, lname;
    Long average;

    public Student(String efname, String elname, Long average) {
        this.fname = efname;
        this.lname = elname;
        this.average = average;
    }

    public String geteFname() {
        return fname;
    }

    public String geteLname() {
        return lname;
    }

    public Long getAverage() {
        return average;
    }

}