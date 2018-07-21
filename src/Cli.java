
public class Cli implements Runnable
{
    Action actions = null;

    Cli(Action action) {
        actions = action;
    }

    public void run() {
        while(true) {
            try{
                String input = System.console().readLine();
                if(input != null) {
                    String [] actionStrings = input.split("\\s+", 3);
                    actions.executeAction(actionStrings);
                }
            }
            catch(NullPointerException e) {
                e.printStackTrace();
            }
            catch(ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
    }
}