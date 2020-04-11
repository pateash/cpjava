import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;


class Main
{
    public static FastIO io;

    //    if true passed,
    //    it means that is LOCAL, so we read/print from/on by FileWriter output.txt, otherwise print on console(for submitting)
    public static void main(String[] args)
    {

        if(args.length!=0 && Boolean.parseBoolean(args[0])){
            io =new FastIO(true);
        }else{
            //if not passed then it is not local so print on console
            io =new FastIO(false);
        }
//      int t=io.readInt();
        String st;
        while((st=io.readLine())!=null){
            io.println(solve(Integer.parseInt(st)));
            ArrayList<Integer> dp=new ArrayList<>(Integer.parseInt(st)+1);

            for (int i=0;i<dp.size();i++) dp.set(i,-1);
            io.println(solve(Integer.parseInt(st)));

        }

        //we have to do flush manually after doing everything
        io.flush();
    }

    private static int solve(int number) {
        int ans=0;
        while(number/2+number/3+number/4>number){
            ans=solve(number/2)+solve(number/3)+solve(number/4);
        }
        return ans;
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
