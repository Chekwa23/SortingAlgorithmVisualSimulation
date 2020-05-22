//package application;
////vm Arguments
////--module-path "..\SAVS\SortingAlgorithmVisualSimulation\lib" --add-modules=javafx.controls	
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.TreeMap;
//
//import javafx.animation.SequentialTransition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//
//
//public class SAVS extends Application {
//	
//	//Constant variables
//	final int scWidth = 700;
//	final int scHeight = 400;
//	
//	//Lists
//	ArrayList<Integer> def = new ArrayList<>(Arrays.asList(1,14,5,11,17,3,8,20,13,9,18,7,12,2,16,10,4,19,15,6));
//	ArrayList<Integer> defClone = new ArrayList<>(def);
//	ArrayList<Triple> tripleList = new ArrayList<>();
//	ArrayList<myRectangle> rectList = new ArrayList<>();
//	TreeMap<Integer, Integer> tree = new TreeMap<>();
//	
//	//Non constant variables
//	int arrSize = def.size();
//	//rectangle width is = (the width of the pane - 50)/the number of items in the array
//	//This is calculated thus so as the have an almost perfect rectangle size which fits almost 
//	//perfectly into the pane side despite varying the pane size.
//	int rectWidth = (scWidth-50)/arrSize;
//	
//	//Layers
//	StackPane root = new StackPane(); 
//	Pane layer1 = new Pane();
//	Pane layer2 = new Pane();
//	
//	Line yaxis = new Line(50,50,50,scHeight);
//	Line xaxis = new Line(0,scHeight-50,scWidth,scHeight-50);
//	Button startBtn = new Button("Start");
//	
//	
//	public SAVS()
//	{
//		startBtn.setMinSize(80, 30);
//		layer1.getChildren().addAll(yaxis,xaxis,startBtn);
//		root.getChildren().addAll(layer1,layer2);
//	}
//
//	private class Points
//	{ 
//		double x;
//		double y;
//		public Points(double x, double y)
//		{
//			this.x = x;
//			this.y = y;
//		}
//	}
//	
//	private class Triple implements Comparable
//	{
//		int height;
//		int index;
//		int distance;
//		
//		public Triple(int height, int index, int distance) 
//		{
//			this.height = height;
//			this.index = index;
//			this.distance = distance;
//		}
//
//		@Override
//		public int compareTo(Object o) 
//		{
//			Triple temp = (Triple) o;
//			
//			if(this.height > temp.height)
//			{
//				return 1;
//			}
//			else if(this.height < temp.height)
//			{
//				return -1;
//			}
//			else
//			{
//				return 0;
//			}
//		}
//		
//		@Override
//		public String toString()
//		{
//			return ""+height;
//		}
//	}
//	
//	private class myRectangle extends Rectangle implements Comparable<Object>
//	{
//		int width;
//		int height;
//		Points xy;
//		
//		public myRectangle(Points xy, int width, int height)
//		{
//			super(xy.x, xy.y, width, height);
//			
//			this.width = width;
//			this.height = height;
//			this.xy = new Points(xy.x, xy.y);
//		}
//
//		@Override
//		public int compareTo(Object o) 
//		{
//			myRectangle temp = (myRectangle) o;
//			if(this.height > temp.height)
//			{
//				return 1;
//			}
//			else if(this.height < temp.height)
//			{
//				return -1;
//			}
//			else
//			{
//				return 0;
//			}
//		}
//		
//		public void setx(double num)
//		{
//			xy.x = num;
//			super.setX(num);
//		}
//		
//		public double getx()
//		{
//			return xy.x;
//		}
//		@Override
//		public String toString()
//		{
////			String str = ""+ height;
////			String str = "width-"+ width +"; Height-"+ height +"; x-"+ xy.x +"; y-"+ xy.y +";";
//			return ""+ height;
//		}
//	}
//	
//	//Drawing the initial unsorted array.
//	public void initialLayout(ArrayList<Integer> arr)
//	{
//		//For every iteration the x position increases by the width of the rectangle. 
//		//This is done to avoid overlapping of rectangles.
//		for(int i = 0, x = 50; i < arr.size(); i++, x+=rectWidth)
//		{
//			//Multiplies the height by 10 to make it more visible when drawn, because drawing a
//			//rectangle with height one is'nt so visible
//			int height = arr.get(i)*10;
//			
//			//The y location is equal to = the height of the pane - (50 + the height of the rectangle)
//			//where 50 is the distance from the bottom of the plane to the x-axis
//			int y = scHeight - (50+height);
//			
//			Points temp = new Points(x,y);
//			
//			myRectangle rect = new myRectangle(temp,rectWidth, height);
//			rect.setStroke(Color.RED);
//			rect.setFill(Color.BLACK);
//			rectList.add(rect);
//			
//			tripleList.add(new Triple(height/10, i, x));
//			
//			layer2.getChildren().add(rect);
//		}
//	}
//	
//	@Override
//	public void start(Stage primary) throws InterruptedException {
//			
//		initialLayout(def);
////		bubbleSort(tripleList);
////		insertionSort(tripleList);
//		selectionSort(tripleList);
//		execute();
//		
//		Scene scene  = new Scene(root, scWidth, scHeight);
//	    primary.setTitle("Sorting Algorithm Visual Simulation");
//		primary.setScene(scene);
//		primary.show();
//	}
//	
//	ArrayList<TranslateTransition> transList = new ArrayList<>();
//	
//	public void execute() throws InterruptedException 
//	{	
//		SequentialTransition seq = new SequentialTransition();
//		SequentialTransition seq1 = new SequentialTransition();
//		
//		for(int i = 0; i < transList.size()-1; i += 2)
//		{
//			seq.getChildren().add(transList.get(i));
//			seq1.getChildren().add(transList.get(i+1));
//		}
//		
//		seq.setCycleCount(1);
//		seq1.setCycleCount(1);
//		seq.play();
//		seq1.play();
//    }
//	
//	public void bubbleSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 0; i < arrTemp.size(); i++)
//		{
//			for(int j = 0; j < arrTemp.size() - 1 - i; j++)
//			{
//				if(arrTemp.get(j).compareTo(arrTemp.get(j+1)) > 0)
//				{
//					translate(arrTemp.get(j).index,arrTemp.get(j+1).index);
//					Triple temp = arrTemp.get(j+1);
//					arrTemp.set(j+1, arrTemp.get(j));
//					arrTemp.set(j, temp);
//				}
//			}
//			
//		}
//	}
//	
//	public void insertionSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 1; i < arr.size(); i++)
//		{
//			Triple cursor = arrTemp.get(i);
//			for(int j = i; j > 0 && cursor.compareTo(arrTemp.get(j-1)) <= 0; j--)
//			{
//				translate(arrTemp.get(j-1).index,cursor.index);
//				arrTemp.set(j, arrTemp.get(j-1));
//				arrTemp.set(j-1, cursor);
//			}
//		}
//
//		System.out.println(arrTemp);
//	}
//	
//	public void selectionSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 0; i < arrTemp.size(); i++)
//		{
//			Triple min =  arrTemp.get(i);
//			int x = i;
//			for(int j = i; j < arrTemp.size(); j++)
//			{
//				if(min.compareTo(arrTemp.get(j)) > 0)
//				{
//					x = j;
//					min = arrTemp.get(j);
//				}
//			}
//			
//			translate(i,x);
//			arrTemp.set(x, arrTemp.get(i));
//			arrTemp.set(i, min);
//		}
//		System.out.println(arrTemp);
//	}
//// change Triple so it would inlcude an x location for the elements
//	double time = 0.1;
////	double time = 5;
//	public void translate(int present, int next)
//	{
//		
//		TranslateTransition trans = new TranslateTransition(Duration.seconds(time), rectList.get(present));
//		trans.setByX(rectWidth);
//		transList.add(trans);
//		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(time), rectList.get(next));
//		trans1.setByX(-rectWidth);
//		transList.add(trans1);
//	}
//	
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}

