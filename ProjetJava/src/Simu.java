import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;


public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;
	    
	    public static void main (String [] args){
	    	
	    	boolean move; 
	    	
	    	// La grille
	        StdDraw.setXscale(5, X_MAX-5);
	        StdDraw.setYscale(0, Y_MAX);
	        
	    	//Menu ecran titre
	        Menu m=new Menu();

	        do{
		        m.principal();
		        
		        //Collision
		        int[] collide=new int[m.nbJoueurs]; 
	
		    	// Les vaisseaux
		    	final Vaisseau[] v = new Vaisseau[m.nbJoueurs];
		    	for (int i=0;i<m.nbJoueurs;i++){
		    		v[i] = new Vaisseau(X_MAX/2,Y_MAX/(2+i),WIDTH*4.5,0.7,i);
		    	}
		    	
		    	// Tunnel
		    	Tunnel t = new Tunnel();
	
		    	// Acceleration (en m/s/s)
		    	double ax = 0;
		    	double ay = -20;
		    	
		    	// Intervalle de temps (en s)
		    	double delta = 0.02;
		    	
		    	//nb joueurs pour le timer
		        final int nbJoueursCopie=m.nbJoueurs;
		    	
		    	//MAJ du score tous les quarts de seconde
	            TimerTask task = new TimerTask(){
	    			public void run(){
	    				for (int i=0;i<nbJoueursCopie;i++)
	    					v[i].score+=v[i].px;
	    			}	
	    		};
	    		
	    		Timer timer = new Timer();
	    		timer.scheduleAtFixedRate(task, 0, 250);
	    		

	    	
	    	
	    	

	    		//On joue tant qu'il reste un joueur vivant
		    	do{
	
		    		// RAZ des collisions
	    			for(int i=0;i<m.nbJoueurs;i++)
		            	collide[i]=0; 
	            	
	    			//Si joueur en vie il se déplace
		            for (int i=0;i<m.nbJoueurs;i++){
		            	if (v[i].exist==1)
		            		v[i].move(ax,ay,delta,X_MAX, Y_MAX,i);
		            }
	            	
		            //Fond
	            	StdDraw.clear(StdDraw.WHITE);
		            
	            	//Affichage tunnel
	            	t.getTunnel();
	            
	            	
	            	//Vitesse de defilement tunnel
	            	t.defilementTunnel();
	           
	            	
	            	//Collisions avec tunnel
	            	collide=Vaisseau.collisionTunnel (v,m.nbJoueurs);
	            	for (int i=0; i<m.nbJoueurs; i++)
	            		if (collide[i] ==1 && v[i].energie > 0 && v[i].invincible == false){
	            			v[i].energie--;
	            			v[i].invincible=true;
	            			
	       			
	            			//Invincibilite d'une sec apres collision
	                		TimerTask task2 = new TimerTask(){
	                			public void run(){
	                				for (int i=0;i<nbJoueursCopie;i++){
	                					v[i].invincible=false;
	                			}
	                			}	
	                	    };
	                	        Timer timer2 = new Timer();
	                	        timer2.schedule(task2, 1000);
	            		}
	            	
	            	//Rebond entre vaisseaux
	            	if (m.nbJoueurs>=2)
	            		Vaisseau.rebondVaisseau(v,1,0);
	            	if (m.nbJoueurs>=3){
		            	Vaisseau.rebondVaisseau(v,2,0);
		            	Vaisseau.rebondVaisseau(v,2,1);
	            	}
	
		            // Score + energie en noir
		            StdDraw.setPenColor(Color.black);
		            
	            	//Energie
		            String [] e = new String [m.nbJoueurs];
		            for (int i=0;i<m.nbJoueurs;i++){
		            	e [i] = "Energie joueur "+ (i+1) +" : " +String.valueOf(v[i].energie);
		            	StdDraw.text(30,95-i*5,e[i]);
		            	}
	            	
				    //Score
		            String [] s = new String [m.nbJoueurs];
		            for (int i=0;i<m.nbJoueurs;i++){
		            	s [i] = "Score joueur "+ (i+1) +" : " +String.valueOf(v[i].score);
		            	StdDraw.text(80,95-i*5,s[i]);
		            }
		            
		            //Verif si au moins un joueur est en vie
		            move = false ;
		    		for (int i=0;i<m.nbJoueurs;i++){
		    			if (v[i].exist == 1)
		    				move = true;
		    		}
		    		
		    		
		    		draw(v, (int)(1000*delta),m.nbJoueurs);
		    	}while (move);
	
		    	
		    	//Temps d'attente avant image de game over
		    	try {
		    		Thread.sleep(500);
		    	}
		    	catch(InterruptedException ex){
		    		Thread.currentThread().interrupt();
		    	}
		    	
		    	StdDraw.picture(50, 50, "game_over.jpg", 100, 100);
		    		
		    	//Vainqueur
		    	Vaisseau.vainqueur(v, m.nbJoueurs);
		    	
		    	//Try again?
		    	m.retourMenu();
	        }while(m.tryAgain);

	      }  
	    
	    
	    
	    

	    public static void draw(Vaisseau[] v, int time, int nbJoueur){
	    	for (int i=0;i<nbJoueur;i++){
	    		if (v[i].energie == 0){					//Verif si joueur vivant
	    			v[i].exist=0;
	    			v[i].px=0;							//Score arrete de s'incrementer
	    		}else{
	    			v[i].draw(i);
	    		}
	    	}
        	
	        // display and pause
	        StdDraw.show(time);	
	    }   
	    
}
