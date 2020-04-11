import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


class Main
{
    public static FastIO io;

    //    if true passed,
    //    it means that is LOCAL, so we read/print from/on by FileWriter output.txt, otherwise print on console(for submitting)
    public static void main(String[] args)
    {

        io = getIO(args);
        int t=io.readInt();
        while(t--!=0){
           int n = io.readInt();
           solve(n);
        }

        //we have to do flush manually after doing everything
        io.flush();
    }

    private static FastIO getIO(String[] args) {
        FastIO result;
        if (args.length != 0 && Boolean.parseBoolean(args[0])) {
            result = new FastIO(true);
        } else {
            //if not passed then it is not local so print on console
            result = new FastIO(false);
        }
        return result;
    }

    private static void solve(int number) {
        List<List<Long>> ans = new ArrayList<>();

        List<BigInteger> lcmList= new ArrayList<>();


        for(long i=1;i<=number;i++){
             checkAndAdd(ans,i,lcmList);
        }

        io.println(ans.size());
        for(List<Long> l: ans){
            io.print(l.size());
            for(int i=0;i<l.size();i++){
                io.print(" "+l.get(i));
//                if(i!=l.size()-1)
//                    io.print(" ");
            }
            io.nl();
        }

    }

    private static void checkAndAdd(List<List<Long>> ans, long  x, List<BigInteger> lcmList) {

        BigInteger X= BigInteger.valueOf(x);

        for(int i=0;i<ans.size();i++){
            if(gcd(lcmList.get(i),X).longValue()== 1L){
                ans.get(i).add(x);
                lcmList.set(i,lcm(lcmList.get(i),X));
                return;
            }
        }

        List<Long> newL = new ArrayList<>();
        newL.add(x);
        ans.add(newL);
        lcmList.add(BigInteger.valueOf(x));
    }

    private static BigInteger lcm(BigInteger a, BigInteger b) {
        BigInteger g = gcd(a,b);
        return a.multiply(b).divide(g);
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
            if(b.longValue()==0) return a;
            return gcd(b,a.mod(b));
    }


}

class FastIO<T>
{
    private BufferedReader br;
    private PrintWriter pr;
    private StringTokenizer st;

    public FastIO(boolean local) {
        if(local){
            System.out.println("Running in LOCAL MODE");
            System.out.println("------------------------");
            try {
                //this also works ->   br= new BufferedReader(new FileReader(System. getProperty("user.dir")+File.separator+"input.txt"));
                br= new BufferedReader(new FileReader("src/main/resources/in.txt"));
                pr=new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/out.txt")));
            } catch(IOException e) {
                e.printStackTrace();
                System.out.println("IO: can't read from file, for local=true");
                System.exit(1);
            }
        }
        else{
            br= new BufferedReader(new InputStreamReader(System.in));
            pr=new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        }
    }

    private String next() //give next element from same line
    {
        //if tokenizer is empty then read otherwise sent form tokenizer
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                //creating a tokenizer after reading a line
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    public int readInt()
    {
        return Integer.parseInt(next());
    }

    public long readLong()
    {
        return Long.parseLong(next());
    }

    public double readDouble()
    {
        return Double.parseDouble(next());
    }

    public String readLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
            return str;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public void print(T value){
        pr.print(value);
    }
    public void println(T value){
        pr.println(value);
    }
    public void nl(){
        pr.print("\n");
    }

    //we will do flush later manually after doing everything so we can write faster

    public void flush() {
        System.out.println("--------------------------\nDONE");
        pr.flush();
    }

}
