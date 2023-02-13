public class PolygonTester {
    /**
     * shorter  for System.out.println() and only takes String txt
     * @param txt - String of what will be printed
     */
    public static void pln(String txt){
        System.out.println(txt);
    }
    public static void main(String[] args) {
        //test cases

        pln("This is the output with 10 sides, side length of 3.2139231293123, shape called decagon");
        Polygon polygon1 = new Polygon(10,3.2139231293123,"decagon");
        pln(polygon1+"\n");

        pln("This is the output with 3 sides, side length of 233, shape called triangle");
        Polygon polygon2 = new Polygon(3,233,"triangle");
        pln(polygon2+"\n");

        pln("This is the output with 16 sides, side length of 0, shape called deggagon (not real name)");
        Polygon polygon3 = new Polygon(16,0,"deggagon");
        pln(polygon3+"\n");

        pln("This is the output with 4 sides, side length of 5, shape called square");
        Polygon polygon4 = new Polygon(4,5,"square");
        pln(polygon4+"\n");

        pln("This is the output with the default constructor");
        Polygon polygon5 = new Polygon();
        pln(polygon5+"\n");

        pln("This is the output with 50 sides, side length of 0.010002, shape called pentacontagon (shape name not known by the class)");
        Polygon polygon6 = new Polygon(50,0.010002,"pentacontagon"); //custom shape name
        pln(polygon6+"\n");

        pln("This is the output with 1 side, side length of 5, shape called 'one sided polygon???' ");
        Polygon polygon7 = new Polygon(1,5,"one sided polygon???");
        pln(polygon7+"\n");

        pln("This is the output with 4 sides, side length of 5, shape called triangle");
        Polygon polygon8 = new Polygon(4,5,"triangle");
        pln(polygon8+"\n");

    }
}