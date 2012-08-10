package com.othelle.cig.email

import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 01.08.12
 * Time: 9:35
 * To change this template use File | Settings | File Templates.
 */
class Utilities {
    static String[] emailParse(String s) {
        String[] tokens = s.split(", ")
        return tokens
    }

    static String emailEval(def s) {
        def EMAIL_PATTERN = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}"
        def p = Pattern.compile(EMAIL_PATTERN)
        StringBuilder st = new StringBuilder()
        def i = 0
        s.each {current ->
            try {
                def m = p.matcher(current.toString())
                while (m.find()) {
                    if (i == 0) {
                        st.append(m.group())
                    }

                    else {
                        st.append(", " + m.group())
                    }
                    i = 1
                }
            }
            catch (PatternSyntaxException ex) {
                println("Pattern Syntax Exception ", ex)
            }
        }
        return st.toString()
    }

    static def getFileContents(File file) {
        String fileContents
        List<String> contents = file.getText("WINDOWS-1251").readLines()
        fileContents = contents*.encodeAsHTML().join("<br/>")
        return fileContents
    }
}
