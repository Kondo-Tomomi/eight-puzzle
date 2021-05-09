import java.util.*;

public class NPD extends NPC {

    public ArrayList<NPD> myChild = new ArrayList<NPD>();

    public void FirstNPD(){     //スタートの設定
        this.n = EightPuzzle.BoardMake();
        //this.n = EightPuzzle.BoardRandom();
        this.p = null;
        this.g = 0;
        //System.out.println("First state was made...");
        //NPrintBoard();
    }

    public boolean isGoal(){   //現在がゴールかどうか
        return EightPuzzle.isPuzzleSame(n,EightPuzzle.GOAL);
    }

    public void Up(){   //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
//        System.out.println("Blank Up ↑↑");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(i0<1|| j0==-1) return;
        NPD upChild = new NPD();
        
        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0-1][j0];
        state[i0-1][j0]=0;
        state[i0][j0]=num;

        upChild.setNPC(state, this.n, 0, this.g + 1);
        myChild.add(upChild);

        //System.out.println("up state added to myChild");
        
    }

    public void Down(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
//        System.out.println("Blank Down ↓↓");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(i0>1 || i0==-1 || j0==-1) return;
        NPD downChild = new NPD();

        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0+1][j0];
        state[i0+1][j0]=0;
        state[i0][j0]=num;

        downChild.setNPC(state, this.n, 0, this.g + 1);
        myChild.add(downChild);

        //System.out.println("down state added to myChild");
    }

    public void Right(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
//        System.out.println("Blank Right →→");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(j0>1 || i0==-1 || j0==-1) return;
        NPD rightChild = new NPD();
        
        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }

        int num = state[i0][j0+1];
        state[i0][j0+1]=0;
        state[i0][j0]=num;

        rightChild.setNPC(state, this.n, 0, this.g + 1);
        myChild.add(rightChild);

        //System.out.println("right state added to myChild");
    }

    public void Left(){  //nを展開,NPC型をmyChildにadd
        int i0=-1,j0=-1;
//        System.out.println("Blank Left ←←");
        for(int i=0;i<n.length;i++)
            for(int j=0;j<n[i].length;j++) 
                if(n[i][j]==0){ i0=i;  j0=j;}
        if(j0<1 || i0==-1) return;
        NPD leftChild = new NPD();

        int[][] state = new int[n.length][];

        for(int i=0; i<n.length; i++){
            state[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, state[i], 0, n[i].length);
        }
        
        int num = state[i0][j0-1];
        state[i0][j0-1]=0;
        state[i0][j0]=num;

        leftChild.setNPC(state, this.n, 0, this.g + 1);
        myChild.add(leftChild);

        //System.out.println("left state added to myChild");
    }

    public int H1(){
        int h1=0;
        for(int i=0;i<n.length;i++){
            for(int j=0;j<n[i].length;j++){
                if(EightPuzzle.GOAL[i][j]!=this.n[i][j]) h1++;
            }
        }
        return h1;
    }

    public int H2Sub(int num, int i, int j){
        return 0;
    }
}