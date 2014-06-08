
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Vaisseau {
	//Attributs de Vaisseau
	double px;
	double py;
	double rayon;
	double rebond;
	int energie=10;
	int score;
	boolean exist=true;
	boolean invincible;
	
	public final static float MOVE=1.5f;
	public final static int SPACE_BETWEEN_MSG = 5;
	
	// Carac img vaisseau
	public final static int VAISSEAU_SIZE_X = 40;
	public final static int VAISSEAU_SIZE_Y = 35;
	

	// Constructeur
	public Vaisseau(double x, double y, double r, double reb, int player){
		px = x;
		py = y;
		rayon = r;
		rebond = reb;
	}
	
	// Deplacement et commandes des joueurs
	public void move (double ax, double ay, double delta, int player, boolean inversed_keys){	
		px = px + ax*delta;
		py = py + ay*delta;
		int up=0, down=0, left=0, right=0, temp;
		
		// Commandes joueur 1
		if (player == 0){
			down=KeyEvent.VK_DOWN;
			up=KeyEvent.VK_UP;
			left=KeyEvent.VK_LEFT;
			right=KeyEvent.VK_RIGHT;
		}
		// Commandes joueur 2
		else if (player == 1){
			down=KeyEvent.VK_S;
			up=KeyEvent.VK_Z;
			left=KeyEvent.VK_Q;
			right=KeyEvent.VK_D;
		}
		// Commandes joueur 3
		else {
			down=KeyEvent.VK_J;
			up=KeyEvent.VK_U;
			left=KeyEvent.VK_H;
			right=KeyEvent.VK_K;
		}
		if (inversed_keys){
			// Inversion Up/Down
			temp=down;
			down=up;
			up=temp;
			
			// Inversion Left/Right
			temp=left;
			left=right;
			right=temp;
		}
		
		// DŽplacement du vaisseau			
	    if(StdDraw.isKeyPressed(down))
	    	py -= MOVE;
	    if(StdDraw.isKeyPressed(up))
	    	py += MOVE;
	    if(StdDraw.isKeyPressed(left))
	    	px -= MOVE;
	    if(StdDraw.isKeyPressed(right))
	    	px += MOVE;

		// Limites du bord gauche et droit de la fenetre
    	if(px<Simu.BORDER_X) 			// a gauche
			px = Simu.BORDER_X ;
		else if(px > Simu.BORDER_X_MAX) // a droite
			px = Simu.BORDER_X_MAX;
	}   
	
	// Affiche images vaisseaux
	public void drawShip(int player){
		String pathShip="";
		if (invincible)
			pathShip="vaisseauclign.png";
		else 
			pathShip="vaisseau"+(player+1)+".png";
		StdDraw.picture(px,py,pathShip,VAISSEAU_SIZE_X,VAISSEAU_SIZE_Y);
	}
	
	// Gestion mort vaisseau et appel de la fonction d'affichage
    public static void draw(Vaisseau[] v, int time, int nbJoueur){
    	for (int i=0;i<nbJoueur;i++){
    		if (v[i].energie == 0){							//Verif si joueur vivant
    			v[i].exist = false;
    			v[i].px = Simu.BORDER_X;					//Score arrete de s'incrementer
    		}else
    			v[i].drawShip(i);
    	}
    	
        // Display and pause
        StdDraw.show(time);	
    }
	
	// Gestion des collisions entre vaisseau et tunnel
	public static boolean[] collisionTunnel(Vaisseau[] v,int nbJoueur){
		boolean[] collide = new boolean[nbJoueur];
		for (int i=0; i<nbJoueur; i++){
        	if (v[i].py+v[i].rayon>=Tunnel.tunnelHautPolygon[(int)v[i].px]){ // Contre tunnel haut
        		v[i].py=Tunnel.tunnelHautPolygon[(int)v[i].px]-v[i].rayon;
        		collide[i] = true;
        	}
        	if (v[i].py-v[i].rayon<=Tunnel.tunnelBasPolygon[(int)v[i].px]){ // Contre tunnel bas
        		v[i].py=Tunnel.tunnelBasPolygon[(int)v[i].px]+v[i].rayon;
        		collide[i] = true;
        	}
		}
    	return collide;
  	}
	
	//Collision (rebond) entre 2 vaisseaux
	public static void rebondVaisseau(Vaisseau[]v, int l, int m){
		double dist = (v[l].px-v[m].px)* (v[l].px-v[m].px) +  (v[l].py-v[m].py)*(v[l].py-v[m].py);
    	if (dist <(v[m].rayon + v[l].rayon)*(v[m].rayon + v[l].rayon)){
    		if (v[m].py>v[l].py || v[m].px>v[l].px){
    			v[m].py+=v[m].rebond;
    			v[l].py-=v[l].rebond;
    		}else{
    			v[m].py-=v[m].rebond;
    			v[l].py+=v[l].rebond;
    		}
    	}
	}
	
	// Rebond entre les vaisseaux
	public static void getRebondVaisseau(Vaisseau[] v, int nbJoueur){
		if (nbJoueur>=2)
    		Vaisseau.rebondVaisseau(v,1,0);
    	if (nbJoueur>=3){
        	Vaisseau.rebondVaisseau(v,2,0);
        	Vaisseau.rebondVaisseau(v,2,1);
    	}
	}
	
	// Affiche vainqueur
	public static void vainqueur(Vaisseau[]v, int nbJoueur){
		StdDraw.setPenColor(Color.red);
		int winner = 0;
		for (int i=0;i<nbJoueur;i++){
			if (v[i].score>v[winner].score)
				winner = i;
		}
		if(nbJoueur>=2){
			String w = "Le vainqueur est le joueur "+String.valueOf(winner+1)+" avec comme score "+String.valueOf(v[winner].score)+".";
			StdDraw.text(Menu.POS_CENTER, Menu.POS_LVL3, w);
		}else{
			String w = "Vous avez perdu avec comme score "+String.valueOf(v[winner].score)+".";
			StdDraw.text(Menu.POS_CENTER, Menu.POS_LVL3, w);
		}
		StdDraw.show(Simu.MS_IN_1SEC);
	}

	// Gestion invincibilite et perte energie
	public static void energieInvincible(final Vaisseau[] v, int nbJoueur, final int nbJoueursCopie, boolean [] c){
		for (int i=0; i<nbJoueur; i++){
    		if (c[i] && v[i].energie > 0 && v[i].invincible == false){
    			v[i].energie--;
    			v[i].invincible = true;
		
    			// Invincibilite d'une sec apres collision
        		TimerTask task2 = new TimerTask(){
        			public void run(){
        				for (int i=0;i<nbJoueursCopie;i++)
        					v[i].invincible=false;
        			}	
        	    };
    	        Timer timer2 = new Timer();
    	        timer2.schedule(task2, Simu.MS_IN_1SEC);
    		}
    	}
	}
		
	// Affichage energie et score
	public static void getEnergieScore(Vaisseau[] v, int nbJoueur){
		// Energie
		String [] e = new String [nbJoueur];
        for (int i=0;i<nbJoueur;i++){
        	e [i] = "Energie joueur "+ (i+1) +" : " +String.valueOf(v[i].energie);
        	StdDraw.text(Menu.POS_LVL1_X,Simu.Y_MAX-i*SPACE_BETWEEN_MSG,e[i]);
        }
    	
	    // Score
        String [] s = new String [nbJoueur];
        for (int i=0;i<nbJoueur;i++){
        	s [i] = "Score joueur "+ (i+1) +" : " +String.valueOf(v[i].score);
        	StdDraw.text(Menu.POS_LVL2_X,Simu.Y_MAX-i*SPACE_BETWEEN_MSG,s[i]);
        }
	}
}
