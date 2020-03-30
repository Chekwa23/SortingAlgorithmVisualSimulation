package application;
//vm Arguments
//--module-path "..\SAVS\SortingAlgorithmVisualSimulation\lib" --add-modules=javafx.controls	
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class SAVS extends Application {
	ArrayList<Integer> def = new ArrayList<>(Arrays.asList(1,14,5,11,17,3,8,20,13,9,18,7,12,2,16,10,4,19,15,6));
	ArrayList<Points> points = new ArrayList<>();
	int arrSize = def.size();
	final int scWidth = 700;
	final int scHeight = 400;
	final int rectWidth = (scWidth-50)/arrSize;
	
	private class Points
	{
		double x;
		double y;
		public Points(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
	}
	
	private class myRectangle extends Rectangle
	{
		public myRectangle(Points xy, int width, int height)
		{
			super(xy.x, xy.y, width, height);
		}
	}
	
	
	
	@Override
	public void start(Stage primary) {
		Pane root = new Pane();
		Line yaxis = new Line(50,50,50,scHeight);
		Line xaxis = new Line(0,scHeight-50,scWidth,scHeight-50);
		Button startBtn = new Button("Start");
		startBtn.setMinSize(80, 30);
		root.getChildren().addAll(yaxis,xaxis,startBtn);
		for(int i = 0, x = 50; i < def.size(); i++, x+=rectWidth)
		{
			int height = def.get(i)*10;
			int y = scHeight - (50+height);
			Points temp = new Points(x,y);
			points.add(temp);
			myRectangle rect = new myRectangle(temp,rectWidth, height);
			rect.setStroke(Color.RED);
			rect.setFill(Color.BLACK);
			root.getChildren().add(rect);
		}
		
		Scene scene  = new Scene(root, scWidth, scHeight);
	    primary.setTitle("Sorting Algorithm Visual Simulation");
		primary.setScene(scene);
		primary.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
