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
            for(int j=0;j<Integer.parseInt(input.get(0).get(i));j++) x.add(new Neuron2(i, j));
            Lis.add(x);
        }

        int rowCount=1;
        int testN;
        for (int i=1;i<Lis.size();i++)
        {
            List<Neuron2> l=Lis.get(i);
            for(Neuron2 n:l)
            {
                //n.w.add(Double.parseDouble(input.get(rowCount)));
                List<String> inplist=input.get(rowCount);
                for(int j=0;j<inplist.size()-1;j++) n.w.add(Double.parseDouble(inplist.get(j)));
                n.b=(Double.parseDouble(inplist.get(inplist.size()-1)));
                rowCount++;
            }
        }
        testN=Integer.parseInt(input.get(rowCount++).get(0));

        List<List<Double>> xLists=new ArrayList<>();
        List<Double> xList=new ArrayList<Double>();
        for(int i=0;i<testN;i++)
        {
            xList=new ArrayList<>();
            List<String> x=input.get(rowCount++);
            for(String s : x) xList.add(Double.parseDouble(s));
            xLists.add(xList);
        }
        /*xList.add(1.0);
        xList.add(0.0);*/

        System.out.println(testN);
        for(List<Double> xL:xLists)
        {
            List<Neuron2> nList=Lis.get(Lis.size()-1);
            for(int i=0;i<nList.size();i++)
            {
                Neuron2 n=nList.get(i);
                System.out.print(n.y(xL));
                if(i==nList.size()-1) System.out.print("\n");
                else System.out.print(",");
            }
        }


        /*for(int i=0;i<Lis.size();i++)
        {
            List<Neuron2> l=Lis.get(i);
            System.out.print(l.size());
            if(i!=Lis.size()-1) System.out.print(",");
            else System.out.print("\n");
        }

        for(int i=1;i<Lis.size();i++)
        {
            List<Neuron2> l=Lis.get(i);
            for(Neuron2 n : l) System.out.println(n.toString());
        }*/
    }

    static class Neuron2
    {
        public List<Double> w=new ArrayList<>();
        public double b;
        public int level;
        public int idx;

        public Neuron2(int level, int idx){this.level=level; this.idx=idx;}

        public String toString()
        {
            String ret="";
            for(int i=0;i<w.size();i++) {
                ret+=w.get(i)+",";
            }
            ret+=b+" "+level;
            return ret;
        }

        public double w(int j)
        {
            double ret=w.get(j);
            return ret;
        }

        public double x(List<Double> xList, int j)
        {
            double ret;
            if(level==0) ret=xList.get(idx);
            else ret=Lis.get(level-1).get(j).y(xList);
            //System.out.println(ret);
            return ret;
        }

        public double s(List<Double> xList)
        {
            double ret=b;
            //System.out.println(level);
            for(int j=0;j<Lis.get(level-1).size();j++)
            {
                ret+=w(j)*x(xList, j);
                //System.out.println(ret);
            }
            //System.out.println(ret);
            return ret;
        }

        public double f(double x)
        {
            double ret;
            if(level==Lis.size()-1) ret=x;
            //System.out.println((x>0)?x:0);
            else ret=(x>0)?x:0;
            return ret;
        }

        public double y(List<Double> xList)
        {
            double ret;
            if(level==0) ret=xList.get(idx);
            else ret=f(s(xList));
            return ret;
        }
    }
}
