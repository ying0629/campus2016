package Main;
import java.util.regex.Pattern;
/**
 * Created by songshaoying on 16/6/5.
 */
public class ImportSave implements Comparable<ImportSave> {
    private String className;
    private int count;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(ImportSave o) {
        if(this.count == o.count)
            return 0;
        else if(this.count < o.count)
            return -1;
        else
            return 1;

    }

    public ImportSave(String className , int count){
        this.className = className;
        this.count = count;
    }
}
