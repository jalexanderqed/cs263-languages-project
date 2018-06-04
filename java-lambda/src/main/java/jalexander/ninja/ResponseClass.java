package jalexander.ninja;

public class ResponseClass {
    public int outputInt;
    public double time1;
    public double time2;
    public long longTime1;
    public long longTime2;
    public double outputDouble;
    public String outputString;
    public String errorMessage;

    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("outputInt: " + outputInt + "\n");
        res.append("time1: " + time1 + "\n");
        res.append("time2: " + time2 + "\n");
        res.append("longTime1: " + longTime1 + "\n");
        res.append("longTime2: " + longTime2 + "\n");
        res.append("outputDouble: " + outputDouble + "\n");
        res.append("outputString: " + outputString + "\n");

        return res.toString();
    }

    public String csvString(){
        return outputInt + "," + time1 + "," + time2 + "," + longTime1 + "," + longTime2 + "," + outputDouble + "," + outputString + "," + errorMessage;
    }
}