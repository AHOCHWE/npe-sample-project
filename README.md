# npe-sample-project

> [!TIP]
> The rule designer was installed by following the official IBM ODM Guides:
>
> - <https://www.ibm.com/docs/en/odm/9.5.0?topic=950-installing-rule-designer>
> - <https://www.ibm.com/docs/en/odm/9.5.0?topic=designer-installing-rule-offline>

## Description

This project demonstrates a NullPointerException (NPE) issue encountered after upgrading IBM ODM from 8.10.4 to 9.5.0, as reported in ticket [TS020171098](https://www.ibm.com/mysupport/s/case/500gJ000003vhmVQAQ/unexpected-nullpointerexceptions-occurring-in-business-rules-after-ibm-odm-upgrade-8104-950).

> It serves as a minimal, reproducible example for debugging and understanding the problem.

## Project Structure

- **XOM:** Example Java XOM (Execution Object Model).
- **BOM:** Business Object Model, referenced by the XOM.
- **Ruleapp:** Contains the rule artifacts.

  - **Decision Tables:**
    - `Ruleapp\rules\00 Test\02 NPE - Decision Table.dta`: Original code from 8.10.4 which was working without problems, but stopped working when we switched to 9.5.0.
    - `Ruleapp\rules\00 Test\03 FIX NPE - Decision Table.dta`: Workaround implemented in 9.5.0 to resolve the NPE.
  - **Test Files:**
    - `Ruleapp\testfiles\request_NPE_DecisionTable.json`: JSON request reproducing the NPE.
    - `Ruleapp\testfiles\request_success.json`: Working JSON request for comparison.

## Running Tests in Rule Designer

To reproduce the issue:

1. Import the project into the rule design using the `Import...` wizard `Existing Projects into Workspace`
2. In Rule Designer, create a Decision Operation in the `Run Configurations`.
3. In the `Parameters & Arguments` tab, modify the `function body` of the `request` parameter:

    ```java
    System.out.println("Choose the Testfile for Request");
    String jsonPathApplReq = com.mb.util.JsonParser.chooseTestFile();
    com.mb.Request result = (com.mb.Request) com.mb.util.JsonParser.parseInRequest(jsonPathApplReq);
    return result;
    ```

4. Use `request_NPE_DecisionTable.json` to trigger the NPE and `request_success.json` for a successful execution.

## Versions

- Eclipse: <https://www.eclipse.org/downloads/packages/release/2024-12/r>
- Ruledesigner: <https://github.com/DecisionsDev/ruledesigner/releases/tag/v9.5.0.0>
