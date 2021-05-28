package t1;
import java.util.List;

public class TfIdf {

    public double tfCalculator(String[] tt, String tc) 
    {
        double count = 0;  //to count the overall occurrence of the term termToCheck
        for (String stt : tt) 
        {
            if (stt.equalsIgnoreCase(tc)) 
            {count++;}
        }
        return count / tt.length;
    }

    
    
    public double idfCalculator(List<String[]> all, String tc) 
    {
        double count = 0;
        for (String[] ss : all) 
        {
            for (String sf : ss) 
            {if (sf.equalsIgnoreCase(tc)) 
                {count++;
                    break;}
            }
        }
        return 1 + Math.log(all.size() / count);
    }
    
    
}
