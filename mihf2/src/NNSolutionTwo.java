/**
 * Created by Martin on 2017. 04. 14..
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martin on 2017. 04. 14..
 */
public class NNSolutionTwo {
    static List<List<Neuron2>> Lis=new ArrayList<>(); //rejtett r

    public static void main( final String[] args ) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line;
        List<List<String>> input = new ArrayList<>();

        try {

            while (!(line = br.readLine()).equals(""))
            {
                List<String> row=new ArrayList<>();
                for (String x : line.split(","))
                {
                    row.add(x);
                }
                input.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0;i<input.get(0).size();i++)
        {
            List x=new ArrayList<Neuron2>();
            for(int j=0;j<Integer.parseInt(input.get(0).get(i));j++) x.add(new Neuron2());
            Lis.add(x);
        }

        int rowCount=1;
        int testN;
        for (List<Neuron2> l:Lis)
        {
            for(Neuron2 n:l)
            {
                //n.w.add(Double.parseDouble(input.get(rowCount)));
                List<String> inplist=input.get(rowCount);
                for(int i=0;i<inplist.size()-1;i++) n.w.add(Double.parseDouble(inplist.get(i)));
                n.b=(Double.parseDouble(inplist.get(inplist.size()-1)));
                rowCount++;
            }
        }

        for(int i=0;i<Lis.size();i++)
        {
            List<Neuron2> l=Lis.get(i);
            System.out.print(l.size());
            if(i!=Lis.size()-1) System.out.print(",");
            else System.out.print("\n");
        }

        for(int i=0;i<Lis.size();i++)
        {
            List<Neuron2> l=Lis.get(i);
            for(Neuron2 n : l) System.out.println(n.toString());
        }
    }
}

class Neuron2
{
    public List<Double> w=new ArrayList<>();
    public double b;

    public Neuron2(){}

    public Neuron2(List<String> inp)
    {
        b=Double.parseDouble(inp.remove(inp.size()));
        for(String x : inp)
        {
            w.add(Double.parseDouble(x));
        }
    }

    public String toString()
    {
        String ret="";
        for(int i=0;i<w.size();i++) {
            ret+=w.get(i)+",";
        }
        ret+=b;
        return ret;
    }
}
