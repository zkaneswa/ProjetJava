import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Simu {
	
	public final static int X_MAX = 100;
    public final static int Y_MAX = 100;
    public final static float WIDTH = 0.8f*4.5f;
    public final static int BORDER_X = 5;
    public final static int BORDER_X_MAX = X_MAX-3;
    public final static int BORDER_Y = 0;
    public final static int FREQ_MAJ_SCORE = 250;
    public final static int ACT_ZONE_SPE = 3000;
    public final static int SLEEP_TIME = 500;
    public final static int MS_IN_1SEC = 1000;
    
    static boolean inversed_keys = false;
    static int nbTours = 0;
    
    public static void main (String [] args){
    	
    	boolean move;
    	
    	// La grille
        StdDraw.setXscale(BORDER_X, X_MAX-BORDER_X);
        StdDraw.setYscale(BORDER_Y, Y_MAX);
        
    	// Menu ecran titre
        Menu m = new Menu();

        do{
	        m.principal();
	        
	        // Collision
	        boolean[] collide = new boolean[m.nbJoueurs]; 

	    	// Les vaisseaux
	    	final Vaisseau[] v = new Vaisseau[m.nbJoueurs];
	    	for (int i=0;i<m.nbJoueurs;i++)
	    		v[i] = new Vaisseau(X_MAX/2,Y_MAX/(2+i),WIDTH,2,i);
	    	
	    	// Tunnel
	    	Tunnel t = new Tunnel();

	    	// Acceleration (en m/s/s)
	    	double ax = 0;
	    	double ay = -20;
	    	
	    	// Intervalle de temps (en s)
	    	double delta = 0.02;
	    	
	    	// nb joueurs pour le timer
	        final int nbJoueursCopie=m.nbJoueurs;
	    	
	    	// MAJ du score tous les quarts de seconde
            TimerTask task = new TimerTask(){
    			public void run(){
    				for (int i=0;i<nbJoueursCopie;i++)
    					v[i].score+=v[i].px-BORDER_X;
    			}	
    		};
    		Timer timer = new Timer();
    		timer.scheduleAtFixedRate(task, 0, FREQ_MAJ_SCORE); //0 de delay car on veut compter tout de suite le score

    		// On joue tant qu'il reste un joueur vivant
	    	do{
	    		// RAZ des collisions
    			for(int i=0;i<m.nbJoueurs;i++)
	            	collide[i]=false; 
            	
    			// Activation zone spéciale : 2% de chance
    			for (int i=0;i<m.nbJoueurs;i++){
    				if (v[i].score>=ACT_ZONE_SPE)
    					t.specialZone(2);
    			}

    			// Si joueur en vie il se déplace
	            for (int i=0;i<m.nbJoueurs;i++){
	            	if (v[i].exist)
	            		v[i].move(ax,ay,delta,i, inversed_keys);
	            }
            	
	            // Fond
            	StdDraw.clear(StdDraw.WHITE);
	            
            	// Affichage tunnel
            	t.getTunnel();
            	
            	// Vitesse de defilement tunnel
            	t.defileTunnel();
            	
            	// On remplit le tableau collide
            	collide = Vaisseau.collisionTunnel (v,m.nbJoueurs);
            	
            	// Gestion des collisions avec le tunnel (energie / invincibilité) et entre joueurs (rebonds)
            	Vaisseau.energieInvincible(v,m.nbJoueurs,nbJoueursCopie,collide);
            	Vaisseau.getRebondVaisseau(v,m.nbJoueurs);
	            
            	// Energie et score
            	StdDraw.setPenColor(Color.black);
	            Vaisseau.getEnergieScore(v,m.nbJoueurs);
	            
	            // Verif si au moins un joueur est en vie
	            move = false ;
	    		for (int i=0;i<m.nbJoueurs;i++){
	    			if (v[i].exist)
	    				move = true;
	    		}
	    		
	    		Vaisseau.draw(v, (int)(MS_IN_1SEC*delta),m.nbJoueurs);
	    	}while (move);
	    	
	    	// Temps d'attente avant image de game over
	    	try {
	    		Thread.sleep(SLEEP_TIME);
	    	}
	    	catch (InterruptedException ex) {
	    		Thread.currentThread().interrupt();
	    	}
	    	
	    	// Image fin de jeu
	    	StdDraw.clear();
	    	StdDraw.picture(Menu.POS_CENTER, Menu.POS_CENTER, "game_over.jpg", X_MAX, Y_MAX);
	    		
	    	// Vainqueur
	    	Vaisseau.vainqueur(v, m.nbJoueurs);
	    	
	    	// Try again?
	    	m.retourMenu();
	    	StdDraw.clear();
	    	StdDraw.setPenColor(Color.black);
	    	
	    	// Reinitialisation zone speciale
	    	inversed_keys = false;
	    	nbTours = 0;
	    	
	    }while(m.tryAgain);
    } 
}
