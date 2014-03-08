   import java.util.ArrayList;
   import java.io.IOException;
   
   /*
    * Created by: Chauncey Philpot
    * 
    * Dependency on Maze class
    * Dependenyc on a text file created in a specific manner
    * first is rows followed by columns.
    * Then a representation of the maze using x as walls
    * S for the start and F for the finish
    * - as open pathways.    * 
    * Supports impossible Mazes
    * 
    * Example file:
    * 6 5
	* xxxxx
	* xSxFx
	* x-x-x
	* x-x-x
	* x---x
	* xxxxx
    * 
    * Rows and columns must maintain width/height.
    * 
    * Will not work:
    * 
    * xxxxx
    * xSxFx
    * x-x-xxxx
    * x-x----x
    * x-xxxx-x
    * x------x
    * xxxxxxxx
    * 
    * but this will
    * 
    * xxxxxxxx
    * xSxFxxxx
    * x-x-xxxx
    * x-x----x
    * x-xxxx-x
    * x------x
    * xxxxxxxx
    */
   public class MazeTools extends Maze {
   	
      ArrayList<Integer> path = new ArrayList<Integer>();
      
      public MazeTools(String fileNameIn) throws IOException
      {
         super (fileNameIn);
      }
         
      public void push()
      {
         path.add(currentX);
         path.add(currentY);
      }
      
      public void push(int XIn, int YIn)
      {
         path.add(XIn);
         path.add(YIn);
      }
      
      public void pop()
      {
         path.remove(path.size() - 1);
         path.remove(path.size() - 1);
      }
      
      public String findFinish(Maze maze)
      {
         boolean isFound = false, impossible = false;
         boolean noPath = false, firstRun = true;
         push();
         while (!impossible && !isFound)
         {
            if (checkFinish())
            {
               isFound = true;
               
            }
            else
            {
               noPath = false;
            }
            while (!noPath && !isFound)
            {
               if (checkSouth())
               {
                  changeMazeIcon(currentX, currentY);
                  currentY++;
                  this.push();
                  noPath = true;
                  firstRun = false;
               }
               
               else if (checkEast())
               {
                  changeMazeIcon(currentX, currentY);
                  currentX++;
                  this.push();
                  noPath = true;
                  firstRun = false;
               }
               
               else if (checkNorth())
               {
                  changeMazeIcon(currentX, currentY);
                  currentY--;
                  this.push();
                  noPath = true;
                  firstRun = false;
               }
               else if (checkWest())
               {
                  changeMazeIcon(currentX, currentY);
                  currentX--;
                  this.push();
                  noPath = true;
                  firstRun = false;
               }
               
               if (!noPath && !firstRun && path.size() != 0)
               {
                  if (map[currentY][currentX] != 'S')
                  {
                     map[currentY][currentX] = 'x';
                  }
                  pop();
                  if (path.size() != 0)
                  {
                     currentY = path.get(path.size() - 1);
                     currentX = path.get(path.size() - 2);
                  }
               }
               
               else if (!noPath && path.size() == 0)
               {
                  noPath = true;
                  impossible = true;
                  isFound = true;
               }
               else if (!noPath && firstRun)
               {
                  noPath = true;
                  impossible = true;
                  isFound = true;
               }
               
               else
               {
                  noPath = true;
                  
               }
            }
         
            
         }
         if (isFound && !impossible)
         {
            if (map[currentY][currentX] != 'S')
            {
               map[currentY][currentX] = 'o';
            }
            return "Found Finish";
         }
         else
         {
            return "Impossible Solution";
         }
      }
      
      public void changeMazeIcon(int XIn, int YIn)
      {
         if (map[YIn][XIn] != 'S' && map[YIn][XIn] != 'F')
         {
            map[YIn][XIn] = 'o';
         }
         processes++;
      }
      
      public String queSearch(Maze maze)
      {
         getStart();
         boolean isFound = false, noSolution = false;
         push();
         
         while (!isFound)
         {
            if (path.size() != 0)
            {
               currentX = path.get(0);
               currentY = path.get(1);
            }
         	
            if (maze.checkFinish())
            {
               isFound = true;
               break;
            }
            if (maze.checkSouth())
            {
               push(currentX, currentY + 1);
               changeMazeIcon(currentX, currentY + 1);
            }
            if (maze.checkNorth())
            {
               push(currentX, currentY - 1);
               changeMazeIcon(currentX, currentY - 1);
            }
            if (maze.checkEast())
            {
               push(currentX + 1, currentY);
               changeMazeIcon(currentX + 1, currentY);
            }
            if (maze.checkWest())
            {
               push(currentX - 1, currentY);
               changeMazeIcon(currentX - 1, currentY);
            }
            if (path.size() != 0)
            {
               path.remove(0);
               path.remove(0);
            }
            
            else if (path.size() == 0 && !isFound)
            {
               noSolution = true;
               isFound = true;
            }
           
         }	
      	  
         if (noSolution)
         {
            return "No Solution";
         }
         return "Solution Found";
      }
      
      public static void main(String[] args) throws IOException
      {
         
         MazeTools tools = new MazeTools("maze5.txt");
         
         System.out.println(tools.queSearch(tools));
         
         MazeTools tools2 = new MazeTools("maze5.txt");
         
         System.out.println(tools2.findFinish(tools));
         
         System.out.println(tools.toString());
         System.out.println(tools2.toString());
         
         
      }
   
   }