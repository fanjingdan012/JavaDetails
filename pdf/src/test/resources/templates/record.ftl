<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <meta http-equiv="Content-Style-Type" content="text/css" />
  <title></title>
  <style type="text/css">
    body {
      font-family: pingfang sc light;
    }

    .center {
      text-align: center;
      width: 100%;
    }

    .rHeader {
      text-align: center;
      padding: 5px;
    }

    nav {
      line-height: 30px;
      background-color: #eeeeee;
      height: 300px;
      width: 100px;
      float: left;
      padding: 5px;
    }




    footer {
      background-color: black;
      color: white;
      clear: both;
      text-align: center;
      padding: 5px;
    }

    h2 {
      display: block;
      border-bottom: 1px solid black
    }

    table {

      width: 100%;
      margin: auto;

    }



    td {
      padding-top: 10;
      padding-bottom: 10;
      text-align: center;
    }

    .propertyLabel {
      color: gray;
    }
    .th{
      color:white;
      background-color:#1E90FF;
    }
  </style>
</head>

<body>

  <div class="page">
    <div class="rHeader">
      <h1>${name}</h1>
      <h3>${category}</h3>
      <p>Status:${status}</p>
      <div><a href="${iTextUrl}">iText官网</a></div>
    </div>

    <div class="genInfo">
      <h2>General Information</h2>
      <div>
        <p class="propertyLabel">Category</p>
        <p>${category}</p>
      </div>
      <div>
        <p class="propertyLabel">Validity Period</p>
        <p>${validPeriod}</p>
      </div>

      <div>
        <p class="propertyLabel">Description</p>
        <p>${description}</p>
      </div>

    </div>

    <div>
      <h2>Requirements</h2>
      <table border="1" cellspacing=0 cellpadding=0>

        <tr>
          <td class="th"><b>Name</b></td>
          <td class="th"><b>Status</b></td>
          <td class="th"><b>Description</b></td>
          <td class="th"><b>Creator</b></td>
        </tr>
        <#list requirements as item>
          <tr>
            <td>${item.name}</td>
            <td>${item.status}</td>
            <td>${item.description1}</td>
            <td>${item.creator}</td>
          </tr>
        </#list>
      </table>
    </div>
    <div>
      <h2>Answered Questions</h2>
      <#list qas as item>
        <div>${item}</div>
      </#list>
    </div>
  </div>
</body>

</html>