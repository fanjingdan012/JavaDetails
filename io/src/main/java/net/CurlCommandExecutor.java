package net;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static file.SoundPlayer.PlaySoundTask.playSound;

public class CurlCommandExecutor {

  public static void main(String[] args) throws Exception {
    PrintStream fileOut = new PrintStream(new FileOutputStream("output3.txt"));

    // Redirect System.out to the file
    System.setOut(fileOut);
    String requestUrl = "https://performancemanager.successfactors.eu/odata/v2/EmpJob?paging=snapshot&fromDate=2013-01-01&$select=seqNumber,startDate,userId,businessUnit,businessUnitNav%2FexternalCode,businessUnitNav%2FstartDate,company,companyNav%2FexternalCode,companyNav%2FstartDate,corporation,corporationNav%2Fid,costCenter,costCenterNav%2FexternalCode,costCenterNav%2FstartDate,countryOfCompany,countryOfCompanyNav%2Fid,createdBy,createdDateTime,createdOn,customDouble1,customDouble2,customDouble3,customDouble4,customDouble5,customString10,customString11,customString12,customString13,customString14,customString15,customString15Nav%2Fid,customString16,customString16Nav%2Fid,customString17,customString17Nav%2Fid,customString18,customString18Nav%2Fid,customString19,customString19Nav%2Fid,customString2,customString2Nav%2FexternalCode,customString2Nav%2FstartDate,customString20,customString20Nav%2Fid,customString21,customString21Nav%2Fid,customString22,customString22Nav%2Fid,customString23,customString23Nav%2FexternalCode,customString23Nav%2FstartDate,customString24,customString24Nav%2FexternalCode,customString24Nav%2FstartDate,customString25,customString25Nav%2Fid,customString27,customString27Nav%2Fid,customString29,customString29Nav%2Fid,customString3,customString3Nav%2Fid,customString30,customString30Nav%2Fid,customString31,customString31Nav%2Fid,customString32,customString34,customString34Nav%2Fid,customString35,customString35Nav%2Fid,customString36,customString36Nav%2Fid,customString37,customString38,customString38Nav%2Fid,customString39,customString39Nav%2Fid,customString4,customString4Nav%2Fid,customString40,customString40Nav%2Fid,customString42,customString42Nav%2Fid,customString43,customString43Nav%2FexternalCode,customString43Nav%2FstartDate,customString5,customString5Nav%2Fid,customString50,customString50Nav%2Fid,customString52,customString52Nav%2Fid,customString53,customString54,customString6,customString6Nav%2Fid,customString60,customString7,customString75,customString75Nav%2Fid,customString8,customString9,department,departmentNav%2FexternalCode,departmentNav%2FstartDate,division,divisionNav%2FexternalCode,divisionNav%2FstartDate,effectiveLatestChange,emplStatus,emplStatusNav%2Fid,employeeClass,employeeClassNav%2Fid,employeeType,employeeTypeNav%2Fid,employmentType,employmentTypeNav%2Fid,employmentNav%2FpersonIdExternal,employmentNav%2FuserId,endDate,event,eventNav%2Fid,eventReason,eventReasonNav%2FexternalCode,eventReasonNav%2FstartDate,flsaStatus,flsaStatusNav%2Fid,fte,holidayCalendarCode,holidayCalendarCodeNav%2FexternalCode,isFulltimeEmployee,jobCode,jobCodeNav%2FexternalCode,jobCodeNav%2FstartDate,lastModifiedBy,lastModifiedDateTime,lastModifiedOn,localJobTitle,location,locationNav%2FexternalCode,locationNav%2FstartDate,managerEmploymentNav%2FpersonIdExternal,managerEmploymentNav%2FuserId,managerId,managerUserNav%2FuserId,noticePeriod,noticePeriodNav%2Fid,payGrade,payGradeNav%2FexternalCode,payGradeNav%2FstartDate,payScaleArea,payScaleAreaNav%2Fcode,payScaleType,payScaleTypeNav%2Fcode,position,positionNav%2Fcode,positionNav%2FeffectiveStartDate,probationPeriodEndDate,regularTemp,regularTempNav%2Fid,standardHours,timeTypeProfileCode,timeTypeProfileCodeNav%2FexternalCode,timeTypeProfileCodeNav%2FmdfSystemEffectiveStartDate,timezone,userNav%2FuserId,workingDaysPerWeek,workscheduleCode,workscheduleCodeNav%2FexternalCode&toDate=9999-12-31&$expand=businessUnitNav,companyNav,corporationNav,costCenterNav,countryOfCompanyNav,customString15Nav,customString16Nav,customString17Nav,customString18Nav,customString19Nav,customString2Nav,customString20Nav,customString21Nav,customString22Nav,customString23Nav,customString24Nav,customString25Nav,customString27Nav,customString29Nav,customString3Nav,customString30Nav,customString31Nav,customString34Nav,customString35Nav,customString36Nav,customString38Nav,customString39Nav,customString4Nav,customString40Nav,customString42Nav,customString43Nav,customString5Nav,customString50Nav,customString52Nav,customString6Nav,customString75Nav,departmentNav,divisionNav,emplStatusNav,employeeClassNav,employeeTypeNav,employmentTypeNav,employmentNav,eventNav,eventReasonNav,flsaStatusNav,holidayCalendarCodeNav,jobCodeNav,locationNav,managerEmploymentNav,managerUserNav,noticePeriodNav,payGradeNav,payScaleAreaNav,payScaleTypeNav,positionNav,regularTempNav,timeTypeProfileCodeNav,userNav,workscheduleCodeNav&$orderby=seqNumber,startDate,userId&$format=json";
    //    BufferedWriter txtWriter = new BufferedWriter(new FileWriter("request_response.txt", true));
    //    BufferedWriter csvWriter = new BufferedWriter(new FileWriter("data.csv"));
    //    csvWriter.write("seqNumber,startDate,userId,endDate,lastModifiedDate\n");
    String sessionInfo = """
                 -H 'accept: application/json' \\
                  -H 'accept-language: en-GB' \\
                  -H 'cache-control: no-cache' \\
                  -H 'content-type: application/json' \\
                  -b 'route=ff1c48c16ce4493e94e04e906f624d585339468d; BIGipServerhcm57.sapsf.com=92813578.20480.0000; bizxThemeId=3yv7wdmjj2*type1ColorVision.diagram; fioriFDStylesVersion=; JSESSIONID=65749B660D6B4D4FB04DC7DC6F531DCA.pc57bcf38; dtCookie=v_4_srv_7_sn_398403A0168381C15955545F5F2B1F5E_perc_100000_ol_0_mul_1_app-3Ad45ce43a97ce7239_0; bizxCompanyId=SwissRe; zsessionid=2e5de406-5694-4b07-9e61-05300b6edd29' \\
                  -H 'optr_cxt: 0100050001b8b42f2b-3495-4524-91bc-cc2e3463350829820909-1501-babe-face-0000000000033629881c-c0b9-42f0-bf58-d9d8a900dc0dHTTP    ;' \\
                  -H 'pragma: no-cache' \\
                  -H 'priority: u=1, i' \\
                  -H 'referer: https://performancemanager.successfactors.eu/sf/home?_s.crb=9kArbwWm8dVOljqdyKRxLlDl4arpTBNzAgB0iMIRC58%253d' \\
                  -H 'sec-ch-ua: "Not(A:Brand";v="99", "Google Chrome";v="133", "Chromium";v="133"' \\
                  -H 'sec-ch-ua-mobile: ?0' \\
                  -H 'sec-ch-ua-platform: "macOS"' \\
                  -H 'sec-fetch-dest: empty' \\
                  -H 'sec-fetch-mode: cors' \\
                  -H 'sec-fetch-site: same-origin' \\
                  -H 'successfactors-accept-language: en_GB' \\
                  -H 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/133.0.0.0 Safari/537.36' \\
                  -H 'x-ajax-token: 9kArbwWm8dVOljqdyKRxLlDl4arpTBNzAgB0iMIRC58%3d' \\
                  -H 'x-csrf-token: 9kArbwWm8dVOljqdyKRxLlDl4arpTBNzAgB0iMIRC58%3d' \\
                  -H 'x-event-id: EVENT-PLT-HOMEPAGE-ob46abe27-20250307072123-383521-1' \\
                  -H 'x-sap-page-info: companyId=SwissRe&moduleId=HOME&pageId=HOME_TAB&pageQualifier=HOME_V4' \\
                  -H 'x-subaction: 0'
        """;
    while (requestUrl != null) {
      String curlCommand = "curl '" + requestUrl + "' \\\n" + sessionInfo;
      int attempts = 0;
      boolean success = false;
      while (attempts < 3 && !success) {
        try {
          requestUrl = executeCurlCommand(curlCommand, requestUrl, attempts);
          success = true;
          Thread.sleep(100);
        } catch (Exception e) {
          attempts++;
          if (attempts == 3) {
            String soundFilePath = "/Users/i312177/githubWdf/cmd-shortcut/fail.wav"; // Replace with the path to your sound file
            playSound(soundFilePath);
            throw e;
          }
        }
      }
    }
    String soundFilePath = "/Users/i312177/githubWdf/cmd-shortcut/success.wav"; // Replace with the path to your sound file
    playSound(soundFilePath);
  }

