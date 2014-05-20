import java.awt.Color;
public class Tunnel {
	
	static int tunnelHaut[] = new int [Simu.X_MAX];
	static int tunnelBas[]= new int [Simu.X_MAX];
	
	Tunnel(){
		//Initialisation
		for (int i=0;i<Simu.X_MAX;i++){
			tunnelHaut[i]=70;
			tunnelBas[i]=30;
		}
	}
	
	public static int[] remplirTab(int[]tunnel){
		for (int i=0;i<tunnel.length;i++){
			if (i==0 || i==tunnel.length-1)
				tunnel[i]=90;
			tunnel[i]=70+StdRandom.uniform(10);
		}
		return tunnel;
	}
	
	public void drawTunnel(int tunnel[]){
		remplirTab(tunnelHaut);
		for (int i=0;i<Simu.X_MAX-1;i++){
			int y = tunnelHaut[i];
			int z = tunnelHaut[i+1];
			StdDraw.setPenColor(Color.red);
			StdDraw.line(i,y,i+1,z);
		}
	}
	
}