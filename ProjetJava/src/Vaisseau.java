
import java.awt.event.KeyEvent;

public class Vaisseau {
		//Attributs de Vaisseau
		double px;
		double py;
		double rayon;
		double rebond;
		int energie=10;
		int exist=1;
		int score;
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
			py = py + ay*delta;//Pour la gravite
			
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
			}
			
			//Limites de la fenetre
	    	if(px<0) // a gauche
				px = 0;
			else if(px > xmax)// a droite
				px = xmax;
		}   
		
		//Affiche images vaisseaux
		public void draw(int player){
			if (player == 0){
				StdDraw.picture(px,py,"vaisseau1.png",40,35);
				//StdDraw.setPenColor(Color.black);
				//StdDraw.filledCircle(px-1.5, py+1.7, rayon);
			}else if (player==1){
				StdDraw.picture(px,py,"vaisseau2.png",40,35);
				//StdDraw.setPenColor(Color.blue);
				//StdDraw.filledCircle(px-1.5, py+1.7, rayon);
			}else{
				//image joueur 3
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
		
		
}
