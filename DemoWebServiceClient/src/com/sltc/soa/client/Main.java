package com.sltc.soa.client;

import com.sltc.soa.client.stub.DemoWS;
import com.sltc.soa.client.stub.DemoWSService;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class Main
{

    public static void main( String[] args )
    {
//         URL url = new URL("http://demowebservice.com:8080/currencyservice?wsdl");
//        DemoWSService demoWSService = new DemoWSService(url);

        DemoWSService demoWSService = new DemoWSService();
        DemoWS demoWSPort = demoWSService.getDemoWSPort();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1 -> get Country Names");
            System.out.println("2 -> convert");
            System.out.println("3 -> exit");
            System.out.println();
            System.out.print("Please enter your choose: ");
            System.out.println();
            int type = sc.nextInt();
            if(type == 1 || type == 2 || type == 3) {

                if(type == 1) {
                    List<String> result = demoWSPort.getCountryNames();
                    System.out.println("-------------Country list------------");
                    result.forEach(country -> System.out.println(country));
                }

                if (type == 2) {

                    sc.nextLine();
                    System.out.println("Enter Source Currency Type");
                    String sourcu = sc.nextLine();
                    System.out.println("Enter target Currency Type");
                    String target = sc.nextLine();
                    System.out.println("enter amount");
                    double amount = sc.nextDouble();
                    double result = demoWSPort.convert(amount, sourcu, target);
                    System.out.println();
                    System.out.println(amount + " of " + sourcu + " = " + result + " of " + target);


                }
                if(type == 3) {
                    break;
                }
            }
        }

    }


}
