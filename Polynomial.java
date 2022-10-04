import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
    public double[] coefs;
    public int[] expo; 
    
    public Polynomial() {
        this.coefs = new double[]{0};
        this.expo = new int[]{0}; 
    }

    public Polynomial(double[] coefs, int[] expo) {
        this.coefs = coefs.clone();
        this.expo = expo.clone();
    }
    
    public Polynomial(File x) throws FileNotFoundException {
    	
        int j = 0;
        int k = 0;
        
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(x);
    	
    	String str = scanner.nextLine();
    	String[] parts = str.split("+");
    	double[] newCoefs = new double[parts.length];
        int[] newExpo = new int[parts.length];
    	
    	for (int n = 0 ; n < parts.length; n++)
    	{
    		String[] moreparts = parts[n].split("x");
    		
    		newCoefs[j] = Double.parseDouble(moreparts[0]);
    		newExpo[k] = Integer.parseInt(moreparts[1]); 
    		j++;
    		k++;
    	}	
    }
    
    public void SaveToFile(String filename) throws IOException{
    	String value = "";
    	
    	for (int i = 0; i < this.expo.length - 2; i++) {
    	
    		value = value + this.coefs[i];
    		value = value + "x"; 
    		value = value + this.expo[i];
    		if (coefs[i+1] >= 0) {
    			value = value + "+";
    		} 
    	}
    	value = value + this.coefs[this.expo.length - 1];
    	value = value + "x"; 
		value = value + this.expo[this.expo.length - 1];
				
		@SuppressWarnings("resource")
		FileWriter output = new FileWriter(filename);

	       // Writes the program to file
	    output.write(value);
    }
    
    
  
    public Polynomial add(Polynomial other) {
        double[] newCoefs;
        int[] newExpo; 
        // Make the bigger list the newCoefs, then go through smaller and add the values
        if (this.coefs.length >= other.coefs.length) {
            newCoefs = this.coefs.clone();
            newExpo = this.expo.clone();
            for (int i = 0; i < other.coefs.length; i++) {
                newCoefs[i] += other.coefs[i];
            }
        } 
        else {
            newCoefs = other.coefs.clone();
            newExpo = other.expo.clone();
            for (int i = 0; i < this.coefs.length; i++) {
                newCoefs[i] += this.coefs[i];
            }
        }

        return new Polynomial(newCoefs, newExpo);
    }
    
    @SuppressWarnings("unlikely-arg-type")
	public Polynomial multiply(Polynomial other) {
    	
    	int a = this.coefs.length;
    	int b = other.coefs.length;
    	
    	
    	double[] newCoefs = new double[a + b - 1];
        int[] newExpo = new int[a+b-1]; 
        
        for (int i = 0; i < a + b - 1; i++) 
        {
            newCoefs[i] = 0;
            newExpo[i] = 0;
        }
        
        for (int i = 0; i < a; i++) 
        {
            for (int j = 0; j < b; j++) 
            {
                newCoefs[i + j] += this.coefs[i] * other.coefs[j];
                
                int tempExpo = this.expo[i] + other.expo[j];
               
                if (Arrays.asList(newExpo).contains(tempExpo) == false){ 
                newExpo[i+j] += this.expo[i] + other.expo[j];
                }
            }
        }
        
    	return new Polynomial(newCoefs, newExpo);
        }
        
    
    public double evaluate(double x) {
         double p = 0;
         for (int i = this.coefs.length-1; i >= 0; i--)
             p = coefs[i] + (x * p);
         return p;
    }
     
   public boolean hasRoot(double x) {
      return this.evaluate(x) == 0;
   } 
}