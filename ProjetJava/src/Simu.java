import java.awt.Color;

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
	    	v[0] = new Vaisseau(X_MAX/2,Y_MAX/2,WIDTH*4.5,0.7,Vaisseau.PLAYER1);
	    	v[1] = new Vaisseau(X_MAX/2,Y_MAX/6,WIDTH,0.7,Vaisseau.PLAYER1);
	    	
	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// intervalle de temps (en s)
	    	double delta = 0.02;
	  		    	
	    	Tunnel t = new Tunnel();
	    	

	    	while(move){       
            	v[0].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER1);
            	v[1].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER2);
            	
            	StdDraw.clear(StdDraw.WHITE);

		    	
			    //Score
		    	Vaisseau.scoreV1+=v[0].px;
		    	Vaisseau.scoreV2+=v[1].px;
		    	String t1 = String.valueOf(Vaisseau.scoreV1); //On cast double en string pour StdDraw.text
		    	String t2 = String.valueOf(Vaisseau.scoreV2);
	            StdDraw.setPenColor(Color.black);
	            StdDraw.text(90, 95, "Joueur 1 : "+t1);
	            StdDraw.text(90, 90,"Joueur 2 : "+t2);
	            
	            
	            
	            //Tunnel
            	t.tunnel();
            	t.afficheTunnel();
            	t.decale();
            	
            	//Collisions
            	int collision = 0;
            	if (v[0].py+v[0].rayon>=Tunnel.tunnelHautPolygon[(int)v[0].px]){ //Contre tunnel haut
            		v[0].py=Tunnel.tunnelHautPolygon[(int)v[0].px]-v[0].rayon;
            		collision = 1;
            	}
            	if (v[1].py+v[1].rayon>=Tunnel.tunnelHautPolygon[(int)v[1].px]){
            		v[1].py=Tunnel.tunnelHautPolygon[(int)v[1].px]-v[1].rayon;
            		collision=1;
            	}
            	if (v[0].py-v[0].rayon<=Tunnel.tunnelBasPolygon[(int)v[0].px]){ //Contre tunnel bas
            		v[0].py=Tunnel.tunnelBasPolygon[(int)v[0].px]+v[0].rayon;
            		collision=1;
            	}
            	if (v[1].py-v[1].rayon<=Tunnel.tunnelBasPolygon[(int)v[1].px]){
            		v[1].py=Tunnel.tunnelBasPolygon[(int)v[1].px]+v[1].rayon;
            		collision=1;
            	}
            	if (v[0].py==v[1].py && v[0].px==v[1].px){						//Entre vaisseaux
            		collision=2;
            	}
			    	
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
