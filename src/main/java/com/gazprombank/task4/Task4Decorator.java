package com.gazprombank.task4;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/*
 * Этот класс модифицирует currencyAddList, по идее лучше бы вычисления перенести в класс AddValute,
 * это бы соответствовало принципам ООП, но не все же должно быть идельно :-)
 */

public class Task4Decorator {
    private List<AddValute> currencyAddList;
    private Decorator decorator;
    public Task4Decorator(Decorator decorator, List<AddValute> currencyAddList) {
        this.decorator = decorator;
        this.currencyAddList = currencyAddList;
    }
    private List<AddValute> findAdditionalCurrencys(){
        DecimalFormat df = new DecimalFormat("#0.0000", new DecimalFormatSymbols(Locale.US));
        for (Valute val : decorator.getValute()) {
            for (AddValute currencyAdd: currencyAddList){
                if (currencyAdd.getUprValute().equals(val.getCharCode())){
                    currencyAdd.setUprValuteValue(val.getValue());
                    currencyAdd.setUprNominal(val.getNominal());
                }
                if (currencyAdd.getLwrValute().equals(val.getCharCode())){
                    currencyAdd.setLwrValuteValue(val.getValue());
                    currencyAdd.setLwrNominal(val.getNominal());
                }
            }
        }
        try {
            for (AddValute currencyAdd : currencyAddList) {
                Double uprVal = df.parse(currencyAdd.getUprValuteValue()).doubleValue()/(double)currencyAdd.getUprNominal();        //за арифметические ошибки в дабле я не несу ответственности
                Double lwrVal = df.parse(currencyAdd.getLwrValuteValue()).doubleValue()/(double)currencyAdd.getLwrNominal();        //за арифметические ошибки в дабле я не несу ответственности
                currencyAdd.setCurrency(df.format(uprVal/lwrVal));                                                                  //за арифметические ошибки в дабле я не несу ответственности
            }
        } catch (ParseException ex){
            ex.printStackTrace();
            System.out.println("Can't read valutes char code");
        }
        return currencyAddList;
    }
    public String prepareFinalLine(){
        String dec = decorator.decorate();
        List<AddValute> modCurrencyAddList = findAdditionalCurrencys();
        String finalString;
        StringBuilder uprLine = new StringBuilder();
        StringBuilder line = new StringBuilder();
        uprLine.append("  ");
        line.append("  ");
        for(AddValute addValute: modCurrencyAddList){
                uprLine.append(addValute.getVsValute()).append("  |  ");
                line.append(addValute.getCurrency()).append("  |  ");
        }
        if(decorator.getDateCount() == 0){
            List<String> linesList = Arrays.asList(dec.split("\\s*\r\n\\s*"));
            linesList.set(0,linesList.get(0)+uprLine.toString());
            linesList.set(1,linesList.get(1)+line.toString());
            finalString = linesList.get(0)+"\r\n"+linesList.get(1);
        } else {
            finalString = dec + line.toString();
        }
        return finalString;
    }
}