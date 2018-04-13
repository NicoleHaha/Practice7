package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.fest.assertions.api.Assertions.assertThat;

public class LibraryTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() throws Exception {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);
    }

    @Test
    public void addStudentTest() {
        Library underTest = new Library();
        String studenta = "lulu，1，语文：90，数学：90，英语：90，编程：90";
        List<String> astudent = new ArrayList<String>();
        astudent.add("lulu");
        astudent.add("语文：90");
        astudent.add("数学：90");
        astudent.add("英语：90");
        astudent.add("编程：90");
        Map<Integer, List<String>> studentinfo = new HashMap<>();
        studentinfo.put(1, astudent);
        underTest.add(studenta);
        assertThat(underTest.StudentInfo).isEqualTo(studentinfo);
        assertThat(systemOut()).isEqualTo("学生成绩被添加");

    }

    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void addStudentTestFailure() {
        Library underTest = new Library();
        String studenta = "lulu，语文：90，数学：90，英语：90，编程：90";
        underTest.add(studenta);
        assertThat(systemOut()).isEqualTo("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
    }

    @Test
    public void reportTest() {
        Library underTest = new Library();
        String studenta = "lulu，1，语文：90，数学：90，英语：90，编程：90";
        String studentb = "haha，2，语文：80，数学：80，英语：80，编程：80";
        underTest.add(studenta);
        underTest.add(studentb);
        String studentnum = "1，2";
        underTest.report(studentnum);
        assertThat(systemOut()).isEqualTo("学生成绩被添加学生成绩被添加成绩单\n" +

                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "====================================\n" +
                "lulu|90|90|90|90|90.0|360\n" +
                "====================================\n" +
                "haha|80|80|80|80|80.0|320\n" +
                "全班总平均分：340.0\n" +
                "全班总分中位数：340.0\n");
    }
}
