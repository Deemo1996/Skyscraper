import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;  
import java.util.Scanner;
import java.util.Stack; 
    public class Skyscraper {
	    
	    public static void skyscraper1() throws IOException { 
	        Scanner sc = new Scanner(System.in);  
	        int m = sc.nextInt();  
	        int n = sc.nextInt();  
	        int c = sc.nextInt();
	        int[][] arr = new int[3][n];  
	        
	        //use borderSideLength to store the maximum side length  
	        int borderSideLength = 0; 
	        int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	        sc.nextLine();
	        for (int t = 0; t < n; t++) {
	        	int x = sc.nextInt();
	        	if(x == c) arr[0][t] = 1;
	        	else arr[0][t] = 0;
	        }
	        for (int i = 1; i < m; i++) {  
	            sc.nextLine(); 
	            arr[1][0] = sc.nextInt();
	            if(arr[1][0] == c)  arr[1][0] = 1;
	            else arr[1][0] = 0;

	            for (int j = 1; j < n; j++) {  
	                int x = sc.nextInt();
            	    if(x == c) {
            	        //make the vertex arr[i][j], find the minimum of the left, diagonize, and the upper of it 
	                    arr[1][j] = Math.min(arr[1][j - 1], Math.min( arr[0][j], arr[0][j - 1])) + 1;  
	                    //if the vertex arr[i][j] is bigger than the maximum side length, update the maximum side length  
	                    if (arr[1][j] > borderSideLength) {
	                        borderSideLength = arr[1][j];  
	                        x2 = i;
	                        y2 = j;
	                        x1 = x2 - borderSideLength + 1;
	                        y1 = y2 - borderSideLength + 1;        
	                    }
	                }
            	    else arr[1][j] = 0;
            	    	   
	            } 
            	for(int r = 0; r < n; r++) {
            	    arr[0][r] = arr[1][r];
            	}
	        } 
	      
	        x1 = x1 + 1;
	        x2 = x2 + 1;
	        y1 = y1 + 1;
	        y2 = y2 + 1;
	        System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
	        sc.close();    
	    }  
	    
	    
	    public static int[] getArea(int[] heights, int left, int right) {
	    	int[] res = new int[3];
	        int leftArea, rightArea = 0;
	   	    int midi = 0, midj = 0, lefti = 0, righti = 0, leftj = 0, rightj = 0;
	    	    
	        if (left == right) {
	            res[0] = left;
	       	    res[1] = heights[left];
	       	    res[2] = left;
	            return res;
	        }
	        int mid = (left + right) / 2;
	        // left max area
	        
	        if(mid - 1 < left) leftArea = 0;
	        else {
	         	int[] leftmax = new int[3];
		        leftmax = getArea(heights, left, mid-1);
		        lefti = leftmax[0];
		        leftArea = leftmax[1];
		        leftj = leftmax[2];
	        }
	        // right max area
	        if( mid+1 > right) rightArea = 0;
	        else{
	         	int[] rightmax = new int[3];
	         	rightmax = getArea(heights, mid+1, right);
	         	righti = rightmax[0];
	         	rightArea = rightmax[1];
	         	rightj = rightmax[2];
	        }
	       
	        
	        // mid max area, including current bar
	        int i = mid, j = mid;
	        int width, midArea = 0;
	        int height = heights[mid];
	        while (i >= left && j <= right) {
	            width = j - i + 1;
	            height = Math.min(height, Math.min(heights[i], heights[j]));
	            
	            if(midArea <= width * height) {
	                midArea = width * height;
	            	midi = i;
	            	midj = j;
	            }
	            
	            
	            if (i == left) j += 1;
	            else if (j == right) i -= 1;
	            else if (heights[i-1] >= heights[j+1])i -= 1;
	            else j += 1;
	        }
	        
	        if(midArea == Math.max( midArea, Math.max(leftArea, rightArea) )) {
	         	res[0] = midi;
	        	res[1] = midArea;
	        	res[2] = midj;
	        }
	        if(leftArea == Math.max( midArea, Math.max(leftArea, rightArea) )) {
	        	res[0] = lefti;
	        	res[1] = leftArea;
	        	res[2] = leftj;
	        }
	        if(rightArea == Math.max( midArea, Math.max(leftArea, rightArea) )) {
	        	res[0] = righti;
	        	res[1] = rightArea;
	        	res[2] = rightj;
	        }
	       
	        return res;
	    }
	    
	    public static int[] largestRectangleArea(int[] heights) {
            return getArea(heights, 0, heights.length-1);
        }

	    public static void skyscraper2() throws IOException {
	     	 
	        Scanner sc = new Scanner(System.in);  
	          
	        int m = sc.nextInt();  
            int n = sc.nextInt();  
            int c = sc.nextInt();
	        
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0, height = 0, width = 0;
            int area = 0;
            int h[] = new int[n];
	            
            sc.nextLine();
	            
            for (int row = 0; row < m; row++) {
             	String array = sc.nextLine();
           	    String numarray[] = array.split(" ");
                int[] g = new int[n];
                for (int i = 0; i < n;i++) {
                	g[i] = Integer.parseInt(numarray[i]);
                    if(g[i] == c) h[i] += 1;
                    else h[i] = 0;
                }
                int[] res = largestRectangleArea(h);
                if(res[1] > area) {
                   x2 = row + 1;
                   y1 = res[0] + 1;
                   area = res[1];
                   y2 = res[2] + 1;
                }
            }
            width = y2 - y1 + 1;
            height = area / width;
            x1 = x2 - height + 1;
               
            System.out.println( x1 + " " + y1 + " " + x2 + " " + y2);
		         
            sc.close();  
	    }     
	   
	    
	    
	    public static void skyscraper3() throws IOException{
	        Scanner sc = new Scanner(System.in); 
            int m = sc.nextInt();  
            int n = sc.nextInt();  
	        int c = sc.nextInt();
            int[] h = new int[n + 1];
            int x1 = 0, x2 = 0, y1 = 0, y2 = 0, height = 0, width = 0;
            h[n] = 0;
            int max = 0;
            sc.nextLine();
		            
            for (int row = 0; row < m; row++) {
            	String array = sc.nextLine();
		            	  
           	    String numarray[] = array.split(" ");
                Stack<Integer> s = new Stack<Integer>();
                int[] g = new int[n];
                for (int i = 0; i < n;i++) {
                	g[i] = Integer.parseInt(numarray[i]);
                	if (i < n)
		            if(g[i] == c) h[i] += 1;
		            else h[i] = 0;
		            if (s.isEmpty()||h[s.peek()]<=h[i]) s.push(i);
		            else {
		                while(!s.isEmpty()&&h[i]<h[s.peek()]){
		                    int top = s.pop();
                            int area = h[top]*(s.isEmpty()?i:(i-s.peek()-1));
                            if (area>max) {
                                max = area;
                                height = h[top];
		                        y2 = i;
		                        x2 = row + 1;
		                    }   
	                    }
		                s.push(i);
		            }
		        }
		    }
		         
            width = max / height;
            x1 = x2 - height + 1;
            y1 = y2 - width + 1;
		    System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
			         
		    sc.close(); 
	    }
	    
	   
		            
	    public static void skyscraper4() throws IOException {
		    Scanner sc = new Scanner(System.in); 
		    int m = sc.nextInt();  
            int n = sc.nextInt();  
            int c = sc.nextInt();

            int arr[][] = new int[m][n];
   		    int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int area = 0;
            int x1 = 1, x2 = m, y1 = 1, y2 = n, height = 0, width = 0;
          
            for (int i = 0; i < m; i++) {  
           	    sc.nextLine();
                for (int j = 0; j < n; j++) {
                    int x = sc.nextInt();
                 	arr[i][j] = x;
                	if (x <= min) min = x;
                	if (x >= max) max = x;
                }  
            } 
            sc.close();

            if(max - min > c) {

            for(int t = min; t <= max - c; t++) {
          	    int[] h = new int[n + 1];
          	    h[n] = 0;
                for (int row = 0; row < m; row++) {
                    for (int i = 0; i < n;i++) {
                        if(arr[row][i] <= t + c && arr[row][i] >= t) h[i] += 1;
                        else h[i] = 0;
                    }
                    int[] res = largestRectangleArea(h);
                    if(res[1] > area) {
                        x2 = row + 1;
                        y1 = res[0] + 1;
                        area = res[1];
                        y2 = res[2] + 1;
                    }
                }
            }
            width = y2 - y1 + 1;
            height = area / width;
            x1 = x2 - height + 1;
            }

            System.out.println( x1 + " " + y1 + " " + x2 + " " + y2);
	    }  
	     
	    public static void main(String[] args) throws IOException {
	    	switch(args[0]){
	    		case "1": skyscraper1();
	    		          break;
	    		case "2": skyscraper2();
	    		          break;
	    		case "3": skyscraper3();
	    		          break;
	    		case "4": skyscraper4();
	    		          break;
	    	}
	    }   
}
