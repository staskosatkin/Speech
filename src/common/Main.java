package common;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        try {
            Application app = new Application();
            app.run();
        } catch (Exception e) {
            System.out.println("Unexpected exception: " + e.getMessage());
            System.exit(1);
        }
    }

}
