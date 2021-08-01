package survey.flat;
import java.util.HashMap;
import java.util.Map;

public class FlatExcelSelector {
    public static final String SKIP = "-3";
    public static final String SELECTED = "1";
    public static final String UNSELECTED = "0";

    public static final String OTHER = "other";


    private final Map<Integer, FlatExcel> map = new HashMap<>();

    public FlatExcelSelector() {
        LocationFlatExcel location = new LocationFlatExcel();
        MultiChoiceFlatExcel multiChoice = new MultiChoiceFlatExcel();
        RadioFlatExcel radio = new RadioFlatExcel();
        TextFlatExcel text = new TextFlatExcel();

        map.put(1, radio);
        map.put(2, multiChoice);
        map.put(3, text);
        map.put(5, location);
        map.put(6, radio);
    }

    public FlatExcel getFlatExcel(Integer type) {
        return map.get(type);
    }
}
