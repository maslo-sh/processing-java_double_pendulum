package aprolabs;

import processing.core.PApplet;
import processing.core.PGraphics;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class DoublePendulumSimulator extends PApplet{
    public static float m1;
    public static float m2;
    public static float r1;
    public static float r2;
    int color = 100;
    int decrease;
    int targetRed = 0;
    int targetGreen = 255;
    int targetBlue = 0;
    int actualRed = 255;
    int actualGreen = 0;
    int actualBlue = 0;
    float angle1 = 49*PI/48;
    float angle2 = 23*PI/48;
    float angle1_velocity = 0;
    float angle2_velocity = 0;
    public static float g;
    public static String choosedType;
    PGraphics canvas;
    Queue<Coordinates> coords;

    public void settings(){
        size(800,800);
    }

    public void setup(){
        canvas = createGraphics(800,800);
        canvas.beginDraw();
        canvas.background(230);
        canvas.endDraw();

        coords = new LinkedList<>();
    }

    public void draw(){
        float num1 = -g * (2 * m1 + m2) * sin(angle1);
        float num2 = m2 * g * sin(angle1 - 2 * angle2);
        float num3_0 = 2 * sin(angle1 - angle2);
        float num3_1 = m2 * (angle2_velocity * angle2_velocity * r2 + angle1_velocity * angle1_velocity * r1 * cos(angle1 - angle2));
        float num4 = r1 * (2 * m1 + m2 - m2 * cos(2 * angle1 - 2 * angle2));

        float num5 = 2 * sin(angle1 - angle2);
        float num6 = angle1_velocity * angle1_velocity * r1 * (m1 + m2);
        float num7 = g * (m1 + m2) * cos(angle1);
        float num8 = angle2_velocity * angle2_velocity * r2 * m2 * cos(angle1 - angle2);
        float num9 = r2 * (2 * m1 + m2 - m2 * cos(2 * angle1 - 2 * angle2));

        float angle1_acceleration = (num1 - num2 - num3_0 * num3_1) / num4;
        float angle2_acceleration = (num5 * (num6 + num7 + num8)) / num9;

        image(canvas, 0, 0);
        stroke(0);
        strokeWeight(1.5F);
        fill(0);

        float x1 = r1 * sin(angle1);
        float y1 = r1 * cos(angle1);
        float x2 = x1 + r2 * sin(angle2);
        float y2 = y1 + r2 * cos(angle2);

        coords.add(new Coordinates(x1,x2,y1,y2));

        /*if (coords.size() == 50){
            Coordinates coord = coords.poll();
            canvas.stroke(230);
            canvas.line(0, 0, coord.getxP1(), coord.getyP1());
            canvas.line(coord.getxP1(), coord.getyP1(), coord.getxP2(), coord.getyP2());
            stroke(0);
        }*/

        translate(400,400);
        ellipse(0, 0,10, 10);
        line(0, 0, x1, y1);
        ellipse(x1,y1, m1/10, m1/10);
        line(x1, y1, x2, y2);
        ellipse(x2, y2, m2/10, m2/10);

        if (color == 255){
            decrease = -1;
        }
        else if (color == 100){
            decrease = 1;
        }
        color += decrease;

        canvas.beginDraw();
        canvas.translate(400,400);

        canvas.stroke(actualRed, actualGreen, actualBlue);
        if (actualRed == 255 && actualGreen == 255 && actualBlue == 0){
            targetRed = 0;
            targetGreen = 255;
            targetBlue = 0;
        }
        if (actualRed == 0 && actualGreen == 255 && actualBlue == 0){
            targetRed = 0;
            targetGreen = 255;
            targetBlue = 255;
        }
        if (actualRed == 0 && actualGreen == 255 && actualBlue == 255){
            targetRed = 0;
            targetGreen = 0;
            targetBlue = 255;
        }
        if (actualRed == 255 && actualGreen == 0 && actualBlue == 255){
            targetRed = 255;
            targetGreen = 0;
            targetBlue = 0;
        }
        if (actualRed == 0 && actualGreen == 0 && actualBlue == 255){
            targetRed = 255;
            targetGreen = 0;
            targetBlue = 255;
        }
        if (actualRed == 255 && actualGreen == 0 && actualBlue == 0){
            targetRed = 255;
            targetGreen = 255;
            targetBlue = 0;
        }

        if (actualRed > targetRed) actualRed--;
        if (actualRed < targetRed) actualRed++;

        if (actualGreen > targetGreen) actualGreen--;
        if (actualGreen < targetGreen) actualGreen++;

        if (actualBlue > targetBlue) actualBlue--;
        if (actualBlue < targetBlue) actualBlue++;

        canvas.strokeWeight(3);
        if(choosedType.equals("fale")) {
            canvas.line(0, 0, x1, y1);
            canvas.line(x1, y1, x2, y2);
        }
        else if (choosedType.equals("punkty")){
            canvas.point(x2, y2);
        }
        canvas.endDraw();


        angle1_velocity += angle1_acceleration;
        angle2_velocity += angle2_acceleration;
        angle1 += angle1_velocity;
        angle2 += angle2_velocity;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj wagę pierwszej kulki:");
        DoublePendulumSimulator.m1 = PApplet.parseFloat(sc.next());
        System.out.println("Podaj wagę drugiej kulki:");
        DoublePendulumSimulator.m2 = PApplet.parseFloat(sc.next());
        System.out.println("Podaj długość pierwszego ramienia:");
        DoublePendulumSimulator.r1 = PApplet.parseFloat(sc.next());
        System.out.println("Podaj długość drugiego ramienia:");
        DoublePendulumSimulator.r2 = PApplet.parseFloat(sc.next());
        System.out.println("Podaj wartość przyspieszenia grawitacyjnego:");
        DoublePendulumSimulator.g = PApplet.parseFloat(sc.next()) * 0.049949F;
        System.out.println("Wybierz pattern wzoru (punkty lub fale)");
        DoublePendulumSimulator.choosedType = sc.next();
        try {
            System.out.println("UWAGA! Program pochłania ok. 40% zasobów procesora 2.6GHz Intel Core i7. Nie zaleca się throttlowania komputera symulacją.");
            Thread.sleep(3500);
            System.out.println("Z uwagi na trudność w uwzględnieniu siły tarcia na powierzchni łożysk, dla pewnych danych wahadło wpada w ruch obrotowy kontynuowany w nieskończoność z coraz większą prędkością.");
            Thread.sleep(4500);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }

        PApplet.main("aprolabs.DoublePendulumSimulator");
    }
}
