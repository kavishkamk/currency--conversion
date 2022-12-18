package com.sltc.soa;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
import java.util.HashMap;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class DemoWS
{

    private static HashMap<String, Float> userAccounts = new HashMap<String, Float>();
//    private static HashMap<String, String> countryCodes = new HashMap<String, String>();

    @WebMethod
    public String[] getCountryCodes(){
        GetCurrencyData obj = new GetCurrencyData();
        String[] keySet = obj.getCountryCodes().keySet().toArray(new String[0]);
        return keySet;
    }

    @WebMethod
    public String[] getCountryNames(){
        GetCurrencyData obj = new GetCurrencyData();
        String[] keySet = obj.getCountryCodes().values().toArray(new String[0]);
        return keySet;
    }

    @WebMethod
    public double convert(double amountInSourceCurrency, String sourceCurrency, String targetCurrency){
        GetCurrencyData obj = new GetCurrencyData();
        Rates rateObj = new Rates();

        String sourceCode = obj.getKey(sourceCurrency);
        String targetCode = obj.getKey(targetCurrency);

        if(sourceCode == null || targetCode == null) {
            return -1.0;
        }

        double dolerValue = amountInSourceCurrency * rateObj.getRate(sourceCode);
        double outputValue = dolerValue * rateObj.getRate(targetCode);

        return outputValue;
    }


    public static void main(String[] args){
        Endpoint.publish("http://localhost:8888/DemoWebService", new DemoWS());
    }
}
