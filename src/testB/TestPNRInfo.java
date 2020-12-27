package testB;

import testA.TestBase;
import testA.TestRunError;

import java.util.Hashtable;

public class TestPNRInfo {

    public static void main(String[] args) throws TestRunError, InterruptedException {
//
//        String pnr = "KIJDFF";
//        String lastName = "Kiran";
//
//        PNRInfo pnrInfo = new PNRInfo(pnr,lastName);
//
//        TestBase.invokeApp("https://www.etihad.com/en-in/manage/check-in");
//        TestBase testBase = TestBase.getInstance();
//        testBase.setValue("#checkinBookingReference",pnrInfo.pnr);
//        testBase.setValue("#checkinLastName",pnrInfo.lastName);


        Hashtable hashtable = new Hashtable();
        hashtable.put("pnr","KIHSHHS");
        hashtable.put("lastName","Kiran");
        TestBase.invokeApp("https://www.etihad.com/en-in/manage/check-in");
        TestBase testBase = TestBase.getInstance();
        testBase.setValue("#checkinBookingReference",hashtable.get("pnr").toString());
        testBase.setValue("#checkinLastName",hashtable.get("lastName").toString());

    }
}
