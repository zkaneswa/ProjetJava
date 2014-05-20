
public class Simu {
	
		public final static int X_MAX= 50;
	    public final static int Y_MAX= 50;
	    public final static float WIDTH= 0.8f;

	    public static void main (String [] args){
	    
	    	boolean move = true;
	    	
			// La grille
	        StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
	        StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
	  
	    	// Les balles
	    	Vaisseau[] v = new Vaisseau[2];
	    	v[0] = new Vaisseau(X_MAX/2,Y_MAX/2,WIDTH,0.7,Vaisseau.PLAYER1);
	    	v[1] = new Vaisseau(X_MAX/2,Y_MAX/6,WIDTH,0.7,Vaisseau.PLAYER1);
	    	
	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// intervalle de temps (en s)
	    	double delta = 0.02;

	    	while(move){
	            //for(int i= 0; i<v.length;i++){
	            	v[0].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER1);
	            	v[1].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER2);
	         //   }
	            draw(v, (int)(1000*delta));
	    	}
	    	
	    }
	    
	    
	    public static void draw(Vaisseau[] v, int time){
	        StdDraw.clear(StdDraw.BLACK);
	        
	        //for(int i= 0; i<v.length;i++){
	        	v[0].draw(Vaisseau.PLAYER1);
	        	v[1].draw(Vaisseau.PLAYER2);
	      //  }
	        // display and pause
	        StdDraw.show(time);	
	    }
	    	
}
