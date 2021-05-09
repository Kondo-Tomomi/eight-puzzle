import java.util.*;

public class FifteenPuzzle extends Puzzle {
    final static int[][] GOAL = { { 1, 2, 3, 4}, { 5, 6, 7, 8}, { 9, 10, 11, 12}, { 13, 14, 15, 0} };
    final static int[][] None = { { -1, -1, -1, -1}, { -1, -1, -1, -1}, { -1, -1, -1, -1}, { -1, -1, -1, -1} };

    public static int[][] BoardRandom(){      //ランダムに初期設定
        int[][] state;
        state = new int[4][4];
        //System.out.println("Board has made...");
        ArrayList<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<16;i++){
            list.add(i);
        }
        Collections.shuffle(list);
        //System.out.println(list);
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++) state[i][j] = list.get(4*i+j);
        }
        if(isImpossible(state, GOAL)) state=BoardRandom();
        return(state);
    }

    public static int[][] BoardMake(){
        int[][] x = { { 1, 4, 8, 11}, { 6, 2, 7, 3}, { 0, 5, 9, 12}, { 13, 10, 14, 15} };
        return x;
    }

}