package Hospital;

public class Program {
    public static void main(String[] args) {
        App.getInstance().init();
        App.getInstance().run();
        // System.out.println(Date.getInstance().changeDateFormat(String.valueOf(java.time.LocalDate.now())));
    }
}
