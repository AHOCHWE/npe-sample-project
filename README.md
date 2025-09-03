# npe-sample-project

## Description

This project demonstrates a NullPointerException (NPE) issue encountered after upgrading IBM ODM from 8.10.4 to 9.5.0, as reported in ticket [TS020171098](https://www.ibm.com/mysupport/s/case/500gJ000003vhmVQAQ/unexpected-nullpointerexceptions-occurring-in-business-rules-after-ibm-odm-upgrade-8104-950).

The `Ruleapp` folder contains the following:

- **Decision Tables:**
    - `Ruleapp\rules\00 Test\02 FIX NPE - Decision Table.dta`:  This decision table represents the workaround implemented in version `9.5.0` to resolve the NPE and restore functionality.
    - `Ruleapp\rules\00 Test\03 NPE - Decision Table.dta`: This decision table contains the original code as it existed in version `8.10.4`, which was functioning correctly before the upgrade.

- **Test Files:**
    - `Ruleapp\testfiles\request_NPE_DecisionTable.json`:  This JSON file provides an example request that reproduces the NullPointerException.
    - `Ruleapp\testfiles\request_success.json`: This JSON file provides a working example request for reference and comparison.

## Configuration for Running Tests in Rule Designer

To execute the tests within the Rule Designer in Eclipse, configure the `Decision Operation` as follows:

1. Navigate to the `Parameters & Arguments` tab of the `Decision Operation`.
2. Modify the `function body` of the `request` parameter with the following Java code:

    ```java
    System.out.println("Choose the Testfile for Request");
    String jsonPathApplReq = com.mb.util.JsonParser.chooseTestFile();
    com.mb.Request result = (com.mb.Request) com.mb.util.JsonParser.parseInRequest(jsonPathApplReq);
    return result;
    ```

This code snippet allows you to select a test file for the request, enabling you to easily switch between the failing and working examples.
