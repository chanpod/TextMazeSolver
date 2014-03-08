   import java.util.Scanner;
   import java.io.File;
   import java.io.IOException;
   import java.util.Arrays;

   public class Maze {
      protected Scanner scanIn;
      protected char[][] map;
      protected int row, column, currentX, currentY, processes;
      public static final char START = 'S';
      public static final char FINISH = 'F';
      
   
      public Maze(String fileNameIn) throws IOException {
      
         scanIn = new Scanner(new File(fileNameIn));
         row = scanIn.nextInt();
         column = scanIn.nextInt();
         map = new char[row][column];
         
         
         for (int i = 0; i < map.length; i++)
         {
            String temp = scanIn.next();
            for (int j = 0; j < map[i].length; j++)
            {
               map[i][j] = temp.charAt(j);
            }
         }
         getStart();
      }
      
      	
      public String getStart() 
      {
         int x = 0;
         int y = 0;
         while (y < getMap().length && getMap()[y][x] != START)
         {
            while (x < getMap()[y].length && getMap()[y][x] != START)
            {
               x++;
            }
            if (x == getMap()[y].length && getMap()[y][x - 1] != START)
            {
               x = 0;
               y++;
            }
         }
         currentX = x;
         currentY = y;
         return x + " " + y;
      }
      
      public String getFinish() 
      {
         int x = 0;
         int y = 0;
         while (y < getMap().length && getMap()[y][x] != FINISH)
         {
            while (x < getMap()[y].length && getMap()[y][x] != FINISH)
            {
               x++;
            }
            if (x == getMap()[y].length && getMap()[y][x - 1] != FINISH)
            {
               x = 0;
               y++;
            }
         }
         return x + " " + y;
      }
      
      public boolean checkNorth()
      {
         if (currentY != 0 && getMap()[currentY - 1][currentX] != 'x' && getMap()[currentY - 1][currentX] != 'o' && getMap()[currentY - 1][currentX] != 'S')
         {
            return true;
         }
         else {
            return false;
         }
      }
      
      public boolean checkSouth()
      {
         if (currentY != getMap().length - 1)
         {
            if (getMap()[currentY + 1][currentX] != 'x' && getMap()[currentY + 1][currentX] != 'o' && getMap()[currentY + 1][currentX] != 'S')
            {
               return true;
            }
         }
         
         return false;  
         
      }
      
      public boolean checkEast()
      {
         if (currentX < getMap()[currentY].length - 1 && getMap()[currentY][currentX + 1] != 'x' && getMap()[currentY][currentX + 1] != 'o' && getMap()[currentY][currentX + 1] != 'S')
         {
            return true;
         }
         else {
            return false;
         }
      }
      
      public boolean checkWest()
      {
         if (currentX > 0 && getMap()[currentY][currentX - 1] != 'x' && getMap()[currentY][currentX - 1] != 'o' && getMap()[currentY][currentX - 1] != 'S')
         {
            return true;
         }
         else {
            return false;
         }
      }
      
      public boolean checkFinish()
      {
         boolean isFound = false;
         if (currentY != 0 && getMap()[currentY - 1][currentX] == 'F')
         {
            return isFound = true;
         }
         
         else if (currentY != map.length - 1)
         {
            if (getMap()[currentY + 1][currentX] == 'F')
            {
               return isFound = true;
            }
            else
            {
            }
         }
         
         if (currentX < getMap()[currentY].length - 1 && getMap()[currentY][currentX + 1] == 'F')
         {
            return isFound = true;
         }
         
         else if (currentX > 0 && getMap()[currentY][currentX - 1] == 'F')
         {
            return isFound = true;
         }
         return isFound;
      }
   	
      public char[][] getMap()
      {
         return map;
      }
      
      public int getCurrentXLocation()
      {
         return currentX;
      }
      
      public int getCurrentYLocation()
      {
         return currentY;
      }   
      
      
      public String toString()
      {
         String output = "";
         	
         for (int i = 0; i < map.length; i++)
         {
            output += Arrays.toString(map[i]) + "\r\n";
            
         }
         output += "\r\n Number of blocks processed: " + processes;
         output += "\r\n\r\n\r\n";
         return output;
      }
      
      
   
   }