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

    static def getFileName(String nameFile) {
        println("name file: " + nameFile)
        def PATTERN = "(=\\?)[a-zA-Z0-9+_.-]+(\\?Q\\?)"
        def p = Pattern.compile(PATTERN)
        String st1 = nameFile
        try {
            def m = p.matcher(nameFile)
            if (m.find()) {
                st1 = nameFile.replaceAll(PATTERN, "").replaceAll("\\?=","")
                println("st1:" + st1)
                println("m.group():" + m.group().toString())
                String st2 = m.group().toString().replaceAll("=\\?", "").replaceAll("\\?Q\\?", "")
                println("st2:" + st2)
                println("UTF-8:" + st1.getBytes("UTF-8"))
                println("ISO-8859-1:" + st1.getBytes("ISO-8859-1"))
                println("getBytes:" + st1.getBytes())
                //st1 = st1.getBytes(st2)
                println("st1:" + st1)

            }
        }
        catch (PatternSyntaxException ex) {
            println("Pattern Syntax Exception ", ex)
        }

        println("getFileName:" + st1)

        return st1

    }
}
