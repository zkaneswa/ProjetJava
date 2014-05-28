import java.awt.Color;

public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;
	    static double scoreV1;
	    static double scoreV2;
	    

	    public static void main (String [] args){
	    	
	    	boolean move = true;
	  
	    	// La grille
	        StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
	        StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);

	    	// Les vaisseaux
	        Vaisseau[] v = new Vaisseau[2];
	    	v[0] = new Vaisseau(X_MAX/2,Y_MAX/2,WIDTH*4.5,0.7,Vaisseau.PLAYER1);
	    	v[1] = new Vaisseau(X_MAX/2,Y_MAX/6,WIDTH*4.5,0.7,Vaisseau.PLAYER1);
	    	
	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// intervalle de temps (en s)
	    	double delta = 0.02;
	  		    	
	    	Tunnel t = new Tunnel();

	    	while(move){
            	for(int i=0;i<=1;i++)
	            	v[i].collision=false;
            	
            	v[0].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER1);
            	v[1].move(ax,ay,delta,X_MAX, Y_MAX,Vaisseau.PLAYER2);
            	
            	StdDraw.clear(StdDraw.WHITE);
	            
	            //Tunnel
            	t.tunnel();
            	t.afficheTunnel();
            	t.decale();
            	
            	//Collisions avec tunnel
            	v[0].collisionTunnel();
            	v[1].collisionTunnel();
            	
            	//Collision entre vaisseaux
            	double dist= (v[1].px-v[0].px)* (v[1].px-v[0].px)+  (v[1].py-v[0].py)*(v[1].py-v[0].py);
            	if(dist<(v[0].rayon + v[1].rayon)*(v[0].rayon + v[1].rayon)){
            		if (v[0].py>v[1].py){
            			v[0].py+=2;
            			v[1].py-=2;
            		}else{
            			v[0].py-=2;
            			v[1].py+=2;
            		}
            		
            		if (v[0].px>v[1].px){
            			v[0].px+=2;
            			v[1].px-=2;
            		}else{
            			v[0].px-=2;
            			v[1].px+=2;
            		}
            	}
            	
            	//Energie
            	for(int i=0;i<=1;i++)
	            	if (v[i].collision==true){
	            		if (v[i].energie<=0)
	            			v[i].energie=0;
	            		else
	            			v[i].energie-=1;
	            	}
            	
            	//Affichage
            	
            	//Barre de vie
            	String e1 = "Energie joueur 1 : "+String.valueOf(v[0].energie);
                String e2 = "Energie joueur 2 : "+String.valueOf(v[1].energie);
                StdDraw.text(30, 95, e1);
                StdDraw.text(30, 90, e2);
                
                //Score
		    	scoreV1+=v[0].px;
		    	scoreV2+=v[1].px;
		    	String t1 = String.valueOf(scoreV1); //On cast double en string pour StdDraw.text
		    	String t2 = String.valueOf(scoreV2);
	            StdDraw.setPenColor(Color.black);
	            StdDraw.text(90, 95, "Joueur 1 : "+t1);
	            StdDraw.text(90, 90,"Joueur 2 : "+t2);
			    	
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
