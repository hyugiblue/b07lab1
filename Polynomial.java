public class Polynomial {
    public double[] coefs;

    public Polynomial() {
        this.coefs = new double[]{0};
    }

    public Polynomial(double[] coefs) {
        this.coefs = coefs.clone();
    }
    
  
    public Polynomial add(Polynomial other) {
        double[] newCoefs;

        // Make the bigger list the newCoefs, then go through smaller and add the values
        if (this.coefs.length >= other.coefs.length) {
            newCoefs = this.coefs.clone();
            for (int i = 0; i < other.coefs.length; i++) {
                newCoefs[i] += other.coefs[i];
            }

        } 
        else {
            newCoefs = other.coefs.clone();
            for (int i = 0; i < this.coefs.length; i++) {
                newCoefs[i] += this.coefs[i];
            }
        }

        return new Polynomial(newCoefs);
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