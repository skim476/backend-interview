package ai.brace.model;

import java.util.Comparator;

public class TextComparator implements Comparator<Text>{

    @Override
    public int compare(Text o1, Text o2) {
        return o1.getId() - o2.getId();
    }
}
