package com.gazprombank.task4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.gazprombank.task4.interfaces.Printer;
import org.w3c.dom.Document;

public class Main {
    private static final String FIRST_PART_URL = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
    
    public static void main(String[] args) {
        System.out.println("Please, input first date in format dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        String frstDate = sc.nextLine();
        System.out.println("Please, input second date in format dd/MM/yyyy");
        String scndDate = sc.nextLine();
        System.out.println("The list of avialable curency is: AUD, GBP, BYR, DKK, USD, EUR, ISK, KZT, CAD, NOK, XDR, SGD, TRL, UAH, SEK, CHF, JPY");
        System.out.println("Please, input required currencys in format 'USD, EUR,...'");
        String currencyStr = sc.nextLine();
        System.out.println("Please, input additional currencys in format 'USD/EUR, AUD/USD,...'");
        String currencyAddStr = sc.nextLine();
        System.out.println("Loading... (it may takes up to few minutes)");
        sc.close();
        
        List<String> currencyArr = Arrays.asList(currencyStr.split("\\s*,\\s*"));
        List<String> currencyAddArr = Arrays.asList(currencyAddStr.split("\\s*,\\s*"));
        
        List<AddValute> currencyAddList = new ArrayList<>();
        for (String curAddArr: currencyAddArr){
            currencyAddList.add(new AddValute(curAddArr));
        }
        List<Valute> valute;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateBefor = LocalDate.parse(frstDate, formatter);
        LocalDate dateAfter = LocalDate.parse(scndDate, formatter);
        long daysBetween = ChronoUnit.DAYS.between(dateBefor, dateAfter);
        dateBefor = dateBefor.minusDays(1);
        for (long i = 0; i <(daysBetween+1); i++){
            dateBefor = dateBefor.plusDays(1);
            String day = dateBefor.format(formatter);
            ValutesOperations valutesOperations = new ValutesOperations(FIRST_PART_URL + day);
            Document doc = valutesOperations.getDocFromUrl();
            doc.getDocumentElement().normalize();
            valute = valutesOperations.getValutesAsList(doc);
            Decorator decorator = new Decorator(valute, currencyArr, dateBefor, (int)i);
            Task4Decorator task4Decorator = new Task4Decorator(decorator, currencyAddList);
            Printer printer = new FilePrinter("valutes.txt", task4Decorator.prepareFinalLine());
            printer.print();
        }
    }
}
