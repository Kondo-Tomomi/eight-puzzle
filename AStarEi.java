import java.util.*;

public class AStarEi {
    public static ArrayList<NPCEi> OPEN = new ArrayList<NPCEi>();
    public static ArrayList<NPCEi> CLOSED = new ArrayList<NPCEi>();
    public static ArrayList<NPCEi> CHILD = new ArrayList<NPCEi>();

    static int cost=0;
    static long start_time;
    static long end_time;

    public static void Setting(){
        NPCEi root = new NPCEi();
        root.FirstNPC();
        start_time = System.currentTimeMillis();
        OPEN.add(root);
    }

    public static NPCEi minC(){   //OPENリストの中で一番小さいcを持つNPCを返す(Step2)
        NPCEi minNPC = OPEN.get(0);
        for(int i=1;i<OPEN.size();i++){
            if(minNPC.c>OPEN.get(i).c) minNPC=OPEN.get(i);
        }
        return minNPC;
    }

    public static boolean isOPENEmpty(){
        if (OPEN == null || OPEN.size() == 0) return true;
        else return false;
    }

    public static void nDeploy(NPCEi x){ // 与えられたNPCのnを展開、子供の集合生成、nをCLOSEDへ, Step3
        x.Deployment();
        OPEN.remove(x);
        //System.out.println();
        //System.out.println("x was deployed , removed from OPEN LIST");
        //System.out.println("OPEN.size() : "+OPEN.size());
        CLOSED.add(x);
        //System.out.println("x was added to CLOSED LIST");
        //System.out.println("CLOSED.size() : "+CLOSED.size());
        //System.out.println();
    }

    public static void SameChild(NPCEi x){   //step4
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

    public static void printEfficiency(NPCEi ans){
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
            NPCEi select = minC();
            cost++;
            if(select.isGoal()){
                end_time = System.currentTimeMillis();
                System.out.println("Searching succeed!!");
                //select.NPrintBoard();
                printEfficiency(select);
                break;
            }
            //System.out.println("Step2 completed");
            //EightPuzzle.stop();

            //step3
            nDeploy(select);
            //System.out.println("Step3 completed");
            //EightPuzzle.stop();

            //step4
            SameChild(select);
            //System.out.println("Step4 completed");
            //EightPuzzle.stop();
        }
    }
}