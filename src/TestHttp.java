
import presentacion.utiles.OlmException;
import presentacion.utiles.OlmHttp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author olmaton
 */
public class TestHttp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String payload = "";
            OlmHttp http = new OlmHttp("http://localhost:17112","codigo");
            String resul = http.post("/auth/login","", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiZWRjNGNjMTYyY2Y1NWU0OGRlZDMyM2UxN2EyNGNmMGRlNzVmOWQ2ZmQ3NmY3YTU3YmNmNmNmZThkNDNhMzJiZjQ2MWIyY2E0NmJmODExNTdiZDM1N2ZiZDVlZWE4ZmNkNTExMGFiZDc5ZjllNDM2NGNlYjg0NDliMDI4MjZkMWE4ZjBmNzg4YWY1MDYzZTg5Y2YxNjFmOWIwZmZjN2MyNDY1OWE2NDU2MTNlOTI2NTVkNTI3ODY3ZDFhYjIyYzJiYWYiLCJpYXQiOjE2MTk1ODQxNjQsImV4cCI6MTYxOTU4Nzc2NH0.9WxGkr_c9WCimUPwaN9roxIFRF-L7y9_RElsGhRrApo");
            System.out.println(resul);
                    
            
        } catch (OlmException e) {
            System.out.println(e);
                    
        }
    }
    
}
