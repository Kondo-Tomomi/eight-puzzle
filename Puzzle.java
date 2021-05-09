public class Puzzle {

    public static void stop(){
        try {
            System.out.println("Stop...");
            Thread.sleep(2000);
            System.out.println();
        } catch(InterruptedException e){
            e.printStackTrace();
        }   
    }

    public static boolean isPuzzleSame(int[][] a, int[][] b){
        for(int i=0; i<a.length; i++){
            for(int j=0; j<a[i].length; j++){
                if(a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    public static boolean isImpossible(int[][] y, int[][] GOAL){  //解けないパズルかどうか判断
        int blank=0;
        int exchange=0;

        int[][] x = new int[y.length][];

        for(int i=0; i<y.length; i++){
            x[i] = new int[y[i].length];
            System.arraycopy(y[i], 0, x[i], 0, y[i].length);
        }

        /*for(int i=0;i<5*x.length+2;i++) System.out.print("-");
        System.out.println();
        for(int i=0;i<x.length;i++){
            System.out.print("|");
            for(int j=0;j<x[i].length;j++){
                if(y[i][j]==0) System.out.print("| "+" "+" |");
                else if(x[i][j]<10) System.out.print("| "+x[i][j]+" |");
                else System.out.print("| "+x[i][j]+"|");
            }
            System.out.println("|");
        }
        for(int i=0;i<5*x.length+2;i++) System.out.print("-");
        System.out.println();*/

        for(int i=0; i<x.length; i++){
            for(int j=0; j<x[i].length; j++){
                if(x[i][j]==0){
                    blank=Math.abs(i-1)+Math.abs(j-1);
                    blank = blank%2;
                    break;
                }
            }
        }
        for(int i=0; i<x.length*x.length; i++){ //GOALの順番
            int a = i/x.length;
            int b = i-x.length*a;
            if(x[a][b]==GOAL[a][b]) continue;
            for(int j=i+1; j<x.length*x.length; j++){  //xの順番
                int c = j/x.length;
                int d = j-x.length*c;
                if(GOAL[a][b]==x[c][d]){
                    //System.out.println(x[c][d]+" <-> "+x[a][b]);
                    int e = x[c][d];
                    x[c][d] = x[a][b];
                    x[a][b] = e;
                    exchange++; 
                }
            }
        }
        exchange = exchange%2;
        //System.out.println("blank: "+blank+"   exchange: "+exchange);
        //System.out.println(!(blank==exchange));
        return !(blank==exchange);
    }
}