  public static String executeCurlCommand(String curlCommand, String requestUrl, int attempts) throws Exception {

    ProcessBuilder processBuilder = new ProcessBuilder();
    processBuilder.command("bash", "-c", curlCommand);
    Process process = processBuilder.start();
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    StringBuilder response = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      response.append(line);
    }
    reader.close();
    System.out.println("requestUrl:" + attempts + requestUrl);
    // Handle the response
    return handleResponse(response.toString());
  }

  public static String handleResponse(String response) {

    Object nextLink = null;
    StringBuilder result = new StringBuilder();

    //      System.out.println(response);
    JSONObject jsonObject1 = new JSONObject(response);
    JSONObject d = (JSONObject) jsonObject1.get("d");

    // Parse the JSON response
    JSONArray jsonArray = (JSONArray) d.get("results");
    try {
      nextLink = d.getString("__next");
    } catch (Exception e) {
      System.out.println("last page:" + jsonArray.length());
      nextLink = null;
    }
    List<String> resultSet = new ArrayList<>();
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonObject = jsonArray.getJSONObject(i);
      String seqNumber = jsonObject.getString("seqNumber");
      String startDate = jsonObject.getString("startDate");
      String userId = jsonObject.getString("userId");
      String endDate = jsonObject.getString("endDate");
      String lastModifiedOn = jsonObject.getString("lastModifiedOn");
      resultSet.add(String.format("%s,%s,%s,%s,%s\n", seqNumber, startDate, userId, endDate, lastModifiedOn));

    }
    assert resultSet.size() == jsonArray.length();
    resultSet.forEach(System.out::print);

    return Optional.ofNullable(nextLink).map(Object::toString).orElse(null);
  }
}