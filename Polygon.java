public class Polygon {
    //class variables
    private int sides; //priority over shape type
    private double sideLength;
    private String shape;
    private String secondaryShapeName;
    private boolean undocumentedShape;
    private boolean autoCorrect; // determines whether the program will autocorrect invalid shapes/sides when mutated
    boolean defaultAutoCorrect = false; //default value for autoCorrect

    //utility variables
    final String[] shapeNames = {"triangle","square","pentagon","hexagon","heptagon","octagon","nonagon","decagon"};
    final int[][] sideIndices = {{3,0},{4,1},{5,2},{6,3},{7,4},{8,5},{9,6},{10,7}}; // {numSides, shapeNames Index}

    //constructors

    /**
     *The default constructor that initializes a Polygon object.
     */
    public Polygon(){
        autoCorrect = defaultAutoCorrect;
        sides = 4;
        sideLength = 1.0;
        this.sideLength = Math.abs(sideLength);
        classifyShape();
    }

    /**
     *Initializes a Polygon object.
     * @param numSides - the number of sides of the Polygon object
     * @param sideLength - the length of a side (Polygon object is equilateral) of the Polygon object
     * @param shape - the shape name of the Polygon object
     */
    public Polygon(int numSides, double sideLength, String shape){
        autoCorrect = defaultAutoCorrect;
        sides = numSides;
        this.sideLength = sideLength;
        if (autoCorrect){
            this.sideLength = Math.abs(sideLength);
            classifyShape(); //String shape is unused for validation
        } else {
            this.shape = shape;
            updateSecondaryShapeName();
        }

    }

    //private utility methods

    /**
     * Updates the String secondaryShapeName to its corresponding shape name -- based on String shape
     */
    private void updateSecondaryShapeName(){
        secondaryShapeName = null;
        switch (sides) {
            case 4:
                secondaryShapeName = "rectangle";
                break;
            case 3:
                secondaryShapeName = "trigon";
                break;
        }
    }

    /**
     *Returns false if the specified shape name does match the specified number of sides or true if it does or the shape's name is not known
     * @param numSides - the number of sides to be checked with its shape name
     * @param shapeName - the shape name that will be checked with sides
     * @return - a boolean true if the specified shape name and number of sides match
     */
    private boolean checkShapeSidesWithName(int numSides, String shapeName){
        for (int[] l : sideIndices){
            if (l[0] == numSides){ //are java arrays always in order?
                undocumentedShape = false;
                if (shapeName.equals(shapeNames[l[1]])){

                    return true;
                }
                return false;
            }
        }

        undocumentedShape = true;
        return true; //note, undocumented polygon
    }

    /**
     *Sets the shape name of the polygon based on its number of sides.
     */
    private void classifyShape(){
        updateSecondaryShapeName(); //include in javadocs!

        for (int[] l : sideIndices){
            if (l[0]==sides){
                shape = shapeNames[l[1]];
            }
        }
    }

    /**
     *Sets the number of sides of the polygon based on its shape name.
     */
    private void classifySides(){
        for (String s: shapeNames){
            for (int[] l : sideIndices){
                if (shapeNames[l[1]].equals(s)){
                    sides = l[0];
                    return;
                }
            }
        }
    }

    /**
     *Returns a rounded number to specified decimal places.
     * @param value - the number that will be rounded
     * @param decimalPlaces - the number of decimal places that number will be rounded to
     * @return - the rounded number to decimalPlaces decimal places.
     */
    private static double roundTo(double value, int decimalPlaces){
        double s = Math.pow(10,decimalPlaces);
        double rounded = Math.round(value*s)/s;
        return rounded;
    }

    //mutators

    /**
     * Sets the shape value of the Polygon object. If autoCorrect is set to true, the shape will be corrected if it doesn't match its corresponding number of sides.
     * @param shape - the new shape name that will be set for Polygon object
     */
    public void setShape(String shape){
        if (autoCorrect){
            for (String s: shapeNames){
                if (s.equals(shape)){
                    this.shape = shape; //shape exists
                    classifySides();
                    return;
                }
            }
            return;
        } else {
            this.shape = shape;
        }
    }

    /**
     *Sets the sides value of the Polygon object.
     * @param sides - the new value of number of sides that will be set for Polygon object
     */
    public void setSides(int sides){
        this.sides = sides;
        if (autoCorrect){
            classifyShape();
        }

    }

    /**
     *Sets the side length value of the Polygon object.
     * @param length - the new value of sideLength that will be set for Polygon object
     */
    public void setSideLength(double length){
        sideLength = length;
    }

    /**
     * Changes the value of autoCorrect within the Polygon object to provided boolean on
     * @param on - a boolean that is the new value of autoCorrect
     */
    public void setAutoCorrect(boolean on){
        autoCorrect = on;
    }

    // other methods

    /**
     *Calculates the double area of the Polygon object.
     * @return area - the area of the Polygon object based on sides and sideLength
     */
    public double calculateArea(){
        double a = sideLength/(2*Math.tan((180/sides)*Math.PI/180));
        double area = 0.5*((sideLength*sides)*a);
        return area;
    }

    /**
     *Calculates the double perimeter of the Polygon object.
     * @return perimeter - the perimeter of the Polygon object; sides*sideLength = perimeter
     */
    public double calculatePerimeter(){
        return sides*sideLength;
    }

    /**
     *Returns a String response if the Polygon object has invalid or conflicting values.
     * @return validity response - a response that is null if the Polygon object values are valid, otherwise it returns a reason to the invalidity
     */
    public String checkForValidity(){
        String response = "Invalid Polygon -- ";
        if (!checkShapeSidesWithName(sides, shape)){
            return response+" The shape name does not match number of sides.";
        }
        if (!(sideLength >0)){
            return response+" Side lengths cannot be negative or zero.";
        }
        if(sides<3){
            return response+" A polygon must have at least 3 sides.";
        }
        return null;
    }

    //accessors

    /**
     *Returns the int sides of the Polygon object.
     * @return sides - the number of sides of the Polygon object
     */
    public int getNumSides(){
        return sides;
    }

    /**
     *Returns the double sideLength of the Polygon object.
     * @return sideLength - the length a side (equilateral) of the Polygon object
     */
    public double getSideLength(){
        return sideLength;
    }

    /**
     *Returns the String shape name of the Polygon object.
     * @return shape - the shape name of the Polygon object
     */
    public String getShapeType(){
        return shape;
    }

    /**
     *Returns the String secondary shape name of the Polygon object.
     * @return secondaryShapeName - the secondary name of the shape of the Polygon object (i.e. square and rectangle)
     */
    public String getSecondaryShapeName(){
        return secondaryShapeName;
    }

    /**
     *Returns a String describing the Polygon object.
     * @return - the description of the Polygon object
     */
    public String toString(){
        String validityCheckResponse = checkForValidity();
        if (!autoCorrect && validityCheckResponse !=null){
            return validityCheckResponse;
        }
        double perimeter = roundTo(calculatePerimeter(), 3);
        double area = roundTo(calculateArea(),3);

        String addTag = "";
        if (secondaryShapeName!=null){
            addTag = " (also known as a "+secondaryShapeName+")";
        }

        String undocumentedTag = "";
        if (undocumentedShape){
            undocumentedTag = "\n"+shape+" is a shape that does not have a specific name tied to it in this class.";
        }
        return "Your shape is a "+shape+addTag+" and it has "+sides+" sides. \nIt has a side length of "+roundTo(sideLength,3)+" units. \nIt has a perimeter of "+ perimeter+" units. \nIt has an area of "+area+" square units."+undocumentedTag;
    }

}