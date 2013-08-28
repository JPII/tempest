/**
 * Graphics3D.java
 * Version 1.0
 * Copyright 5/10/2013 by John Schram
 *
 * This version allows 3D drawings using lines, cubes, and rectangular prisms.
 * 3D Tranformations, such are rotation, scaling and translation are possible
 * provided all points are added to the <points> ArrayList.
 *
 * Multiple <Graphics3D> objects can be created if you wish to transform
 * different pictures differently.
 *
 * NOTE: The fillPrism and fillCube methods do not always display properly
 *       when "Perspective" is "On".
 * ALSO: Problems will occur if you attempt to rotate something composed of
 *       filled prisms/cubes and individual lines.
 *
 * Based on programs written by Robby Slaughter
 * for the book
 * Student Friendly Advanced VGA Graphics for C++ (c) 1997
 * Authored by Leon Schram
 *
 * These programs were translated into Java by Zachary Mathewson and Max Kirby in 2012.
 * From these translated programs, and several of my own ideas, I created
 * a set of programs that are more in line with "Object Oriented Programming".
 * This required the creation of the <Point3D> and <Graphics3D> classes.
 *
 * The <Graphics3D> class requires the <Point3D> class in order to compile.
 *
 * This code is free software; you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation.
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 */


import java.awt.*;
import java.awt.event.*;
import java.util.*;


class Graphics3D
{
	private Graphics g;
	private ArrayList <Point3D> points;
	private ArrayList <Color> prismColors;
	private int originX, originY;


	public Graphics3D(Graphics g)
	{
		this.g = g;
		points = new ArrayList<Point3D>();
		prismColors = new ArrayList<Color>();
		originX = Point3D.getOriginX();
		originY = Point3D.getOriginY();
	}


	public Graphics3D(Graphics g, int x, int y)
	{
		this.g = g;
		points = new ArrayList<Point3D>();
		prismColors = new ArrayList<Color>();
		originX = x;
		originY = y;
	}


	public int getUserX(int index)             {  return points.get(index).getUserX();      }
	public int getUserY(int index)             {  return points.get(index).getUserY();      }
	public int getUserZ(int index)             {  return points.get(index).getUserZ();      }
	public int getNumPoints()         	       {  return points.size();                     }
	public void setColor(Color color)          {  g.setColor(color);                        }
	public void setColor(int prismColor)       {  g.setColor(prismColors.get(prismColor));  }
	public void addPoint(Point3D point)        {  points.add(point);                        }
	public void addPoint(int x, int y, int z)  {  points.add(new Point3D(x,y,z));           }
	public void addPoint(int x, int y)		   {  points.add(new Point3D(x,y,0));           }
	public Point3D getPoint(int index)         {  return points.get(index);                 }
	public String toString()	               {  return points.toString() + "\n";          }


	public void drawPixel(int userX, int userY, int userZ)
	{
		Point3D point = new Point3D(userX, userY, userZ);
		drawPixel(point);
	}

	public void drawPixel(Point3D point)
	{
		g.fillRect(point.getTrueX()+originX, point.getTrueY()+originY, 2, 2);
	}

	public void drawFatPixel(int userX, int userY, int userZ)
	{
		Point3D point = new Point3D(userX, userY, userZ);
		drawFatPixel(point);
	}

	public void drawFatPixel(Point3D point)
	{
		g.fillRect(point.getTrueX()+originX, point.getTrueY()+originY, 5, 5);
	}


	public void drawLine(int userX1, int userY1, int userZ1,
	                     int userX2, int userY2, int userZ2)
	{
		Point3D point1 = new Point3D(userX1, userY1, userZ1);
		Point3D point2 = new Point3D(userX2, userY2, userZ2);
		drawLine(point1,point2);
	}

	public void drawLine(Point3D point1, Point3D point2)
	{
		g.drawLine(point1.getTrueX()+originX, point1.getTrueY()+originY,
			       point2.getTrueX()+originX, point2.getTrueY()+originY);
	}

	public void drawLine(int index1, int index2)
	{
		drawLine(points.get(index1),points.get(index2));
	}