package application;
//vm Arguments
//--module-path "..\SAVS\SortingAlgorithmVisualSimulation\lib" --add-modules=javafx.controls	
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	ArrayList<Integer> def = new ArrayList<>(Arrays.asList(1,14,5,11,17,3,8,20,13,5,9,18,7,12,2,16,10,4,19,15,6));
	ArrayList<Integer> defClone = new ArrayList<>(def);
	ArrayList<Triple> tripleList = new ArrayList<>();
	ArrayList<Triple> tripleListClone;// = new ArrayList<>();
	ArrayList<myRectangle> rectList = new ArrayList<>();
	TreeMap<Integer, Integer> tree = new TreeMap<>();
	
	//Non constant variables
	int arrSize = def.size();
	//rectangle width is = (the width of the pane - 50)/the number of items in the array
	//This is calculated thus so as the have an almost perfect rectangle size which fits almost 
	//perfectly into the pane side despite varying the pane size.
//	int rectWidth = (scWidth-50)/arrSize;
	int rectWidth = 30;
	
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
	
	private class Triple implements Comparable
	{
		int height;
		int index;
		int distance;
		
		public Triple(int height, int index, int distance) 
		{
			this.height = height;
			this.index = index;
			this.distance = distance;
		}

		@Override
		public int compareTo(Object o) 
		{
			Triple temp = (Triple) o;
			
			if(this.height > temp.height)
			{
				return 1;
			}
			else if(this.height < temp.height)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		
		public boolean equals(Object o)
		{
			Triple temp = (Triple) o;
			if(this.hashCode() == temp.hashCode())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public void setDistance(int dist)
		{
			distance = dist;
		}
		
		@Override
		public String toString()
		{
			return ""+height*10;
		}
		
		@Override
		public int hashCode()
		{
			int code = 31;
			code = code * distance;
			code = code * height;
			code = code * index;
			return code;
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
			this.xy = new Points(xy.x, xy.y);
		}

		@Override
		public int compareTo(Object o) 
		{
			myRectangle temp = (myRectangle) o;
			if(this.height > temp.height)
			{
				return 1;
			}
			else if(this.height < temp.height)
			{
				return -1;
			}
			else
			{
				return 0;
			}
		}
		
		public void setx(double num)
		{
			xy.x = num;
			super.setX(num);
		}
		
		public double getx()
		{
			return xy.x;
		}
		
		@Override
		public String toString()
		{
//			String str = ""+ height;
//			String str = "width-"+ width +"; Height-"+ height +"; x-"+ xy.x +"; y-"+ xy.y +";";
			return ""+ height;
		}
	}
	
	//Drawing the initial unsorted array.
	public void initialLayout(ArrayList<Integer> arr)
	{
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
			
			myRectangle rect = new myRectangle(temp,rectWidth, height);
			rect.setStroke(Color.RED);
			rect.setFill(Color.BLACK);
			rectList.add(rect);
			
			tripleList.add(new Triple(height/10, i, x));
			
			layer2.getChildren().add(rect);
		}
		tripleListClone = new ArrayList<>(tripleList);
		System.out.println(tripleList);
	}
	
	@Override
	public void start(Stage primary) throws InterruptedException {
			
		initialLayout(def);
//		bubbleSort(tripleList);
//		insertionSort(tripleList);
		selectionSort(tripleList);
		execute();
		
		Scene scene  = new Scene(root, scWidth, scHeight);
	    primary.setTitle("Sorting Algorithm Visual Simulation");
		primary.setScene(scene);
		primary.show();
	}
	
	ArrayList<TranslateTransition> transList = new ArrayList<>();
	
	public void execute() throws InterruptedException 
	{	
		SequentialTransition seq = new SequentialTransition();
		SequentialTransition seq1 = new SequentialTransition();
		
		for(int i = 0; i < transList.size()-1; i += 2)
		{
			seq.getChildren().add(transList.get(i));
			seq1.getChildren().add(transList.get(i+1));
		}
		
		seq.setCycleCount(1);
		seq1.setCycleCount(1);
		seq.play();
		seq1.play();
    }
	
	public void bubbleSort(ArrayList<Triple> arr)
	{
		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
		for(int i = 0; i < arrTemp.size(); i++)
		{
			for(int j = 0; j < arrTemp.size() - 1 - i; j++)
			{
				if(arrTemp.get(j).compareTo(arrTemp.get(j+1)) > 0)
				{
					int diff = arrTemp.get(j+1).distance - arrTemp.get(j).distance;
					int tempDist = arrTemp.get(j+1).distance;
					arrTemp.get(j+1).setDistance(arrTemp.get(j).distance);
					arrTemp.get(j).setDistance(tempDist);
					
					translate(arrTemp.get(j).index, arrTemp.get(j+1).index, diff);
//					swapDistance(arrTemp.get(j).index,arrTemp.get(j+1).index);
					
					Triple temp = arrTemp.get(j+1);
					arrTemp.set(j+1, arrTemp.get(j));
					arrTemp.set(j, temp);
				}
			}
			
		}
	}
	
	public void swapDistance(int present, int next)
	{
		int tempDist = tripleListClone.get(present).distance;
	}
	
	public void insertionSort(ArrayList<Triple> arr)
	{
		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
		for(int i = 1; i < arr.size(); i++)
		{
			Triple cursor = arrTemp.get(i);
			for(int j = i; j > 0 && cursor.compareTo(arrTemp.get(j-1)) <= 0; j--)
			{
				int diff = arrTemp.get(j-1).distance - arrTemp.get(j).distance;
				int tempDist = arrTemp.get(j-1).distance;
				arrTemp.get(j-1).setDistance(arrTemp.get(j).distance);
				arrTemp.get(j).setDistance(tempDist);
				
				translate(arrTemp.get(j).index, arrTemp.get(j-1).index, diff);
//				translate(arrTemp.get(j-1).index,cursor.index);
				arrTemp.set(j, arrTemp.get(j-1));
				arrTemp.set(j-1, cursor);
			}
		}

		System.out.println(arrTemp);
	}
	
	public void selectionSort(ArrayList<Triple> arr)
	{
		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
		for(int i = 0; i < arrTemp.size(); i++)
		{
			Triple min = arrTemp.get(i);
			int x = i;
			for(int j = i; j < arrTemp.size(); j++)
			{
				if(min.compareTo(arrTemp.get(j)) >= 0)
				{
					x = j;
					min = arrTemp.get(j);
				}
			}
			System.out.println(x);
			System.out.println(min);
			
			int diff = arrTemp.get(x).distance-arrTemp.get(i).distance ;
			int tempDist = arrTemp.get(i).distance;
			arrTemp.get(i).setDistance(arrTemp.get(x).distance);
			arrTemp.get(x).setDistance(tempDist);
			
			translate(arrTemp.get(i).index, arrTemp.get(x).index, diff);
//			translate(i,x);
			arrTemp.set(x, arrTemp.get(i));
			arrTemp.set(i, min);
		}
		System.out.println(arrTemp);
	}
	
// change Triple so it would inlcude an x location for the elements
//	double time = 0.1;
//	double time = 5;
	double time = 1;
	
	public void translate(int present, int next, int diff)
	{
//		System.out.println("Moving "+rectList.get(present).toString()+" and "+ rectList.get(next).toString());
		TranslateTransition trans = new TranslateTransition(Duration.seconds(time), rectList.get(present));
		trans.setByX(diff);
		transList.add(trans);
		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(time), rectList.get(next));
		trans1.setByX(-diff);
		transList.add(trans1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

//package application;
////vm Arguments
////--module-path "..\SAVS\SortingAlgorithmVisualSimulation\lib" --add-modules=javafx.controls	
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.TreeMap;
//
//import javafx.animation.SequentialTransition;
//import javafx.animation.TranslateTransition;
//import javafx.application.Application;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//
//
//public class SAVS extends Application {
//	
//	//Constant variables
//	final int scWidth = 700;
//	final int scHeight = 400;
//	
//	//Lists
//	ArrayList<Integer> def = new ArrayList<>(Arrays.asList(1,14,5,11,17));//,3,8,20,13,5,9,18,7,12,2,16,10,4,19,15,6));
//	ArrayList<Integer> defClone = new ArrayList<>(def);
//	ArrayList<Triple> tripleList = new ArrayList<>();
//	ArrayList<Triple> tripleListClone;// = new ArrayList<>();
//	ArrayList<myRectangle> rectList = new ArrayList<>();
//	TreeMap<Integer, Integer> tree = new TreeMap<>();
//	
//	//Non constant variables
//	int arrSize = def.size();
//	//rectangle width is = (the width of the pane - 50)/the number of items in the array
//	//This is calculated thus so as the have an almost perfect rectangle size which fits almost 
//	//perfectly into the pane side despite varying the pane size.
////	int rectWidth = (scWidth-50)/arrSize;
//	int rectWidth = 30;
//	
//	//Layers
//	StackPane root = new StackPane(); 
//	Pane layer1 = new Pane();
//	Pane layer2 = new Pane();
//	
//	Line yaxis = new Line(50,50,50,scHeight);
//	Line xaxis = new Line(0,scHeight-50,scWidth,scHeight-50);
//	Button startBtn = new Button("Start");
//	
//	
//	public SAVS()
//	{
//		startBtn.setMinSize(80, 30);
//		layer1.getChildren().addAll(yaxis,xaxis,startBtn);
//		root.getChildren().addAll(layer1,layer2);
//	}
//
//	private class Points
//	{ 
//		double x;
//		double y;
//		public Points(double x, double y)
//		{
//			this.x = x;
//			this.y = y;
//		}
//	}
//	
//	private class Triple implements Comparable
//	{
//		int height;
//		int index;
//		int distance;
//		
//		public Triple(int height, int index, int distance) 
//		{
//			this.height = height;
//			this.index = index;
//			this.distance = distance;
//		}
//
//		@Override
//		public int compareTo(Object o) 
//		{
//			Triple temp = (Triple) o;
//			
//			if(this.height > temp.height)
//			{
//				return 1;
//			}
//			else if(this.height < temp.height)
//			{
//				return -1;
//			}
//			else
//			{
//				return 0;
//			}
//		}
//		
//		public boolean equals(Object o)
//		{
//			if(this.hashCode() == o.hashCode())
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		}
//		
//		public void setDistance(int dist)
//		{
//			distance = dist;
//		}
//		
//		@Override
//		public String toString()
//		{
//			return ""+height*10;
//		}
//		
//		@Override
//		public int hashCode()
//		{
//			int code = 31;
//			code = code * height;
//			code = code * index;
//			return code;
//		}
//	}
//	
//	private class myRectangle extends Rectangle implements Comparable<Object>
//	{
//		int width;
//		int height;
//		int index;
//		Points xy;
//		
//		public myRectangle(Points xy, int width, int height)
//		{
//			super(xy.x, xy.y, width, height);
//			
//			this.width = width;
//			this.height = height;
//			this.xy = new Points(xy.x, xy.y);
//		}
//
//		@Override
//		public int compareTo(Object o) 
//		{
//			myRectangle temp = (myRectangle) o;
//			if(this.height > temp.height)
//			{
//				return 1;
//			}
//			else if(this.height < temp.height)
//			{
//				return -1;
//			}
//			else
//			{
//				return 0;
//			}
//		}
//		
//		public boolean equals(Object o)
//		{
//			if(this.hashCode() == o.hashCode())
//			{
//				return true;
//			}
//			else
//			{
//				return false;
//			}
//		}
//		
//		public void setx(double num)
//		{
//			xy.x = num;
//			super.setX(num);
//		}
//		
//		public double getx()
//		{
//			return xy.x;
//		}
//		
//		@Override
//		public int hashCode()
//		{
//			int code = 31;
//			code = code * height;
//			code = code * index;
//			return code;
//		}
//		
//		@Override
//		public String toString()
//		{
////			String str = ""+ height;
////			String str = "width-"+ width +"; Height-"+ height +"; x-"+ xy.x +"; y-"+ xy.y +";";
//			return ""+ height;
//		}
//	}
//	
//	//Drawing the initial unsorted array.
//	public void initialLayout(ArrayList<Integer> arr)
//	{
//		//For every iteration the x position increases by the width of the rectangle. 
//		//This is done to avoid overlapping of rectangles.
//		for(int i = 0, x = 50; i < arr.size(); i++, x+=rectWidth)
//		{
//			//Multiplies the height by 10 to make it more visible when drawn, because drawing a
//			//rectangle with height one is'nt so visible
//			int height = arr.get(i)*10;
//			
//			//The y location is equal to = the height of the pane - (50 + the height of the rectangle)
//			//where 50 is the distance from the bottom of the plane to the x-axis
//			int y = scHeight - (50+height);
//			
//			Points temp = new Points(x,y);
//			
//			myRectangle rect = new myRectangle(temp, rectWidth, height);
//			rect.setStroke(Color.RED);
//			rect.setFill(Color.BLACK);
//			rectList.add(rect);
//			
//			tripleList.add(new Triple(height/10, i, x));
//			
//			layer2.getChildren().add(rect);
//		}
//		tripleListClone = new ArrayList<>(tripleList);
//		System.out.println(tripleList);
//	}
//	
//	@Override
//	public void start(Stage primary) throws InterruptedException {
//			
//		initialLayout(def);
////		bubbleSort(tripleList);
////		insertionSort(tripleList);
//		selectionSort(tripleList);
//		execute();
//		
//		Scene scene  = new Scene(root, scWidth, scHeight);
//	    primary.setTitle("Sorting Algorithm Visual Simulation");
//		primary.setScene(scene);
//		primary.show();
//	}
//	
//	ArrayList<TranslateTransition> transList = new ArrayList<>();
//	
//	public void execute() throws InterruptedException 
//	{	
//		SequentialTransition seq = new SequentialTransition();
//		SequentialTransition seq1 = new SequentialTransition();
//		
//		for(int i = 0; i < transList.size()-1; i += 2)
//		{
//			seq.getChildren().add(transList.get(i));
//			seq1.getChildren().add(transList.get(i+1));
//		}
//		
//		seq.setCycleCount(1);
//		seq1.setCycleCount(1);
//		seq.play();
//		seq1.play();
//    }
//	
//	public void bubbleSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 0; i < arrTemp.size(); i++)
//		{
//			for(int j = 0; j < arrTemp.size() - 1 - i; j++)
//			{
//				if(arrTemp.get(j).compareTo(arrTemp.get(j+1)) > 0)
//				{
//					int diff = arrTemp.get(j+1).distance - arrTemp.get(j).distance;
//					int tempDist = arrTemp.get(j+1).distance;
//					arrTemp.get(j+1).setDistance(arrTemp.get(j).distance);
//					arrTemp.get(j).setDistance(tempDist);
//					
//					translate(arrTemp.get(j).index, arrTemp.get(j+1).index, diff);
////					swapDistance(arrTemp.get(j).index,arrTemp.get(j+1).index);
//					
//					Triple temp = arrTemp.get(j+1);
//					arrTemp.set(j+1, arrTemp.get(j));
//					arrTemp.set(j, temp);
//					
//				}
//			}
//			
//		}
//	}
//	
//	public void insertionSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 1; i < arr.size(); i++)
//		{
//			Triple cursor = arrTemp.get(i);
//			for(int j = i; j > 0 && cursor.compareTo(arrTemp.get(j-1)) <= 0; j--)
//			{
//				int diff = arrTemp.get(j-1).distance - arrTemp.get(j).distance;
//				int tempDist = arrTemp.get(j-1).distance;
//				arrTemp.get(j-1).setDistance(arrTemp.get(j).distance);
//				arrTemp.get(j).setDistance(tempDist);
//				
//				translate(arrTemp.get(j).index, arrTemp.get(j-1).index, diff);
////				translate(arrTemp.get(j-1).index,cursor.index);
//				arrTemp.set(j, arrTemp.get(j-1));
//				arrTemp.set(j-1, cursor);
//			}
//		}
//
//		System.out.println(arrTemp);
//	}
//	
//	public void selectionSort(ArrayList<Triple> arr)
//	{
//		ArrayList<Triple> arrTemp = new ArrayList<>(arr);
//		for(int i = 0; i < arrTemp.size(); i++)
//		{
//			Triple min = arrTemp.get(i);
//			int x = i;
//			for(int j = i; j < arrTemp.size(); j++)
//			{
//				if(min.compareTo(arrTemp.get(j)) >= 0)
//				{
//					x = j;
//					min = arrTemp.get(j);
//				}
//			}
//			System.out.println(x);
//			System.out.println(min);
//			
//			int diff = min.distance-arrTemp.get(i).distance ;
//			int tempDist = arrTemp.get(i).distance;
//			arrTemp.get(i).setDistance(min.distance);
//			min.setDistance(tempDist);
//			
//			translate(i, x, diff);
////			translate(i,x);
//			arrTemp.set(x, arrTemp.get(i));
//			arrTemp.set(i, min);
//		}
//		System.out.println(arrTemp);
//	}
//	
//// change Triple so it would inlcude an x location for the elements
////	double time = 0.1;
//	double time = 5;
////	double time = 1;
////	public void translate(int present, int next)
////	{
////		
////		TranslateTransition trans = new TranslateTransition(Duration.seconds(time), rectList.get(present));
////		trans.setByX(rectWidth);
////		transList.add(trans);
////		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(time), rectList.get(next));
////		trans1.setByX(-rectWidth);
////		transList.add(trans1);
////	}
//	
//	public void translate(int present, int next, int diff)
//	{
//		System.out.println("Moving "+rectList.get(present).toString()+" and "+ rectList.get(next).toString());
//		TranslateTransition trans = new TranslateTransition(Duration.seconds(time), rectList.get(present));
//		trans.setByX(diff);
//		transList.add(trans);
//		TranslateTransition trans1 = new TranslateTransition(Duration.seconds(time), rectList.get(next));
//		trans1.setByX(-diff);
//		transList.add(trans1);
//	}
//	
//	public static void main(String[] args) {
//		launch(args);
//	}
//}