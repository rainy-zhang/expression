package org.rainy.expression.reader;

import org.rainy.expression.ExpressionException;
import org.rainy.expression.common.Cacheable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 从文件中获取数据
 * @author: zhangyu
 * @date: 2021/9/29 0029 16:11
 */
public class FileReader implements Reader {

    private static final String SEPARATOR = ";";

    @Override
    public List<Map<String, String>> read() {
        try {
            final Path filePath = Paths.get("D:\\my\\expression\\src\\main\\java\\org\\rainy\\expression\\20123919_RYRY_291230120312.txt");
            Stream<String> lines = Files.lines(filePath);
            String dataType = getDataType(filePath);
            return lines.map(line -> {
                String[] fieldValues = line.split(SEPARATOR, -1);
                List<String> fieldNames = Cacheable.fieldNameMap.get(dataType);
                if (fieldValues.length != fieldNames.size()) {
                    throw new ExpressionException("字段长度错误");
                }
                return toMap(fieldValues, fieldNames);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDataType(Path filePath) {
        String fileName = filePath.getFileName().toString();
        int start = fileName.indexOf("_");
        int end = fileName.lastIndexOf("_");
        return fileName.substring(start + "_".length(), end);
    }

}
