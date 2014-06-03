
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
		int exist=1;
		int score;
		boolean invincible;
		public final static int  MOVE=1;

		//Constructeur
		public Vaisseau(double x, double y, double r, double reb, int player){
			px = x;
			py = y;
			rayon = r;
			rebond = reb;
		}
					
		public void move (double ax, double ay, double delta, int xmax, int ymax, int player, boolean inversed_keys){	
			px = px + ax*delta;
			py = py + ay*delta;
			int up=0, down=0, left=0, right=0, temp;
			
			if (player == 0){
				down=KeyEvent.VK_DOWN;
				up=KeyEvent.VK_UP;
				left=KeyEvent.VK_LEFT;
				right=KeyEvent.VK_RIGHT;
			}
			else if (player == 1){
				down=KeyEvent.VK_S;
				up=KeyEvent.VK_Z;
				left=KeyEvent.VK_Q;
				right=KeyEvent.VK_D;
			}
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
		    	py -= MOVE-0.5;
		    if(StdDraw.isKeyPressed(up))
		    	py += MOVE+0.5;
		    if(StdDraw.isKeyPressed(left))
		    	px -= MOVE;
		    if(StdDraw.isKeyPressed(right))
		    	px += MOVE+0.5;

			//Limites de la fenetre
	    	if(px<5) // a gauche
				px =5 ;
			else if(px > xmax-3)// a droite
				px = xmax-3;
		}   
		
		
		
		//Affiche images vaisseaux
		public void draw(int player){
			String pathShip="";
			if (invincible)
				pathShip="vaisseauclign.png";
			else 
				pathShip="vaisseau"+(player+1)+".png";
				StdDraw.picture(px,py,pathShip,40,35);

		}
		//Gerer les collisions entre vaisseau et tunnel
		public static int[] collisionTunnel(Vaisseau[] v,int nbJoueur){
			int[] collide = new int[nbJoueur];
			for (int i=0; i<nbJoueur; i++){
	        	if (v[i].py+v[i].rayon>=Tunnel.tunnelHautPolygon[(int)v[i].px]){ //Contre tunnel haut
	        		v[i].py=Tunnel.tunnelHautPolygon[(int)v[i].px]-v[i].rayon;
	        		collide[i]=1;
	        	}
	        	if (v[i].py-v[i].rayon<=Tunnel.tunnelBasPolygon[(int)v[i].px]){ //Contre tunnel bas
	        		v[i].py=Tunnel.tunnelBasPolygon[(int)v[i].px]+v[i].rayon;
	        		collide[i] = 1;
	        	}
			}
        	return collide;
      	}
		
		
		//Collision (rebond) entre les vaisseaux
		public static void rebondVaisseau(Vaisseau[]v, int l, int m){
			double dist = (v[l].px-v[m].px)* (v[l].px-v[m].px) +  (v[l].py-v[m].py)*(v[l].py-v[m].py);
        	if(dist <(v[m].rayon + v[l].rayon)*(v[m].rayon + v[l].rayon)){
        		if (v[m].py>v[l].py || v[m].px>v[l].px){
        			v[m].py+=v[m].rebond;
        			v[l].py-=v[l].rebond;
        		}else{
        			v[m].py-=v[m].rebond;
        			v[l].py+=v[l].rebond;
        		}
        	}
		}
		
		
		//Affiche vainqueur
		public static void vainqueur(Vaisseau[]v, int nbJoueur){
			StdDraw.setPenColor(Color.red);
			int winner = 0;
			for (int i=0;i<nbJoueur;i++){
				if (v[i].score>v[winner].score)
					winner=i;
			}
			if(nbJoueur>=2){
				String w = "Le vainqueur est le joueur "+String.valueOf(winner+1)+" avec comme score "+String.valueOf(v[winner].score)+".";
				StdDraw.text(50, 30, w);
			}else{
				String w = "Vous avez perdu avec comme score "+String.valueOf(v[winner].score)+".";
				StdDraw.text(50, 30, w);
			}
			StdDraw.show(1000);
		}

		public static void energieInvincible(final Vaisseau[] v, int nbJoueur, final int nbJoueursCopie, int[] c){
			for (int i=0; i<nbJoueur; i++){
        		if (c[i] ==1 && v[i].energie > 0 && v[i].invincible == false){
        			v[i].energie--;
        			v[i].invincible=true;
        			
   			
        			//Invincibilite d'une sec apres collision
            		TimerTask task2 = new TimerTask(){
            			public void run(){
            				for (int i=0;i<nbJoueursCopie;i++)
            					v[i].invincible=false;
            			}	
            	    };
        	        Timer timer2 = new Timer();
        	        timer2.schedule(task2, 1000);
        		}
        	}
		}
		
		public static void getRebondVaisseau(Vaisseau[] v, int nbJoueur){
			if (nbJoueur>=2)
        		Vaisseau.rebondVaisseau(v,1,0);
        	if (nbJoueur>=3){
            	Vaisseau.rebondVaisseau(v,2,0);
            	Vaisseau.rebondVaisseau(v,2,1);
        	}
		}
		
		public static void getEnergieScore(Vaisseau[] v, int nbJoueur){
			//Energie
			String [] e = new String [nbJoueur];
            for (int i=0;i<nbJoueur;i++){
            	e [i] = "Energie joueur "+ (i+1) +" : " +String.valueOf(v[i].energie);
            	StdDraw.text(30,95-i*5,e[i]);
            }
        	
		    //Score
            String [] s = new String [nbJoueur];
            for (int i=0;i<nbJoueur;i++){
            	s [i] = "Score joueur "+ (i+1) +" : " +String.valueOf(v[i].score);
            	StdDraw.text(80,95-i*5,s[i]);
            }
		}
		

}
