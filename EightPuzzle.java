import java.util.*;

public class EightPuzzle extends Puzzle {
    final static int[][] GOAL = { { 1, 2, 3}, { 8, 0, 4}, { 7, 6, 5} };
    final static int[][] None = { { -1, -1, -1}, { -1, -1, -1}, { -1, -1, -1} };

    public static int[][] BoardRandom(){      //ランダムに初期設定
        int[][] state;
        state = new int[3][3];
        //System.out.println("Board has made...");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<9;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        //System.out.println(list);
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) state[i][j] = list.get(3*i+j);
        }
        if(isImpossible(state, GOAL)) state=BoardRandom();
        return(state);
    }

    public static int[][] BoardMake(){
        int[][] x = { { 7, 2, 8}, { 4, 5, 3}, { 1, 0, 6} };
        return x;
    }

}