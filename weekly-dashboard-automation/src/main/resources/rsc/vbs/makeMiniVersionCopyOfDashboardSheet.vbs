Dim objXL
Set objXL = CreateObject("Excel.Application")
If WScript.Arguments.Count < 2 Then
    WScript.Echo "Missing parameters"
End If
With objXL
    .Workbooks.Open ("weekly_dashboard_macrobook.xlsb")
    .Application.Run "makeacopy", WScript.Arguments(0), WScript.Arguments(1)
    .Application.Quit
End With
Set objXL = Nothing
