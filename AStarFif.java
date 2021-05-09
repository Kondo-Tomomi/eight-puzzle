import java.util.*;

public class AStarFif{
    public static ArrayList<NPCFif> OPEN = new ArrayList<NPCFif>();
    public static ArrayList<NPCFif> CLOSED = new ArrayList<NPCFif>();
    public static ArrayList<NPCFif> CHILD = new ArrayList<NPCFif>();
        
    static int cost=0;
    static long start_time;
    static long end_time;

    public static boolean isOPENEmpty(){
        if (OPEN == null || OPEN.size() == 0) return true;
        else return false;
    }

    public static void Setting(){
        NPCFif root = new NPCFif();
        root.FirstNPC();
        start_time = System.currentTimeMillis();
        OPEN.add(root);
    }

    public static NPCFif minC(){   //OPENリストの中で一番小さいcを持つNPCを返す(Step2)
        NPCFif minNPC = OPEN.get(0);
        for(int i=1;i<OPEN.size();i++){
            if(minNPC.c>OPEN.get(i).c) minNPC=OPEN.get(i);
        }
        return minNPC;
    }

    public static void nDeploy(NPCFif x){ // 与えられたNPCのnを展開、子供の集合生成、nをCLOSEDへ, Step3
        x.Deployment();
        OPEN.remove(x);
        CLOSED.add(x);
    }

    public static void SameChild(NPCFif x){   //step4
        for(int i=0;i<x.myChild.size();i++){
            int check = 1;
            for(int j=0;j<OPEN.size();j++){
                if(Puzzle.isPuzzleSame(x.myChild.get(i).n, OPEN.get(j).n)){
                    if(x.myChild.get(i).c >= OPEN.get(j).c) check=0;
                    else { //System.out.println("x.myChild.get("+i+").n は既にOPENにあるものよりcが小さいです");
                        OPEN.remove(OPEN.get(j));
                        //System.out.println("古いものをOPENリストから削除   OPEN.size() : "+OPEN.size());
                        check=1; }
                }
            }
            for(int j=0;j<CLOSED.size();j++){
                if(Puzzle.isPuzzleSame(x.myChild.get(i).n, CLOSED.get(j).n)){
                    if(x.myChild.get(i).c >= CLOSED.get(j).c) check=0;
                    else { //System.out.println("x.myChild.get("+i+").n は既にCLOSEDにあるものよりcが小さいです");
                        CLOSED.remove(CLOSED.get(j));
                        //System.out.println("古いものをCLOSEDリストから削除   CLOSED.size() : "+ CLOSED.size());
                        check=1; }
                }
            }
            if(check==1){
                OPEN.add(x.myChild.get(i));
                //System.out.println("x.myChild.get("+i+").n was added to OPEN   OPEN.size() : "+ OPEN.size());
            }
        }
    }

    public static void printEfficiency(NPCFif ans){
        System.out.println();
        System.out.println("cost: "+cost);
        System.out.println("time: "+ (double)(end_time - start_time)/1000 +" [s]");
        System.out.println("ans.g: "+ans.g);
    }

    public static void main(String[] args){
        //step1
        Setting();

        while(true){
            //step2
            if(isOPENEmpty()){
                end_time = System.currentTimeMillis();
                System.out.println("Searching failed...");
                break;
            }
            NPCFif select = minC();
            cost++;
            if(select.isGoal()){
                end_time = System.currentTimeMillis();
                System.out.println("Searching succeed!!");
                //select.NPrintBoard();
                printEfficiency(select);
                break;
            }
            //step3
            nDeploy(select);

            //step4
            SameChild(select);
        }
    }
}