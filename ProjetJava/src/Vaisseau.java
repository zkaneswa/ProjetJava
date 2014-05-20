import java.awt.Color;
import java.awt.event.KeyEvent;
public class Vaisseau {
	
		double px;
		double py;
		double vx;
		double vy;
		double rayon;
		double rebond;
		Color color;
		public final static int  MOVE=1;
		public final static int PLAYER1=1;
		public final static int PLAYER2=2;


		public Vaisseau(double x, double y, double r, double reb, int player){
			px = x;
			py = y;
			rayon = r;
			rebond = reb;
			//color = c;
		}

					
		public void move (double ax, double ay, double delta, int xmax, int ymax, int player){
			
			px = px + ax*delta;
			py = py + ay*delta;
			
			if (player==PLAYER1){
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_DOWN)){
		    		py-= MOVE-0.5;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_UP)){
		    		py+= MOVE;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
		    		px-=MOVE;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
		    		px+=MOVE+0.5;
		    		
		    	}
			}else{
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_S)){
		    		py-= MOVE-0.5;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Z)){
		    		py+= MOVE;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_Q)){
		    		px-=MOVE;
		    	}
		    	if(StdDraw.isKeyPressed(KeyEvent.VK_D)){
		    		px+=MOVE+0.5;
		    	}
			}
		    	
		    	if(px<0){ // rebond sur le mur de gauche
					px = 0;
					
				} else if(px > xmax) { // rebond sur le mur de droite
					px = xmax;			
				}
				
				if(py<0){ // rebond sur le sol
					py = 0;
								
				} else if(py>ymax){ // rebond sur le plafond
					py = ymax;			
				}
		}    
		
		public void draw(int player){
	    	//StdDraw.setPenColor(color);
			if (player == PLAYER1){
				StdDraw.picture(px,py,"vaisseau1.png",30,25);
			}else{
				StdDraw.picture(px,py,"vaisseau2.png",30,25);
			}
		}
}
