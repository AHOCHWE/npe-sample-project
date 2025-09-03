# npe-sample-project

## Description

This project demonstrates a NullPointerException (NPE) issue encountered after upgrading IBM ODM from 8.10.4 to 9.5.0, as reported in ticket [TS020171098](https://www.ibm.com/mysupport/s/case/500gJ000003vhmVQAQ/unexpected-nullpointerexceptions-occurring-in-business-rules-after-ibm-odm-upgrade-8104-950).

> It serves as a minimal, reproducible example for debugging and understanding the problem.

## Project Structure

- **XOM:** Example Java XOM (Execution Object Model).
- **BOM:** Business Object Model, referenced by the XOM.
- **Ruleapp:** Contains the rule artifacts.

  - **Decision Tables:**
    - `Ruleapp\rules\00 Test\02 FIX NPE - Decision Table.dta`: Workaround implemented in 9.5.0 to resolve the NPE.
    - `Ruleapp\rules\00 Test\03 NPE - Decision Table.dta`: Original code from 8.10.4 exhibiting the NPE.
  - **Test Files:**
    - `Ruleapp\testfiles\request_NPE_DecisionTable.json`: JSON request reproducing the NPE.
    - `Ruleapp\testfiles\request_success.json`: Working JSON request for comparison.

## Running Tests in Rule Designer

To reproduce the issue:

1. In Rule Designer, configure the Decision Operation.
2. In the `Parameters & Arguments` tab, modify the `function body` of the `request` parameter:

    ```java
    System.out.println("Choose the Testfile for Request");
    String jsonPathApplReq = com.mb.util.JsonParser.chooseTestFile();
    com.mb.Request result = (com.mb.Request) com.mb.util.JsonParser.parseInRequest(jsonPathApplReq);
    return result;
    ```

3. Use `request_NPE_DecisionTable.json` to trigger the NPE and `request_success.json` for a successful execution.
