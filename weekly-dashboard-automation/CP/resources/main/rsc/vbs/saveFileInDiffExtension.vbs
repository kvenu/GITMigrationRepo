Dim objXL
Set objXL = CreateObject("Excel.Application")
If WScript.Arguments.Count < 2 Then
    WScript.Echo "Missing parameters"
End If
With objXL
    .Workbooks.Open ("weekly_dashboard_macrobook.xlsb")
    .Application.Run WScript.Arguments(0), WScript.Arguments(1), WScript.Arguments(2)
    .Application.Quit
End With
Set objXL = Nothing