
import java.awt.Color;
import java.awt.event.KeyEvent;

public class Vaisseau {
		//Attributs de Vaisseau
		double px;
		double py;
		double vx;
		double vy;
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
			rebond = reb;//Pour plus tard
		}
					
		public void move (double ax, double ay, double delta, int xmax, int ymax, int player){	
			px = px + ax*delta;
			py = py + ay*delta;
			
			//Commandes du joueur 1			
			if (player==0){
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
		    		py -= MOVE-0.5;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
		    		py += MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
		    		px -= MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
		    		px += MOVE+0.5;
		    //Commandes du joueur 2
			}else if (player==1){
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_S))
		    		py -= MOVE-0.5;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Z))
		    		py += MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Q))
		    		px -= MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_D))
		    		px += MOVE+0.5;
			}else{
				//Commandes du joueur3
				if(StdDraw.isKeyPressed(KeyEvent.VK_J))
		    		py -= MOVE-0.5;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_U))
		    		py += MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_H))
		    		px -= MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_K))
		    		px += MOVE+0.5;
			}
			
			//Limites de la fenetre
	    	if(px<3) // a gauche
				px =3 ;
			else if(px > xmax-3)// a droite
				px = xmax-3;
		}   
		
		//Affiche images vaisseaux
		public void draw(int player){
			if (player == 0){
				StdDraw.picture(px,py,"vaisseau1.png",40,35);
				
			}else if (player==1){
				StdDraw.picture(px,py,"vaisseau2.png",40,35);
			}else {
				StdDraw.picture(px,py,"vaisseau3.png",40,35);
			}
			
		}
		
		
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
		
		public static void rebondVaisseau(Vaisseau[]v, int l, int m){
			double dist = (v[l].px-v[m].px)* (v[l].px-v[m].px) +  (v[l].py-v[m].py)*(v[l].py-v[m].py);
        	if(dist <(v[m].rayon + v[l].rayon)*(v[m].rayon + v[l].rayon)){
        		if (v[m].py>v[l].py || v[m].px>v[l].px){
        			v[m].py+=2;
        			v[l].py-=2;
        		}else{
        			v[m].py-=2;
        			v[l].py+=2;
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
		
		
		
}
