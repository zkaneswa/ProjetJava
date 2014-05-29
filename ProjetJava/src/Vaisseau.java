
import java.awt.event.KeyEvent;

public class Vaisseau {
		//Attributs de Vaisseau
		double px;
		double py;
		double rayon;
		double rebond;
		int energie=10;
		boolean collision = false;
		public final static int  MOVE=1;
		public final static int PLAYER1=0;
		public final static int PLAYER2=1;
		int exist=1;
		int score;

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
			if (player==PLAYER1){
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN))
		    		py -= MOVE-0.5;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_UP))
		    		py += MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT))
		    		px -= MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT))
		    		px += MOVE+0.5;
		    //Commandes du joueur 2
			}else{
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_S))
		    		py -= MOVE-0.5;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Z))
		    		py += MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Q))
		    		px -= MOVE;
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_D))
		    		px += MOVE+0.5;
			}
			
			//Limites de la fenetre
	    	if(px<0) // a gauche
				px = 0;
			else if(px > xmax)// a droite
				px = xmax;
		}   
		
		//Affiche images vaisseaux
		public void draw(int player){
			if (player == PLAYER1){
				StdDraw.picture(px,py,"vaisseau1.png",40,35);
				//StdDraw.setPenColor(Color.black);
				//StdDraw.filledCircle(px-1.5, py+1.7, rayon);
			}else{
				StdDraw.picture(px,py,"vaisseau2.png",40,35);
				//StdDraw.setPenColor(Color.blue);
				//StdDraw.filledCircle(px-1.5, py+1.7, rayon);
			}
		}
		
		/*public int collision(double x, double y){
			double dist = Math.pow((x-px), 2) + Math.pow((y-py), 2);
			int collision = 0;
			if (dist<=rayon){
				collision = 1;
			}
			return collision;
		}*/
		
		public static int[] collisionTunnel(Vaisseau[] v){
			int[] collide = {0, 0};
			for (int i=0; i<=1; i++){
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
		
		/*public void collisionVaisseau(){
			double dist = Math.pow((this.px-px), 2) + Math.pow((this.py-py), 2);
			if(dist<=rayon*rayon){
				
			}
		}*/
}
