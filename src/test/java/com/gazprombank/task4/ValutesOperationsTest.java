package com.gazprombank.task4;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class ValutesOperationsTest implements SysoutCaptureAndAssertionAbility{
    
        //region given
    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
        //endregion
    @Test
    public void shouldDownloadXMLpageAndParse() throws IOException {
        
        ValutesOperations valutesOperations = new ValutesOperations("http://www.cbr.ru/scripts/XML_daily.asp?date_req=01/01/2018");
        Document doc = valutesOperations.getDocFromUrl();
        doc.getDocumentElement().normalize();
        List<Valute> valute = valutesOperations.getValutesAsList(doc);
        
        //region when
        for(Valute val: valute){
            System.out.println(val.getValue());
        }
        //endregion

        //region then
        assertSysoutContains("33,8228");
        assertSysoutContains("11,9009");
        assertSysoutContains("29,1013");
        assertSysoutContains("35,2252");
        assertSysoutContains("17,3887");
        assertSysoutContains("22,2052");
        assertSysoutContains("54,0119");
        assertSysoutContains("73,7056");
        assertSysoutContains("92,5379");
        assertSysoutContains("57,6002");
        assertSysoutContains("68,8668");
        assertSysoutContains("90,1376");
        assertSysoutContains("17,3184");
        assertSysoutContains("45,9258");
        assertSysoutContains("83,4786");
        assertSysoutContains("88,4497");
        assertSysoutContains("33,6548");
        assertSysoutContains("16,4807");
        assertSysoutContains("70,0664");
        assertSysoutContains("14,7822");
        assertSysoutContains("81,8326");
        assertSysoutContains("43,0881");
        assertSysoutContains("65,2634");
        assertSysoutContains("70,9358");
        assertSysoutContains("20,4955");
        assertSysoutContains("77,6739");
        assertSysoutContains("26,9305");
        assertSysoutContains("69,9915");
        assertSysoutContains("58,9743");
        assertSysoutContains("46,8538");
        assertSysoutContains("51,1479");
        //endregion
    }
}
