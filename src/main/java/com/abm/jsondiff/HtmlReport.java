package com.abm.jsondiff;

import java.util.Map;

public class HtmlReport {

    public static final String TAB_SPACE = "&nbsp;&nbsp;";
    public static final String LINE_BREAK_NEXT_LINE = "<br>";
    private int index;
    private int size;

    public String print(Map<String, Object> leftDiff, Map<String, Object> rightDiff) {
        String leftDiffReport = generateDiff(leftDiff);
        String rightDiffReport = generateDiff(rightDiff);

        String reportString = "<html>\n" +
                "\t<head>\n" +
                "\t\t<title>JSON Diff Report</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<table border=\"1\">\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<th>EXPECTED</th><th>ACTUAL</th>\n" +
                "\t\t\t</tr>\n" +
                "\t\t\t<tr>\n" +
                "\t\t\t\t<td valign=\"top\">" +
                leftDiffReport +
                "\n" +
                "\t\t\t\t<td valign=\"top\">" +
                rightDiffReport +
                "</td>\n" +
                "\t\t\t</tr>\n" +
                "\t\t</table>\n" +
                "\t</body>\n" +
                "</html>";

        System.out.println(reportString);
        return reportString;
    }


    private String generateDiff(Map<String, Object> diffMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");

        size = diffMap.size();
        resetIndex();

        diffMap.forEach((key, val) -> {
            incrementIndex();
            if (key.contains("->")) {
                String newKey = key.substring(0, key.indexOf("->"));
                stringBuilder
                        .append("<span style=\"background-color: #fc9b44\">")
                        .append(LINE_BREAK_NEXT_LINE).append(TAB_SPACE)
                        .append("\"").append(newKey).append("\"")
                        .append(" : ")
                        .append("\"").append(val).append("\"").append(getTrailingComma()).append("</span>\n");
            } else {
                stringBuilder
                        .append(LINE_BREAK_NEXT_LINE).append(TAB_SPACE)
                        .append("\"").append(key).append("\"")
                        .append(" : ")
                        .append("\"").append(val).append("\"").append(getTrailingComma()).append("\n");
            }
        });
        stringBuilder.append("<br>&nbsp;&nbsp;}");

        return stringBuilder.toString();
    }

    public String getTrailingComma() {
        return index < size ? "," : "";
    }

    private void incrementIndex() {
        index++;
    }

    private void resetIndex() {
        index = 0;
    }

}
