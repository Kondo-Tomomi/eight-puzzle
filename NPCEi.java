import java.util.*;

public class NPCEi extends NPC {   //(n,p,c)

    public ArrayList<NPCEi> myChild = new ArrayList<NPCEi>();

    public void printState(){
        System.out.print("n");
        for(int i=0; i<17; i++) System.out.print(" ");
        System.out.print("p");
        for(int i=0; i<17; i++) System.out.print(" ");
        System.out.println("c = "+c);

        System.out.print("|");
        for(int j=0;j<n[0].length;j++){
            if(n[0][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+n[0][j]+" |");
        }
        System.out.print("| |");
        for(int j=0;j<p[0].length;j++){
            if(p[0][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+p[0][j]+" |");
        }
        System.out.println("| g = "+g);

        System.out.print("|");
        for(int j=0;j<n[1].length;j++){
            if(n[1][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+n[1][j]+" |");
        }
        System.out.print("| |");
        for(int j=0;j<p[1].length;j++){
            if(p[1][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+p[1][j]+" |");
        }
        System.out.println("|");

        System.out.print("|");
        for(int j=0;j<n[2].length;j++){
            if(n[2][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+n[2][j]+" |");
        }
        System.out.print("| |");
        for(int j=0;j<p[2].length;j++){
            if(p[2][j]==0) System.out.print("| "+" "+" |");
            else System.out.print("| "+p[2][j]+" |");
        }
        System.out.println("|");
        System.out.println();
    }

    public void FirstNPC(){     //スタートの設定
        //this.n = EightPuzzle.BoardRandom();
        this.n = EightPuzzle.BoardMake();
        this.p = null;
        this.c = 0;
        this.g = 0;
        //System.out.println("First state was made...");
        //NPrintBoard();
    }

    public boolean isGoal(){   //現在がゴールかどうか
        return Puzzle.isPuzzleSame(n,EightPuzzle.GOAL);
    }

    
    public void Up(){   //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
        //System.out.println("Blank Up ↑↑");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(i0<1|| j0==-1) return;
        NPCEi upChild = new NPCEi();
        
        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0-1][j0];
        state[i0-1][j0]=0;
        state[i0][j0]=num;

        upChild.n = state;

        int newc;
        int ng = this.g + 1;
        int h = upChild.H();
        //System.out.print("c = H(n)("+h+") + ng("+ng+") = ");
        newc = h + ng;
        //System.out.println(newc);
        //System.out.println();
        upChild.setNPC(state, this.n, newc, ng);
        myChild.add(upChild);
        //System.out.println("up state added to myChild");
    }

    public void Down(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
        //System.out.println("Blank Down ↓↓");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(i0>n.length-2 || i0==-1 || j0==-1) return;
        NPCEi downChild = new NPCEi();

        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0+1][j0];
        state[i0+1][j0]=0;
        state[i0][j0]=num;

        downChild.n = state;

        int newc;
        int ng = this.g + 1;
        int h = downChild.H();
        //System.out.print("c = H(n)("+h+") + g("+ng+") = ");
        newc = h + ng;
        //System.out.println(newc);
        //System.out.println();

        downChild.setNPC(state, this.n, newc, ng);
        myChild.add(downChild);

        //System.out.println("down state added to myChild");
    }

    public void Right(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
        //System.out.println("Blank Right →→");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(j0>n.length-2 || i0==-1 || j0==-1) return;
        NPCEi rightChild = new NPCEi();
        
        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0][j0+1];
        state[i0][j0+1]=0;
        state[i0][j0]=num;

        rightChild.n = state;

        int newc;
        int ng = this.g + 1;
        int h = rightChild.H();
        //System.out.print("c = H(n)("+h+") + ng("+ng+") = ");
        newc = h + ng;
        //System.out.println(newc);
        //System.out.println();

        rightChild.setNPC(state, this.n, newc, ng);
        myChild.add(rightChild);

        //System.out.println("right state added to myChild");
    }

    public void Left(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
        //System.out.println("Blank Left ←←");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(j0<1 || i0==-1) return;
        NPCEi leftChild = new NPCEi();

        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }
        
        int num = state[i0][j0-1];
        state[i0][j0-1]=0;
        state[i0][j0]=num;

        leftChild.n = state;

        int newc;
        int ng = this.g + 1;
        int h = leftChild.H();
        //System.out.print("c = H(n)("+h+") + ng("+ng+") = ");
        newc = h + ng;
        //System.out.println(newc);
        //System.out.println();

        leftChild.setNPC(state, this.n, newc, ng);
        myChild.add(leftChild);
        //System.out.println("left state added to myChild");
    }

    //ヒューリスティック関数

    public int H1(){
        int h1=0;
        for(int i=0;i<n.length;i++){
            for(int j=0;j<n[i].length;j++){
                if(EightPuzzle.GOAL[i][j]!=this.n[i][j]) h1++;
            }
        }
        return h1;
    }

    public int H2Sub(int num,int i, int j){    //numのゴールまでの距離を計算
        int IG=0,JG=0;
        switch(num){
            case 0:  IG=1;  JG=1;  break;
            case 1:  IG=0;  JG=0;  break;
            case 2:  IG=0;  JG=1;  break;
            case 3:  IG=0;  JG=2;  break;
            case 4:  IG=1;  JG=2;  break;
            case 5:  IG=2;  JG=2;  break;
            case 6:  IG=2;  JG=1;  break;
            case 7:  IG=2;  JG=0;  break;
            case 8:  IG=1;  JG=0;  break;
        }
        return (Math.abs(i-IG)+Math.abs(j-JG));
    }
}