package com.othelle.cig.email

import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import javax.mail.internet.MimeUtility

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
        def EMAIL_PATTERN = "[a-zA-Z0-9+_.-]+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}"
        def p = Pattern.compile(EMAIL_PATTERN)
        StringBuilder st = new StringBuilder()
        try {
            def m = p.matcher(s.toString())
            if (m.find()) {
                st.append(m.group())
            }
        }
        catch (PatternSyntaxException ex) {
            println("Pattern Syntax Exception ", ex)
        }

        return st.toString()
    }
    static String bodyEval(def s) {
        def body_PATTERN = "/<body([^>]*)>(.*?)<\\/body\\s*>/si"
        def p = Pattern.compile(body_PATTERN)
        StringBuilder st = new StringBuilder()
        try {
            def m = p.matcher(s.toString())
            if (m.find()) {
                st.append(m.group())
            }
        }
        catch (PatternSyntaxException ex) {
            println("Pattern Syntax Exception ", ex)
        }

        return st.toString().replaceAll("<body([^>]*)>","").replaceAll("<\\/body\\s*>","")
    }
    static def getFileContents(File file) {
        String fileContents
        List<String> contents = file.getText("WINDOWS-1251").readLines()
        fileContents = contents*.encodeAsHTML().join("<br/>")
        return fileContents
    }

    static Map<String, String> fileLinksEval(def s, def PATTERN, def PATTERN1, def PATTERN2, def STARTPATTERN, def MIDLPATTERN, def ENDPATTERN) {

        def p = Pattern.compile(PATTERN)
        def map1 = [:]
        try {
            def m = p.matcher(s.toString())
            while (m.find()) {
                def p1 = Pattern.compile(PATTERN1)
                StringBuilder st1 = new StringBuilder()
                try {
                    def m1 = p1.matcher(m.group().toString())
                    if (m1.find()) {
                        st1.append(m1.group().replaceAll(STARTPATTERN, "").replaceAll(MIDLPATTERN, ""))
                    }
                }
                catch (PatternSyntaxException ex) {
                    println("Pattern Syntax Exception ", ex)
                }
                def p2 = Pattern.compile(PATTERN2)
                StringBuilder st2 = new StringBuilder()
                try {
                    def m2 = p2.matcher(m.group().toString())
                    if (m2.find()) {
                        st2.append(m2.group().replaceAll(ENDPATTERN, "").replaceAll(MIDLPATTERN, ""))
                    }
                }
                catch (PatternSyntaxException ex) {
                    println("Pattern Syntax Exception ", ex)
                }
                map1.put(st1, st2)
            }
        }
        catch (PatternSyntaxException ex) {
            println("Pattern Syntax Exception ", ex)
        }

        return map1
    }

}

