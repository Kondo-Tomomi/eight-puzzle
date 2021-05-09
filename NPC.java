abstract class NPC {

    int[][] n;
    int[][] p;
    int c;
    int g;  //自分のg(n)

    public void setNPC(int[][] n,int[][] p,int c,int g){
        this.n = n;
        this.p = p;
        this.c = c;
        this.g = g;

        //System.out.println("setNPC!!");
        //NPrintBoard();
    }

    public void NPrintBoard(){         //現在の状態をプリント
        int[][] x = new int[n.length][];

        for(int i=0; i<n.length; i++){
            x[i] = new int[n[i].length];
            System.arraycopy(n[i], 0, x[i], 0, n[i].length);
        }

        System.out.println();
        for(int i=0;i<5*n.length+2;i++) System.out.print("-");
        System.out.println();
        for(int i=0;i<n.length;i++){
            System.out.print("|");
            for(int j=0;j<n[i].length;j++){
                if(x[i][j]==0) System.out.print("| "+" "+" |");
                else if(x[i][j]<10) System.out.print("| "+x[i][j]+" |");
                else System.out.print("| "+x[i][j]+"|");
            }
            System.out.println("|");
        }
        for(int i=0;i<5*n.length+2;i++) System.out.print("-");
        System.out.println();
        System.out.println();
    }

    public void PPrintBoard(){         //親の状態をプリント
        int[][] x = new int[p.length][];

        for(int i=0; i<p.length; i++){
            x[i] = new int[p[i].length];
            System.arraycopy(p[i], 0, x[i], 0, p[i].length);
        }
 
        System.out.println();
        for(int i=0;i<5*p.length+2;i++) System.out.print("-");
        System.out.println();
        for(int i=0;i<p.length;i++){
            System.out.print("|");
            for(int j=0;j<p[i].length;j++){
                if(x[i][j]==0) System.out.print("| "+" "+" |");
                else if(x[i][j]<10) System.out.print("| "+x[i][j]+" |");
                else System.out.print("| "+x[i][j]+"|");
            }
            System.out.println("|");
        }
        for(int i=0;i<5*p.length+2;i++) System.out.print("-");
        System.out.println();
        System.out.println();
    }

    public abstract void Up();
    public abstract void Down();
    public abstract void Right();
    public abstract void Left();
    
    public void Deployment(){  //nを展開,myChildにaddされていく
        //this.NPrintBoard();
        //System.out.println("is Deployed...");
        this.Up();
        this.Down();
        this.Right();
        this.Left();
    }

    //ヒューリスティック関数

    public int H0(){
        return 0;
    }

    public abstract int H1();

    public abstract int H2Sub(int num,int i, int j);    //numのゴールまでの距離を計算

    public int H2(){
        int sum=0;
        for(int i=0;i<n.length;i++){
            for(int j=0;j<n[i].length;j++){
                sum+=H2Sub(this.n[i][j],i,j);
            }
        }
        return sum;
    }

    public int H(){
        //return H0();
        return H1();
        //return H2();
    }

}