package com.gazprombank.task4;

import java.time.LocalDate;
import java.util.List;

public class Decorator {
    private List<Valute> valute;
    private List<String> currencyArr;
    private LocalDate date;
    private int dateCount;

    public int getDateCount() {
        return dateCount;
    }
    public List<Valute> getValute() {
        return valute;
    }
    public List<String> getCurrencyArr() {
        return currencyArr;
    }
    public Decorator(List<Valute> valute, List<String> currencyArr, LocalDate date, int dateCount) {
        this.valute = valute;
        this.currencyArr = currencyArr;
        this.date = date;
        this.dateCount = dateCount;
    }
    public String decorate(){
        StringBuilder uprLine = new StringBuilder();
        StringBuilder line = new StringBuilder();
        uprLine.append("Date       |   ");
        line.append(date).append(" | ");
        for (Valute val : valute) {
            for (String currency : currencyArr) {
                if (currency.equals(val.getCharCode())) {
                    line.append(val.getValue()).append(" | ");
                }
            }
        }
        if (dateCount == 0) {
            for (Valute val : valute) {
                for (String currency : currencyArr) {
                    if (currency.equals(val.getCharCode())) uprLine.append(val.getCharCode()).append("   |   ");
                }
            }
            line = uprLine.append("\r\n").append(line);
        }
        return line.toString();
    }
}