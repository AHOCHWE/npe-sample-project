## Configure Decision Operation to Run in RD

```java
System.out.println("Choose the Testfile for Request");
String jsonPathApplReq = com.mb.util.JsonParser.chooseTestFile(); 
com.mb.Request result = (com.mb.Request) com.mb.util.JsonParser.parseInRequest(jsonPathApplReq); 
return result;
```