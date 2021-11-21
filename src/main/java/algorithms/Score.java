package algorithms;

import java.util.Arrays;

public class Score {
	
	public int[] calcScore(int[][] state) {
		int[] score = new int[2];
		//score vertical
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 7;j++) {
				if(state[i][j]==1 && state[i+1][j]==1 && state[i+2][j]==1 && state[i+3][j]==1 ) {
					score[0]++;
				}else if(state[i][j]==2 && state[i+1][j]==2 && state[i+2][j]==2 && state[i+3][j]==2 ) {
					score[1]++;
				}
			}
		}
		//score horizontal
		for(int i = 0 ; i < 6 ; i++ ) {
			for(int j = 0 ; j < 4;j++) {
				if(state[i][j]==1 && state[i][j+1]==1 && state[i][j+2]==1 && state[i][j+3]==1 ) {
					score[0]++;
				}else if(state[i][j]==2 && state[i][j+1]==2 && state[i][j+2]==2 && state[i][j+3]==2 ) {
					score[1]++;
				}
			}
		}
		//score diagonal right
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 4;j++) {
				if(state[i][j]==1 && state[i+1][j+1]==1 && state[i+2][j+2]==1 && state[i+3][j+3]==1 ) {
					score[0]++;
				}else if(state[i][j]==2 && state[i+1][j+1]==2 && state[i+2][j+2]==2 && state[i+3][j+3]==2 ) {
					score[1]++;
				}
			}
		}
		//score diagonal left
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 3 ; j < 7;j++) {
				if(state[i][j]==1 && state[i+1][j-1]==1 && state[i+2][j-2]==1 && state[i+3][j-3]==1 ) {
					score[0]++;
				}else if(state[i][j]==2 && state[i+1][j-1]==2 && state[i+2][j-2]==2 && state[i+3][j-3]==2 ) {
					score[1]++;
				}
			}
		}
		
		return score;
	}
	
	public static void main(String[] args) {
		int[][] state = new int [6][7];
		/*state[5][0]=1;
		state[5][1]=1;
		state[5][2]=2;
		state[5][3]=2;
		state[5][4]=1;
		state[5][5]=2;
		state[4][0]=1;
		state[4][1]=1;
		state[4][3]=2;
		state[4][4]=1;
		state[3][1]=1;
		state[3][3]=2;
		state[3][4]=2;
		state[2][1]=2;
		state[2][3]=1;*/
		Arrays.fill(state[0], 2);
		Arrays.fill(state[1], 2);
		Arrays.fill(state[2], 1);
		Arrays.fill(state[3], 2);
		Arrays.fill(state[4], 2);
		Arrays.fill(state[5], 2);
		Score s = new Score();
		int[] a = s.calcScore(state);
		System.out.println(a[0] +"    "+ a[1]);
	}
	
}
