package algorithms;

import java.util.HashSet;

public class Heuristic {

    HashSet<String> set = new HashSet<>();

    public int heuristic_function(int[][] state) {
        set.clear();
        int wins = 3 * 7 + 4 * 6 + 12 * 2;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (state[i][j] == 2) {
                    check_horizontal(i, j);
                    check_vertical(i, j);
                    check_diagonal(i, j);
                }
            }
        }
        int remove_wins = set.size();
        //System.out.println(wins+" "+remove_wins+" "+set.toString());
        wins -= remove_wins;
        return wins;
    }

    private void check_horizontal(int i, int j) {
        String s;
        if (j >= 0 && j <= 3) {
            s = "h" + i + "0";
            set.add(s);
        }
        if (j >= 1 && j <= 4) {
            s = "h" + i + "1";
            set.add(s);
        }
        if (j >= 2 && j <= 5) {
            s = "h" + i + "2";
            set.add(s);
        }
        if (j >= 3 && j <= 6) {
            s = "h" + i + "3";
            set.add(s);
        }
    }

    private void check_vertical(int i, int j) {
        String s;
        if (i >= 0 && i <= 3) {
            s = "v" + j + "0";
            set.add(s);
        }
        if (i >= 1 && i <= 4) {
            s = "v" + j + "1";
            set.add(s);
        }
        if (i >= 2 && i <= 5) {
            s = "v" + j + "2";
            set.add(s);
        }
    }

    private void check_diagonal(int i, int j) {
        String s;
        int right_i = i, left_i = i;
        int right_j = j, left_j = j;
        for (int k = 0; k < 4; k++) {
            if (right_i >= 0 && right_i <= 2 && right_j >= 0 && right_j <= 3) {
                s = "dr" + right_i + right_j;
                set.add(s);
            }
            right_i--;
            right_j--;
        }
        for (int k = 0; k < 4; k++) {
            if (left_i >= 0 && left_i <= 2 && left_j >= 3 && left_j <= 6) {
                s = "dl" + left_i + left_j;
                set.add(s);
            }
            left_i--;
            left_j++;
        }
    }

    public static void main(String[] args) {
        int[][] state = new int[6][7];
        state[5][0] = 1;
        state[5][1] = 1;
        state[5][2] = 2;
        state[5][3] = 2;
        state[5][4] = 1;
        state[5][5] = 2;
        state[4][0] = 1;
        state[4][1] = 1;
        state[4][3] = 2;
        state[4][4] = 1;
        state[3][1] = 1;
        state[3][3] = 2;
        state[3][4] = 2;
        state[2][1] = 2;
        state[2][3] = 1;
        Heuristic h = new Heuristic();
        int a = h.heuristic_function(state);
        System.out.println(a);
    }
}
