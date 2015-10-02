Dim objXL
Set objXL = CreateObject("Excel.Application")
With objXL
    .Workbooks.Open ("weekly_dashboard_macrobook.xlsb")
    .Application.Run "copyBase"
    .Application.Quit
End With
Set objXL = Nothing