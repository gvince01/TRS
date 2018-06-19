package ApplicationLogic;

/**
 * @author gvince01
 */

public class ConjestionResult {
    public static String conjested(Boolean result){
        if (result) return "Euston Road is currently conjested";
        else return "Euston road isn't conjested";
    }
}
