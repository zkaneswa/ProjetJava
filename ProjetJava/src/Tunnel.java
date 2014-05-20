import java.awt.Color;
public class Tunnel {
	
	static double tunnelHaut[] = new double [Simu.X_MAX];
	static double tunnelBas[]= new double [Simu.X_MAX];
	static double tab_X[]= new double [Simu.X_MAX];
	
	Tunnel(){
		//Initialisation
		for (int i=0;i<Simu.X_MAX;i++){
			tunnelHaut[i]=70;
			tunnelBas[i]=30;
		}
	}
	
	public static double[] remplirTab(double[]tunnel){
		for (int i=0;i<tunnel.length;i++){
			if(i==0|| i==tunnel.length-1){
				tunnel[i]=90;
			}
			tunnel[i]=70+StdRandom.uniform(10);
		}
		return tunnel;
	}
	
	public static double[] tabX(){
		for (int i=0; i<Simu.X_MAX; i++){
			tab_X[i]=i;
		}
		return tab_X;
	}
	

	public void drawTunnel(double tunnel[]){
					
		StdDraw.setPenColor(Color.blue);
		StdDraw.filledPolygon(tabX(),remplirTab(tunnelHaut));
		
	}
	
}