	public void turnPerspectiveOn()
	{
		for (Point3D point: points)
			point.turnPerspectiveOn();
	}

	public void turnPerspectiveOff()
	{
		for (Point3D point: points)
			point.turnPerspectiveOff();
	}


	public int distance(int index1, int index2)
	{
		double xLength = points.get(index1).getUserX() - points.get(index2).getUserX();
		double yLength = points.get(index1).getUserY() - points.get(index2).getUserY();
		double zLength = points.get(index1).getUserZ() - points.get(index2).getUserZ();
		return (int)(Math.sqrt(xLength * xLength + yLength * yLength + zLength * zLength));
	}

	public int distance(Point3D point1, Point3D point2)
	{
		double xLength = point1.getUserX() - point2.getUserX();
		double yLength = point1.getUserY() - point2.getUserY();
		double zLength = point1.getUserZ() - point2.getUserZ();
		return (int)(Math.sqrt(xLength * xLength + yLength * yLength + zLength * zLength));
	}

	public int distanceFromOrigin(Point3D point)
	{
		double xLength = point.getUserX();
		double yLength = point.getUserY();
		double zLength = point.getUserZ();
		return (int)(Math.sqrt(xLength * xLength + yLength * yLength + zLength * zLength));
	}

	public int distanceFromOrigin(int index)
	{
		double xLength = points.get(index).getUserX();
		double yLength = points.get(index).getUserY();
		double zLength = points.get(index).getUserZ();
		return (int)(Math.sqrt(xLength * xLength + yLength * yLength + zLength * zLength));
	}

	public int makePrism(int userX, int userY,  int userZ,
	                      int width, int height, int depth, Color prismColor)
	{
		int start = points.size();

		addPoint(userX,userY,userZ);
		addPoint(userX+width,userY,userZ);
		addPoint(userX+width,userY+height,userZ);
		addPoint(userX,userY+height,userZ);
		addPoint(userX,userY,userZ+depth);
		addPoint(userX+width,userY,userZ+depth);
		addPoint(userX+width,userY+height,userZ+depth);
		addPoint(userX,userY+height,userZ+depth);
		prismColors.add(prismColor);

		return start;
    }

	public int makePrism(int userX, int userY,  int userZ,
	                      int width, int height, int depth)
	{
		int start = points.size();

		addPoint(userX,userY,userZ);
		addPoint(userX+width,userY,userZ);
		addPoint(userX+width,userY+height,userZ);
		addPoint(userX,userY+height,userZ);
		addPoint(userX,userY,userZ+depth);
		addPoint(userX+width,userY,userZ+depth);
		addPoint(userX+width,userY+height,userZ+depth);
		addPoint(userX,userY+height,userZ+depth);

		return start;
    }

	public void drawPrism(int start)
	{
		if (behindUser(start))
			return;

		drawLine(start,start+1);
		drawLine(start+1,start+2);
		drawLine(start+2,start+3);
		drawLine(start+3,start);

		drawLine(start+4,start+5);
		drawLine(start+5,start+6);
		drawLine(start+6,start+7);
		drawLine(start+7,start+4);

		drawLine(start,start+4);
		drawLine(start+1,start+5);
		drawLine(start+2,start+6);
		drawLine(start+3,start+7);
    }


