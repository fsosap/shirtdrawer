package app;

public class Shirt {

    private double largo;
    private double ancho;
    private double espalda;
    private double cadera;

    private double manga;
    private double anchoPosY;
    private double largoPosX;

    private double posX;
    private double posY;

    public Shirt(double largo, double ancho, double espalda, double cadera, double posX, double posY){
        this.largo = largo;
        this.ancho = ancho;
        this.espalda = espalda;
        this.cadera = cadera;

        this.manga = (1.0/3)*espalda;
        this.anchoPosY = (2.0/3)*largo;
        this.largoPosX = (1.0/4)*cadera;

        this.posX = posX;
        this.posY = posY;
    }  

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }
    
    public void setLargo(double largo) {
        this.largo = largo;
    }

    public void setCadera(double cadera) {
        this.cadera = cadera;
    }

    public void setEspalda(double espalda) {
        this.espalda = espalda;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAnchoPosY() {
        return this.anchoPosY;
    }

    public double getLargo() {
        return largo;
    }

    public double getLargoPosX() {
        return largoPosX;
    }

    public double getEspalda() {
        return espalda;
    }

    public double getCadera() {
        return cadera;
    }

    public double getManga() {
        return manga;
    }
}
