package pl.edu.pk.mech.datasplitter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class SplitterImpl implements Splitter {

    public Map<String, String> splitData(String data) {
        if(data == null || data.isEmpty())
            throw new IllegalArgumentException("Empty data frame");

        String[] params = data.split(";");

        Map<String, String> collectedData = Arrays.stream(params)
                .skip(1)
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));

        String size = params[0].split(":")[1];

        if(Integer.valueOf(size) != collectedData.size())
            throw new IllegalArgumentException("Invalid data frame");

        return collectedData;
    }
}