	public void fillPrism(int start)
	{
		if (behindUser(start))
			return;

		Color color1 = g.getColor();
		int red = color1.getRed();
		int green = color1.getGreen();
		int blue = color1.getBlue();
		Color color2 = new Color((int)(red*0.95),(int)(green*0.95),(int)(blue*0.95));
		Color color3 = new Color((int)(red*0.90),(int)(green*0.90),(int)(blue*0.90));
		Color color4 = new Color((int)(red*0.85),(int)(green*0.85),(int)(blue*0.85));
		Color color5 = new Color((int)(red*0.80),(int)(green*0.80),(int)(blue*0.80));
		Color color6 = new Color((int)(red*0.75),(int)(green*0.75),(int)(blue*0.75));
		int front = mostForwardIndex(start) - start;

		if (front < 4)
		{
			Polygon side1 = new Polygon();
			side1.addPoint(points.get(start).getTrueX()+originX, points.get(start).getTrueY()+originY);
			side1.addPoint(points.get(start+1).getTrueX()+originX, points.get(start+1).getTrueY()+originY);
			side1.addPoint(points.get(start+2).getTrueX()+originX, points.get(start+2).getTrueY()+originY);
			side1.addPoint(points.get(start+3).getTrueX()+originX, points.get(start+3).getTrueY()+originY);
			g.setColor(color1);
			g.fillPolygon(side1);
		}

		if (front > 3)
		{
			Polygon side6 = new Polygon();
			side6.addPoint(points.get(start+4).getTrueX()+originX, points.get(start+4).getTrueY()+originY);
			side6.addPoint(points.get(start+5).getTrueX()+originX, points.get(start+5).getTrueY()+originY);
			side6.addPoint(points.get(start+6).getTrueX()+originX, points.get(start+6).getTrueY()+originY);
			side6.addPoint(points.get(start+7).getTrueX()+originX, points.get(start+7).getTrueY()+originY);
			g.setColor(color6);
			g.fillPolygon(side6);
		}

		if (front == 0 || front == 1 || front == 4 || front == 5)
		{
			Polygon side2 = new Polygon();
			side2.addPoint(points.get(start).getTrueX()+originX, points.get(start).getTrueY()+originY);
			side2.addPoint(points.get(start+1).getTrueX()+originX, points.get(start+1).getTrueY()+originY);
			side2.addPoint(points.get(start+5).getTrueX()+originX, points.get(start+5).getTrueY()+originY);
			side2.addPoint(points.get(start+4).getTrueX()+originX, points.get(start+4).getTrueY()+originY);
			g.setColor(color2);
			g.fillPolygon(side2);
		}

		if (front == 2 || front == 3 || front == 6 || front == 7)
		{
			Polygon side5 = new Polygon();
			side5.addPoint(points.get(start+2).getTrueX()+originX, points.get(start+2).getTrueY()+originY);
			side5.addPoint(points.get(start+3).getTrueX()+originX, points.get(start+3).getTrueY()+originY);
			side5.addPoint(points.get(start+7).getTrueX()+originX, points.get(start+7).getTrueY()+originY);
			side5.addPoint(points.get(start+6).getTrueX()+originX, points.get(start+6).getTrueY()+originY);
			g.setColor(color5);
			g.fillPolygon(side5);
		}

		if (front == 0 || front == 3 || front == 4 || front == 7)
		{
			Polygon side3 = new Polygon();
			side3.addPoint(points.get(start).getTrueX()+originX, points.get(start).getTrueY()+originY);
			side3.addPoint(points.get(start+3).getTrueX()+originX, points.get(start+3).getTrueY()+originY);
			side3.addPoint(points.get(start+7).getTrueX()+originX, points.get(start+7).getTrueY()+originY);
			side3.addPoint(points.get(start+4).getTrueX()+originX, points.get(start+4).getTrueY()+originY);
			g.setColor(color3);
			g.fillPolygon(side3);
		}

		if (front == 1 || front == 2 || front == 5 || front == 6)
		{
			Polygon side4 = new Polygon();
			side4.addPoint(points.get(start+1).getTrueX()+originX, points.get(start+1).getTrueY()+originY);
			side4.addPoint(points.get(start+2).getTrueX()+originX, points.get(start+2).getTrueY()+originY);
			side4.addPoint(points.get(start+6).getTrueX()+originX, points.get(start+6).getTrueY()+originY);
			side4.addPoint(points.get(start+5).getTrueX()+originX, points.get(start+5).getTrueY()+originY);
			g.setColor(color4);
			g.fillPolygon(side4);
		}

    }


	public void drawCube(int start)  {  drawPrism(start);  }
	public void fillCube(int start)  {  fillPrism(start);  }


	public int makeCube(int userX, int userY, int userZ, int size, Color cubeColor)
	{
		return makePrism(userX,userY,userZ,size,size,size,cubeColor);
    }

	public int makeCube(int userX, int userY, int userZ, int size)
	{
		return makePrism(userX,userY,userZ,size,size,size);
    }

	public void rotateX(double deltaX)
	{
		for (Point3D point: points)
			point.rotateX(deltaX);
	}

