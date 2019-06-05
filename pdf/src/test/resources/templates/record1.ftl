<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>Record</title>
  <style type="text/css">
    h2 {
      display: block;
      border-bottom: 1px solid black
    }

    table {
      border-collapse: collapse;
      width: 100%;
      margin: auto;
    }

    td {
      padding-top: 10;
      padding-bottom: 10;
      padding-right: 10;
      text-align: left;
    }

    .propertyLabel {
      color: gray;
    }

    .th {

      background-color: #CCC;
    }

    .colTable td {
      text-align: left;
    }

    .answer {
      margin-left: 5px;
      margin-top: 5px;
      margin-bottom: 5px;
    }

    .rHeader {
      padding: 5px;
    }
  </style>
</head>

<body>


  <div class="rHeader">
    <h2>${name}</h2>
    <h3>${businessId}</h3>

    <table class="colTable" cellspacing=0 cellpadding=0>

      <tr>
        <td width="30%"><b>Administrative Data</b></td>
        <td width="20%"><b>Status</b></td>
        <td width="20%"><b>Version</b></td>
        <td width="30%"><b>Evaluation Result</b></td>
      </tr>

      <tr>
        <td>
          <div class="propertyLabel">Created On: ${(createdAt?string("yyyy-MM-dd"))!} </div>
          <div class="propertyLabel">Created By: ${createdBy}</div>
          <div class="propertyLabel">Updated On: ${(updatedAt?string("yyyy-MM-dd"))!}</div>
          <div class="propertyLabel">Updated By: ${updatedBy}</div>
        </td>
        <td>${status}</td>
        <td>${version}</td>
        <td>${evaluationResult}</td>
      </tr>

    </table>
  </div>

  <div class="genInfo">
    <h2>General Information</h2>
    <table class="colTable" cellspacing=0 cellpadding=0>

      <tr>
        <td width="50%">
          <p class="propertyLabel">Name</p>
          <p>${name}</p>
          <p class="propertyLabel">Description</p>
          <p>${description}</p>
        </td>
        <td width="50%">
          <p class="propertyLabel">Type</p>
          <p>${recordTypeName}</p>
          <p class="propertyLabel">Template</p>
          <p>${template}</p>
        </td>

      </tr>



    </table>

  </div>
  <div>
    <h2>Owners</h2>
    <table border="1" cellspacing=0 cellpadding=0>

      <tr>
        <td class="th"><b>Type</b></td>
        <td class="th"><b>User Id</b></td>

      </tr>
      <#list owners as item>
        <tr>
          <td>${item.type}</td>
          <td>${item.id}</td>
        </tr>
      </#list>
    </table>
  </div>
  <div>
    <h2>Linked Records</h2>
    <table border="1" cellspacing=0 cellpadding=0>

      <tr>
        <td class="th"><b>Type</b></td>
        <td class="th"><b>Business Id</b></td>
        <td class="th"><b>Name</b></td>

      </tr>
      <#list linkedRecords as item>
        <tr>
          <td>${item.recordTypeName}</td>
          <td>${item.businessId}</td>
          <td>${item.name}</td>
        </tr>
      </#list>
    </table>
  </div>
  <div>
    <h2>Record Details</h2>
    <div>
      <h3>Answered</h3>
      <#list questionsAnswered as item>
        <p>${item.question}</p>
        <p class="answer">${item.answer}</p>
      </#list>
    </div>
    <div>
      <h3>Not Answered</h3>
      <#list questionsNotAnswered as item>
        <p>${item.question}</p>
        <br />
      </#list>
    </div>
  </div>
  <div>
    <h2>Evaluation Results</h2>

    <table border="1" cellspacing=0 cellpadding=0>

      <tr>
        <td class="th"><b>Name</b></td>
        <td class="th"><b>Status</b></td>
        <td class="th"><b>Description</b></td>
        <td class="th"><b>Creator</b></td>
      </tr>
      <#list evaluationResults as item>
        <tr>
          <td>${item.name}</td>
          <td>${item.summary}</td>
          <td>${item.description}</td>
          <td>${(item.date?string("yyyy-MM-dd"))!}</td>
        </tr>
      </#list>
    </table>
  </div>

</body>
</html>