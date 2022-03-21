package app;

public class DeCasteljau {

    public static double[] run(double t, double[] coefsX, double[] coefsY){
        double [] betaX = coefsX;
        double [] betaY = coefsY;
        int n = betaX.length;
        for(int j = 1; j < n; j++){
            for(int k = 0; k < n - j; k++){
                betaX[k] = (betaX[k] * (1-t)) + (betaX[k+1] * t);
                betaY[k] = (betaY[k] * (1-t)) + (betaY[k+1] * t);
            }
        }
        double coord[] = {betaX[0], betaY[0]};
        return coord;
    }
}
