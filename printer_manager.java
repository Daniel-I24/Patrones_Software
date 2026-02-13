public class printer_manager {
    private static printer_manager instance:
    private boolean powerOn;
    private String model;
    private int Pagesprinted;

    private printer_manager() {
        this.powerOn = false;
        this.model = "HP LaserJet Pro M15w";
        this.Pagesprinted = 0;
        System.out.println("Printer Manager initialized. Model: " + model);    
    }

    public static printer_manager getInstance() {
        if (instance == null) {
            instance = new printer_manager();
        }
        return instance;
    }
}
