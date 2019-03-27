package pl.edu.pk.mech.datasplitter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;
import static org.apache.commons.lang3.StringUtils.countMatches;

class SplitterImpl implements Splitter {

    @Override
    public Map<String, String> splitData(String data) throws DataValidationException {
        if(data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Empty data frame");
        }

        String[] params = validateData(data);

        return Arrays.stream(params)
                .skip(1)
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(a -> a[0], a -> a[1]));
    }

    private String[] validateData(String data) throws DataValidationException {
        String[] params = data.split(";");
        int numberOfParams = countMatches(data, ";");
        String size = params[0].split(":")[1];

        if(valueOf(size) != (numberOfParams - 1)) {
            throw new DataValidationException("Invalid data frame");
        }

        return params;
    }
}
