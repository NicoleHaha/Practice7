package com.tw;

import java.util.*;


public class Library {
    public boolean someLibraryMethod() {
        return true;
    }

    static HashMap<Integer, List<String>> StudentInfo = new HashMap<Integer, List<String>>();
    static List<Integer> total = new ArrayList<Integer>();
    static Scanner scanner = new Scanner(System.in);
    private int choice;
    private String students;
    private String numofstu;

    public void start(int choice) {
        this.choice = choice;
    }
    public void setStudents(String students){
        this.students = students;
    }
    public void setNumofstu(String numofstu){
        this.numofstu = numofstu;
    }



    public void mianInterface(int choices) {
        while (true) {
            System.out.println("1.添加学生");
            System.out.println("2.生成成绩单");
            System.out.println("3.退出");
            System.out.println("请输入你的选择（1~3)：");
            if (choices == 3) break;
            switch (choices) {
                case 1:
                    add(students);
                    break;
                case 2:
                    report(numofstu);
                    break;
            }
        }
    }


    static void add(String student){
        List<String> astudent = new ArrayList<String>();
        if(!student.matches("\\S+，\\d+，\\S+：\\d+，\\S+：\\d+，\\S+：\\d+")){
            System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            return;
        }
        else{
            String[] splitinfo = student.split("，");
            int stuid = Integer.parseInt(splitinfo[1]);
            if (StudentInfo.containsKey(stuid)) {
                System.out.println("已添加过该学生");
            }
            boolean flag = true;
            for (int i = 2; i < 6; i++) {
                String[] temp = splitinfo[i].split("：");
                if (!(temp[0].equals("语文")) && !(temp[0].equals("数学")) && !(temp[0].equals("英语")) && !(temp[0].equals("编程"))) {
                    System.out.println("没有该学科，请重新输入！");
                    break;
                }
            }

            astudent.add(splitinfo[0]);
            astudent.add(splitinfo[2]);
            astudent.add(splitinfo[3]);
            astudent.add(splitinfo[4]);
            astudent.add(splitinfo[5]);

            int atotal = Integer.parseInt(splitinfo[2].substring(3)) + Integer.parseInt(splitinfo[3].substring(3))
                    + Integer.parseInt(splitinfo[4].substring(3)) + Integer.parseInt(splitinfo[5].substring(3));
            total.add(atotal);
            StudentInfo.put(stuid, astudent);

            System.out.print("学生成绩被添加");
        }
    }


