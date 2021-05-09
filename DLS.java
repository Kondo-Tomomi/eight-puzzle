import java.util.*;

public class DLS {
    public ArrayList<NPD> OPEN = new ArrayList<NPD>();
    public ArrayList<NPD> CLOSED = new ArrayList<NPD>();
    public ArrayList<NPD> CHILD = new ArrayList<NPD>();

    public static NPD start;
    NPD ans;
    static int limit;
    static int cost=0;
    static long start_time;
    static long end_time;

    public static void setLimit(){
        limit++;
    }
    
    public DLS(){  //step1
        OPEN.add(start);
        cost++;
    }

    public boolean isOPENEmpty(){
        if (OPEN == null || OPEN.size() == 0) return true;
        else return false;
    }

    public NPD selectNPD(){  //Step2
        if(isOPENEmpty()) return null;
        NPD select = OPEN.get(0);
        for(int i=0; i<CLOSED.size(); i++){
            if(select.g > limit){
                OPEN.remove(select);
                return selectNPD();
            }
            if(EightPuzzle.isPuzzleSame(select.n, CLOSED.get(i).n)){
                OPEN.remove(select);
                return selectNPD();
            }
        }
        return select;
    }

    public boolean nDeploy(NPD x){ // 与えられたNPのnを展開、子供の集合生成、nをCLOSEDへ, Step3
        if(x.g<limit) x.Deployment();
        OPEN.remove(x);
        for(int i=0; i<x.myChild.size(); i++){
            cost++;
            if(x.myChild.get(i).isGoal()){
                ans = x.myChild.get(i);
                return true;
            }
        }
        CLOSED.add(x);
        return false;
    }

    public void makeChild(NPD x){   //step4
        for(int i=0; i<x.myChild.size(); i++){
            if(!(CLOSED.contains(x.myChild.get(i)))) OPEN.add(x.myChild.get(i));
        }
    }

    public static void printEfficiency(NPD ans){
        System.out.println();
        System.out.println("cost: "+cost);
        System.out.println("time: "+ (double)(end_time - start_time)/1000 +" [s]");
        System.out.println("ans.d: "+(ans.g+1));
    }


    public static void main(String[] args){
        DLS d = new DLS();
        //step1
        if(d.OPEN.get(0).isGoal()){
            end_time = System.currentTimeMillis();
            System.out.println("Searching succeed!!");
            //d.OPEN.get(0).NPrintBoard();
            DLS.printEfficiency(d.OPEN.get(0));
            System.exit(0);
        }

        while(true){
            //step2
            NPD select = new NPD();
            select = d.selectNPD();
            if(select==null){
                //System.out.println("Searching failed...  limit:"+limit);
                break;
            }
            //step3
            if(d.nDeploy(select)){
                end_time = System.currentTimeMillis();
                System.out.println("Searching succeed!!");
                //d.ans.NPrintBoard();
                DLS.printEfficiency(select);
                System.exit(0);
            }
            //step4
            d.makeChild(select);
        }
    }
}