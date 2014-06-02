
public class Tunnel {


	static double[] tunnelHautPolygon = new double[ Simu.X_MAX + 2];
	static double[] tunnelBasPolygon = new double[ Simu.X_MAX + 2];
	static double tunnelHaut[]= new double [Simu.X_MAX];
	static double tunnelBas[]= new double [Simu.X_MAX];
	
	Tunnel(){
		//Initialisation	
		for (int i = 0; i < Simu.X_MAX; i++) {
			tunnelHaut[i] = 70;
			tunnelBas[i] = 20;
		}
	}

	public void tunnel() {
		do {
			//Ajout d'une valeur aleatoire dans la derniere case du tableau
			tunnelHaut[ Simu.X_MAX - 1] = tunnelHaut[ Simu.X_MAX - 2] + Math.random()*10-5;
			tunnelBas[ Simu.X_MAX - 1] = tunnelBas[Simu.X_MAX - 2] + Math.random()*10-5;
	
			if (tunnelHaut[ Simu.X_MAX - 1] > Simu.Y_MAX - 16) // pour que le decorhaut reste dans la fenetre
				tunnelHaut[ Simu.X_MAX - 1] = Simu.Y_MAX - 16;
			
			if (tunnelBas[ Simu.X_MAX - 1] < 0) // pour que le decorbas reste dans la fenetre
				tunnelBas[ Simu.X_MAX - 1] = 0;
		} 
		while ( tunnelHaut[ Simu.X_MAX - 1] - tunnelBas[ Simu.X_MAX - 1] <20);	
	}

	public void afficheTunnel() {
		double[] x = new double[ Simu.X_MAX + 2]; // remplissage du decors
		
		for (int j = 0; j < Simu.X_MAX + 2; j++) {
			if (j == 0) {
				x[j] = 0;
				tunnelHautPolygon[j] = Simu.Y_MAX - 15;
				tunnelBasPolygon[j] = 0;
			} else if (j ==Simu.X_MAX + 1) {
				x[j] = Simu.X_MAX;
				tunnelHautPolygon[j] = Simu.Y_MAX - 15;
				tunnelBasPolygon[j] = 0;
			} else {
				x[j] = j - 1;
				tunnelHautPolygon[j] = tunnelHaut[j - 1];
				tunnelBasPolygon[j] = tunnelBas[j - 1];
			}
		}
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledPolygon(x, tunnelHautPolygon);
		StdDraw.filledPolygon(x, tunnelBasPolygon);
	}

	public void decale() {
		for (int i = 0; i < Simu.X_MAX - 1; i++) {
			tunnelHaut[i] = tunnelHaut[i + 1];
			tunnelBas[i] = tunnelBas[i + 1];
		}
	}
	
	public void getTunnel(){
		tunnel();
		afficheTunnel();
		decale();
	}
	
	public void defilementTunnel(){
		int rdm=StdRandom.uniform(100);
    	if (rdm > 60){
    		decale();
    		decale();
    		decale();
    		decale();
    	}
	}
}