    static void report(String studentsnum){
        if ((!studentsnum.matches("(\\d+，)+\\d+")) && (!studentsnum.matches("\\d+"))) {
            System.out.println("请按正确的格式输入（格式：学号, 学号, 学号...）：");
        }
        String[] stunums = studentsnum.split("，");
        System.out.print("成绩单\n");
        System.out.print("姓名|数学|语文|英语|编程|平均分|总分\n");
        for (String str : stunums) {
            int key = Integer.parseInt(str);
            if (StudentInfo.containsKey(key)) {
                List<String> ls = StudentInfo.get(key);
                String print = ls.get(0);
                String math = new String();
                String chinese = new String();
                String english = new String();
                String progranmming = new String();
                for (String sss : ls) {
                    if (sss == ls.get(0)) {
                        continue;
                    }
                    String[] temp = sss.split("：");
                    switch (temp[0]) {
                        case "数学":
                            math = temp[1];
                            break;
                        case "英语":
                            english = temp[1];
                            break;
                        case "语文":
                            chinese = temp[1];
                            break;
                        case "编程":
                            progranmming = temp[1];
                            break;
                        default:
                            break;
                    }
                }
                int total = Integer.parseInt(math) + Integer.parseInt(chinese) +
                        Integer.parseInt(english) + Integer.parseInt(progranmming);
                print = print + "|" + math + "|" + chinese + "|" + english + "|"
                        + progranmming + "|" + (double) total / 4+ "|" + total +"\n";

                System.out.print("====================================\n");
                System.out.print(print);

            }
        }
        System.out.print("全班总平均分：" + (double) getSumOfTotal(total) / total.size()+"\n");
        System.out.print("全班总分中位数：" + getOrderedMedian(total)+"\n");
    }
    /*    public static void main(String[] args) {
            int choice;
            while (true) {
                System.out.println("1.添加学生");
                System.out.println("2.生成成绩单");
                System.out.println("3.退出");
                System.out.println("请输入你的选择（1~3)：");
                choice = scanner.nextInt();
                if (choice == 3) break;
                switch (choice) {
                    case 1:
                        add();
                        break;
                    case 2:
                        report();
                        break;
                }
            }
        }*/
    /*static void add() {
        while (true) {
            List<String> astudent = new ArrayList<String>();
            String stuinfo = new String();
            while (true) {
                Scanner sc = new Scanner(System.in);
                System.out.print("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                stuinfo = sc.nextLine();
                break;
            }

            while (!stuinfo.matches("\\S+，\\d+，\\S+：\\d+，\\S+：\\d+，\\S+：\\d+")) {
                System.out.print("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
                stuinfo = scanner.nextLine();
            }

            String[] splitinfo = stuinfo.split("，");
            int stuid = Integer.parseInt(splitinfo[1]);
            if (StudentInfo.containsKey(stuid)) {
                System.out.println("已添加过该学生");
                break;
            }
            boolean flag = true;
            for (int i = 2; i < 6; i++) {
                String[] temp = splitinfo[i].split("：");
                if (!(temp[0].equals("语文")) && !(temp[0].equals("数学")) && !(temp[0].equals("英语")) && !(temp[0].equals("编程"))) {
                    flag = false;
                    System.out.println("没有该学科，请重新输入！");
                    break;
                }
            }
            if (!flag) {
                break;
            }

            astudent.add(splitinfo[0]);
            astudent.add(splitinfo[2]);
            astudent.add(splitinfo[3]);
            astudent.add(splitinfo[4]);
            astudent.add(splitinfo[5]);

            int atotal = Integer.parseInt(splitinfo[2].substring(3)) + Integer.parseInt(splitinfo[3].substring(3))
                    + Integer.parseInt(splitinfo[4].substring(3)) + Integer.parseInt(splitinfo[5].substring(3));
            total.add(atotal);
            StudentInfo.put(stuid, astudent);

            System.out.println("学生成绩被添加");
            break;
        }

    }

    static void report() {
        System.out.println("请输入学生的学号（格式：学号, 学号, 学号...），按回车提交：");
        Scanner sc = new Scanner(System.in);
        String studentsnum = sc.nextLine();
        if ((!studentsnum.matches("(\\d+，)+\\d+")) && (!studentsnum.matches("\\d+"))) {
            System.out.println("请按正确的格式输入（格式：学号, 学号, 学号...）：");
            studentsnum = sc.nextLine();
        }
        String[] stunums = studentsnum.split("，");
        System.out.println("姓名|数学|语文|英语|编程|平均分|总分");
        for (String str : stunums) {
            int key = Integer.parseInt(str);
            if (StudentInfo.containsKey(key)) {
                List<String> ls = StudentInfo.get(key);
                String print = ls.get(0);
                String math = new String();
                String chinese = new String();
                String english = new String();
                String progranmming = new String();
                for (String sss : ls) {
                    if (sss == ls.get(0)) {
                        continue;
                    }
                    String[] temp = sss.split("：");
                    switch (temp[0]) {
                        case "数学":
                            math = temp[1];
                            break;
                        case "英语":
                            english = temp[1];
                            break;
                        case "语文":
                            chinese = temp[1];
                            break;
                        case "编程":
                            progranmming = temp[1];
                            break;
                        default:
                            break;
                    }
                }
                int total = Integer.parseInt(math) + Integer.parseInt(chinese) +
                        Integer.parseInt(english) + Integer.parseInt(progranmming);
                print = print + "|" + math + "|" + chinese + "|" + english + "|"
                        + progranmming + "|" + total + "|" + (double) total / 4;

                System.out.println("====================================");
                System.out.println(print);

            }
        }
        System.out.println("全班总平均分：" + (double) getSumOfTotal(total) / total.size());
        System.out.println("全班总分中位数：" + getOrderedMedian(total));
    }
*/
    static public double getOrderedMedian(List<Integer> arrayList) {
        double temp;
        double result = 0;
        if (arrayList.size() % 2 == 0) {
            temp = arrayList.stream()
                    .sorted()
                    .skip(arrayList.size() / 2 - 1)
                    .limit(2).reduce(Integer::sum).get();
            result = (double) temp / 2;
        } else {
            result = arrayList.stream()
                    .sorted()
                    .skip(arrayList.size() / 2)
                    .limit(1)
                    .findFirst()
                    .get();
        }
        return result;
    }

    static public int getSumOfTotal(List<Integer> arrayList) {
        int result = arrayList.stream()
                .reduce(0, Integer::sum);
        return result;
    }
}
