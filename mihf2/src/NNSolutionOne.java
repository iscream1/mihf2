import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Martin on 2017. 04. 14..
 */
public class NNSolutionOne {
    static List<List<Neuron>> Lis=new ArrayList<List<Neuron>>(); //rejtett r

    public static void main( final String[] args ) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String line;
        List<String> input = new ArrayList<>();

        try {

            while (!(line = br.readLine()).equals(""))
            {
                for (String x : line.split(","))
                {
                    input.add(x);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i=0;i<input.size();i++)
        {
            List x=new ArrayList<Neuron>();
            for(int j=0;j<Integer.parseInt(input.get(i));j++) x.add(new Neuron((i==0)?0:Lis.get(i-1).size()));
            Lis.add(x);
        }

        for(int i=0;i<Lis.size();i++)
        {
            List<Neuron> l=Lis.get(i);
            System.out.print(l.size());
            if(i!=Lis.size()-1) System.out.print(",");
            else System.out.print("\n");
        }

        for(int i=1;i<Lis.size();i++)
        {
            List<Neuron> l=Lis.get(i);
            for(Neuron n : l) System.out.println(n.toString());
        }
    }
}

class Neuron
{
    public List<Double> w=new ArrayList<>();
    public double b;

    public Neuron(int prevLevelCount)
    {
        //System.out.println(level);
        b=0.0;
        for(int i=0;i<prevLevelCount;i++)
            w.add(new Random().nextGaussian()*0.1);
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
