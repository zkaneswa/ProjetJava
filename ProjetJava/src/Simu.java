
public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;

	    public static void main (String [] args){

	    	boolean move = true;
	    	
			// La grille
	        StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
	        StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);
	  
	    	// Les vaisseaux
	    	Vaisseau[] v = new Vaisseau[2];
	    	v[0] = new Vaisseau(X_MAX/2,Y_MAX/2,WIDTH,0.7,Vaisseau.PLAYER1);
	    	v[1] = new Vaisseau(X_MAX/2,Y_MAX/6,WIDTH,0.7,Vaisseau.PLAYER1);
	    	
	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// intervalle de temps (en s)
	    	double delta = 0.02;

	    	while(move){
	           
	            	v[0].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER1);
	            	v[1].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER2);
	        
	            	
	            	StdDraw.clear(StdDraw.WHITE);
	            	
	            	//Tunnel
			    	Tunnel[] t = new Tunnel[1];
			    	t[0]=new Tunnel();
			    	t[0].drawTunnel(Tunnel.tunnelHaut);
			    	t[0].drawTunnel(Tunnel.tunnelBas);
			    	
	            draw(v, (int)(1000*delta));
	    	}

	    }
	    
	    
	    public static void draw(Vaisseau[] v, int time){
	              
	            v[0].draw(Vaisseau.PLAYER1);
	        	v[1].draw(Vaisseau.PLAYER2);

	        	// display and pause
	        StdDraw.show(time);	
	    }
	    	
}
