public class App {

    public static void main(String[] args) {
        //Cannot instantiate abstract classes
        //Shape myShape = new Shape();

        Square mySquare = new Square(2.0);
        mySquare.setColor("blue");

        System.out.println(mySquare.getArea());
        System.out.println(mySquare.getColor());

        Rectangle myRectangle = new Rectangle(2.0, 3.0);
        myRectangle.setLength(2);
        myRectangle.setWidth(3);
        System.out.println(myRectangle.getArea());

        //polymorphism
        Shape myShape = new Square(3.0); //only has access to parent methods
        Shape myShape2 = new Rectangle(4.0, 5.0);

        System.out.println(myShape.getArea());
        System.out.println(myShape2.getArea());
        System.out.println(myShape2.getColor());

    }
}
