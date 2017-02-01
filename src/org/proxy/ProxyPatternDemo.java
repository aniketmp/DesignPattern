package org.proxy;

/*
Simply, proxy means an object representing another object.
Proxy pattern is used when we need to create a wrapper to cover the main object’s complexity from the client.

What are the usage scenarios?
1)Virtual Proxy – Imagine a situation where there is multiple database call to extract huge size image. Since this is an expensive operation we can possibly use the proxy pattern which would create multiple proxies and point to the huge size memory consuming object for further processing. The real object gets created only when a client first requests/accesses the object and after that we can just refer to the proxy to reuse the object. This avoids duplication of the object and hence saving memory.
2)Remote Proxy – A remote proxy can be thought about the stub in the RPC call. The remote proxy provides a local representation of the object which is present in the different address location. Another example can be providing interface for remote resources such as web service or REST resources.
3)Protective Proxy – The protective proxy acts as an authorisation layer to verify if the actual user has access to appropriate content. An example can be thought about the proxy server which provides restrictive internet access in office. Only the websites and contents which are valid will be allowed and the remaining ones will be blocked.
4)Smart Proxy – A smart proxy provides additional layer of security by interposing specific actions when the object is accessed. An example can be to check if the real object is locked before it is accessed to ensure that no other object can change it.

*/
interface OfficeInternetAccess 
{  
    public void grantInternetAccess();  
}  
class RealInternetAccess implements OfficeInternetAccess 
{  
    private String employeeName;  
    public RealInternetAccess(String empName) 
    {  
        this.employeeName = empName;  
    }
    @Override  
    public void grantInternetAccess() {  
        System.out.println("Internet Access granted for employee: "+ employeeName);  
    }  
}
class ProxyInternetAccess implements OfficeInternetAccess 
{  
    private String employeeName;  
    private RealInternetAccess  realaccess;  
        public ProxyInternetAccess(String employeeName) 
        {  
        	this.employeeName = employeeName;  
        }  
	 @Override  
	 public void grantInternetAccess()   
	 {  
	     if (getRole(employeeName) > 4)   
	     {  
	         realaccess = new RealInternetAccess(employeeName);  
	         realaccess.grantInternetAccess();  
	     }   
	     else   
	     {  
	         System.out.println("No Internet access granted. Your job level is below 5");  
	     }  
	 }  
	 public int getRole(String emplName) {  
	     // Check role from the database based on Name and designation  
	     // return job level or job designation.  
	     return 9;  
	 }  
}  
public class ProxyPatternDemo 
{
	
	
	public static void main(String args[]) 
	{
		OfficeInternetAccess access = new ProxyInternetAccess("Aniket Paranjpe");  
        access.grantInternetAccess();  
	}

}
