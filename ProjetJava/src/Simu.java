import java.awt.Color;
import java.util.*;

public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;
	    static int scoreV1;
	    static int scoreV2;
	    
	    public static void main (String [] args){
	    	
	    	boolean move = false; 
		    int[] collide={0,0}; 
		    
	    	// La grille
	        StdDraw.setXscale(-WIDTH, X_MAX+WIDTH);
	        StdDraw.setYscale(-WIDTH, Y_MAX+WIDTH);

	    	// Les vaisseaux
	    	final Vaisseau[] v = new Vaisseau[2];
	    	v[0] = new Vaisseau(X_MAX/2,Y_MAX/2,WIDTH*4.5,0.7,Vaisseau.PLAYER1);
	    	v[1] = new Vaisseau(X_MAX/2,Y_MAX/3,WIDTH*4.5,0.7,Vaisseau.PLAYER2);

	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// Intervalle de temps (en s)
	    	double delta = 0.02;
	    	    	
	    	Tunnel t = new Tunnel();
	    	
            TimerTask task = new TimerTask(){
    			public void run(){
    	            scoreV1+=v[0].px;
    	            scoreV2+=v[1].px;
    			}	
    		};
    		
    		Timer timer = new Timer();
    		timer.scheduleAtFixedRate(task, 0, 250);
    		
    		
    		
	    	do{
		            for(int i=0;i<=1;i++)
		            	collide[i]=0; 
	            	
		            for (int i=0;i<=1;i++){
		            	if (v[i].exist==1)
		            		v[i].move(ax,ay,delta,X_MAX, Y_MAX,i);
		            }
	            	
		            //Fond
	            	StdDraw.clear(StdDraw.WHITE);
		            
	            	//Tunnel
	            	t.tunnel();
	            	t.afficheTunnel();
	            	t.decale(); 
	            	
	            	//Collisions avec tunnel
	            	collide=Vaisseau.collisionTunnel (v);
	            	
	            	
		            
	            	for (int i=0; i<=1; i++)
	            		if (collide[i]==1 && v[i].energie > 0)
	            			 v[i].energie--;
	            		
	            	
	            	
	            	
	            	//Collision entre vaisseaux
	            	double dist = (v[1].px-v[0].px)* (v[1].px-v[0].px) +  (v[1].py-v[0].py)*(v[1].py-v[0].py);
	            	if(dist <(v[0].rayon + v[1].rayon)*(v[0].rayon + v[1].rayon)){
	            		if (v[0].py>v[1].py){
	            			v[0].py+=5;
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
	
		            // Score + energie en noir
		            StdDraw.setPenColor(Color.black);
		            
	            	//Energie
	            	String e1 = "Energie joueur 1 : "+String.valueOf(v[0].energie);
	            	String e2 = "Energie joueur 2 : "+String.valueOf(v[1].energie);
		            StdDraw.text(30, 95, e1);
		            StdDraw.text(30, 90, e2);
	            	
				    //Score
			    	String t1 = "Score joueur 1 : "+String.valueOf(scoreV1);
			    	String t2 = "Score joueur 2 : "+String.valueOf(scoreV2);
		            StdDraw.text(80, 95, t1);
		            StdDraw.text(80, 90, t2);
		            
		            move = false ;
		    		for (int i=0;i<=1;i++){
		    			if (v[i].exist == 1)
		    				move = true;
		    		}
		            				    	
		            draw(v, (int)(1000*delta));
	    	}while (move);
	    }  
	    
	    public static void draw(Vaisseau[] v, int time){
	    	for (int i=0;i<=1;i++){
	    		if (v[i].energie == 0){					//VŽrif si joueur vivant
	    			v[i].exist=0;
	    			v[i].px=0;							//Score arr�te de s'incrŽmenter
	    		}else{
	    			v[i].draw(i);
	    		}
	    	}
        	
	        	
	        // display and pause
	        StdDraw.show(time);	
	    }   	
}
