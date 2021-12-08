package algorithms;


public class Heurestic2 {
	
	int isOne(int[][] state,int i,int j) {
		if(state[i][j]==1) {return 1;}
		else return 0;
	}
	
	int isTwo(int[][] state,int i,int j) {
		if(state[i][j]==2) {return 1;}
		else return 0;
	}
	
	int heurestic_horizontal(int[][] state) {
		int back = 0 ;
		for(int i = 0 ; i < 6 ; i++ ) {
			for(int j = 0 ; j < 4;j++) {
				int one = 0,two = 0;
				one = isOne(state, i, j)+isOne(state, i, j+1)+isOne(state, i, j+2)+isOne(state, i, j+3);
				two = isTwo(state, i, j)+isTwo(state, i, j+1)+isTwo(state, i, j+2)+isTwo(state, i, j+3);
				if(one == 0) {
					if(two == 4) {
						back-=100;
					}else if(two == 3) {
						back-=10;
					}else if(two == 2) {
						back-=5;
					}else if(two == 1) {
						back-=1;
					}
				}else if (two == 0) {
					if(one == 4) {
						back+=100;
					}else if(one == 3) {
						back+=10;
					}else if(one == 2) {
						back+=5;
					}else if(one == 1) {
						back+=1;
					}
				}
				//System.out.println("h back = "+back+"  i="+i+" j="+j);
			}
		}
		return back;
	}
	
	int heurestic_vertical(int[][] state) {
		int back = 0 ;
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 7;j++) {
				int one = 0,two = 0;
				one = isOne(state, i, j)+isOne(state, i+1, j)+isOne(state, i+2, j)+isOne(state, i+3, j);
				two = isTwo(state, i, j)+isTwo(state, i+1, j)+isTwo(state, i+2, j)+isTwo(state, i+3, j);
				if(one == 0) {
					if(two == 4) {
						back-=100;
					}else if(two == 3) {
						back-=10;
					}else if(two == 2) {
						back-=5;
					}else if(two == 1) {
						back-=1;
					}
				}else if (two == 0) {
					if(one == 4) {
						back+=100;
					}else if(one == 3) {
						back+=10;
					}else if(one == 2) {
						back+=5;
					}else if(one == 1) {
						back+=1;
					}
				}
				//System.out.println("v back = "+back+"  i="+i+" j="+j);
			}
		}
		return back;
	}
	
	
	int digonal_right(int[][] state) {
		int back = 0;
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 0 ; j < 4;j++) {
				int one = 0,two = 0;
				one = isOne(state, i, j)+isOne(state, i+1, j+1)+isOne(state, i+2, j+2)+isOne(state, i+3, j+3);
				two = isTwo(state, i, j)+isTwo(state, i+1, j+1)+isTwo(state, i+2, j+2)+isTwo(state, i+3, j+3);
				if(one == 0) {
					if(two == 4) {
						back-=100;
					}else if(two == 3) {
						back-=10;
					}else if(two == 2) {
						back-=5;
					}else if(two == 1) {
						back-=1;
					}
				}else if (two == 0) {
					if(one == 4) {
						back+=100;
					}else if(one == 3) {
						back+=10;
					}else if(one == 2) {
						back+=5;
					}else if(one == 1) {
						back+=1;
					}
				}
				//System.out.println("dr back = "+back+"  i="+i+" j="+j);
			}
		}
		return back;
	}
	
	int digonal_left(int[][] state) {
		int back = 0;
		for(int i = 0 ; i < 3 ; i++ ) {
			for(int j = 3 ; j < 7;j++) {
				int one = 0,two = 0;
				one = isOne(state, i, j)+isOne(state, i+1, j-1)+isOne(state, i+2, j-2)+isOne(state, i+3, j-3);
				two = isTwo(state, i, j)+isTwo(state, i+1, j-1)+isTwo(state, i+2, j-2)+isTwo(state, i+3, j-3);
				if(one == 0) {
					if(two == 4) {
						back-=100;
					}else if(two == 3) {
						back-=10;
					}else if(two == 2) {
						back-=5;
					}else if(two == 1) {
						back-=1;
					}
				}else if (two == 0) {
					if(one == 4) {
						back+=100;
					}else if(one == 3) {
						back+=10;
					}else if(one == 2) {
						back+=5;
					}else if(one == 1) {
						back+=1;
					}
				}
				//System.out.println("dl back = "+back+"  i="+i+" j="+j);
			}
		}
		return back;
	}

	public int heuristic_function(int[][] state) {
        int heurestic = 0;
        heurestic+=heurestic_horizontal(state);
        heurestic+=heurestic_vertical(state);
        heurestic+=digonal_left(state);
        heurestic+=digonal_right(state);
        return heurestic;
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
        Heurestic2 h = new Heurestic2();
        int a = h.heuristic_function(state);
        System.out.println(a);
    }
}

