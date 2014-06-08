
public class Tunnel {

	static double[] tunnelHautPolygon = new double[Simu.X_MAX+2];
	static double[] tunnelBasPolygon = new double[ Simu.X_MAX+2];
	static double tunnelHaut[]= new double [Simu.X_MAX];
	static double tunnelBas[]= new double [Simu.X_MAX];
	
	public final static int BORDER_TUNNEL_Y_MAX = 16;
	public final static int INNER_BORDER_TUNNEL_Y_MAX = 15;
	public final static int BORDER_TUNNEL_Y = 0;
	public final static int INNER_BORDER_TUNNEL_Y = -1;
	public static final int NB_TOUR_BOUCLE = 100;
	public static final int ECART_TUNNEL = 20;
	public static final int FRQ_DEFILE_TUNNEL = 40;
	
	Tunnel(){
		//Initialisation	
		for (int i = 0; i < Simu.X_MAX; i++) {
			tunnelHaut[i] = 70;
			tunnelBas[i] = 20;
		}
	}

	// Remplissage des tableaux avec des valeurs aleatoires
	public void tunnel() {
		do {
			//Ajout d'une valeur aleatoire dans la derniere case du tableau
			tunnelHaut[Simu.X_MAX-1] = tunnelHaut[Simu.X_MAX-2] + Math.random()*10-5;
			tunnelBas[Simu.X_MAX-1] = tunnelBas[Simu.X_MAX-2] + Math.random()*10-5;
	
			if (tunnelHaut[Simu.X_MAX-1] > Simu.Y_MAX - BORDER_TUNNEL_Y_MAX) 	// Verif si cette nvlle valeur depasse tunnel
				tunnelHaut[Simu.X_MAX-1] = Simu.Y_MAX - BORDER_TUNNEL_Y_MAX;		//pour que le tunnelhaut reste dans la fenetre
			
			if (tunnelBas[Simu.X_MAX-1] < BORDER_TUNNEL_Y) 					// pour que le tunnelbas reste dans la fenetre
				tunnelBas[Simu.X_MAX-1] = BORDER_TUNNEL_Y;
		}while (tunnelHaut[Simu.X_MAX-1] - tunnelBas[Simu.X_MAX-1] < ECART_TUNNEL);	
	}

	// Affichage du tunnel
	public void afficheTunnel() {
		// Remplissage des tableaux pour le tunnel
		double[] x = new double[Simu.X_MAX+2];
		
		for (int j = 0; j < Simu.X_MAX+2; j++) {
			if (j == 0) {
				x[j] = 0;
				tunnelHautPolygon[j] = Simu.Y_MAX - INNER_BORDER_TUNNEL_Y_MAX;
				tunnelBasPolygon[j] = INNER_BORDER_TUNNEL_Y;
			} else if (j == Simu.X_MAX + 1) {
				x[j] = Simu.X_MAX;
				tunnelHautPolygon[j] = Simu.Y_MAX - INNER_BORDER_TUNNEL_Y_MAX;
				tunnelBasPolygon[j] = INNER_BORDER_TUNNEL_Y;
			} else {
				x[j] = j - 1;
				tunnelHautPolygon[j] = tunnelHaut[j-1];
				tunnelBasPolygon[j] = tunnelBas[j-1];
			}
		}
		// Coloration zone speciale
		if (Simu.inversed_keys)
			StdDraw.setPenColor(StdDraw.BLUE);
		else
			StdDraw.setPenColor(StdDraw.RED);
		
		StdDraw.filledPolygon(x, tunnelHautPolygon);
		StdDraw.filledPolygon(x, tunnelBasPolygon);
	}

	// Defilement du tunnel
	public void decale() {
		for (int i = 0; i < Simu.X_MAX-1; i++) {
			tunnelHaut[i] = tunnelHaut[i+1];
			tunnelBas[i] = tunnelBas[i+1];
		}
	}
	
	// Remplissage + affichage + defilement du tunnel
	public void getTunnel(){
		tunnel();
		afficheTunnel();
		decale();
	}

	//Vitesse de defilement tunnel
	public void defileTunnel(){
		int rdm=StdRandom.uniform(100);
    	if (rdm < FRQ_DEFILE_TUNNEL){
    		decale();
    		decale();
    		decale();
    		decale();
    	}
	}
	
	// Zone speciale
	public void specialZone(int pourcentInvKey){
		int rdm=StdRandom.uniform(100);
		if (rdm < pourcentInvKey && Simu.nbTours == 0)
			Simu.inversed_keys=true;

		if (Simu.inversed_keys){
			if (Simu.nbTours > NB_TOUR_BOUCLE){
				Simu.inversed_keys=false;
				Simu.nbTours=-1;
			}
			Simu.nbTours++;
		}
	}
}


