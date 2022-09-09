package com.swordie.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONParser {
    public static List<Student> parseFromJsonToStudentsList(String json) {
        String patternId = "\"id\":\\s*\\d+";
        String patternName = "\"name\":\\s*\"\\w+\"";
        ArrayList<String> matches = new ArrayList<>();

        Matcher mId = Pattern.compile(patternId).matcher(json);
        Matcher mName = Pattern.compile(patternName).matcher(json);

        while (mId.find() && mName.find()) {
            matches.add(mId.group() + ":" + mName.group());
        }

        List<Student> students = new ArrayList<>();
        for (String s : matches) {
            String[] strings = s.split(":");
            int id = Integer.parseInt(strings[1].replaceAll("\\s+", ""));
            String name = strings[3].split("\"")[1];

            students.add(new Student(id, name));
        }
        return students;
    }

    public static String parseFromStudentsListToJson(List<Student> students) {
        if (students.size() == 0) {
            return "[\n\"students\": [\n{\n}\n]\n}";
        }
        StringBuilder json = new StringBuilder("{\n" +
                "\"students\": [");
        for (Student student : students) {
            json.append("\n{\n");
            json.append(student.toString());
            json.append("\n},");
        }
        json.deleteCharAt(json.length() - 1);
        json.append("\n]\n}");
        return json.toString();
    }
}
