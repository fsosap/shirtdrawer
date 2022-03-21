package app;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Drawer extends JPanel{
    Shirt shirt;
    int xMax;
    int yMax;
    boolean rota;
    double angle;

    public void setShirt(Shirt shirt) {
        this.shirt = shirt;
    }

    public void setRota(boolean rota, double angle) {
        this.rota = rota;
        this.angle = angle;
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setLayout(null);
        drawCCS(g);

        this.xMax = 1390;
        this.yMax = 660;

        if(rota){
            rotate(g);
        }else{
            printShirt(g);
        }
    }

    private void printCurve(Graphics g, double[] inputX, double[] inputY){
        //draw start, end and control points, and the lines between them
        // for(int i = 0; i < inputX.length; i++){
        //     drawPoint(g, inputX[i], inputY[i]);
        //     if(i > 0){
        //         drawLine(g, inputX[i-1], inputY[i-1], inputX[i], inputY[i]);
        //     }
        // }
        
        double [][] points = new double[100][2];
        int iter = 0;
        //get the outline points of the courve
        for(double t=0.0; t < 1.0; t += 0.01){
            double [] resul = DeCasteljau.run(t, inputX, inputY);
            points[iter][0]=resul[0];
            points[iter][1]=resul[1];
            iter++;
            //drawPoint(g, resul[0], resul[1]);
        }

        //print the points and interpolate
        for(int i = 0; i< points.length; i++){
            double x1= points[i][0];
            double y1= points[i][1];
            //drawPoint(g, x1, y1);
            if(i < points.length-1){
                double x2 = points[i+1][0];
                double y2 = points[i+1][1];
                //drawPoint(g, x2 , y2);
                drawLine(g, x1, y1, x2, y2);
            }
        }
    }

    private void rotate(Graphics g){
        ArrayList<Double> points = new ArrayList();

        double coorX= shirt.getPosX();
        double coorY= shirt.getPosY();

        double largo = shirt.getLargo();
        double ancho = shirt.getAncho();
        double cadera = shirt.getCadera();
        double espalda = shirt. getEspalda();

        points.add((cadera/2)-(espalda/2));
        points.add((15.0/16)*largo);
        //->manga
        points.add((cadera/2)-(espalda/2)-(1.0/3*espalda));
        points.add((15.0/16)*largo-(1.0/9)*espalda);

        points.add((cadera/2)+(espalda/2));
        points.add((15.0/16)*largo);
        //->manga
        points.add((cadera/2)+(espalda/2)+(1.0/3*espalda));
        points.add((15.0/16)*largo-(1.0/9)*espalda);

        points.add((cadera/2)-(ancho/2));
        points.add((2.0/3)*largo);
        //->manga
        points.add((cadera/2)-(espalda/2));
        points.add((3.0/5)*largo);

        points.add((cadera/2)+(ancho/2));
        points.add((2.0/3)*largo);
        //->manga
        points.add((cadera/2)+(espalda/2));
        points.add((3.0/5)*largo);

        points.add(0.0);
        points.add(0.0);
        //->tronco izq
        points.add((cadera/2)-ancho/2);
        points.add((2.0/3)*largo);

        points.add(cadera);
        points.add(0.0);
        //->tronco der
        points.add((cadera/2)+ancho/2);
        points.add((2.0/3)*largo);

        points.add(0.0);
        points.add(0.0);
        //->cadera
        points.add(cadera);
        points.add(0.0);

        points.add((cadera/2)-(espalda/2));
        points.add((15.0/16)*largo);
        //->top1
        points.add((1.0/4)*cadera);
        points.add(largo);

        points.add((cadera/2)+(espalda/2));
        points.add((15.0/16)*largo);
        //->
        points.add((3.0/4)*cadera);
        points.add(largo);

        points.add((1.0/4)*cadera);
        points.add(largo);
        //->
        points.add((3.0/4)*cadera);
        points.add(largo);

        points.add((cadera/2)-(espalda/2));
        points.add((3.0/5)*largo);
        //->
        points.add((cadera/2)-(espalda/2)-(1.0/3)*espalda);
        points.add((15.0/16)*largo-(1.0/9)*espalda);

        points.add((cadera/2)+(espalda/2));
        points.add((3.0/5)*largo);
        //->
        points.add((cadera/2)+(espalda/2)+(1.0/3)*espalda);
        points.add((15.0/16)*largo-(1.0/9)*espalda);

        points = rotatePoints(points);

        ArrayList<Double> transPoints = new ArrayList();
        for(int i =0; i< points.size(); i++){
            if(i%2==0){
                transPoints.add(points.get(i)+coorX);
            }else{
                transPoints.add(points.get(i)+coorY);
            }
        }

        printShirtRotated(g, transPoints);
    }

    private void printShirtRotated(Graphics g, ArrayList<Double> points){
        for(int i = 0; i< points.size(); i+=4){
            drawLine(g, points.get(i), points.get(i+1), points.get(i+2), points.get(i+3));
        }
    }

    private ArrayList<Double> rotatePoints(ArrayList<Double> points){
        ArrayList<Double> result = new ArrayList();
        for(int i = 0; i < points.size(); i++){
            if(i%2==0){
                result.add((Math.cos(angle)*points.get(i))-(Math.sin(angle)*points.get(i+1)));
            }else{
                result.add((Math.sin(angle)*points.get(i-1))+(Math.cos(angle)*points.get(i)));
            }
        }
        return result;
    }


    private void printShirt(Graphics g){
        double coorX= shirt.getPosX();
        double coorY= shirt.getPosY();
        //the shirt must be created wit
        //the shirt is made with 11 bezier curves.

        //Draw lines.

        double largo = shirt.getLargo();
        double ancho = shirt.getAncho();
        double cadera = shirt.getCadera();
        double espalda = shirt. getEspalda();

        g.setColor(Color.GREEN);
        //draw largo line.
        double positionA = (1.0/4)*cadera;
        double positionB = (3.0/4)*cadera;
        drawLine(g, coorX+positionA, coorY, coorX+positionA, coorY+largo);
        drawLine(g, coorX+positionB, coorY, coorX+positionB, coorY+largo);
        //draw cadera line.
        positionA = (1.0/16)*largo;
        drawLine(g, coorX, coorY+positionA, coorX+cadera, coorY+positionA);
        double half = cadera/2;
        //draw ancho line
        positionA = (2.0/3)*largo;
        drawLine(g, (coorX+half)-(ancho/2), coorY+positionA, (coorX+half)+(ancho/2), coorY+positionA);
        //draw espalda line
        positionA = (15.0/16)*largo;
        drawLine(g, (coorX+half)-(espalda/2), coorY+positionA, (coorX+half)+(espalda/2), coorY+positionA);
        //draw manga line
        g.setColor(Color.BLACK);
        positionB = (1.0/9)*espalda;
        double betwAE = (1.0/5)*largo;
        double mangaPosX= (1.0/3)*espalda;
        drawLine(g, (coorX+half)-(espalda/2), coorY+positionA, (coorX+half)-(espalda/2)-mangaPosX, coorY+positionA-positionB);
        drawLine(g, (coorX+half)+(espalda/2), coorY+positionA, (coorX+half)+(espalda/2)+mangaPosX, coorY+positionA-positionB);
        drawLine(g, (coorX+half)-(ancho/2), coorY+(2.0/3)*largo, (coorX+half)-(espalda/2), coorY+(3.0/5)*largo);
        drawLine(g, (coorX+half)+(ancho/2), coorY+(2.0/3)*largo, (coorX+half)+(espalda/2), coorY+(3.0/5)*largo);
        
        //print left side
        double inputX1[] = {coorX, coorX+0.09, (coorX+half)-ancho/2};
        double inputY1[] = {coorY, coorY+(1.0/3)*largo ,coorY+(2.0/3)*largo};
        printCurve(g, inputX1, inputY1);
        //print right side
        double inputX2[] = {coorX+cadera, coorX+cadera-0.09, (coorX+half)+ancho/2};
        double inputY2[] = {coorY, coorY+(1.0/3)*largo ,coorY+(2.0/3)*largo};
        printCurve(g, inputX2, inputY2);
        //print bottom
        double inputX3[]= {coorX, coorX+cadera/3, coorX+(cadera/3)*2 ,coorX+cadera};
        double inputY3[]= {coorY, coorY+0.1, coorY-0.1, coorY};
        printCurve(g, inputX3, inputY3);
        //print top 
        double inputX4[]= {(coorX+half)-(espalda/2), (coorX+half)-(espalda/4), coorX+(1.0/4)*cadera};
        double inputY4[]= {coorY+positionA, coorY+positionA, coorY+largo};
        printCurve(g, inputX4, inputY4);
        double inputX5[]= {(coorX+half)+(espalda/2), (coorX+half)+(espalda/4), coorX+(3.0/4)*cadera};
        double inputY5[]= {coorY+positionA, coorY+positionA, coorY+largo};
        printCurve(g, inputX5, inputY5);
        double inputX6[]= {coorX+(1.0/4)*cadera, coorX+(2.0/4)*cadera, coorX+(3.0/4)*cadera};
        double inputY6[]= {coorY+largo, coorY+largo-0.09, coorY+largo};
        printCurve(g, inputX6, inputY6);
        double inputX7[]= {coorX+(1.0/4)*cadera, coorX+(2.0/4)*cadera, coorX+(3.0/4)*cadera};
        double inputY7[]= {coorY+largo, coorY+largo-0.6, coorY+largo};
        printCurve(g, inputX7, inputY7);
        //print external manga
        double inputX8[]= {(coorX+half)-(espalda/2), (coorX+half)-(espalda/2)-mangaPosX, (coorX+half)-(espalda/2)-mangaPosX};
        double inputY8[]= {coorY+(3.0/5)*largo, coorY+(3.0/5)*largo+betwAE/2, coorY+positionA-positionB};
        printCurve(g, inputX8, inputY8);
        double inputX9[]= {(coorX+half)+(espalda/2), (coorX+half)+(espalda/2)+mangaPosX, (coorX+half)+(espalda/2)+mangaPosX};
        double inputY9[]= {coorY+(3.0/5)*largo, coorY+(3.0/5)*largo+betwAE/2, coorY+positionA-positionB};
        printCurve(g, inputX9, inputY9);
    }

    //Draw Cartesian Coordinate System
    private void drawCCS(Graphics g){
        
        int count = 1;

        g.setColor(Color.BLACK);
        //draw x axis
        g.drawLine(50, yMax-60, xMax-50, yMax-60);
        g.drawLine(xMax-70, yMax-70, xMax-50, yMax-60);
        g.drawLine(xMax-70, yMax-50, xMax-50, yMax-60);
        //draw x labels
        for(int i=80; i < xMax-50; i+=30){
            g.drawLine(i, yMax-60, i, yMax-58);

            JLabel jl = new JLabel(Integer.toString(count));
            if(count > 9){
                jl.setBounds(i-7, yMax-54, 16, 10);
            }else{
                jl.setBounds(i-3, yMax-54, 16, 10);
            }
            this.add(jl);

            count++;
        }

        //draw y axis
        g.drawLine(50, yMax-60, 50, 40);
        g.drawLine(40, 60, 50, 40);
        g.drawLine(60, 60, 50, 40);
        
        count = 1;
        //draw y labels
        for(int i=yMax-90; i > 50; i-=30){
            g.drawLine(50, i, 48, i);

            JLabel jl = new JLabel(Integer.toString(count));
            if(count > 9){
                jl.setBounds(32, i-4, 16, 10);
            }else{
                jl.setBounds(38, i-4, 16, 10);
            }
            this.add(jl);

            count++;
        }
    }

    public void drawPoint(Graphics g, double x, double y){
        double xTrans = (x*30)+50;
        double yTrans = (yMax-60)-(y*30); 
        g.fillOval((int)xTrans-2, (int)yTrans-2, 4, 4);
    }

    public void drawLine(Graphics g, double x1, double y1, double x2, double y2){
        g.drawLine((int)((x1*30)+50), (int)((yMax-60)-(y1*30)), (int)((x2*30)+50), (int)((yMax-60)-(y2*30)));
    }
}
