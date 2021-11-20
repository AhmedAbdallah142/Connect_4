package algorithms;

public class Score {
	
	public int[] calcScore(int[][] state) {
		int[] score = new int[2];
		score[0]=0;	score[1]=0;
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
	
}
