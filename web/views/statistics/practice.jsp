<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.ArrayList,statistics.model.vo.Statistics"%>

<%
	
%>

<!DOCTYPE html>
<html>
<head>

{
  "type": "bar",
  "title": {
    "text": "Everybody likes animations!",
    "fontSize": 18
  },
  "subtitle": {
    "text": "adjust the properties to re-animate the chart"
  },
  "plot": {
    "animation": {
      "delay": "100",
      "effect": "4",
      "method": "5",
      "sequence": "1"
    }
  },
  "series": [
    {
      "values": [3,6,9]
    },
    {
      "values": [1,4,3]
    }
  ]
}

</head>
<body>
   
</body>
</html>


