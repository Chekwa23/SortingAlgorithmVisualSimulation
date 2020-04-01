package application;
//vm Arguments
//--module-path "..\SAVS\SortingAlgorithmVisualSimulation\lib" --add-modules=javafx.controls	
import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.StrokeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;


public class SAVS extends Application {
	
	//Constant variables
	final int scWidth = 700;
	final int scHeight = 400;
	
	//Lists
	ArrayList<Integer> def = new ArrayList<>(Arrays.asList(1,14,5,11,17,3,8,20,13,9,18,7,12,2,16,10,4,19,15,6));
	ArrayList<Points> points = new ArrayList<>();
	ArrayList<myRectangle> rectList = new ArrayList<>();
	
	//Non constant variables
	int arrSize = def.size();
	//rectangle width is = (the width of the pane - 50)/the number of items in the array
	//This is calculated thus so as the have an almost perfect rectangle size which fits almost 
	//perfectly into the pane side despite varying the pane size.
	int rectWidth = (scWidth-50)/arrSize;
	
	//Layers
	StackPane root = new StackPane();
	Pane layer1 = new Pane();
	Pane layer2 = new Pane();
	
	Line yaxis = new Line(50,50,50,scHeight);
	Line xaxis = new Line(0,scHeight-50,scWidth,scHeight-50);
	Button startBtn = new Button("Start");
	
	
	public SAVS()
	{
		startBtn.setMinSize(80, 30);
		layer1.getChildren().addAll(yaxis,xaxis,startBtn);
		root.getChildren().addAll(layer1,layer2);
	}

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
	
	private class myRectangle extends Rectangle implements Comparable<Object>
	{
		int width;
		int height;
		Points xy;
		
		public myRectangle(Points xy, int width, int height)
		{
			super(xy.x, xy.y, width, height);
			
			this.width = width;
			this.height = height;
			this.xy = xy;
		}
		
		public myRectangle(myRectangle rect)
		{
			super(rect.xy.x, rect.xy.y, rect.width, rect.height);
			this.width = rect.width;
			this.height = rect.height;
			this.xy = rect.xy;
		}

		@Override
		public int compareTo(Object o) 
		{
			myRectangle temp = (myRectangle) o;
			if(this.height > temp.height)
			{
				return 1;
			}
			return -1;
		}
		
		public void setx(double num)
		{
			xy.x = num;
		}
		
		public double getx()
		{
			return xy.x;
		}
		
		@Override
		public String toString()
		{
			String str = "width-"+ width +"; Height-"+ height +"; x-"+ xy.x +"; y-"+ xy.y +";";
			return str;
		}
	}
	
	public void initialLayout(ArrayList<Integer> arr)
	{
		//Drawing the initial unsorted array.
		
		//For every iteration the x position increases by the width of the rectangle. 
		//This is done to avoid overlapping of rectangles.
		for(int i = 0, x = 50; i < arr.size(); i++, x+=rectWidth)
		{
			//Multiplies the height by 10 to make it more visible when drawn, because drawing a
			//rectangle with height one is'nt so visible
			int height = arr.get(i)*10;
			
			//The y location is equal to = the height of the pane - (50 + the height of the rectangle)
			//where 50 is the distance from the bottom of the plane to the x-axis
			int y = scHeight - (50+height);
			
			Points temp = new Points(x,y);
			points.add(temp);
			
			myRectangle rect = new myRectangle(temp,rectWidth, height);
			rect.setStroke(Color.RED);
			rect.setFill(Color.BLACK);
			rectList.add(rect);
			layer2.getChildren().add(rect);
		}
	}
	
	@Override
	public void start(Stage primary) {
			
		initialLayout(def);
		bubbleSort(rectList);
		
		Scene scene  = new Scene(root, scWidth, scHeight);
	    primary.setTitle("Sorting Algorithm Visual Simulation");
		primary.setScene(scene);
		primary.show();
	}
	
//	public void bubbleSort(ArrayList<Integer> arr)
//	{
//		ArrayList<Integer> arrTemp = new ArrayList<>(arr);
//		for(int i = 0; i < arrTemp.size(); i++)
//		{
//			for(int j = 0; j < arrTemp.size() - 1 - i; j++)
//			{
//				if(arrTemp.get(j) > arrTemp.get(j+1))
//				{
//					int temp = arrTemp.get(j+1);
//					arrTemp.set(j+1, arrTemp.get(j));
//					arrTemp.set(j, temp);
//				}
//			}
//			
//		}
//	}
	
	
//	TranslateTransition trans = new TranslateTransition(Duration.seconds(5), arrTemp.get(j));
//	trans.setToX(arrTemp.get(j+1).getX()-arrTemp.get(j).getX());
//	trans.play();
//	TranslateTransition trans1 = new TranslateTransition(Duration.seconds(5), arrTemp.get(j+1));
//	trans1.setToX(arrTemp.get(j).getX()-arrTemp.get(j+1).getX());
//	trans1.play();
	
	public void bubbleSort(ArrayList<myRectangle> arr)
	{
		ArrayList<myRectangle> arrTemp = new ArrayList<>(arr);

		for(int i = 0; i < arrTemp.size(); i++)
		{
			for(int j = 0; j < arrTemp.size() - 1 - i; j++)
			{
				if(arrTemp.get(j).compareTo(arrTemp.get(j+1)) > 0)
				{
					//Deleting the rectangles from the panes
					layer2.getChildren().removeAll(arrTemp.get(j),arrTemp.get(j+1));
					
					
					
					//Swapping the x locations of the two rectangles
					double xtemp = arrTemp.get(j+1).getx();
					arrTemp.get(j+1).setx(arrTemp.get(j).getx());
					arrTemp.get(j).setx(xtemp);
					
					//Swapping the rectangles in the rectangle array
					myRectangle temp = new myRectangle(arrTemp.get(j+1));
					arrTemp.set(j+1, arrTemp.get(j));
					arrTemp.set(j, temp);
					
					myRectangle temp1 = new myRectangle(arrTemp.get(j));
					temp1.setStroke(Color.RED);
					temp1.setFill(Color.BLACK);
					myRectangle temp2 = new myRectangle(arrTemp.get(j+1));
					temp2.setStroke(Color.RED);
					temp2.setFill(Color.BLACK);
					
					//Drawing the rectangles back on the plane but in different locations
					layer2.getChildren().addAll(temp1,temp2);	
				}
				
			}
			
		}
		
		
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

