package lazy;


public class HolderNaive {
    private Heavy heavy;

    public HolderNaive() {
        System.out.println("Holder created");
    }

    //lazy initialisation here means synchronized has to be put in for thread safety :(
    public synchronized Heavy getHeavy() {
        if(heavy == null) {
            heavy = new Heavy();
        }

        return heavy;
    }

    public static void main(final String[] args) {
        final HolderNaive holder = new HolderNaive();
        System.out.println("deferring heavy creation...");
        System.out.println(holder.getHeavy());
        System.out.println(holder.getHeavy());
    }
}
