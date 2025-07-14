package com.louis;

public class ShellDemo {

    public static void main(String[] args) {
        try {
            String shPath = "/Users/louis/workspace/louis/louis-coub/demoProject/minaproject/startService.sh";
//            Process process = Runtime.getRuntime().exec(shPath);
//
//            process.waitFor();
//            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            StringBuffer sb = new StringBuffer();
//            String line;
//            while ((line = br.readLine()) != null) {
//                sb.append(line).append("\n");
//            }
//            String result = sb.toString();
//            System.out.println(result);
            ProcessBuilder pb = new ProcessBuilder(shPath);

            pb.inheritIO();

            pb.start();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