	public void rotateY(double deltaY)
	{
		for (Point3D point: points)
			point.rotateY(deltaY);
	}

	public void rotateZ(double deltaZ)
	{
		for (Point3D point: points)
			point.rotateZ(deltaZ);
	}

	public void rotateXY(double deltaX, double deltaY)
	{
		for (Point3D point: points)
			point.rotateXY(deltaX,deltaY);
	}

	public void rotateXZ(double deltaX, double deltaZ)
	{
		for (Point3D point: points)
			point.rotateXZ(deltaX,deltaZ);
	}

	public void rotateYZ(double deltaY, double deltaZ)
	{
		for (Point3D point: points)
			point.rotateYZ(deltaY,deltaZ);
	}

	public void rotateXYZ(double deltaX, double deltaY, double deltaZ)
	{
		for (Point3D point: points)
			point.rotateXYZ(deltaX,deltaY,deltaZ);
	}

	public void rotateXY(double deltaXY)	{  rotateXY(deltaXY,deltaXY);              }
	public void rotateXZ(double deltaXZ)	{  rotateXZ(deltaXZ,deltaXZ);              }
	public void rotateYZ(double deltaYZ)    {  rotateXY(deltaYZ,deltaYZ);              }
	public void rotateXYZ(double deltaXYZ)  {  rotateXYZ(deltaXYZ,deltaXYZ,deltaXYZ);  }


	public void scale(double s)
	{
		for (Point3D point: points)
			point.scale(s);
	}

	public void scaleX(double s)
	{
		for (Point3D point: points)
			point.scaleX(s);
	}

	public void scaleY(double s)
	{
		for (Point3D point: points)
			point.scaleY(s);
	}

	public void scaleZ(double s)
	{
		for (Point3D point: points)
			point.scaleZ(s);
	}


	public void translate(double tx, double ty, double tz)
	{
		for (Point3D point: points)
			point.translate(tx,ty,tz);
	}

	public void translateX(double t)
	{
		for (Point3D point: points)
			point.translateX(t);
	}

	public void translateY(double t)
	{
		for (Point3D point: points)
			point.translateY(t);
	}

	public void translateZ(double t)
	{
		for (Point3D point: points)
			point.translateZ(t);
	}


	private double mostBackwardValue(int start)
	{
		int finish = start+8;
		int smallest = start;
		for (int j = start+1; j < finish; j++)
			if (points.get(j).getResultantZ() < points.get(smallest).getResultantZ())
				smallest = j;
		return points.get(smallest).getResultantZ();
	}


	private int mostForwardIndex(int start)
	{
		int finish = start+8;
		int largest = start;
		for (int j = start+1; j < finish; j++)
			if (points.get(j).getResultantZ() > points.get(largest).getResultantZ())
				largest = j;
		return largest;
	}


	private void swap(int index1, int index2)
	{
		// swap group of 8 points in the prism
		for (int j = 0; j < 8; j++)
		{
			Point3D temp = new Point3D();
			temp = points.get(index1+j);
			points.set(index1+j,points.get(index2+j));
			points.set(index2+j,temp);
		}

		// swap colors
		int colorIndex1 = index1 / 8;
		int colorIndex2 = index2 / 8;
		Color tempColor = prismColors.get(colorIndex1);
		prismColors.set(colorIndex1,prismColors.get(colorIndex2));
		prismColors.set(colorIndex2,tempColor);
	}


	public void sortBackToFront()   // sorts groups of 8 points based how close
	{								// they are to the person viewing the image
		int smallest = 0;
		for (int p = 0; p < points.size(); p+=8)
		{
	   		smallest = p;
	    	for (int q = p+8; q < points.size(); q+=8)
	 			if (mostBackwardValue(q) < mostBackwardValue(smallest))
	    			smallest = q;
	    	if (!points.get(p).equals(points.get(smallest)))
	    		swap(p,smallest);
	    }
	}


	public boolean behindUser(int start)
	{
		boolean behind = false;
		int finish = start+8;
		for (int j = start; j < finish; j++)
			if (points.get(j).getResultantZ() >= 650)
				behind = true;
		return behind;
	}
}

