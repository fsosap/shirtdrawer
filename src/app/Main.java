package app;

import java.util.Scanner;
import javax.swing.JFrame;

public class Main{
    public static void main(String [] args){
        Scanner user= new Scanner(System.in);
        System.out.println("Bienvenido! a continuación escribe las dimensiones de tu camisa:");
        System.out.print("ancho:");
        double ancho = user.nextDouble();
        System.out.print("largo:");
        double largo = user.nextDouble();
        System.out.print("cadera:");
        double cadera = user.nextDouble();
        System.out.print("espalda:");
        double espalda = user.nextDouble();
        
        System.out.println("Seleccione la posición");
        System.out.print("posición en X:");
        double x = user.nextDouble();
        System.out.print("posición en Y:");
        double y = user.nextDouble();
        final Shirt shirt = new Shirt(largo, ancho, espalda, cadera, x, y);

        Drawer d = new Drawer();

        d.setShirt(shirt);
        
        JFrame jf = new JFrame();
        jf.setTitle("Bezier curves in 2D");
        jf.setSize(1400,700);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.add(d);

        Thread thread= new Thread(new Runnable() {
            public void run(){
                while(true){
                    Scanner user = new Scanner(System.in);
                    System.out.println("¿Que acción desea ejecutar?\n 1. trasladar\n 2. escalar\n 3. rotar");

                    int response = user.nextInt();
                    if(response == 1){
                        System.out.println("¿a donde desea mover la camisa?");
                        System.out.print("x: ");
                        double x= user.nextDouble();
                        System.out.print("y: ");
                        double y= user.nextDouble();
                        shirt.setPosX(x);
                        shirt.setPosY(y);
                        d.setShirt(shirt);
                        jf.repaint();
                    }

                    if(response == 2){
                        System.out.print("valor para escalar:");
                        double escala = user.nextDouble();
                        shirt.setAncho(shirt.getAncho()*escala);
                        shirt.setLargo(shirt.getLargo()*escala);
                        shirt.setEspalda(shirt.getEspalda()*escala);
                        shirt.setCadera(shirt.getCadera()*escala);
                        d.setShirt(shirt);
                        jf.repaint();
                    }

                    if(response == 3){
                        System.out.println("ingresa el angulo a rotar");
                        double angle = Math.toRadians(user.nextDouble());
                        d.setRota(true, angle);
                        jf.repaint();
                        try{
                            Thread.sleep(5000);
                        }catch(Exception e){
                            System.err.print("ERROR EN EL HILO");
                        }
                        d.setRota(false, 0.0);
                        jf.repaint();
                    }

                    try{
                        Thread.sleep(500);
                    }catch(Exception e){
                        System.err.print("ERROR EN EL HILO");
                    }
                    
                }
            }
        });
        thread.start();
    }

    static void setPosition(Drawer d, double newX, double newY){
        d.shirt.setPosX(newX);
        d.shirt.setPosY(newY);
    }


    static void escale(){

    }
}
