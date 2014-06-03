import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;


public class Simu {
	
		public final static int X_MAX= 100;
	    public final static int Y_MAX= 100;
	    public final static float WIDTH= 0.8f;
	    static boolean inversed_keys=false;
	    static int nbTours=0;
	    
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
		    	for (int i=0;i<m.nbJoueurs;i++)
		    		v[i] = new Vaisseau(X_MAX/2,Y_MAX/(2+i),WIDTH*4.5,2,i);
		    	
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
	    					v[i].score+=v[i].px-5;
	    			}	
	    		};
	    		Timer timer = new Timer();
	    		timer.scheduleAtFixedRate(task, 0, 250);

	    		//On joue tant qu'il reste un joueur vivant
		    	do{
	
		    		// RAZ des collisions
	    			for(int i=0;i<m.nbJoueurs;i++)
		            	collide[i]=0; 
	            	
	    			// Activation zone spéciale : 2% de chance
	    			for (int i=0;i<m.nbJoueurs;i++)
	    				if (v[i].score>=3000)
	    					t.specialZone();
	    			
	    			//Si joueur en vie il se déplace
		            for (int i=0;i<m.nbJoueurs;i++){
		            	if (v[i].exist==1)
		            		v[i].move(ax,ay,delta,X_MAX, Y_MAX,i, inversed_keys);
		            }
	            	
		            //Fond
	            	StdDraw.clear(StdDraw.WHITE);
		            
	            	//Affichage tunnel
	            	t.getTunnel();
	            	
	            	//Vitesse de defilement tunnel
	            	t.defilementTunnel();
	            	
	            	// On remplit le tableau collide
	            	collide=Vaisseau.collisionTunnel (v,m.nbJoueurs);
	            	
	            	// Gestion des collisions avec le tunnel (energie / invincibilité) et entre joueurs (rebonds)
	            	Vaisseau.energieInvincible(v,m.nbJoueurs,nbJoueursCopie,collide);
	            	
	            	//Rebond entre vaisseaux
	            	Vaisseau.getRebondVaisseau(v,m.nbJoueurs);
	
		            // Score + energie en noir
		            StdDraw.setPenColor(Color.black);
		            
	            	//Energie
		            Vaisseau.getEnergieScore(v,m.nbJoueurs);
		            
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
		    	
		    	StdDraw.clear();
		    	StdDraw.picture(50, 50, "game_over.jpg", 100, 100);
		    		
		    	//Vainqueur
		    	Vaisseau.vainqueur(v, m.nbJoueurs);
		    	
		    	//Try again?
		    	m.retourMenu();
		    	StdDraw.clear();
		    	StdDraw.setPenColor(Color.black);
		    	
		    	//Reinitialisation zone speciale
		    	inversed_keys=false;
		    	nbTours=0;
		    	
		    	}while(m.tryAgain);

	    }  
	    
	    public static void draw(Vaisseau[] v, int time, int nbJoueur){
	    	for (int i=0;i<nbJoueur;i++){
	    		if (v[i].energie == 0){					//Verif si joueur vivant
	    			v[i].exist=0;
	    			v[i].px=0;							//Score arrete de s'incrementer
	    		}else
	    			v[i].draw(i);
	    	}
        	
	        // display and pause
	        StdDraw.show(time);	
	    }   
	    
}
